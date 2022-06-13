package bedwars.bedwarsmm.Maps;

import bedwars.bedwarsmm.*;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

class MultiBlockChanger {

    private String worldName;

    private int maxChanges = 100;

    private boolean async = false;

    /* not sure does this thing have to exists at here, so i didnt code this */
    private boolean javaThread = false;

    private Runnable callback = null;

    private long tick = 5L;

    private final Queue<BlockChange> blockChanges = new ArrayDeque<>();

    public MultiBlockChanger(World world) {
        this.worldName = world.getName();
    }


    public MultiBlockChanger(String worldName) {
        this.worldName = worldName;
    }

    public String getWorldName() {
        return worldName;
    }

    public int getMaxChanges() {
        return maxChanges;
    }

    public boolean isAsync() {
        return async;
    }

    public boolean isJavaThread() {
        return javaThread;
    }

    public Runnable getCallback() {
        return callback;
    }

    public long getTick() {
        return tick;
    }

    public Queue<BlockChange> getBlockChanges(){
        return blockChanges;
    }

    public MultiBlockChanger setWorldName(String worldName) {
        this.worldName = worldName;
        return this;
    }

    public MultiBlockChanger setMaxChanges(int maxChanges) {
        this.maxChanges = maxChanges;
        return this;
    }

    public MultiBlockChanger async() {
        this.async = true;
        return this;
    }

    public MultiBlockChanger javaThread() {
        this.javaThread = true;
        return this;
    }

    public MultiBlockChanger tick(long tick) {
        this.tick = tick;
        return this;
    }

    public MultiBlockChanger callback(Runnable callback) {
        this.callback = callback;
        return this;
    }

    public MultiBlockChanger addBlockChanges(Block block, MaterialData materialData) {
        this.blockChanges.add(new BlockChange(BlockVector.toBlockVector(block), materialData));
        return this;
    }

    public MultiBlockChanger addBlockChanges(Location location, MaterialData materialData) {
        this.blockChanges.add(new BlockChange(BlockVector.toBlockVector(location), materialData));
        return this;
    }

    public MultiBlockChanger addBlockChanges(Block block, Material material, byte data) {
        this.blockChanges.add(new BlockChange(BlockVector.toBlockVector(block), material, data));
        return this;
    }

    public MultiBlockChanger addBlockChanges(Location location, Material material, byte data) {
        this.blockChanges.add(new BlockChange(BlockVector.toBlockVector(location), material, data));
        return this;
    }

    public MultiBlockChanger addBlockChanges(Material material, byte data, Location... locations) {
        for (final Location location : locations) {
            this.blockChanges.add(new BlockChange(BlockVector.toBlockVector(location), material, data));
        }
        return this;
    }

    public MultiBlockChanger addBlockChanges(Material material, byte data, Block... blocks) {
        for (final Block block : blocks) {
            this.blockChanges.add(new BlockChange(BlockVector.toBlockVector(block), material, data));
        }
        return this;
    }

    private BukkitTask bukkitTask = null;

    public void start(JavaPlugin plugin) {

        final World world = Bukkit.getWorld(worldName);

        final Runnable runnable = new Runnable() {
            private final Queue<SimpleChunk> chunksToRefresh = new ArrayDeque<SimpleChunk>() {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean add(SimpleChunk simpleChunk) {
                    if (this.contains(simpleChunk)) {
                        return false;
                    }
                    return super.add(simpleChunk);
                }

            };
            private final Queue<BlockChange> blockChanges = new ArrayDeque<>(MultiBlockChanger.this.blockChanges);

            private boolean blockSetDone = false;

            @Override
            public void run() {

                if (!blockChanges.isEmpty()) {
                    for (int i = 0; i < maxChanges; i++) {


                        if (blockChanges.isEmpty()) {
                            blockSetDone = true;

                            return;
                        }

                        final BlockChange blockChange = blockChanges.poll();
                        final BlockVector blockVector = blockChange.getBlockVector();
                        chunksToRefresh.add(new SimpleChunk(blockVector.getX() >> 4, blockVector.getZ() >> 4));

						/*
							i make the block set to nms because its pertty great for performance
							you can change it to bukkit api if you want
						*/
                        final net.minecraft.server.v1_8_R3.World w = ((CraftWorld) world).getHandle();
                        final net.minecraft.server.v1_8_R3.Chunk chunk = w.getChunkAt(blockVector.getX() >> 4, blockVector.getZ() >> 4);
                        final BlockPosition bp = new BlockPosition(blockVector.getX(), blockVector.getY(), blockVector.getZ());
                        final int combined = blockChange.getMaterialData().getItemTypeId() + (blockChange.getMaterialData().getData() << 12);
                        final IBlockData ibd = net.minecraft.server.v1_8_R3.Block.getByCombinedId(combined);
                        w.setTypeAndData(bp, ibd, 2);
                        chunk.a(bp, ibd);

                    }
                }

                if (!blockSetDone) {
                    return;
                }

                while (!chunksToRefresh.isEmpty()) {
                    final SimpleChunk simpleChunk = chunksToRefresh.poll();
                    world.refreshChunk(simpleChunk.getX(), simpleChunk.getZ());
                }

                if (callback != null) {
                    callback.run();
                }
                bukkitTask.cancel();
                return;
            }
        };

        if (async) {
            bukkitTask = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, 0L, tick);
            return;
        }
        bukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, runnable, 0L, tick);
        return;
    }

    private static class BlockVector {

        int x = 0, y = 0, z = 0;

        private BlockVector() {}

        public BlockVector(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        private static BlockVector toBlockVector(Block block) {
            return new BlockVector(block.getX(), block.getY(), block.getZ());
        }

        private static BlockVector toBlockVector(Location location) {
            return new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        }

    }

    private static class BlockChange {

        private BlockVector blockVector;
        private MaterialData materialData;

        private BlockChange() {}

        public BlockChange(BlockVector blockVector, MaterialData materialData) {
            this.blockVector = blockVector;
            this.materialData = materialData;
        }

        public BlockChange(BlockVector blockVector, Material material, byte data) {
            this.blockVector = blockVector;
            this.materialData = new MaterialData(material, data);
        }

        public BlockVector getBlockVector() {
            return blockVector;
        }

        public MaterialData getMaterialData() {
            return materialData;
        }

    }

    private static class SimpleChunk {

        private int x = 0, z = 0;

        private SimpleChunk() {}

        public SimpleChunk(int x, int z) {
            this.x = x;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getZ() {
            return z;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof SimpleChunk)) {
                return false;
            }
            return ((SimpleChunk) object).getX() == this.x && ((SimpleChunk) object).getZ() == this.z;
        }
    }

}

public class MapHandler implements Listener {
    private MapsLoad ml = new MapsLoad();
    static HashMap<UUID,Integer> currentAmountOfPlayersOnMap = new HashMap<>();
    static HashMap<UUID,Integer> currentAmountOfPlayersInTeam = new HashMap<>();
    static HashMap<UUID,List<UUID>> playersOnThatMap = new HashMap<>();
    static HashMap<UUID,List<UUID>> playersOnSpecificTeam = new HashMap<>();
    static HashMap<UUID,List<Integer>> mapSchedulers = new HashMap<>();
    static HashMap<UUID,List<Location>> placedBlocks = new HashMap<>();
    static HashMap<UUID,Boolean> runnablesStarted = new HashMap<>();

    List<Location> getBlockPlacedOnMap(UUID mapid){
        return placedBlocks.getOrDefault(mapid,new ArrayList<>());
    }

    private List<UUID> getPlayersInSpecificTeam (UUID teamid){
        return playersOnSpecificTeam.getOrDefault(teamid,new ArrayList<>());
    }
    private void setPlayersToSpecificTeam(UUID teamid,List<UUID> players){
        playersOnSpecificTeam.put(teamid,players);
    }
    static String maxPlayersAlreadyMessage = "There are already max players on that map!";
    public int getCurrentAmountOfPlayersOnMap(UUID mapid){
        return currentAmountOfPlayersOnMap.getOrDefault(mapid,0);
    }
    public int getMaxAmountOfPlayersPerMap(UUID mapid){
        List<UUID> teams = ml.getTeamsForThatMap(mapid);
        int x = 0;
        for(UUID teamid:teams){
            int teamsize = ml.getTeamSize(teamid);
            x = x+teamsize;
        }
        return x;
    }
    public int getCurrentAmountOfPlayersInTeam(UUID teamId){
        return currentAmountOfPlayersInTeam.getOrDefault(teamId,0);
    }
    public void setCurrentAmountOfPlayersInTeam(UUID teamId){
        int current = getCurrentAmountOfPlayersInTeam(teamId)+1;
       currentAmountOfPlayersInTeam.put(teamId,current);
    }
    public int getMaxAmountOfPlayersInTeam(UUID teamId){
        return ml.getTeamSize(teamId);
    }
    private List<UUID> getPlayersOnSpecificMap(UUID mapid){
        return playersOnThatMap.getOrDefault(mapid,new ArrayList<>());
    }
    private  void setPlayersOnSpecificMap(UUID mapid,List<UUID> players){
        playersOnThatMap.put(mapid,players);
    }
    private final NPC npc = new NPC();
    public void joinMap(Player p, UUID mapid){
        int current = getCurrentAmountOfPlayersOnMap(mapid);
        if(current==0){
            if(!runnablesStarted.getOrDefault(mapid,false)){
                startRunnables(mapid);
                runnablesStarted.put(mapid,true);
            }
        }

        int max = getMaxAmountOfPlayersPerMap(mapid);
        if(current>=max){
            p.sendMessage(maxPlayersAlreadyMessage);
            return;
        }
        int found = 0;
        List<UUID> teamList = ml.getTeamsForThatMap(mapid);
        UUID playerID = p.getUniqueId();
        for(UUID id:teamList){
            int currentTeam = getCurrentAmountOfPlayersInTeam(id);
            int maxTeam = getMaxAmountOfPlayersInTeam(id);
            if(currentTeam>=maxTeam)continue;
            Bukkit.broadcastMessage("TEEAMD ID "+id);
            found++;
            setCurrentAmountOfPlayersInTeam(id);
            PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
            pdc.setCurrentMap(mapid);
            pdc.setTeam(id);
            pdc.setCurrentMap(mapid);

            List<UUID> playersOnThatMap = getPlayersOnSpecificMap(mapid);
            if(!playersOnThatMap.contains(playerID)){
                playersOnThatMap.add(playerID);
                setPlayersOnSpecificMap(mapid,playersOnThatMap);
            }
            List<UUID> playersInTeam = getPlayersInSpecificTeam(id);
            if(!playersInTeam.contains(playerID)){
                playersInTeam.add(playerID);
                setPlayersToSpecificTeam(id,playersInTeam);
            }
            p.sendMessage(ml.getJoinMessage(mapid));
            teleportToLobby(p,mapid);
            npc.sendMapNpc(p,mapid);
            break;
        }
        if(found==0){
            p.sendMessage(maxPlayersAlreadyMessage);
            return;
        }

    }
    private void teleportToLobby(Player p,UUID mapid){
        Location loc = ml.getMapSpawn(mapid);
        p.teleport(loc);
        p.sendMessage(ml.getChangeTeamMessage(mapid));
    }
    //temp
    private static HashMap<UUID, ItemStack>gemItems = new HashMap<>();
    void dropItem(UUID gemid,UUID mapid){
        ItemStack item = gemItems.getOrDefault(gemid,new ItemStack(Material.WOOD_AXE));
        Location loc =  ml.getGemSpawnLocation(gemid);
        Bukkit.getWorld(ml.getWorldName(mapid)).dropItem(loc,item);
    }
    static HashMap<UUID,Integer> weaponsUpgrade = new HashMap<>();
    int getWeaponsUpgrade(UUID teamId){
        return weaponsUpgrade.getOrDefault(teamId,0);
    }
    void setWeaponsUpgrade(UUID teamId){
        weaponsUpgrade.put(teamId,(weaponsUpgrade.getOrDefault(teamId,0))+1);
    }
    static HashMap<UUID,Integer> armorUpgrade = new HashMap<>();
    int getArmorUpgrade(UUID teamId){
        return armorUpgrade.getOrDefault(teamId,0);
    }
    void setArmorUpgrade(UUID teamId){
        armorUpgrade.put(teamId,(armorUpgrade.getOrDefault(teamId,0))+1);
    }
    static HashMap<UUID,Integer> pickaxeUpgrade = new HashMap<>();
    int getPickaxeUpgrade(UUID teamId){
        return pickaxeUpgrade.getOrDefault(teamId,0);
    }
    void setPickaxeUpgrade(UUID teamId){
        pickaxeUpgrade.put(teamId,(pickaxeUpgrade.getOrDefault(teamId,0))+1);
    }
    static HashMap<UUID,Integer> resourcesUpgrade = new HashMap<>();
    int getResourcesUpgrade(UUID teamId){
        return resourcesUpgrade.getOrDefault(teamId,0);
    }
    void setResourcesUpgrade(UUID teamId){
        resourcesUpgrade.put(teamId,(resourcesUpgrade.getOrDefault(teamId,0))+1);
    }
    static HashMap<UUID,Integer> healUpgrade = new HashMap<>();
    int getHealUpgrade(UUID teamId){
        return healUpgrade.getOrDefault(teamId,0);
    }
    void setHealUpgrade(UUID teamId){
        healUpgrade.put(teamId,(healUpgrade.getOrDefault(teamId,0))+1);
    }
    static HashMap<UUID,Integer> trapUpgrade = new HashMap<>();
    int getTrapUpgrade(UUID teamId){
        return trapUpgrade.getOrDefault(teamId,0);
    }
    void setTrapUpgrade(UUID teamId){
        trapUpgrade.put(teamId,(trapUpgrade.getOrDefault(teamId,0))+1);
    }
    void startRunnables(UUID mapid){
        List<Integer> runnablesIds = new ArrayList<>();
        int lobbyDelay = ml.getLobbyDelayTicks(mapid);
        int arenaStartSoon = ml.getArenaStartSoonTicks(mapid);
        int howLongTillBedDestroyed = ml.getHowLongTillBedDestroyedTicks(mapid);
        int bedDestroyedSoonDelay = ml.getBedDestroyedSoonDelayTicks(mapid);
        int howLongTillFinalFight = ml.getHowLongTillFinalFightsTicks(mapid);
        int finalFightSoonDelay = ml.getFinalFightSoonDelayTicks(mapid);
        int howLongTillKillEveryone = ml.getHowLongTillKillEveryoneTicks(mapid);
        int finalKillEveryone  = ml.getFinalKillEveryoneDelayTicks(mapid);


        List<UUID> gemsMap = ml.getMapGems(mapid);

        for(UUID gemid: gemsMap){
            ItemStack item  =  ml.getGemMaterial(gemid);
            Location loc = ml.getGemSpawnLocation(gemid);
            int repeat = ml.getGemRepeat(gemid);

           int scb0 =  Bukkit.getScheduler().scheduleSyncRepeatingTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
                @Override
                public void run() {
                    Bukkit.getWorld(ml.getWorldName(mapid)).dropItem(loc,item);
                }
            },lobbyDelay,repeat);
          runnablesIds.add(scb0);
        }
        List<UUID> teamIds = ml.getTeamsForThatMap(mapid);
        for(UUID teamid : teamIds){
            List<UUID> gemsTeam = ml.getTeamGems(teamid);
            for(UUID id:gemsTeam){
                ItemStack item = ml.getGemMaterial(id);
                gemItems.put(id,item);
                int repeat = ml.getGemRepeat(id);
                int sch0 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
                    @Override
                    public void run() {
                        dropItem(id,mapid);
                    }
                },lobbyDelay,repeat);
                runnablesIds.add(sch0);
            }
        }


        int scd1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                 //Teleport PlayersOnMap(To Lobby)
            }
        },lobbyDelay);
        runnablesIds.add(scd1);
        int lobbyCountingTime = lobbyDelay-arenaStartSoon;
        int scd2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
              sendMessageMap(mapid,ml.getArenaStartSoonMessage(mapid));
                }
        },lobbyCountingTime);
        runnablesIds.add(scd2);
        for(int x = 10;x>=1;x--){
            int finalX = x;
           int scd3 =  Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
                @Override
                public void run() {
                sendMessageMap(mapid,ml.getArenaStartSoonCountingMessage(mapid)+" "+finalX);
                if(finalX==2){
                    spawnBeds(mapid);
                }
                if(finalX==1){
                    teleportToMap(mapid);
                }
                }
            },lobbyCountingTime-(20*x));
            runnablesIds.add(scd3);
        }


        int bedDestruction = lobbyDelay+howLongTillBedDestroyed;
        int bedDestructionSoon = lobbyDelay+howLongTillBedDestroyed-bedDestroyedSoonDelay;
        int scd4 = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                sendMessageMap(mapid,ml.getBedDestroySoonMessage(mapid));
            }
        }, bedDestructionSoon);
        runnablesIds.add(scd4);
        for(int x = 10;x>=1;x--){
            int finalX = x;
           int scd5 = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
                @Override
                public void run() {
                    sendMessageMap(mapid,ml.getBedDestroyCountingMessage(mapid)+" "+finalX);
                    if(finalX==1){
                        destroyBeds(mapid);
                    }
                }
            },bedDestruction-(20*x));
            runnablesIds.add(scd5);
        }



        int finalFight = lobbyDelay+howLongTillBedDestroyed+howLongTillFinalFight;
        int finalFightSoon = lobbyDelay+howLongTillBedDestroyed+howLongTillFinalFight - finalFightSoonDelay;
       int scd6 =  Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                sendMessageMap(mapid,ml.getFinalFightSoonMessage(mapid));
            }
        }, finalFightSoon);
        runnablesIds.add(scd6);
        for(int x = 10;x>=1;x--){
            int finalX = x;
           int scd7 =  Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
                @Override
                public void run() {
                    sendMessageMap(mapid,ml.getFinalFightCountningMessage(mapid)+" "+finalX);
                    if(finalX==1){
                        teleportToFinalFight(mapid);
                    }
                }
            },finalFight-(20*x));
            runnablesIds.add(scd7);
        }



        int killeveryone = lobbyDelay+howLongTillBedDestroyed+howLongTillFinalFight+howLongTillKillEveryone;
        int killeveryoneSoon = lobbyDelay+howLongTillBedDestroyed+howLongTillFinalFight+howLongTillKillEveryone - finalKillEveryone;
        int scd8 = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                sendMessageMap(mapid,ml.getKillEveryoneSoonMessage(mapid));
            }
        }, killeveryoneSoon);
        runnablesIds.add(scd8);
        for(int x = 10;x>=1;x--){
            int finalX = x;
          int scd9 =  Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
                @Override
                public void run() {
                    sendMessageMap(mapid,ml.getKillEveryoneCountningMessage(mapid)+" "+finalX);
                    if(finalX==1){
                        killEveryone(mapid);
                    }
                }
            },killeveryone-(20*x));
            runnablesIds.add(scd9);
        }
        mapSchedulers.put(mapid,runnablesIds);
    }






    final String shopTitle = "ShopTitle";
    final ItemStack shopBackGround = new ItemStack(Material.LADDER);
    final String upgradeTitle = "UpgradeTitle";
    final ItemStack upgradeBackGround = new ItemStack(Material.LADDER);
    final String weaponsTitle = "WeaponsTitle";
    final ItemStack weaponsBackGround = new ItemStack(Material.LADDER);
    final String pickaxesTitle = "PickaxesTitle";
    final ItemStack pickaxesBackGround = new ItemStack(Material.LADDER);
    final String armorsTitle = "ArmorsTitle";
    final ItemStack armorsBackGround = new ItemStack(Material.LADDER);
    final String resourcesTitle = "ResourcesTitle";
    final ItemStack resourcesBackGround = new ItemStack(Material.LADDER);
    final String healPoolTitle = "HealPoolTitle";
    final ItemStack healBackGround = new ItemStack(Material.LADDER);
    final String trapsTitle = "TrapsTitle";


    final ItemStack trapsBackGround = new ItemStack(Material.LADDER);
    final ItemStack weapons = new ItemStack(Material.IRON_SWORD);
    final ItemStack armors = new ItemStack(Material.IRON_CHESTPLATE);
    final ItemStack pickaxe = new ItemStack(Material.WOOD_PICKAXE);
    final ItemStack resources = new ItemStack(Material.FURNACE);
    final ItemStack heal = new ItemStack(Material.BEACON);
    final ItemStack trap = new ItemStack(Material.LEATHER);

    public Inventory createShopInventory(Player p){
        System.out.println("ShipTOPTLE: "+shopTitle);
        int size = 45;
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        UUID teamid =  pdc.getTeam();
        MapsLoad ml = new MapsLoad();


        ItemStack baseBlock = ml.getTeamBlock(teamid).clone();
        int baseBlockAmount = 16;
        baseBlock.setAmount(baseBlockAmount);

        ItemStack secondBlock = new ItemStack(Material.WOOD);
        int secondBlockAmount = 17;
        secondBlock.setAmount(secondBlockAmount);
        ItemStack thirdBlock = new ItemStack(Material.STONE);
        int thirdBlockAmount = 18;
        thirdBlock.setAmount(thirdBlockAmount);

        ItemStack firstSword = new ItemStack(Material.WOOD_SWORD);
        ItemStack secondSword = new ItemStack(Material.IRON_SWORD);
        ItemStack thirdSword = new ItemStack(Material.DIAMOND_SWORD);

        ItemStack ironSet = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack diamondSet = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack enderPearl = new ItemStack(Material.ENDER_PEARL);

        ItemStack shears  = new ItemStack(Material.SHEARS);
        ItemStack pickaxe  = new ItemStack(Material.WOOD_PICKAXE);
        ItemStack axe  = new ItemStack(Material.WOOD_AXE);

        ItemStack goldenAple  = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack fireball  = new ItemStack(Material.FIREBALL);
        ItemStack tnt  = new ItemStack(Material.TNT);

        ItemStack invisibility  = new ItemStack(Material.POTION);
        ItemStack doubleJump  = new ItemStack(Material.POTION);
        ItemStack fireResistance  = new ItemStack(Material.POTION);

        ItemStack ladder  = new ItemStack(Material.LADDER);

        Inventory inv = Bukkit.createInventory(null,size,shopTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = shopBackGround.clone();
            if(x==8){
                item = addTeamIDto8thItem(shopBackGround.clone(),teamid);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);



        }
        inv.setItem(10,baseBlock);
        inv.setItem(11,firstSword);
        inv.setItem(12,ironSet);
        inv.setItem(13,shears);
        inv.setItem(14,goldenAple);
        inv.setItem(15,invisibility);
        inv.setItem(16,ladder);
        inv.setItem(19,secondBlock);
        inv.setItem(20,secondSword);
        inv.setItem(21,diamondSet);
        inv.setItem(22,pickaxe);
        inv.setItem(23,fireball);
        inv.setItem(24,doubleJump);
        inv.setItem(28,thirdBlock);
        inv.setItem(29,thirdSword);
        inv.setItem(30,enderPearl);
        inv.setItem(31,axe);
        inv.setItem(32,tnt);
        inv.setItem(33,fireResistance);

        return inv;
    }
    public Inventory createUpgradeInventory(Player p){
        System.out.println("UpgradeTitle: "+upgradeTitle);
        int size = 27;
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        UUID teamid =  pdc.getTeam();

        Inventory inv = Bukkit.createInventory(null,size,upgradeTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = upgradeBackGround.clone();
            if(x==8){
                item = addTeamIDto8thItem(upgradeBackGround.clone(),teamid);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }

        inv.setItem(10,weapons);
        inv.setItem(11,armors);
        inv.setItem(12,pickaxe);
        inv.setItem(14,resources);
        inv.setItem(15,heal);
        inv.setItem(16,trap);
        return inv;
    }

    public Inventory createWeaponsInventory(UUID mapid,UUID teamid){
        int size = 27;
        Inventory inv = Bukkit.createInventory(null,size,weaponsTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = weaponsBackGround.clone();
            if(x==8){
                item = addTeamIDto8thItem(weaponsBackGround.clone(),teamid);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }

        UpgradesSorter us = new UpgradesSorter(mapid);
        List<UUID> mapUpgrades = us.getUpgradesWeapons();
        for(UUID upid:mapUpgrades){
            Upgrades upgrades = new Upgrades(upid);
            String activated = upgrades.getActivatedMessage();
            String unactivated = upgrades.getUnactivatedMessage();
            ItemStack item  = upgrades.getUpgradeItem();
            int slot = upgrades.getUpgradeSlot();
            IsUpgradeDone isUpgradeDone = new IsUpgradeDone(mapid,teamid,upid);
            if(isUpgradeDone.getUpgradeDone()){
                addToLore(item,activated);
            }else{
                addToLore(item,unactivated);
            }
            ItemStack finalItem  = addNBTTAG(item,"upid",upid.toString());
            inv.setItem(slot,finalItem);
        }


        return inv;
    }
    public ItemStack addNBTTAG(ItemStack item, String path, String value){
        net.minecraft.server.v1_8_R3.ItemStack nms =  CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nms.hasTag()?nms.getTag():new NBTTagCompound();
        tag.setString(path,value);
        return CraftItemStack.asBukkitCopy(nms);
    }
    public String getNBTTAG(ItemStack item, String path){
        net.minecraft.server.v1_8_R3.ItemStack nms =  CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nms.hasTag()?nms.getTag():new NBTTagCompound();
        return tag.getString(path);
    }
    public Inventory createPickaxeInventory(UUID mapid,UUID teamid){
        int size = 27;
        Inventory inv = Bukkit.createInventory(null,size,pickaxesTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = pickaxesBackGround.clone();
            if(x==8){
                item = addTeamIDto8thItem(pickaxesBackGround.clone(),teamid);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }
        UpgradesSorter us = new UpgradesSorter(mapid);
        List<UUID> mapUpgrades = us.getUpgradesTools();
        for(UUID upid:mapUpgrades){
            Upgrades upgrades = new Upgrades(upid);
            String activated = upgrades.getActivatedMessage();
            String unactivated = upgrades.getUnactivatedMessage();
            ItemStack item  = upgrades.getUpgradeItem();
            int slot = upgrades.getUpgradeSlot();
            IsUpgradeDone isUpgradeDone = new IsUpgradeDone(mapid,teamid,upid);
            if(isUpgradeDone.getUpgradeDone()){
                addToLore(item,activated);
            }else{
                addToLore(item,unactivated);
            }
            ItemStack finalItem  = addNBTTAG(item,"upid",upid.toString());
            inv.setItem(slot,finalItem);
        }


        return inv;
    }
    public Inventory createArmorInventory(UUID mapid,UUID teamid){
        int size = 27;
        Inventory inv = Bukkit.createInventory(null,size,armorsTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = armorsBackGround.clone();
            if(x==8){
                item = addTeamIDto8thItem(armorsBackGround.clone(),teamid);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }

        UpgradesSorter us = new UpgradesSorter(mapid);
        List<UUID> mapUpgrades = us.getUpgradesArmors();
        for(UUID upid:mapUpgrades){
            Upgrades upgrades = new Upgrades(upid);
            String activated = upgrades.getActivatedMessage();
            String unactivated = upgrades.getUnactivatedMessage();
            ItemStack item  = upgrades.getUpgradeItem();
            int slot = upgrades.getUpgradeSlot();
            IsUpgradeDone isUpgradeDone = new IsUpgradeDone(mapid,teamid,upid);
            if(isUpgradeDone.getUpgradeDone()){
                addToLore(item,activated);
            }else{
                addToLore(item,unactivated);
            }
            ItemStack finalItem  = addNBTTAG(item,"upid",upid.toString());
            inv.setItem(slot,finalItem);
        }

        return inv;
    }

    public Inventory createResourceInventory(UUID mapid,UUID teamid){
        int size = 27;

        Inventory inv = Bukkit.createInventory(null,size,resourcesTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = resourcesBackGround.clone();
            if(x==8){
                item = addTeamIDto8thItem(resourcesBackGround.clone(),teamid);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }
        UpgradesSorter us = new UpgradesSorter(mapid);
        List<UUID> mapUpgrades = us.getUpgradesResources();
        for(UUID upid:mapUpgrades){
            Upgrades upgrades = new Upgrades(upid);
            String activated = upgrades.getActivatedMessage();
            String unactivated = upgrades.getUnactivatedMessage();
            ItemStack item  = upgrades.getUpgradeItem();
            int slot = upgrades.getUpgradeSlot();
            IsUpgradeDone isUpgradeDone = new IsUpgradeDone(mapid,teamid,upid);
            if(isUpgradeDone.getUpgradeDone()){
                addToLore(item,activated);
            }else{
                addToLore(item,unactivated);
            }
            ItemStack finalItem  = addNBTTAG(item,"upid",upid.toString());
            inv.setItem(slot,finalItem);
        }

        return inv;
    }
    public Inventory createHealPoolInventory(UUID mapid,UUID teamid){
        int size = 27;

        Inventory inv = Bukkit.createInventory(null,size,healPoolTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = healBackGround.clone();
            if(x==8){
                item = addTeamIDto8thItem(healBackGround.clone(),teamid);
            }
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }
        UpgradesSorter us = new UpgradesSorter(mapid);
        List<UUID> mapUpgrades = us.getUpgradesBuffs();
        for(UUID upid:mapUpgrades){
            Upgrades upgrades = new Upgrades(upid);
            String activated = upgrades.getActivatedMessage();
            String unactivated = upgrades.getUnactivatedMessage();
            ItemStack item  = upgrades.getUpgradeItem();
            int slot = upgrades.getUpgradeSlot();
            IsUpgradeDone isUpgradeDone = new IsUpgradeDone(mapid,teamid,upid);
            if(isUpgradeDone.getUpgradeDone()){
                addToLore(item,activated);
            }else{
                addToLore(item,unactivated);
            }
            ItemStack finalItem  = addNBTTAG(item,"upid",upid.toString());
            inv.setItem(slot,finalItem);
        }

        return inv;
    }
    public Inventory createTrapsInventory(Player p,boolean[]trapsActive){
        int size = 27;
        String title = "ShopTitle";

        ItemStack item1 = new ItemStack(Material.COAL);
        ItemStack item2 = new ItemStack(Material.COAL);
        ItemStack item3 = new ItemStack(Material.COAL);
        ItemStack item4 = new ItemStack(Material.COAL);
        ItemStack item5 = new ItemStack(Material.COAL);
        ItemStack backGroundItem = new ItemStack(Material.GLASS);
        String active = "trap is active";
        String activate = "click to ACTIVATE";
        Inventory inv = Bukkit.createInventory(null,size,trapsTitle);
        for(int x = 0;x<size;x++){
            ItemStack item = trapsBackGround.clone();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        UUID teamid =  pdc.getTeam();

        if(trapsActive[0]){item1 = addToLore(item1,active);}else {item1 = addToLore(item1,activate);}
        if(trapsActive[1]){item2 = addToLore(item2,active);}else {item2 = addToLore(item2,activate);}
        if(trapsActive[2]){item3 = addToLore(item3,active);}else {item3 = addToLore(item3,activate);}
        if(trapsActive[3]){item4 = addToLore(item4,active);}else {item4 = addToLore(item4,activate);}
        if(trapsActive[4]){item5 = addToLore(item5,active);}else {item5 = addToLore(item5,activate);}

        inv.setItem(11,item1);
        inv.setItem(12,item2);
        inv.setItem(13,item3);
        inv.setItem(14,item4);
        inv.setItem(15,item5);

        return inv;
    }

    private UUID getTeamIdBY8thSlotItem(ItemStack item){
        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nms.hasTag()?nms.getTag():new NBTTagCompound();
        if(tag.hasKey("lvl")){
         UUID  id = UUID.fromString(tag.getString("lvl"));
         return id;
        }else{
            return null;
        }
    }
    private ItemStack addTeamIDto8thItem(ItemStack item, UUID teamId){
        net.minecraft.server.v1_8_R3.ItemStack nms =  CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nms.hasTag()? nms.getTag() : new NBTTagCompound();
        tag.setString("lvl",teamId.toString());
        nms.setTag(tag);
        ItemStack bukkit = CraftItemStack.asBukkitCopy(nms);
        return bukkit;
    }
    static  HashMap<UUID,boolean[]> teamTraps = new HashMap<>();
    private boolean[] getTeamTraps(UUID teamID){
        int baseTrapsSize = 5;
        boolean[] baseTraps = new boolean[baseTrapsSize];
        for(int x = 0;x<baseTrapsSize;x++){
            baseTraps[x] = false;
        }
        return teamTraps.getOrDefault(teamID,baseTraps);
    }
    @EventHandler
    public  void onItemClick(InventoryClickEvent e){
        String title  = e.getView().getTitle();
        if(
                !title.equals(shopTitle)&&
                !title.equals(upgradeTitle)&&
                !title.equals(weaponsTitle)&&
                !title.equals(armorsTitle)&&
                !title.equals(resourcesTitle)&&
                !title.equals(healPoolTitle)&&
                !title.equals(trapsTitle))
            return;

        if(!e.getClickedInventory().getType().equals(InventoryType.CHEST))return;
        e.setCancelled(true);
        if (e.getCurrentItem() == null)
            return;
        if (e.getCurrentItem().getItemMeta() == null)
            return;
        ItemStack clickedItem = e.getCurrentItem();
        if(
                        clickedItem.isSimilar(shopBackGround)||
                        clickedItem.isSimilar(upgradeBackGround)||
                        clickedItem.isSimilar(weaponsBackGround)||
                        clickedItem.isSimilar(armorsBackGround)||
                        clickedItem.isSimilar(resourcesBackGround)||
                        clickedItem.isSimilar(healBackGround)||
                        clickedItem.isSimilar(trapsBackGround))return;

        Player p = (Player) e.getWhoClicked();
        String name = p.getName();

        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        UUID mapid = pdc.getCurrentMap();
//
//
//
//
//
//
//
//
// THE WAY OF GETTING MAPID To be changed
//
//
//
//
//
//


        ItemStack eightItem = e.getClickedInventory().getItem(8);
        if (eightItem==null){
            System.out.println(ChatColor.DARK_RED+"Missing item on 8'th slot of inv: "+title);
            Bukkit.broadcastMessage(ChatColor.DARK_RED+"Missing item on 8'th slot of inv: "+title);
            return;
        }
        UUID teamID = getTeamIdBY8thSlotItem(eightItem);
        if (teamID==null){
            System.out.println(ChatColor.DARK_RED+"Team id is null in inv: "+title);
            return;
        }
        if (mapid==null){
            System.out.println(ChatColor.DARK_RED+"Map id is null in inv: "+title);
            return;
        }

        if(title.equals(shopTitle)){


            return;}
        if(title.equals(upgradeTitle)){
            if(clickedItem.isSimilar(weapons)){
                p.openInventory(createWeaponsInventory(mapid,teamID));
            }
            if(clickedItem.isSimilar(armors)){
                p.openInventory(createArmorInventory(mapid,teamID));
            }
            if(clickedItem.isSimilar(resources)){
                p.openInventory(createResourceInventory(mapid,teamID));
            }
            if(clickedItem.isSimilar(pickaxe)){
                p.openInventory(createPickaxeInventory(mapid,teamID));
            }
            if(clickedItem.isSimilar(heal)){
                p.openInventory(createHealPoolInventory(mapid,teamID));
            }
            if(clickedItem.isSimilar(trap)){
                p.openInventory(createTrapsInventory(p,getTeamTraps(teamID)));
            }
            return;}

        if(title.equals(weaponsTitle)){
         String wannaBeUPID = getNBTTAG(clickedItem,"upid");
         if(wannaBeUPID==null)return;
         UUID id  =UUID.fromString(wannaBeUPID);
         IsUpgradeDone iud = new IsUpgradeDone(mapid,teamID,id);
         if(iud.getUpgradeDone()){
             p.sendMessage(alreadyDoneMessage);
             p.closeInventory();
         }else{
             Upgrades upgrades = new Upgrades(mapid,teamID);
         }
         ;
            return;}
        if(title.equals(armorsTitle)){return;}
        if(title.equals(resourcesTitle)){return;}
        if(title.equals(healPoolTitle)){return;}
        if(title.equals(trapsTitle)){return;}
    }




 /*   public void giveUpgrade(Player p, UUID teamID,UUID upgradeID){
        UpgradeType type =  getUpgradeType(upgradeID);
        if(type==null)return;

        if(type.equals(UpgradeType.ARMOR_ENCHANT)){}
        if(type.equals(UpgradeType.SWORD_ENCHANT)){}
        if(type.equals(UpgradeType.PICKAXE_ENCHANT)){}
        if(type.equals(UpgradeType.AXE_ENCHANT)){}
        if(type.equals(UpgradeType.TRAP)){}
        if(type.equals(UpgradeType.POTION)){}
        if(type.equals(UpgradeType.RESOURCES)){}

    }*/

    private ItemStack addToLore(ItemStack item, String messageYouWantToAdd){
        if(item.hasItemMeta()){
            if(item.getItemMeta().hasLore()){
                List<String> lore = item.getItemMeta().getLore();
                lore.add(messageYouWantToAdd);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(lore);
                item.setItemMeta(meta);
                return item;
            }else{
                List<String> lore = new ArrayList<>();
                lore.add(messageYouWantToAdd);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(lore);
                item.setItemMeta(meta);
                return item;
            }

        }else{
            List<String> lore = new ArrayList<>();
            lore.add(messageYouWantToAdd);
            ItemMeta meta = item.getItemMeta();
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }



    }

    void sendMessageMap(UUID mapid, String message){
        List<UUID> playersOnThatMapID = getPlayersOnSpecificMap(mapid);
        for(UUID id:playersOnThatMapID){
            Player g = Bukkit.getPlayer(id);
            if(g.isOnline()){
                g.sendMessage(message);
            }
        }
    }
    void sendMessageTeam(UUID teamid, String message){
        List<UUID> playersOnThatTeamID = getPlayersInSpecificTeam(teamid);
        for(UUID id:playersOnThatTeamID){
            Player g = Bukkit.getPlayer(id);
            if(g.isOnline()){
                g.sendMessage(message);
            }
        }
    }
    void teleportToMap(UUID mapid){
        List<UUID> teamIDList = ml.getTeamsForThatMap(mapid);
        for(UUID id:teamIDList){
            List<UUID> player = getPlayersInSpecificTeam(id);
            Location loc = ml.getTeamSpawnLoc(id);
            for(UUID playerid:player){
                Player g = Bukkit.getPlayer(playerid);
                if(g.isOnline()){
                    g.teleport(loc);
                    g.sendMessage(ml.getArenaStartedMessage(mapid));
                }
            }
        }
   /*     List<UUID> playersOnThatMapID = getPlayersOnSpecificMap(mapid);
        for(UUID id:playersOnThatMapID){
            Player g = Bukkit.getPlayer(id);
            if(g.isOnline()){
                PlayerDataClass pdc = new PlayerDataClass(g.getLocation(),g);
                UUID teamid = pdc.getTeam();
                Location teamLoc = ml.getTeamSpawnLoc(teamid);
                g.teleport(teamLoc);

            }
        }*/
    }
    void destroyBeds(UUID mapid){
        List<UUID> playersOnThatMapID = getPlayersOnSpecificMap(mapid);
        for(UUID id:playersOnThatMapID){
            Player g = Bukkit.getPlayer(id);
            if(g.isOnline()){
                g.sendMessage(ml.getBedDestroyedMessage(mapid));
            }
        }
        List<UUID> teamsOnThatMap = ml.getTeamsForThatMap(mapid);
        for (UUID id:teamsOnThatMap){
            Location loc = ml.getTeamBedLoc(id);
            Block block = Bukkit.getWorld(ml.getWorldName(mapid)).getBlockAt(loc);
            if(block.getType().equals(Material.BED)){
                block.getState().setType(Material.AIR);
                block.getState().update(true);
            }
        }
    }
    void spawnBeds(UUID mapid){
        List<UUID> teams = ml.getTeamsForThatMap(mapid);

        for(UUID id:teams){
            Location loc = ml.getTeamBedLoc(id);
            Location loc2 = loc.clone().add(0,0,-1);
            Block block = Bukkit.getWorld(ml.getWorldName(mapid)).getBlockAt(loc);


            BlockState bedFoot = block.getRelative(block.getFace(block)).getState();
            BlockState bedHead = bedFoot.getBlock().getRelative(BlockFace.SOUTH).getState();
            bedFoot.setType(Material.BED_BLOCK);
            bedHead.setType(Material.BED_BLOCK);
            bedFoot.setRawData((byte) 0x0);
            bedHead.setRawData((byte) 0x8);
            bedFoot.update(true, false);
            bedHead.update(true, true);





        }
    }
    void teleportToFinalFight(UUID mapid){
        List<UUID> teamsOnThatMap = ml.getTeamsForThatMap(mapid);
        for (UUID id:teamsOnThatMap){
            Location loc = ml.getTeamFinalLoc(id);
            List<UUID> teamPlayer = getPlayersInSpecificTeam(id);
            for(UUID idg:teamPlayer){
                Player g = Bukkit.getPlayer(idg);
                if(g.isOnline()){
                    g.teleport(loc);
                    g.sendMessage(ml.getFinalFightStartedMessage(mapid));
                }
            }

        }
    }
    void killEveryone(UUID mapid){
        List<UUID> listPlayers = getPlayersOnSpecificMap(mapid);
        ServerDataClass sdc = new ServerDataClass();
        Location loc = sdc.getSpawnlocation();
        for(UUID id:listPlayers){
            Player g = Bukkit.getPlayer(id);
            g.teleport(loc);
            g.sendMessage(ml.getKillEveryoneStartedMessage(mapid));
        }
        cancelTasks(mapid);
        cleanUpBlocks(mapid);
    }
    void cancelTasks(UUID mapid){
        List<Integer> tasks = mapSchedulers.getOrDefault(mapid,new ArrayList<>());
        for(int task: tasks){
            Bukkit.getScheduler().cancelTask(task);
            System.out.println("Task Canceled Succesfully!");
        }
    }
    void cleanUpBlocks (UUID mapid){
        MultiBlockChanger mbc = new MultiBlockChanger(ml.getWorldName(mapid));
        for(Location loc: getBlockPlacedOnMap(mapid)){
            MaterialData data = new MaterialData(Material.AIR);
            mbc.addBlockChanges(loc,data);
        }
        mbc.start(Bedwars.getPlugin(Bedwars.class));
    }
}
