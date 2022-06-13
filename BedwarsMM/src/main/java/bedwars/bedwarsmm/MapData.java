/*
package bedwars.bedwarsmm;

import com.sun.istack.internal.NotNull;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


import java.util.*;

abstract class MapData<T extends MapData <T>> {
    public MapData(){

    }
    abstract void zBz();
    public T commonMethodForAllPages () {
        startMap();
        return (T) this;
    }
    abstract void startMap();
    abstract Location getSpawnMain();
    String[] getAllTeamNames(){
        return new String[]{"team1","team2","team3","team4","team5","team6","team7","team8","team9","team10","team11","team12"};
    }
    List<UUID> getTeam(String teamName){
        switch (teamName){
            case"team1":return getTeam1Players();
            case"team2":return getTeam2Players();
            case"team3":return getTeam3Players();
            case"team4":return getTeam4Players();
            case"team5":return getTeam5Players();
            case"team6":return getTeam6Players();
            case"team7":return getTeam7Players();
            case"team8":return getTeam8Players();
            case"team9":return getTeam9Players();
            case"team10":return getTeam10Players();
            case"team11":return getTeam11Players();
            case"team12":return getTeam12Players();
            default:return null;
        }
    }
    Location getTeamSpawnByTeamName(String teamname){
        switch (teamname){
            case"team1":return getSpawnTeam1();
            case"team2":return getSpawnTeam2();
            case"team3":return getSpawnTeam3();
            case"team4":return getSpawnTeam4();
            case"team5":return getSpawnTeam5();
            case"team6":return getSpawnTeam6();
            case"team7":return getSpawnTeam7();
            case"team8":return getSpawnTeam8();
            case"team9":return getSpawnTeam9();
            case"team10":return  getSpawnTeam10();
            case"team11":return  getSpawnTeam11();
            case"team12":return  getSpawnTeam12();
            default:return null;
        }
    }


    Location getSpawnTeam1(){return null;}
    Location getSpawnTeam2(){return null;}
    Location getSpawnTeam3(){return null;}
    Location getSpawnTeam4(){return null;}
    Location getSpawnTeam5(){return null;}
    Location getSpawnTeam6(){return null;}
    Location getSpawnTeam7(){return null;}
    Location getSpawnTeam8(){return null;}
    Location getSpawnTeam9(){return null;}
    Location getSpawnTeam10(){return null;}
    Location getSpawnTeam11(){return null;}
    Location getSpawnTeam12(){return null;}

     List<UUID> getAllPlayers(){return null;}
     List<UUID> getTeam1Players(){return null;}
     Location getTeam1UpgradeManLocation(){return null;}
     Location getTeam1ShopManLocation(){return null;}
     Location getTeam1ItemSpawnLocation(){return null;}
     List<UUID> getTeam2Players(){return null;}
    Location getTeam2UpgradeManLocation(){return null;}
    Location getTeam2ShopManLocation(){return null;}
    Location getTeam2ItemSpawnLocation(){return null;}
     List<UUID> getTeam3Players(){return null;}
    Location getTeam3UpgradeManLocation(){return null;}
    Location getTeam3ShopManLocation(){return null;}
    Location getTeam3ItemSpawnLocation(){return null;}
     List<UUID> getTeam4Players(){return null;}
    Location getTeam4UpgradeManLocation(){return null;}
    Location getTeam4ShopManLocation(){return null;}
    Location getTeam4ItemSpawnLocation(){return null;}
     List<UUID> getTeam5Players(){return null;}
    Location getTeam5UpgradeManLocation(){return null;}
    Location getTeam5ShopManLocation(){return null;}
    Location getTeam5ItemSpawnLocation(){return null;}
     List<UUID> getTeam6Players(){return null;}
    Location getTeam6UpgradeManLocation(){return null;}
    Location getTeam6ShopManLocation(){return null;}
    Location getTeam6ItemSpawnLocation(){return null;}
     List<UUID> getTeam7Players(){return null;}
    Location getTeam7UpgradeManLocation(){return null;}
    Location getTeam7ShopManLocation(){return null;}
    Location getTeam7ItemSpawnLocation(){return null;}
     List<UUID> getTeam8Players(){return null;}
    Location getTeam8UpgradeManLocation(){return null;}
    Location getTeam8ShopManLocation(){return null;}
    Location getTeam8ItemSpawnLocation(){return null;}
     List<UUID> getTeam9Players(){return null;}
    Location getTeam9UpgradeManLocation(){return null;}
    Location getTeam9ShopManLocation(){return null;}
    Location getTeam9ItemSpawnLocation(){return null;}
     List<UUID> getTeam10Players(){return null;}
    Location getTeam10UpgradeManLocation(){return null;}
    Location getTeam10ShopManLocation(){return null;}
    Location getTeam10ItemSpawnLocation(){return null;}
     List<UUID> getTeam11Players(){return null;}
    Location getTeam11UpgradeManLocation(){return null;}
    Location getTeam11ShopManLocation(){return null;}
    Location getTeam11ItemSpawnLocation(){return null;}
     List<UUID> getTeam12Players(){return null;}
    Location getTeam12UpgradeManLocation(){return null;}
    Location getTeam12ShopManLocation(){return null;}
    Location getTeam12ItemSpawnLocation(){return null;}

    abstract List<Location> getItemSpawnsList();
    abstract List<Location> getUpgradeMansList();
    abstract List<Location> getShopMansList();
    abstract List<Location> getLapisSpawnLocations();
    abstract List<Location> getEmeraldsSpawnLocations();
    abstract List<Location> getDiamondsSpawnLocations();

    abstract int timeBreakingBed();
    abstract int timeFinalFight();
    abstract int timeSpawnGold();
    abstract int amountGold();
    abstract int timeSpawnSilver();
    abstract int amountSilver();
    abstract int teamSize();
    abstract int getAmountOfPlayers();
    abstract void addPlayer(Player p);
    abstract void removePlayer(Player p);
    abstract List<UUID> playerTeamList(Player p);


}

class Map11 extends MapData<Map11> {
    private static int schedulerIDsendLobbyMessage;
    private static List<Integer> schedulerIDlobbyCounting = new ArrayList<>();
    private static int schedulerIDteleportOnMap;
    private static int schedulerIDdestroyPlayerBeds;
    private static int schedulerIDteleportToArenaAttention;
    private static List<Integer> schedulerIDteleportToArenaCounting = new ArrayList<>();
    private static int schedulerIDteleportToArenaFinal;
    private static int schedulerIDkillPlayersFinal;
    private static int goldScheduler;
    private static int silverScheduler;
    private static int lapisScheduler;
    private static int emeraldScheduler;
    private static int diamondScheduler;
    String getMapName(){
        return "map11";
    }
    public Map11(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map11.yml");
    Config conf = new Config(configManager);
    private final IdontKnowYet idk = new IdontKnowYet();
    private final UUID mapID = idk.getMapID(getMapName());

    Colors c = new Colors();

    static HashMap<UUID, Location> blocksBeforeLoca = new HashMap<>();
    static HashMap<UUID, Material> blocksBeforeMate = new HashMap<>();
    static HashMap<UUID, Byte> blocksBeforeByte = new HashMap<>();
    void clearBlocksAfterZBZ(){
        blocksBeforeByte.clear();
        blocksBeforeMate.clear();
        blocksBeforeMate.clear();
    }
    @Override
    void zBz(){
        stopSchedulers();
        clearBlocksAfterZBZ();
        idk.addToCanBePlayed(mapID);
    }

    void clearMap(){


    }



    void startMap(){
        sendLobbyMesages();
        lobbyCounting();
        teleportOnMap();
        destroyPlayersBeds();
        teleportToArenaAttention();
        teleportToArenaCounting();
        teleportToArenaFinal();
        killPlayersFinal();
        spawnGold();
        spawnSilver();
        spawnLapis();
        spawnDiamonds();
        spawnEmerald();
    }
    private void cancelTask(int i){
        Bukkit.getScheduler().cancelTask(i);
    }
    void stopSchedulers(){
        cancelTask(schedulerIDsendLobbyMessage);
        for(int i:schedulerIDlobbyCounting){
            cancelTask(i);
        }
        cancelTask(schedulerIDteleportOnMap);
        cancelTask(schedulerIDdestroyPlayerBeds);
        cancelTask(schedulerIDteleportToArenaAttention);
        for(int i:schedulerIDteleportToArenaCounting){
            cancelTask(i);
        }
        cancelTask(schedulerIDteleportToArenaFinal);
        cancelTask(schedulerIDkillPlayersFinal);
        cancelTask(goldScheduler);
        cancelTask(silverScheduler);
        cancelTask(lapisScheduler);
        cancelTask(emeraldScheduler);
        cancelTask(diamondScheduler);
    }
    ///////////////////////////////////////////
    //
    //      Schedulers Data
    //
    //////////////


    String warWillStartInXSecondsMessage = conf.getString("schedulers.messages..warWillStartInXSecondsMessage");
    int lobbyMessageDelay = 20*conf.getInt("schedulers.delays..lobbyMessageDelay");
    String bedDestroyedMessage = conf.getString("schedulers.messages..bedDestroyedMessage");
    String firstMessageFinalFight = conf.getString("schedulers.messages..firstMessageFinalFight");
    int firstmessageBeforeFinalFightTime = 20* conf.getInt("schedulers.delays..firstmessageBeforeFinalFightTime");
    int timeInLobbyAwaiting = 20*conf.getInt("schedulers.delays..timeInLobbyAwaiting");
    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnDiamonds = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int timeSpawnLapis = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int timeSpawnEmerald = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountDiamonds = conf.getInt("mapData.gems..amountOfGems");
    int amountLapis = conf.getInt("mapData.gems..amountOfGems");
    int amountEmerald = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");




    int sendLobbyMesage = timeInLobbyAwaiting - lobbyMessageDelay; //5 - 2 = 3;
    int lobbycounting = timeInLobbyAwaiting; //-10*20, 9 , 8 , 7 ETC.
    int teleportonMap = timeInLobbyAwaiting;
    int destroyPlayerBeds = timeInLobbyAwaiting+timeBreakingBed;
    int teleporToArenaAttention = timeInLobbyAwaiting+timeFinalFight-firstmessageBeforeFinalFightTime;
    int teleportToArenaCounting = timeInLobbyAwaiting+timeFinalFight;//-10*20, 9 , 8 , 7 ETC.
    int teleportToArenaFinal =timeInLobbyAwaiting+timeFinalFight ;
    int timeForPlayersToKillEachOther = conf.getInt("schedulers.delays..timeForPlayersToKillEachOther");
    int killPlayersFinal = timeInLobbyAwaiting+timeFinalFight+timeForPlayersToKillEachOther;

    int startSpawningGold = timeInLobbyAwaiting;
    int startSpawningSilver = timeInLobbyAwaiting;
    int startSpawningDiamonds = timeInLobbyAwaiting;
    int startSpawningEmerald = timeInLobbyAwaiting;
    int startSpawningLapis = timeInLobbyAwaiting;

    void spawnGold(){
        goldScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(Location loc:getItemSpawnsList()){
                    Bukkit.getWorld("world").dropItem(loc,new ItemStack(Material.GOLD_INGOT));
                }
            }
        },startSpawningGold,timeSpawnGold);
    }
    void spawnSilver(){
        silverScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(Location loc:getItemSpawnsList()){
                    Bukkit.getWorld("world").dropItem(loc,new ItemStack(Material.IRON_INGOT));
                }
            }
        },startSpawningSilver,timeSpawnSilver);
    }
    void spawnDiamonds(){
        diamondScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(Location loc:getDiamondsSpawnLocations()){
                    Bukkit.getWorld("world").dropItem(loc,new ItemStack(Material.DIAMOND));
                }
            }
        },startSpawningDiamonds,timeSpawnDiamonds);
    }
    void spawnEmerald(){
        emeraldScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(Location loc:getEmeraldsSpawnLocations()){
                    Bukkit.getWorld("world").dropItem(loc,new ItemStack(Material.EMERALD));
                }
            }
        },startSpawningEmerald,timeSpawnEmerald);
    }
    void spawnLapis(){
        lapisScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(Location loc:getLapisSpawnLocations()){
                    Bukkit.getWorld("world").dropItem(loc,new ItemStack(Material.LAPIS_ORE));
                }
            }
        },startSpawningLapis,timeSpawnLapis);
    }

    void sendLobbyMesages(){

        schedulerIDsendLobbyMessage = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(UUID id:playersList){
                    Player p = Bukkit.getPlayer(id);
                    if(p.isOnline()){
                        p.sendMessage("JEDEN");
                        p.sendMessage(warWillStartInXSecondsMessage);
                    }
                }
            }
        },sendLobbyMesage);
    }
    void lobbyCounting(){
        for(int x = 10;x>=1;x--){
            int finalX = x;

            int schedID = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
                @Override
                public void run() {
                    for(UUID id:getAllPlayers()){
                        Player g = Bukkit.getPlayer(id);
                        if(g.isOnline()){
                            g.sendMessage("DWA");
                            g.sendMessage(c.g+"War will start in "+c.r+ finalX +c.g+" seconds");
                        }
                    }
                }
            },lobbycounting-(20*x));
            schedulerIDlobbyCounting.add(schedID);
        }

    }
    void teleportOnMap(){
        schedulerIDteleportOnMap = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                String[] allTeamNames = getAllTeamNames();
                for(String s: allTeamNames){
                    List<UUID> team =  getTeam(s);
                    if(team!=null&&!team.isEmpty()){
                        for(UUID id:team){
                            Player g = Bukkit.getPlayer(id);
                            if(g.isOnline()){
                                g.sendMessage("TRZY");
                                g.teleport(getTeamSpawnByTeamName(s));
                            }
                        }
                    }
                }


                idk.addToCurrentlyPlayed(mapID);
            }
        },teleportonMap);
    }
    void destroyPlayersBeds(){
        schedulerIDdestroyPlayerBeds = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(Location l:getplayerBedsLocations()){
                    if(Bukkit.getWorld("world").getBlockAt(l).getType().equals(Material.BED)){
                        Bukkit.getWorld("world").getBlockAt(l).setType(Material.AIR,true);
                    }
                }
                for(UUID id:playersList){
                    Player p = Bukkit.getPlayer(id);
                    if(p.isOnline()){
                        p.sendMessage("Trzy i pol");
                        p.sendMessage(bedDestroyedMessage);
                    }
                }

            }
        },destroyPlayerBeds);
    }
    void teleportToArenaAttention(){
        schedulerIDteleportToArenaAttention = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(UUID id:getAllPlayers()){
                    Player g = Bukkit.getPlayer(id);
                    if(g.isOnline()){
                        g.sendMessage("CZTERY");
                        g.sendMessage(firstMessageFinalFight);
                    }
                }
            }
        },teleporToArenaAttention);
    }
    void teleportToArenaCounting(){

                for(int x = 10;x>=1;x--){
                    int finalX = x;

                   int schedID =  Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {

                        @Override
                        public void run() {

                            for(UUID id:getAllPlayers()){
                                Player g = Bukkit.getPlayer(id);
                                if(g.isOnline()){
                                    g.sendMessage("PIEC");
                                    g.sendMessage(c.g+"Final fight will start in "+c.r+ finalX +c.g+" seconds");
                                }
                            }
                        }
                    },teleportToArenaCounting-(20*x));
                    schedulerIDteleportToArenaCounting.add(schedID);
                }




    }
    void teleportToArenaFinal(){
        schedulerIDteleportToArenaFinal = Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(UUID id:getAllPlayers()){
                    Player g = Bukkit.getPlayer(id);
                    Location spawn = getSpawnMain();
                    g.teleport(spawn);
                    g.sendMessage("SZEŚC");
                    g.sendMessage(c.r+"Its time for fight!");
                    g.sendMessage(c.r+"You have 2 minutes for eliminate your enemies!");
                }
            }
        },teleportToArenaFinal);
    }
    void killPlayersFinal(){
        schedulerIDkillPlayersFinal =  Bukkit.getScheduler().scheduleSyncDelayedTask(Bedwars.getPlugin(Bedwars.class), new Runnable() {
            @Override
            public void run() {
                for(UUID id:getAllPlayers()){
                    Player g = Bukkit.getPlayer(id);
                    g.setHealth(0);
                    g.sendMessage("SIEDEM");
                    g.sendMessage(c.r+"You lose!");
                }
                idk.addToBeCleared(mapID);
            }
        },killPlayersFinal);
    }





    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
        if(team3Players.size()<teamSize){
            team3Players.add(p.getUniqueId());
            pdc.setTeam("team3");
            p.sendMessage(c.g+"You have joined "+team3Name);
            return;
        }
        if(team4Players.size()<teamSize){
            team4Players.add(p.getUniqueId());
            pdc.setTeam("team4");
            p.sendMessage(c.g+"You have joined "+team4Name);
            return;
        }
        if(team5Players.size()<teamSize){
            team5Players.add(p.getUniqueId());
            pdc.setTeam("team5");
            p.sendMessage(c.g+"You have joined "+team5Name);
            return;
        }
        if(team6Players.size()<teamSize){
            team6Players.add(p.getUniqueId());
            pdc.setTeam("team6");
            p.sendMessage(c.g+"You have joined "+team6Name);
            return;
        }
        if(team7Players.size()<teamSize){
            team7Players.add(p.getUniqueId());
            pdc.setTeam("team7");
            p.sendMessage(c.g+"You have joined "+team7Name);
            return;
        }
        if(team8Players.size()<teamSize){
            team8Players.add(p.getUniqueId());
            pdc.setTeam("team8");
            p.sendMessage(c.g+"You have joined "+team8Name);
            return;
        }
        if(team9Players.size()<teamSize){
            team9Players.add(p.getUniqueId());
            pdc.setTeam("team9");
            p.sendMessage(c.g+"You have joined "+team9Name);
            return;
        }
        if(team10Players.size()<teamSize){
            team10Players.add(p.getUniqueId());
            pdc.setTeam("team10");
            p.sendMessage(c.g+"You have joined "+team10Name);
            return;
        }
        if(team11Players.size()<teamSize){
            team11Players.add(p.getUniqueId());
            pdc.setTeam("team11");
            p.sendMessage(c.g+"You have joined "+team11Name);
            return;
        }
        if(team12Players.size()<teamSize){
            team12Players.add(p.getUniqueId());
            pdc.setTeam("team12");
            p.sendMessage(c.g+"You have joined "+team12Name);
            return;
        }

    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids: team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids: team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }
        for(UUID ids: team3Players){
            if(ids.equals(id)){
                team3Players.remove(id);
                return;
            }
        }
        for(UUID ids: team4Players){
            if(ids.equals(id)){
                team4Players.remove(id);
                return;
            }
        }
        for(UUID ids: team5Players){
            if(ids.equals(id)){
                team5Players.remove(id);
                return;
            }
        }
        for(UUID ids: team6Players){
            if(ids.equals(id)){
                team6Players.remove(id);
                return;
            }
        }
        for(UUID ids: team7Players){
            if(ids.equals(id)){
                team7Players.remove(id);
                return;
            }
        }
        for(UUID ids: team8Players){
            if(ids.equals(id)){
                team8Players.remove(id);
                return;
            }
        }
        for(UUID ids: team9Players){
            if(ids.equals(id)){
                team9Players.remove(id);
                return;
            }
        }
        for(UUID ids: team10Players){
            if(ids.equals(id)){
                team10Players.remove(id);
                return;
            }
        }
        for(UUID ids: team11Players){
            if(ids.equals(id)){
                team11Players.remove(id);
                return;
            }
        }
        for(UUID ids: team12Players){
            if(ids.equals(id)){
                team12Players.remove(id);
                return;
            }
        }
    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;
        if(team3Players.contains(id))return team3Players;
        if(team4Players.contains(id))return team4Players;
        if(team5Players.contains(id))return team5Players;
        if(team6Players.contains(id))return team6Players;
        if(team7Players.contains(id))return team7Players;
        if(team8Players.contains(id))return team8Players;
        if(team9Players.contains(id))return team9Players;
        if(team10Players.contains(id))return team10Players;
        if(team11Players.contains(id))return team11Players;
        if(team12Players.contains(id))return team12Players;
        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    String team3Name = conf.getString("teams.name..team3Name");
    String team4Name = conf.getString("teams.name..team4Name");
    String team5Name = conf.getString("teams.name..team5Name");
    String team6Name = conf.getString("teams.name..team6Name");
    String team7Name = conf.getString("teams.name..team7Name");
    String team8Name = conf.getString("teams.name..team8Name");
    String team9Name = conf.getString("teams.name..team9Name");
    String team10Name = conf.getString("teams.name..team10Name");
    String team11Name = conf.getString("teams.name..team11Name");
    String team12Name = conf.getString("teams.name..team12Name");


    static List<UUID> team1Players = new ArrayList<>();
    static List<UUID> team2Players = new ArrayList<>();
    static List<UUID> team3Players = new ArrayList<>();
    static List<UUID> team4Players = new ArrayList<>();
    static List<UUID> team5Players = new ArrayList<>();
    static List<UUID> team6Players = new ArrayList<>();
    static List<UUID> team7Players = new ArrayList<>();
    static List<UUID> team8Players = new ArrayList<>();
    static List<UUID> team9Players = new ArrayList<>();
    static List<UUID> team10Players = new ArrayList<>();
    static List<UUID> team11Players = new ArrayList<>();
    static List<UUID> team12Players = new ArrayList<>();

    double xBed1 =    conf.getDouble("team1.bed..x");
    double yBed1 =    conf.getDouble("team1.bed..y");
    double zBed1 =    conf.getDouble("team1.bed..z");
    Location team1BedLoc = new Location(Bukkit.getWorld("world"),xBed1,yBed1,zBed1);
    double xBed2 =    conf.getDouble("team2.bed..x");
    double yBed2 =    conf.getDouble("team2.bed..y");
    double zBed2 =    conf.getDouble("team2.bed..z");
    Location team2BedLoc = new Location(Bukkit.getWorld("world"),xBed2,yBed2,zBed2);

    double xBed3 =    conf.getDouble("team3.bed..x");
    double yBed3 =    conf.getDouble("team3.bed..y");
    double zBed3 =    conf.getDouble("team3.bed..z");
    Location team3BedLoc = new Location(Bukkit.getWorld("world"),xBed3,yBed3,zBed3);


    double xBed4 =    conf.getDouble("team4.bed..x");
    double yBed4 =    conf.getDouble("team4.bed..y");
    double zBed4 =    conf.getDouble("team4.bed..z");
    Location team4BedLoc = new Location(Bukkit.getWorld("world"),xBed4,yBed4,zBed4);

    double xBed5 =    conf.getDouble("team5.bed..x");
    double yBed5 =    conf.getDouble("team5.bed..y");
    double zBed5 =    conf.getDouble("team5.bed..z");
    Location team5BedLoc = new Location(Bukkit.getWorld("world"),xBed5,yBed5,zBed5);

    double xBed6 =    conf.getDouble("team6.bed..x");
    double yBed6 =    conf.getDouble("team6.bed..y");
    double zBed6 =    conf.getDouble("team6.bed..z");
    Location team6BedLoc = new Location(Bukkit.getWorld("world"),xBed6,yBed6,zBed6);

    double xBed7 =    conf.getDouble("team7.bed..x");
    double yBed7 =    conf.getDouble("team7.bed..y");
    double zBed7 =    conf.getDouble("team7.bed..z");
    Location team7BedLoc = new Location(Bukkit.getWorld("world"),xBed7,yBed7,zBed7);

    double xBed8 =    conf.getDouble("team8.bed..x");
    double yBed8 =    conf.getDouble("team8.bed..y");
    double zBed8 =    conf.getDouble("team8.bed..z");
    Location team8BedLoc = new Location(Bukkit.getWorld("world"),xBed8,yBed8,zBed8);

    double xBed9 =    conf.getDouble("team9.bed..x");
    double yBed9 =    conf.getDouble("team9.bed..y");
    double zBed9 =    conf.getDouble("team9.bed..z");
    Location team9BedLoc = new Location(Bukkit.getWorld("world"),xBed9,yBed9,zBed9);

    double xBed10 =    conf.getDouble("team10.bed..x");
    double yBed10 =    conf.getDouble("team10.bed..y");
    double zBed10 =    conf.getDouble("team10.bed..z");
    Location team10BedLoc = new Location(Bukkit.getWorld("world"),xBed10,yBed10,zBed10);

    double xBed11 =    conf.getDouble("team11.bed..x");
    double yBed11 =    conf.getDouble("team11.bed..y");
    double zBed11 =    conf.getDouble("team11.bed..z");
    Location team11BedLoc = new Location(Bukkit.getWorld("world"),xBed11,yBed11,zBed11);

    double xBed12 =    conf.getDouble("team12.bed..x");
    double yBed12 =    conf.getDouble("team12.bed..y");
    double zBed12 =    conf.getDouble("team12.bed..z");
    Location team12BedLoc = new Location(Bukkit.getWorld("world"),xBed12,yBed12,zBed12);

    private List<Location> getplayerBedsLocations(){
        return Arrays.asList(team1BedLoc,team2BedLoc,team3BedLoc,team4BedLoc,team5BedLoc,team6BedLoc,team7BedLoc,team8BedLoc,team9BedLoc,team10BedLoc,team11BedLoc,team12BedLoc);
    }

    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");

    double xT3 =conf.getDouble("team3.spawn..x");
    double yT3 =conf.getDouble("team3.spawn..y");
    double zT3 =conf.getDouble("team3.spawn..z");
    float yawT3 = (float)conf.getDouble("team3.spawn..yaw");
    float pitchT3 = (float)conf.getDouble("team3.spawn..pitch");

    double xT4 = conf.getDouble("team4.spawn..x");
    double yT4 = conf.getDouble("team4.spawn..y");
    double zT4 = conf.getDouble("team4.spawn..z");
    float yawT4 =  (float)conf.getDouble("team4.spawn..yaw");
    float pitchT4 =  (float)conf.getDouble("team4.spawn..pitch");

    double xT5 = conf.getDouble("team5.spawn..x");
    double yT5 = conf.getDouble("team5.spawn..y");
    double zT5 = conf.getDouble("team5.spawn..z");
    float yawT5 = (float) conf.getDouble("team5.spawn..yaw");
    float pitchT5 =  (float)conf.getDouble("team5.spawn..pitch");

    double xT6 = conf.getDouble("team6.spawn..x");
    double yT6 = conf.getDouble("team6.spawn..y");
    double zT6 = conf.getDouble("team6.spawn..z");
    float yawT6 = (float)conf.getDouble("team6.spawn..yaw");
    float pitchT6 = (float)conf.getDouble("team6.spawn..pitch");

    double xT7 = conf.getDouble("team7.spawn..x");
    double yT7 = conf.getDouble("team7.spawn..y");
    double zT7 = conf.getDouble("team7.spawn..z");
    float yawT7 =  (float)conf.getDouble("team7.spawn..yaw");
    float pitchT7 =  (float)conf.getDouble("team7.spawn..pitch");

    double xT8 = conf.getDouble("team8.spawn..x");
    double yT8 = conf.getDouble("team8.spawn..y");
    double zT8 = conf.getDouble("team8.spawn..z");
    float yawT8 = (float)conf.getDouble("team8.spawn..yaw");
    float pitchT8 = (float)conf.getDouble("team8.spawn..pitch");

    double xT9 = conf.getDouble("team9.spawn..x");
    double yT9 = conf.getDouble("team9.spawn..y");
    double zT9 = conf.getDouble("team9.spawn..z");
    float yawT9 = (float)conf.getDouble("team9.spawn..yaw");
    float pitchT9 = (float)conf.getDouble("team9.spawn..pitch");

    double xT10 =conf.getDouble("team10.spawn..x");
    double yT10 =conf.getDouble("team10.spawn..y");
    double zT10 =conf.getDouble("team10.spawn..z");
    float yawT10 = (float)conf.getDouble("team10.spawn..yaw");
    float pitchT10 = (float)conf.getDouble("team10.spawn..pitch");

    double xT11 =conf.getDouble("team11.spawn..x");
    double yT11 =conf.getDouble("team11.spawn..y");
    double zT11 =conf.getDouble("team11.spawn..z");
    float yawT11 = (float)conf.getDouble("team11.spawn..yaw");
    float pitchT11 = (float)conf.getDouble("team11.spawn..pitch");

    double xT12 =conf.getDouble("team12.spawn..x");
    double yT12 =conf.getDouble("team12.spawn..y");
    double zT12 =conf.getDouble("team12.spawn..z");
    float yawT12 = (float)conf.getDouble("team12.spawn..yaw");
    float pitchT12 = (float)conf.getDouble("team12.spawn..pitch");



    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xu3 = conf.getDouble("team3.upgradeManLoc..x");
    double yu3 = conf.getDouble("team3.upgradeManLoc..y");
    double zu3 = conf.getDouble("team3.upgradeManLoc..z");
    float  wu3 = (float)conf.getDouble("team3.upgradeManLoc..yaw");
    float  pu3 = (float)conf.getDouble("team3.upgradeManLoc..pitch");
    double xs3 = conf.getDouble("team3.shopManLoc..x");
    double ys3 = conf.getDouble("team3.shopManLoc..y");
    double zs3 = conf.getDouble("team3.shopManLoc..z");
    float  ws3 = (float)conf.getDouble("team3.shopManLoc..yaw");
    float  ps3 = (float)conf.getDouble("team3.shopManLoc..pitch");
    double xi3 = conf.getDouble("team3.itemsSpawnLoc..x");
    double yi3 = conf.getDouble("team3.itemsSpawnLoc..y");
    double zi3 = conf.getDouble("team3.itemsSpawnLoc..z");
    float  wi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..yaw");
    float  pi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..pitch");

    double xu4 = conf.getDouble("team4.upgradeManLoc..x");
    double yu4 = conf.getDouble("team4.upgradeManLoc..y");
    double zu4 = conf.getDouble("team4.upgradeManLoc..z");
    float  wu4 = (float)conf.getDouble("team4.upgradeManLoc..yaw");
    float  pu4 = (float)conf.getDouble("team4.upgradeManLoc..pitch");
    double xs4 = conf.getDouble("team4.shopManLoc..x");
    double ys4 = conf.getDouble("team4.shopManLoc..y");
    double zs4 = conf.getDouble("team4.shopManLoc..z");
    float  ws4 = (float)conf.getDouble("team4.shopManLoc..yaw");
    float  ps4 = (float)conf.getDouble("team4.shopManLoc..pitch");
    double xi4 = conf.getDouble("team4.itemsSpawnLoc..x");
    double yi4 = conf.getDouble("team4.itemsSpawnLoc..y");
    double zi4 = conf.getDouble("team4.itemsSpawnLoc..z");
    float  wi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..yaw");
    float  pi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..pitch");

    double xu5 = conf.getDouble("team5.upgradeManLoc..x");
    double yu5 = conf.getDouble("team5.upgradeManLoc..y");
    double zu5 = conf.getDouble("team5.upgradeManLoc..z");
    float  wu5 = (float)conf.getDouble("team5.upgradeManLoc..yaw");
    float  pu5 = (float)conf.getDouble("team5.upgradeManLoc..pitch");
    double xs5 = conf.getDouble("team5.shopManLoc..x");
    double ys5 = conf.getDouble("team5.shopManLoc..y");
    double zs5 = conf.getDouble("team5.shopManLoc..z");
    float  ws5 = (float)conf.getDouble("team5.shopManLoc..yaw");
    float  ps5 = (float)conf.getDouble("team5.shopManLoc..pitch");
    double xi5 = conf.getDouble("team5.itemsSpawnLoc..x");
    double yi5 = conf.getDouble("team5.itemsSpawnLoc..y");
    double zi5 = conf.getDouble("team5.itemsSpawnLoc..z");
    float  wi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..yaw");
    float  pi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..pitch");

    double xu6 = conf.getDouble("team6.upgradeManLoc..x");
    double yu6 = conf.getDouble("team6.upgradeManLoc..y");
    double zu6 = conf.getDouble("team6.upgradeManLoc..z");
    float  wu6 = (float)conf.getDouble("team6.upgradeManLoc..yaw");
    float  pu6 = (float)conf.getDouble("team6.upgradeManLoc..pitch");
    double xs6 = conf.getDouble("team6.shopManLoc..x");
    double ys6 = conf.getDouble("team6.shopManLoc..y");
    double zs6 = conf.getDouble("team6.shopManLoc..z");
    float  ws6 = (float)conf.getDouble("team6.shopManLoc..yaw");
    float  ps6 = (float)conf.getDouble("team6.shopManLoc..pitch");
    double xi6 = conf.getDouble("team6.itemsSpawnLoc..x");
    double yi6 = conf.getDouble("team6.itemsSpawnLoc..y");
    double zi6 = conf.getDouble("team6.itemsSpawnLoc..z");
    float  wi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..yaw");
    float  pi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..pitch");

    double xu7 = conf.getDouble("team7.upgradeManLoc..x");
    double yu7 = conf.getDouble("team7.upgradeManLoc..y");
    double zu7 = conf.getDouble("team7.upgradeManLoc..z");
    float  wu7 = (float)conf.getDouble("team7.upgradeManLoc..yaw");
    float  pu7 = (float)conf.getDouble("team7.upgradeManLoc..pitch");
    double xs7 = conf.getDouble("team7.shopManLoc..x");
    double ys7 = conf.getDouble("team7.shopManLoc..y");
    double zs7 = conf.getDouble("team7.shopManLoc..z");
    float  ws7 = (float)conf.getDouble("team7.shopManLoc..yaw");
    float  ps7 = (float)conf.getDouble("team7.shopManLoc..pitch");
    double xi7 = conf.getDouble("team7.itemsSpawnLoc..x");
    double yi7 = conf.getDouble("team7.itemsSpawnLoc..y");
    double zi7 = conf.getDouble("team7.itemsSpawnLoc..z");
    float  wi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..yaw");
    float  pi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..pitch");

    double xu8 = conf.getDouble("team8.upgradeManLoc..x");
    double yu8 = conf.getDouble("team8.upgradeManLoc..y");
    double zu8 = conf.getDouble("team8.upgradeManLoc..z");
    float  wu8 = (float)conf.getDouble("team8.upgradeManLoc..yaw");
    float  pu8 = (float)conf.getDouble("team8.upgradeManLoc..pitch");
    double xs8 = conf.getDouble("team8.shopManLoc..x");
    double ys8 = conf.getDouble("team8.shopManLoc..y");
    double zs8 = conf.getDouble("team8.shopManLoc..z");
    float  ws8 = (float)conf.getDouble("team8.shopManLoc..yaw");
    float  ps8 = (float)conf.getDouble("team8.shopManLoc..pitch");
    double xi8 = conf.getDouble("team8.itemsSpawnLoc..x");
    double yi8 = conf.getDouble("team8.itemsSpawnLoc..y");
    double zi8 = conf.getDouble("team8.itemsSpawnLoc..z");
    float  wi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..yaw");
    float  pi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..pitch");

    double xu9 = conf.getDouble("team9.upgradeManLoc..x");
    double yu9 = conf.getDouble("team9.upgradeManLoc..y");
    double zu9 = conf.getDouble("team9.upgradeManLoc..z");
    float  wu9 = (float)conf.getDouble("team9.upgradeManLoc..yaw");
    float  pu9 = (float)conf.getDouble("team9.upgradeManLoc..pitch");
    double xs9 = conf.getDouble("team9.shopManLoc..x");
    double ys9 = conf.getDouble("team9.shopManLoc..y");
    double zs9 = conf.getDouble("team9.shopManLoc..z");
    float  ws9 = (float)conf.getDouble("team9.shopManLoc..yaw");
    float  ps9 = (float)conf.getDouble("team9.shopManLoc..pitch");
    double xi9 = conf.getDouble("team9.itemsSpawnLoc..x");
    double yi9 = conf.getDouble("team9.itemsSpawnLoc..y");
    double zi9 = conf.getDouble("team9.itemsSpawnLoc..z");
    float  wi9 = (float)conf.getDouble("team9.itemsSpawnManLoc..yaw");
    float  pi9 = (float)conf.getDouble("team9.itemsSpawnManLoc..pitch");

    double xu10 = conf.getDouble("team10.upgradeManLoc..x");
    double yu10 = conf.getDouble("team10.upgradeManLoc..y");
    double zu10 = conf.getDouble("team10.upgradeManLoc..z");
    float  wu10 = (float)conf.getDouble("team10.upgradeManLoc..yaw");
    float  pu10 = (float)conf.getDouble("team10.upgradeManLoc..pitch");
    double xs10 = conf.getDouble("team10.shopManLoc..x");
    double ys10 = conf.getDouble("team10.shopManLoc..y");
    double zs10 = conf.getDouble("team10.shopManLoc..z");
    float  ws10 = (float)conf.getDouble("team10.shopManLoc..yaw");
    float  ps10 = (float)conf.getDouble("team10.shopManLoc..pitch");
    double xi10 = conf.getDouble("team10.itemsSpawnLoc..x");
    double yi10 = conf.getDouble("team10.itemsSpawnLoc..y");
    double zi10 = conf.getDouble("team10.itemsSpawnLoc..z");
    float  wi10 = (float)conf.getDouble("team10.itemsSpawnManLoc..yaw");
    float  pi10 = (float)conf.getDouble("team10.itemsSpawnManLoc..pitch");

    double xu11 = conf.getDouble("team11.upgradeManLoc..x");
    double yu11 = conf.getDouble("team11.upgradeManLoc..y");
    double zu11 = conf.getDouble("team11.upgradeManLoc..z");
    float  wu11 = (float)conf.getDouble("team11.upgradeManLoc..yaw");
    float  pu11 = (float)conf.getDouble("team11.upgradeManLoc..pitch");
    double xs11 = conf.getDouble("team11.shopManLoc..x");
    double ys11 = conf.getDouble("team11.shopManLoc..y");
    double zs11 = conf.getDouble("team11.shopManLoc..z");
    float  ws11 = (float)conf.getDouble("team11.shopManLoc..yaw");
    float  ps11 = (float)conf.getDouble("team11.shopManLoc..pitch");
    double xi11 = conf.getDouble("team11.itemsSpawnLoc..x");
    double yi11 = conf.getDouble("team11.itemsSpawnLoc..y");
    double zi11 = conf.getDouble("team11.itemsSpawnLoc..z");
    float  wi11 = (float)conf.getDouble("team11.itemsSpawnManLoc..yaw");
    float  pi11 = (float)conf.getDouble("team11.itemsSpawnManLoc..pitch");

    double xu12 = conf.getDouble("team12.upgradeManLoc..x");
    double yu12 = conf.getDouble("team12.upgradeManLoc..y");
    double zu12 = conf.getDouble("team12.upgradeManLoc..z");
    float  wu12 = (float)conf.getDouble("team12.upgradeManLoc..yaw");
    float  pu12 = (float)conf.getDouble("team12.upgradeManLoc..pitch");
    double xs12 = conf.getDouble("team12.shopManLoc..x");
    double ys12 = conf.getDouble("team12.shopManLoc..y");
    double zs12 = conf.getDouble("team12.shopManLoc..z");
    float  ws12 = (float)conf.getDouble("team12.shopManLoc..yaw");
    float  ps12 = (float)conf.getDouble("team12.shopManLoc..pitch");
    double xi12 = conf.getDouble("team12.itemsSpawnLoc..x");
    double yi12 = conf.getDouble("team12.itemsSpawnLoc..y");
    double zi12 = conf.getDouble("team12.itemsSpawnLoc..z");
    float  wi12 = (float)conf.getDouble("team12.itemsSpawnManLoc..yaw");
    float  pi12 = (float)conf.getDouble("team12.itemsSpawnManLoc..pitch");


    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }
    double xdiamonds3 = conf.getInt("mapData.gems.diamonds3..locX");
    double ydiamonds3 =  conf.getInt("mapData.gems.diamonds3..locY");
    double zdiamonds3 =  conf.getInt("mapData.gems.diamonds3..locZ");
    Location getdiamonds3Location(){
        return new Location(world,xdiamonds3,ydiamonds3,zdiamonds3);
    }
    double xdiamonds4 = conf.getInt("mapData.gems.diamonds4..locX");
    double ydiamonds4 =  conf.getInt("mapData.gems.diamonds4..locY");
    double zdiamonds4 =  conf.getInt("mapData.gems.diamonds4..locZ");
    Location getdiamonds4Location(){
        return new Location(world,xdiamonds4,ydiamonds4,zdiamonds4);
    }
    double xdiamonds5 = conf.getInt("mapData.gems.diamonds5..locX");
    double ydiamonds5 =  conf.getInt("mapData.gems.diamonds5..locY");
    double zdiamonds5 =  conf.getInt("mapData.gems.diamonds5..locZ");
    Location getdiamonds5Location(){
        return new Location(world,xdiamonds5,ydiamonds5,zdiamonds5);
    }
    double xdiamonds6 = conf.getInt("mapData.gems.diamonds6..locX");
    double ydiamonds6 =  conf.getInt("mapData.gems.diamonds6..locY");
    double zdiamonds6 =  conf.getInt("mapData.gems.diamonds6..locZ");
    Location getdiamonds6Location(){
        return new Location(world,xdiamonds6,ydiamonds6,zdiamonds6);
    }
    double xdiamonds7 = conf.getInt("mapData.gems.diamonds7..locX");
    double ydiamonds7 =  conf.getInt("mapData.gems.diamonds7..locY");
    double zdiamonds7 =  conf.getInt("mapData.gems.diamonds7..locZ");
    Location getdiamonds7Location(){
        return new Location(world,xdiamonds7,ydiamonds7,zdiamonds7);
    }
    double xdiamonds8 = conf.getInt("mapData.gems.diamonds8..locX");
    double ydiamonds8 =  conf.getInt("mapData.gems.diamonds8..locY");
    double zdiamonds8 =  conf.getInt("mapData.gems.diamonds8..locZ");
    Location getdiamonds8Location(){
        return new Location(world,xdiamonds8,ydiamonds8,zdiamonds8);
    }
    double xdiamonds9 = conf.getInt("mapData.gems.diamonds9..locX");
    double ydiamonds9 =  conf.getInt("mapData.gems.diamonds9..locY");
    double zdiamonds9 =  conf.getInt("mapData.gems.diamonds9..locZ");
    Location getdiamonds9Location(){
        return new Location(world,xdiamonds9,ydiamonds9,zdiamonds9);
    }
    double xdiamonds10 = conf.getInt("mapData.gems.diamonds10..locX");
    double ydiamonds10 =  conf.getInt("mapData.gems.diamonds10..locY");
    double zdiamonds10 =  conf.getInt("mapData.gems.diamonds10..locZ");
    Location getdiamonds10Location(){
        return new Location(world,xdiamonds10,ydiamonds10,zdiamonds10);
    }
    double xdiamonds11 = conf.getInt("mapData.gems.diamonds11..locX");
    double ydiamonds11 =  conf.getInt("mapData.gems.diamonds11..locY");
    double zdiamonds11 =  conf.getInt("mapData.gems.diamonds11..locZ");
    Location getdiamonds11Location(){
        return new Location(world,xdiamonds11,ydiamonds11,zdiamonds11);
    }
    double xdiamonds12 = conf.getInt("mapData.gems.diamonds12..locX");
    double ydiamonds12 =  conf.getInt("mapData.gems.diamonds12..locY");
    double zdiamonds12 =  conf.getInt("mapData.gems.diamonds12..locZ");
    Location getdiamonds12Location(){
        return new Location(world,xdiamonds12,ydiamonds12,zdiamonds12);
    }



    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    Location getTeam3UpgradeManLocation(){return new Location(world,xu3,yu3,zu3,wu3,pu3);}
    Location getTeam3ShopManLocation(){return new Location(world,xs3,ys3,zs3,ws3,ps3);}
    Location getTeam3ItemSpawnLocation(){return new Location(world,xi3,yi3,zi3,wi3,pi3);}
    Location getTeam4UpgradeManLocation(){return new Location(world,xu4,yu4,zu4,wu4,pu4);}
    Location getTeam4ShopManLocation(){return new Location(world,xs4,ys4,zs4,ws4,ps4);}
    Location getTeam4ItemSpawnLocation(){return new Location(world,xi4,yi4,zi4,wi4,pi4);}
    Location getTeam5UpgradeManLocation(){return new Location(world,xu5,yu5,zu5,wu5,pu5);}
    Location getTeam5ShopManLocation(){return new Location(world,xs5,ys5,zs5,ws5,ps5);}
    Location getTeam5ItemSpawnLocation(){return new Location(world,xi5,yi5,zi5,wi5,pi5);}
    Location getTeam6UpgradeManLocation(){return new Location(world,xu6,yu6,zu6,wu6,pu6);}
    Location getTeam6ShopManLocation(){return new Location(world,xs6,ys6,zs6,ws6,ps6);}
    Location getTeam6ItemSpawnLocation(){return new Location(world,xi6,yi6,zi6,wi6,pi6);}
    Location getTeam7UpgradeManLocation(){return new Location(world,xu7,yu7,zu7,wu7,pu7);}
    Location getTeam7ShopManLocation(){return new Location(world,xs7,ys7,zs7,ws7,ps7);}
    Location getTeam7ItemSpawnLocation(){return new Location(world,xi7,yi7,zi7,wi7,pi7);}
    Location getTeam8UpgradeManLocation(){return new Location(world,xu8,yu8,zu8,wu8,pu8);}
    Location getTeam8ShopManLocation(){return new Location(world,xs8,ys8,zs8,ws8,ps8);}
    Location getTeam8ItemSpawnLocation(){return new Location(world,xi8,yi8,zi8,wi8,pi8);}
    Location getTeam9UpgradeManLocation(){return new Location(world,xu9,yu9,zu9,wu9,pu9);}
    Location getTeam9ShopManLocation(){return new Location(world,xs9,ys9,zs9,ws9,ps9);}
    Location getTeam9ItemSpawnLocation(){return new Location(world,xi9,yi9,zi9,wi9,pi9);}
    Location getTeam10UpgradeManLocation(){return new Location(world,xu10,yu10,zu10,wu10,pu10);}
    Location getTeam10ShopManLocation(){return new Location(world,xs10,ys10,zs10,ws10,ps10);}
    Location getTeam10ItemSpawnLocation(){return new Location(world,xi10,yi10,zi10,wi10,pi10);}
    Location getTeam11UpgradeManLocation(){return new Location(world,xu11,yu11,zu11,wu11,pu11);}
    Location getTeam11ShopManLocation(){return new Location(world,xs11,ys11,zs11,ws11,ps11);}
    Location getTeam11ItemSpawnLocation(){return new Location(world,xi11,yi11,zi11,wi11,pi11);}
    Location getTeam12UpgradeManLocation(){return new Location(world,xu12,yu12,zu12,wu12,pu12);}
    Location getTeam12ShopManLocation(){return new Location(world,xs12,ys12,zs12,ws12,ps12);}
    Location getTeam12ItemSpawnLocation(){return new Location(world,xi12,yi12,zi12,wi12,pi12);}

    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation(),getTeam3ItemSpawnLocation(),getTeam4ItemSpawnLocation(),getTeam5ItemSpawnLocation(),getTeam6ItemSpawnLocation(),getTeam7ItemSpawnLocation(),getTeam8ItemSpawnLocation(),getTeam9ItemSpawnLocation(),getTeam10ItemSpawnLocation(),getTeam11ItemSpawnLocation(),getTeam12ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation(),getTeam3UpgradeManLocation(),getTeam4UpgradeManLocation(),getTeam5UpgradeManLocation(),getTeam6UpgradeManLocation(),getTeam7UpgradeManLocation(),getTeam8UpgradeManLocation(),getTeam9UpgradeManLocation(),getTeam10UpgradeManLocation(),getTeam11UpgradeManLocation(),getTeam12UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation(),getTeam3ShopManLocation(),getTeam4ShopManLocation(),getTeam5ShopManLocation(),getTeam6ShopManLocation(),getTeam7ShopManLocation(),getTeam8ShopManLocation(),getTeam9ShopManLocation(),getTeam10ShopManLocation(),getTeam11ShopManLocation(),getTeam12ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location(),getdiamonds3Location(),getdiamonds4Location(),getdiamonds5Location(),getdiamonds6Location(),getdiamonds7Location(),getdiamonds8Location(),getdiamonds9Location(),getdiamonds10Location(),getdiamonds11Location(),getdiamonds12Location());
    }


    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }
    @Override
    List<UUID> getTeam3Players(){
        return team3Players;
    }
    @Override
    List<UUID> getTeam4Players(){
        return team4Players;
    }
    @Override
    List<UUID> getTeam5Players(){
        return team5Players;
    }
    @Override
    List<UUID> getTeam6Players(){
        return team6Players;
    }
    @Override
    List<UUID> getTeam7Players(){
        return team7Players;
    }

    @Override
    List<UUID> getTeam8Players(){
        return team8Players;
    }
    @Override
    List<UUID> getTeam9Players(){
        return team9Players;
    }
    @Override
    List<UUID> getTeam10Players(){
        return team10Players;
    }

    @Override
    List<UUID> getTeam11Players(){
        return team11Players;
    }
    @Override
    List<UUID> getTeam12Players(){
        return team12Players;
    }





    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }

    @Override
    Location getSpawnTeam3() {
        return new Location(world,xT3,yT3,zT3,yawT3,pitchT3);
    }

    @Override
    Location getSpawnTeam4() {
        return new Location(world,xT4,yT4,zT4,yawT4,pitchT4);
    }
    @Override
    Location getSpawnTeam5() {
        return new Location(world,xT5,yT5,zT5,yawT5,pitchT5);
    }
    @Override
    Location getSpawnTeam6() {
        return new Location(world,xT6,yT6,zT6,yawT6,pitchT6);
    }
    @Override
    Location getSpawnTeam7() {
        return new Location(world,xT7,yT7,zT7,yawT7,pitchT7);
    }
    @Override
    Location getSpawnTeam8() {
        return new Location(world,xT8,yT8,zT8,yawT8,pitchT8);
    }
    @Override
    Location getSpawnTeam9() {
        return new Location(world,xT9,yT9,zT9,yawT9,pitchT9);
    }
    @Override
    Location getSpawnTeam10() {
        return new Location(world,xT10,yT10,zT10,yawT10,pitchT10);
    }
    @Override
    Location getSpawnTeam11() {
        return new Location(world,xT11,yT11,zT11,yawT11,pitchT11);
    }
    @Override
    Location getSpawnTeam12() {
        return new Location(world,xT12,yT12,zT12,yawT12,pitchT12);
    }

    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }



    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map12 extends MapData<Map12> {
    String getMapName(){
        return "map12";
    }
    public Map12(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map12.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();

    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
        if(team3Players.size()<teamSize){
            team3Players.add(p.getUniqueId());
            pdc.setTeam("team3");
            p.sendMessage(c.g+"You have joined "+team3Name);
            return;
        }
        if(team4Players.size()<teamSize){
            team4Players.add(p.getUniqueId());
            pdc.setTeam("team4");
            p.sendMessage(c.g+"You have joined "+team4Name);
            return;
        }
        if(team5Players.size()<teamSize){
            team5Players.add(p.getUniqueId());
            pdc.setTeam("team5");
            p.sendMessage(c.g+"You have joined "+team5Name);
            return;
        }
        if(team6Players.size()<teamSize){
            team6Players.add(p.getUniqueId());
            pdc.setTeam("team6");
            p.sendMessage(c.g+"You have joined "+team6Name);
            return;
        }
        if(team7Players.size()<teamSize){
            team7Players.add(p.getUniqueId());
            pdc.setTeam("team7");
            p.sendMessage(c.g+"You have joined "+team7Name);
            return;
        }
        if(team8Players.size()<teamSize){
            team8Players.add(p.getUniqueId());
            pdc.setTeam("team8");
            p.sendMessage(c.g+"You have joined "+team8Name);
            return;
        }
        if(team9Players.size()<teamSize){
            team9Players.add(p.getUniqueId());
            pdc.setTeam("team9");
            p.sendMessage(c.g+"You have joined "+team9Name);
            return;
        }
        if(team10Players.size()<teamSize){
            team10Players.add(p.getUniqueId());
            pdc.setTeam("team10");
            p.sendMessage(c.g+"You have joined "+team10Name);
            return;
        }
        if(team11Players.size()<teamSize){
            team11Players.add(p.getUniqueId());
            pdc.setTeam("team11");
            p.sendMessage(c.g+"You have joined "+team11Name);
            return;
        }
        if(team12Players.size()<teamSize){
            team12Players.add(p.getUniqueId());
            pdc.setTeam("team12");
            p.sendMessage(c.g+"You have joined "+team12Name);
            return;
        }

    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids: team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids: team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }
        for(UUID ids: team3Players){
            if(ids.equals(id)){
                team3Players.remove(id);
                return;
            }
        }
        for(UUID ids: team4Players){
            if(ids.equals(id)){
                team4Players.remove(id);
                return;
            }
        }
        for(UUID ids: team5Players){
            if(ids.equals(id)){
                team5Players.remove(id);
                return;
            }
        }
        for(UUID ids: team6Players){
            if(ids.equals(id)){
                team6Players.remove(id);
                return;
            }
        }
        for(UUID ids: team7Players){
            if(ids.equals(id)){
                team7Players.remove(id);
                return;
            }
        }
        for(UUID ids: team8Players){
            if(ids.equals(id)){
                team8Players.remove(id);
                return;
            }
        }
        for(UUID ids: team9Players){
            if(ids.equals(id)){
                team9Players.remove(id);
                return;
            }
        }
        for(UUID ids: team10Players){
            if(ids.equals(id)){
                team10Players.remove(id);
                return;
            }
        }
        for(UUID ids: team11Players){
            if(ids.equals(id)){
                team11Players.remove(id);
                return;
            }
        }
        for(UUID ids: team12Players){
            if(ids.equals(id)){
                team12Players.remove(id);
                return;
            }
        }
    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;
        if(team3Players.contains(id))return team3Players;
        if(team4Players.contains(id))return team4Players;
        if(team5Players.contains(id))return team5Players;
        if(team6Players.contains(id))return team6Players;
        if(team7Players.contains(id))return team7Players;
        if(team8Players.contains(id))return team8Players;
        if(team9Players.contains(id))return team9Players;
        if(team10Players.contains(id))return team10Players;
        if(team11Players.contains(id))return team11Players;
        if(team12Players.contains(id))return team12Players;
        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    String team3Name = conf.getString("teams.name..team3Name");
    String team4Name = conf.getString("teams.name..team4Name");
    String team5Name = conf.getString("teams.name..team5Name");
    String team6Name = conf.getString("teams.name..team6Name");
    String team7Name = conf.getString("teams.name..team7Name");
    String team8Name = conf.getString("teams.name..team8Name");
    String team9Name = conf.getString("teams.name..team9Name");
    String team10Name = conf.getString("teams.name..team10Name");
    String team11Name = conf.getString("teams.name..team11Name");
    String team12Name = conf.getString("teams.name..team12Name");

    static List<UUID> team1Players = new ArrayList<>();
    static List<UUID> team2Players = new ArrayList<>();
    static List<UUID> team3Players = new ArrayList<>();
    static List<UUID> team4Players = new ArrayList<>();
    static List<UUID> team5Players = new ArrayList<>();
    static List<UUID> team6Players = new ArrayList<>();
    static List<UUID> team7Players = new ArrayList<>();
    static List<UUID> team8Players = new ArrayList<>();
    static List<UUID> team9Players = new ArrayList<>();
    static List<UUID> team10Players = new ArrayList<>();
    static List<UUID> team11Players = new ArrayList<>();
    static List<UUID> team12Players = new ArrayList<>();


    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");

    double xT3 =conf.getDouble("team3.spawn..x");
    double yT3 =conf.getDouble("team3.spawn..y");
    double zT3 =conf.getDouble("team3.spawn..z");
    float yawT3 = (float)conf.getDouble("team3.spawn..yaw");
    float pitchT3 = (float)conf.getDouble("team3.spawn..pitch");

    double xT4 = conf.getDouble("team4.spawn..x");
    double yT4 = conf.getDouble("team4.spawn..y");
    double zT4 = conf.getDouble("team4.spawn..z");
    float yawT4 =  (float)conf.getDouble("team4.spawn..yaw");
    float pitchT4 =  (float)conf.getDouble("team4.spawn..pitch");

    double xT5 = conf.getDouble("team5.spawn..x");
    double yT5 = conf.getDouble("team5.spawn..y");
    double zT5 = conf.getDouble("team5.spawn..z");
    float yawT5 = (float) conf.getDouble("team5.spawn..yaw");
    float pitchT5 =  (float)conf.getDouble("team5.spawn..pitch");

    double xT6 = conf.getDouble("team6.spawn..x");
    double yT6 = conf.getDouble("team6.spawn..y");
    double zT6 = conf.getDouble("team6.spawn..z");
    float yawT6 = (float)conf.getDouble("team6.spawn..yaw");
    float pitchT6 = (float)conf.getDouble("team6.spawn..pitch");

    double xT7 = conf.getDouble("team7.spawn..x");
    double yT7 = conf.getDouble("team7.spawn..y");
    double zT7 = conf.getDouble("team7.spawn..z");
    float yawT7 =  (float)conf.getDouble("team7.spawn..yaw");
    float pitchT7 =  (float)conf.getDouble("team7.spawn..pitch");

    double xT8 = conf.getDouble("team8.spawn..x");
    double yT8 = conf.getDouble("team8.spawn..y");
    double zT8 = conf.getDouble("team8.spawn..z");
    float yawT8 = (float)conf.getDouble("team8.spawn..yaw");
    float pitchT8 = (float)conf.getDouble("team8.spawn..pitch");

    double xT9 = conf.getDouble("team9.spawn..x");
    double yT9 = conf.getDouble("team9.spawn..y");
    double zT9 = conf.getDouble("team9.spawn..z");
    float yawT9 = (float)conf.getDouble("team9.spawn..yaw");
    float pitchT9 = (float)conf.getDouble("team9.spawn..pitch");

    double xT10 =conf.getDouble("team10.spawn..x");
    double yT10 =conf.getDouble("team10.spawn..y");
    double zT10 =conf.getDouble("team10.spawn..z");
    float yawT10 = (float)conf.getDouble("team10.spawn..yaw");
    float pitchT10 = (float)conf.getDouble("team10.spawn..pitch");

    double xT11 =conf.getDouble("team11.spawn..x");
    double yT11 =conf.getDouble("team11.spawn..y");
    double zT11 =conf.getDouble("team11.spawn..z");
    float yawT11 = (float)conf.getDouble("team11.spawn..yaw");
    float pitchT11 = (float)conf.getDouble("team11.spawn..pitch");

    double xT12 =conf.getDouble("team12.spawn..x");
    double yT12 =conf.getDouble("team12.spawn..y");
    double zT12 =conf.getDouble("team12.spawn..z");
    float yawT12 = (float)conf.getDouble("team12.spawn..yaw");
    float pitchT12 = (float)conf.getDouble("team12.spawn..pitch");

    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xu3 = conf.getDouble("team3.upgradeManLoc..x");
    double yu3 = conf.getDouble("team3.upgradeManLoc..y");
    double zu3 = conf.getDouble("team3.upgradeManLoc..z");
    float  wu3 = (float)conf.getDouble("team3.upgradeManLoc..yaw");
    float  pu3 = (float)conf.getDouble("team3.upgradeManLoc..pitch");
    double xs3 = conf.getDouble("team3.shopManLoc..x");
    double ys3 = conf.getDouble("team3.shopManLoc..y");
    double zs3 = conf.getDouble("team3.shopManLoc..z");
    float  ws3 = (float)conf.getDouble("team3.shopManLoc..yaw");
    float  ps3 = (float)conf.getDouble("team3.shopManLoc..pitch");
    double xi3 = conf.getDouble("team3.itemsSpawnLoc..x");
    double yi3 = conf.getDouble("team3.itemsSpawnLoc..y");
    double zi3 = conf.getDouble("team3.itemsSpawnLoc..z");
    float  wi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..yaw");
    float  pi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..pitch");

    double xu4 = conf.getDouble("team4.upgradeManLoc..x");
    double yu4 = conf.getDouble("team4.upgradeManLoc..y");
    double zu4 = conf.getDouble("team4.upgradeManLoc..z");
    float  wu4 = (float)conf.getDouble("team4.upgradeManLoc..yaw");
    float  pu4 = (float)conf.getDouble("team4.upgradeManLoc..pitch");
    double xs4 = conf.getDouble("team4.shopManLoc..x");
    double ys4 = conf.getDouble("team4.shopManLoc..y");
    double zs4 = conf.getDouble("team4.shopManLoc..z");
    float  ws4 = (float)conf.getDouble("team4.shopManLoc..yaw");
    float  ps4 = (float)conf.getDouble("team4.shopManLoc..pitch");
    double xi4 = conf.getDouble("team4.itemsSpawnLoc..x");
    double yi4 = conf.getDouble("team4.itemsSpawnLoc..y");
    double zi4 = conf.getDouble("team4.itemsSpawnLoc..z");
    float  wi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..yaw");
    float  pi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..pitch");

    double xu5 = conf.getDouble("team5.upgradeManLoc..x");
    double yu5 = conf.getDouble("team5.upgradeManLoc..y");
    double zu5 = conf.getDouble("team5.upgradeManLoc..z");
    float  wu5 = (float)conf.getDouble("team5.upgradeManLoc..yaw");
    float  pu5 = (float)conf.getDouble("team5.upgradeManLoc..pitch");
    double xs5 = conf.getDouble("team5.shopManLoc..x");
    double ys5 = conf.getDouble("team5.shopManLoc..y");
    double zs5 = conf.getDouble("team5.shopManLoc..z");
    float  ws5 = (float)conf.getDouble("team5.shopManLoc..yaw");
    float  ps5 = (float)conf.getDouble("team5.shopManLoc..pitch");
    double xi5 = conf.getDouble("team5.itemsSpawnLoc..x");
    double yi5 = conf.getDouble("team5.itemsSpawnLoc..y");
    double zi5 = conf.getDouble("team5.itemsSpawnLoc..z");
    float  wi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..yaw");
    float  pi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..pitch");

    double xu6 = conf.getDouble("team6.upgradeManLoc..x");
    double yu6 = conf.getDouble("team6.upgradeManLoc..y");
    double zu6 = conf.getDouble("team6.upgradeManLoc..z");
    float  wu6 = (float)conf.getDouble("team6.upgradeManLoc..yaw");
    float  pu6 = (float)conf.getDouble("team6.upgradeManLoc..pitch");
    double xs6 = conf.getDouble("team6.shopManLoc..x");
    double ys6 = conf.getDouble("team6.shopManLoc..y");
    double zs6 = conf.getDouble("team6.shopManLoc..z");
    float  ws6 = (float)conf.getDouble("team6.shopManLoc..yaw");
    float  ps6 = (float)conf.getDouble("team6.shopManLoc..pitch");
    double xi6 = conf.getDouble("team6.itemsSpawnLoc..x");
    double yi6 = conf.getDouble("team6.itemsSpawnLoc..y");
    double zi6 = conf.getDouble("team6.itemsSpawnLoc..z");
    float  wi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..yaw");
    float  pi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..pitch");

    double xu7 = conf.getDouble("team7.upgradeManLoc..x");
    double yu7 = conf.getDouble("team7.upgradeManLoc..y");
    double zu7 = conf.getDouble("team7.upgradeManLoc..z");
    float  wu7 = (float)conf.getDouble("team7.upgradeManLoc..yaw");
    float  pu7 = (float)conf.getDouble("team7.upgradeManLoc..pitch");
    double xs7 = conf.getDouble("team7.shopManLoc..x");
    double ys7 = conf.getDouble("team7.shopManLoc..y");
    double zs7 = conf.getDouble("team7.shopManLoc..z");
    float  ws7 = (float)conf.getDouble("team7.shopManLoc..yaw");
    float  ps7 = (float)conf.getDouble("team7.shopManLoc..pitch");
    double xi7 = conf.getDouble("team7.itemsSpawnLoc..x");
    double yi7 = conf.getDouble("team7.itemsSpawnLoc..y");
    double zi7 = conf.getDouble("team7.itemsSpawnLoc..z");
    float  wi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..yaw");
    float  pi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..pitch");

    double xu8 = conf.getDouble("team8.upgradeManLoc..x");
    double yu8 = conf.getDouble("team8.upgradeManLoc..y");
    double zu8 = conf.getDouble("team8.upgradeManLoc..z");
    float  wu8 = (float)conf.getDouble("team8.upgradeManLoc..yaw");
    float  pu8 = (float)conf.getDouble("team8.upgradeManLoc..pitch");
    double xs8 = conf.getDouble("team8.shopManLoc..x");
    double ys8 = conf.getDouble("team8.shopManLoc..y");
    double zs8 = conf.getDouble("team8.shopManLoc..z");
    float  ws8 = (float)conf.getDouble("team8.shopManLoc..yaw");
    float  ps8 = (float)conf.getDouble("team8.shopManLoc..pitch");
    double xi8 = conf.getDouble("team8.itemsSpawnLoc..x");
    double yi8 = conf.getDouble("team8.itemsSpawnLoc..y");
    double zi8 = conf.getDouble("team8.itemsSpawnLoc..z");
    float  wi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..yaw");
    float  pi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..pitch");

    double xu9 = conf.getDouble("team9.upgradeManLoc..x");
    double yu9 = conf.getDouble("team9.upgradeManLoc..y");
    double zu9 = conf.getDouble("team9.upgradeManLoc..z");
    float  wu9 = (float)conf.getDouble("team9.upgradeManLoc..yaw");
    float  pu9 = (float)conf.getDouble("team9.upgradeManLoc..pitch");
    double xs9 = conf.getDouble("team9.shopManLoc..x");
    double ys9 = conf.getDouble("team9.shopManLoc..y");
    double zs9 = conf.getDouble("team9.shopManLoc..z");
    float  ws9 = (float)conf.getDouble("team9.shopManLoc..yaw");
    float  ps9 = (float)conf.getDouble("team9.shopManLoc..pitch");
    double xi9 = conf.getDouble("team9.itemsSpawnLoc..x");
    double yi9 = conf.getDouble("team9.itemsSpawnLoc..y");
    double zi9 = conf.getDouble("team9.itemsSpawnLoc..z");
    float  wi9 = (float)conf.getDouble("team9.itemsSpawnManLoc..yaw");
    float  pi9 = (float)conf.getDouble("team9.itemsSpawnManLoc..pitch");

    double xu10 = conf.getDouble("team10.upgradeManLoc..x");
    double yu10 = conf.getDouble("team10.upgradeManLoc..y");
    double zu10 = conf.getDouble("team10.upgradeManLoc..z");
    float  wu10 = (float)conf.getDouble("team10.upgradeManLoc..yaw");
    float  pu10 = (float)conf.getDouble("team10.upgradeManLoc..pitch");
    double xs10 = conf.getDouble("team10.shopManLoc..x");
    double ys10 = conf.getDouble("team10.shopManLoc..y");
    double zs10 = conf.getDouble("team10.shopManLoc..z");
    float  ws10 = (float)conf.getDouble("team10.shopManLoc..yaw");
    float  ps10 = (float)conf.getDouble("team10.shopManLoc..pitch");
    double xi10 = conf.getDouble("team10.itemsSpawnLoc..x");
    double yi10 = conf.getDouble("team10.itemsSpawnLoc..y");
    double zi10 = conf.getDouble("team10.itemsSpawnLoc..z");
    float  wi10 = (float)conf.getDouble("team10.itemsSpawnManLoc..yaw");
    float  pi10 = (float)conf.getDouble("team10.itemsSpawnManLoc..pitch");

    double xu11 = conf.getDouble("team11.upgradeManLoc..x");
    double yu11 = conf.getDouble("team11.upgradeManLoc..y");
    double zu11 = conf.getDouble("team11.upgradeManLoc..z");
    float  wu11 = (float)conf.getDouble("team11.upgradeManLoc..yaw");
    float  pu11 = (float)conf.getDouble("team11.upgradeManLoc..pitch");
    double xs11 = conf.getDouble("team11.shopManLoc..x");
    double ys11 = conf.getDouble("team11.shopManLoc..y");
    double zs11 = conf.getDouble("team11.shopManLoc..z");
    float  ws11 = (float)conf.getDouble("team11.shopManLoc..yaw");
    float  ps11 = (float)conf.getDouble("team11.shopManLoc..pitch");
    double xi11 = conf.getDouble("team11.itemsSpawnLoc..x");
    double yi11 = conf.getDouble("team11.itemsSpawnLoc..y");
    double zi11 = conf.getDouble("team11.itemsSpawnLoc..z");
    float  wi11 = (float)conf.getDouble("team11.itemsSpawnManLoc..yaw");
    float  pi11 = (float)conf.getDouble("team11.itemsSpawnManLoc..pitch");

    double xu12 = conf.getDouble("team12.upgradeManLoc..x");
    double yu12 = conf.getDouble("team12.upgradeManLoc..y");
    double zu12 = conf.getDouble("team12.upgradeManLoc..z");
    float  wu12 = (float)conf.getDouble("team12.upgradeManLoc..yaw");
    float  pu12 = (float)conf.getDouble("team12.upgradeManLoc..pitch");
    double xs12 = conf.getDouble("team12.shopManLoc..x");
    double ys12 = conf.getDouble("team12.shopManLoc..y");
    double zs12 = conf.getDouble("team12.shopManLoc..z");
    float  ws12 = (float)conf.getDouble("team12.shopManLoc..yaw");
    float  ps12 = (float)conf.getDouble("team12.shopManLoc..pitch");
    double xi12 = conf.getDouble("team12.itemsSpawnLoc..x");
    double yi12 = conf.getDouble("team12.itemsSpawnLoc..y");
    double zi12 = conf.getDouble("team12.itemsSpawnLoc..z");
    float  wi12 = (float)conf.getDouble("team12.itemsSpawnManLoc..yaw");
    float  pi12 = (float)conf.getDouble("team12.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }
    double xdiamonds3 = conf.getInt("mapData.gems.diamonds3..locX");
    double ydiamonds3 =  conf.getInt("mapData.gems.diamonds3..locY");
    double zdiamonds3 =  conf.getInt("mapData.gems.diamonds3..locZ");
    Location getdiamonds3Location(){
        return new Location(world,xdiamonds3,ydiamonds3,zdiamonds3);
    }
    double xdiamonds4 = conf.getInt("mapData.gems.diamonds4..locX");
    double ydiamonds4 =  conf.getInt("mapData.gems.diamonds4..locY");
    double zdiamonds4 =  conf.getInt("mapData.gems.diamonds4..locZ");
    Location getdiamonds4Location(){
        return new Location(world,xdiamonds4,ydiamonds4,zdiamonds4);
    }
    double xdiamonds5 = conf.getInt("mapData.gems.diamonds5..locX");
    double ydiamonds5 =  conf.getInt("mapData.gems.diamonds5..locY");
    double zdiamonds5 =  conf.getInt("mapData.gems.diamonds5..locZ");
    Location getdiamonds5Location(){
        return new Location(world,xdiamonds5,ydiamonds5,zdiamonds5);
    }
    double xdiamonds6 = conf.getInt("mapData.gems.diamonds6..locX");
    double ydiamonds6 =  conf.getInt("mapData.gems.diamonds6..locY");
    double zdiamonds6 =  conf.getInt("mapData.gems.diamonds6..locZ");
    Location getdiamonds6Location(){
        return new Location(world,xdiamonds6,ydiamonds6,zdiamonds6);
    }
    double xdiamonds7 = conf.getInt("mapData.gems.diamonds7..locX");
    double ydiamonds7 =  conf.getInt("mapData.gems.diamonds7..locY");
    double zdiamonds7 =  conf.getInt("mapData.gems.diamonds7..locZ");
    Location getdiamonds7Location(){
        return new Location(world,xdiamonds7,ydiamonds7,zdiamonds7);
    }
    double xdiamonds8 = conf.getInt("mapData.gems.diamonds8..locX");
    double ydiamonds8 =  conf.getInt("mapData.gems.diamonds8..locY");
    double zdiamonds8 =  conf.getInt("mapData.gems.diamonds8..locZ");
    Location getdiamonds8Location(){
        return new Location(world,xdiamonds8,ydiamonds8,zdiamonds8);
    }
    double xdiamonds9 = conf.getInt("mapData.gems.diamonds9..locX");
    double ydiamonds9 =  conf.getInt("mapData.gems.diamonds9..locY");
    double zdiamonds9 =  conf.getInt("mapData.gems.diamonds9..locZ");
    Location getdiamonds9Location(){
        return new Location(world,xdiamonds9,ydiamonds9,zdiamonds9);
    }
    double xdiamonds10 = conf.getInt("mapData.gems.diamonds10..locX");
    double ydiamonds10 =  conf.getInt("mapData.gems.diamonds10..locY");
    double zdiamonds10 =  conf.getInt("mapData.gems.diamonds10..locZ");
    Location getdiamonds10Location(){
        return new Location(world,xdiamonds10,ydiamonds10,zdiamonds10);
    }
    double xdiamonds11 = conf.getInt("mapData.gems.diamonds11..locX");
    double ydiamonds11 =  conf.getInt("mapData.gems.diamonds11..locY");
    double zdiamonds11 =  conf.getInt("mapData.gems.diamonds11..locZ");
    Location getdiamonds11Location(){
        return new Location(world,xdiamonds11,ydiamonds11,zdiamonds11);
    }
    double xdiamonds12 = conf.getInt("mapData.gems.diamonds12..locX");
    double ydiamonds12 =  conf.getInt("mapData.gems.diamonds12..locY");
    double zdiamonds12 =  conf.getInt("mapData.gems.diamonds12..locZ");
    Location getdiamonds12Location(){
        return new Location(world,xdiamonds12,ydiamonds12,zdiamonds12);
    }

    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    Location getTeam3UpgradeManLocation(){return new Location(world,xu3,yu3,zu3,wu3,pu3);}
    Location getTeam3ShopManLocation(){return new Location(world,xs3,ys3,zs3,ws3,ps3);}
    Location getTeam3ItemSpawnLocation(){return new Location(world,xi3,yi3,zi3,wi3,pi3);}
    Location getTeam4UpgradeManLocation(){return new Location(world,xu4,yu4,zu4,wu4,pu4);}
    Location getTeam4ShopManLocation(){return new Location(world,xs4,ys4,zs4,ws4,ps4);}
    Location getTeam4ItemSpawnLocation(){return new Location(world,xi4,yi4,zi4,wi4,pi4);}
    Location getTeam5UpgradeManLocation(){return new Location(world,xu5,yu5,zu5,wu5,pu5);}
    Location getTeam5ShopManLocation(){return new Location(world,xs5,ys5,zs5,ws5,ps5);}
    Location getTeam5ItemSpawnLocation(){return new Location(world,xi5,yi5,zi5,wi5,pi5);}
    Location getTeam6UpgradeManLocation(){return new Location(world,xu6,yu6,zu6,wu6,pu6);}
    Location getTeam6ShopManLocation(){return new Location(world,xs6,ys6,zs6,ws6,ps6);}
    Location getTeam6ItemSpawnLocation(){return new Location(world,xi6,yi6,zi6,wi6,pi6);}
    Location getTeam7UpgradeManLocation(){return new Location(world,xu7,yu7,zu7,wu7,pu7);}
    Location getTeam7ShopManLocation(){return new Location(world,xs7,ys7,zs7,ws7,ps7);}
    Location getTeam7ItemSpawnLocation(){return new Location(world,xi7,yi7,zi7,wi7,pi7);}
    Location getTeam8UpgradeManLocation(){return new Location(world,xu8,yu8,zu8,wu8,pu8);}
    Location getTeam8ShopManLocation(){return new Location(world,xs8,ys8,zs8,ws8,ps8);}
    Location getTeam8ItemSpawnLocation(){return new Location(world,xi8,yi8,zi8,wi8,pi8);}
    Location getTeam9UpgradeManLocation(){return new Location(world,xu9,yu9,zu9,wu9,pu9);}
    Location getTeam9ShopManLocation(){return new Location(world,xs9,ys9,zs9,ws9,ps9);}
    Location getTeam9ItemSpawnLocation(){return new Location(world,xi9,yi9,zi9,wi9,pi9);}
    Location getTeam10UpgradeManLocation(){return new Location(world,xu10,yu10,zu10,wu10,pu10);}
    Location getTeam10ShopManLocation(){return new Location(world,xs10,ys10,zs10,ws10,ps10);}
    Location getTeam10ItemSpawnLocation(){return new Location(world,xi10,yi10,zi10,wi10,pi10);}
    Location getTeam11UpgradeManLocation(){return new Location(world,xu11,yu11,zu11,wu11,pu11);}
    Location getTeam11ShopManLocation(){return new Location(world,xs11,ys11,zs11,ws11,ps11);}
    Location getTeam11ItemSpawnLocation(){return new Location(world,xi11,yi11,zi11,wi11,pi11);}
    Location getTeam12UpgradeManLocation(){return new Location(world,xu12,yu12,zu12,wu12,pu12);}
    Location getTeam12ShopManLocation(){return new Location(world,xs12,ys12,zs12,ws12,ps12);}
    Location getTeam12ItemSpawnLocation(){return new Location(world,xi12,yi12,zi12,wi12,pi12);}

    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation(),getTeam3ItemSpawnLocation(),getTeam4ItemSpawnLocation(),getTeam5ItemSpawnLocation(),getTeam6ItemSpawnLocation(),getTeam7ItemSpawnLocation(),getTeam8ItemSpawnLocation(),getTeam9ItemSpawnLocation(),getTeam10ItemSpawnLocation(),getTeam11ItemSpawnLocation(),getTeam12ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation(),getTeam3UpgradeManLocation(),getTeam4UpgradeManLocation(),getTeam5UpgradeManLocation(),getTeam6UpgradeManLocation(),getTeam7UpgradeManLocation(),getTeam8UpgradeManLocation(),getTeam9UpgradeManLocation(),getTeam10UpgradeManLocation(),getTeam11UpgradeManLocation(),getTeam12UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation(),getTeam3ShopManLocation(),getTeam4ShopManLocation(),getTeam5ShopManLocation(),getTeam6ShopManLocation(),getTeam7ShopManLocation(),getTeam8ShopManLocation(),getTeam9ShopManLocation(),getTeam10ShopManLocation(),getTeam11ShopManLocation(),getTeam12ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location(),getdiamonds3Location(),getdiamonds4Location(),getdiamonds5Location(),getdiamonds6Location(),getdiamonds7Location(),getdiamonds8Location(),getdiamonds9Location(),getdiamonds10Location(),getdiamonds11Location(),getdiamonds12Location());
    }


    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }
    @Override
    List<UUID> getTeam3Players(){
        return team3Players;
    }
    @Override
    List<UUID> getTeam4Players(){
        return team4Players;
    }
    @Override
    List<UUID> getTeam5Players(){
        return team5Players;
    }
    @Override
    List<UUID> getTeam6Players(){
        return team6Players;
    }
    @Override
    List<UUID> getTeam7Players(){
        return team7Players;
    }

    @Override
    List<UUID> getTeam8Players(){
        return team8Players;
    }
    @Override
    List<UUID> getTeam9Players(){
        return team9Players;
    }
    @Override
    List<UUID> getTeam10Players(){
        return team10Players;
    }

    @Override
    List<UUID> getTeam11Players(){
        return team11Players;
    }
    @Override
    List<UUID> getTeam12Players(){
        return team12Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }

    @Override
    Location getSpawnTeam3() {
        return new Location(world,xT3,yT3,zT3,yawT3,pitchT3);
    }

    @Override
    Location getSpawnTeam4() {
        return new Location(world,xT4,yT4,zT4,yawT4,pitchT4);
    }

    @Override
    Location getSpawnTeam5() {
        return new Location(world,xT5,yT5,zT5,yawT5,pitchT5);
    }
    @Override
    Location getSpawnTeam6() {
        return new Location(world,xT6,yT6,zT6,yawT6,pitchT6);
    }
    @Override
    Location getSpawnTeam7() {
        return new Location(world,xT7,yT7,zT7,yawT7,pitchT7);
    }
    @Override
    Location getSpawnTeam8() {
        return new Location(world,xT8,yT8,zT8,yawT8,pitchT8);
    }
    @Override
    Location getSpawnTeam9() {
        return new Location(world,xT9,yT9,zT9,yawT9,pitchT9);
    }
    @Override
    Location getSpawnTeam10() {
        return new Location(world,xT10,yT10,zT10,yawT10,pitchT10);
    }
    @Override
    Location getSpawnTeam11() {
        return new Location(world,xT11,yT11,zT11,yawT11,pitchT11);
    }
    @Override
    Location getSpawnTeam12() {
        return new Location(world,xT12,yT12,zT12,yawT12,pitchT12);
    }

    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }


    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map21 extends MapData<Map21> {
    String getMapName(){
        return "map21";
    }
    public Map21(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map21.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
        if(team3Players.size()<teamSize){
            team3Players.add(p.getUniqueId());
            pdc.setTeam("team3");
            p.sendMessage(c.g+"You have joined "+team3Name);
            return;
        }
        if(team4Players.size()<teamSize){
            team4Players.add(p.getUniqueId());
            pdc.setTeam("team4");
            p.sendMessage(c.g+"You have joined "+team4Name);
            return;
        }
        if(team5Players.size()<teamSize){
            team5Players.add(p.getUniqueId());
            pdc.setTeam("team5");
            p.sendMessage(c.g+"You have joined "+team5Name);
            return;
        }
        if(team6Players.size()<teamSize){
            team6Players.add(p.getUniqueId());
            pdc.setTeam("team6");
            p.sendMessage(c.g+"You have joined "+team6Name);
            return;
        }
        if(team7Players.size()<teamSize){
            team7Players.add(p.getUniqueId());
            pdc.setTeam("team7");
            p.sendMessage(c.g+"You have joined "+team7Name);
            return;
        }
        if(team8Players.size()<teamSize){
            team8Players.add(p.getUniqueId());
            pdc.setTeam("team8");
            p.sendMessage(c.g+"You have joined "+team8Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids: team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids: team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }
        for(UUID ids: team3Players){
            if(ids.equals(id)){
                team3Players.remove(id);
                return;
            }
        }
        for(UUID ids: team4Players){
            if(ids.equals(id)){
                team4Players.remove(id);
                return;
            }
        }
        for(UUID ids: team5Players){
            if(ids.equals(id)){
                team5Players.remove(id);
                return;
            }
        }
        for(UUID ids: team6Players){
            if(ids.equals(id)){
                team6Players.remove(id);
                return;
            }
        }
        for(UUID ids: team7Players){
            if(ids.equals(id)){
                team7Players.remove(id);
                return;
            }
        }
        for(UUID ids: team8Players){
            if(ids.equals(id)){
                team8Players.remove(id);
                return;
            }
        }

    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;
        if(team3Players.contains(id))return team3Players;
        if(team4Players.contains(id))return team4Players;
        if(team5Players.contains(id))return team5Players;
        if(team6Players.contains(id))return team6Players;
        if(team7Players.contains(id))return team7Players;
        if(team8Players.contains(id))return team8Players;

        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    String team3Name = conf.getString("teams.name..team3Name");
    String team4Name = conf.getString("teams.name..team4Name");
    String team5Name = conf.getString("teams.name..team5Name");
    String team6Name = conf.getString("teams.name..team6Name");
    String team7Name = conf.getString("teams.name..team7Name");
    String team8Name = conf.getString("teams.name..team8Name");

    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();
    static  List<UUID> team3Players = new ArrayList<>();
    static  List<UUID> team4Players = new ArrayList<>();
    static  List<UUID> team5Players = new ArrayList<>();
    static  List<UUID> team6Players = new ArrayList<>();
    static  List<UUID> team7Players = new ArrayList<>();
    static  List<UUID> team8Players = new ArrayList<>();



    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");

    double xT3 =conf.getDouble("team3.spawn..x");
    double yT3 =conf.getDouble("team3.spawn..y");
    double zT3 =conf.getDouble("team3.spawn..z");
    float yawT3 = (float)conf.getDouble("team3.spawn..yaw");
    float pitchT3 = (float)conf.getDouble("team3.spawn..pitch");

    double xT4 = conf.getDouble("team4.spawn..x");
    double yT4 = conf.getDouble("team4.spawn..y");
    double zT4 = conf.getDouble("team4.spawn..z");
    float yawT4 =  (float)conf.getDouble("team4.spawn..yaw");
    float pitchT4 =  (float)conf.getDouble("team4.spawn..pitch");

    double xT5 = conf.getDouble("team5.spawn..x");
    double yT5 = conf.getDouble("team5.spawn..y");
    double zT5 = conf.getDouble("team5.spawn..z");
    float yawT5 = (float) conf.getDouble("team5.spawn..yaw");
    float pitchT5 =  (float)conf.getDouble("team5.spawn..pitch");

    double xT6 = conf.getDouble("team6.spawn..x");
    double yT6 = conf.getDouble("team6.spawn..y");
    double zT6 = conf.getDouble("team6.spawn..z");
    float yawT6 = (float)conf.getDouble("team6.spawn..yaw");
    float pitchT6 = (float)conf.getDouble("team6.spawn..pitch");

    double xT7 = conf.getDouble("team7.spawn..x");
    double yT7 = conf.getDouble("team7.spawn..y");
    double zT7 = conf.getDouble("team7.spawn..z");
    float yawT7 =  (float)conf.getDouble("team7.spawn..yaw");
    float pitchT7 =  (float)conf.getDouble("team7.spawn..pitch");

    double xT8 = conf.getDouble("team8.spawn..x");
    double yT8 = conf.getDouble("team8.spawn..y");
    double zT8 = conf.getDouble("team8.spawn..z");
    float yawT8 = (float)conf.getDouble("team8.spawn..yaw");
    float pitchT8 = (float)conf.getDouble("team8.spawn..pitch");


    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xu3 = conf.getDouble("team3.upgradeManLoc..x");
    double yu3 = conf.getDouble("team3.upgradeManLoc..y");
    double zu3 = conf.getDouble("team3.upgradeManLoc..z");
    float  wu3 = (float)conf.getDouble("team3.upgradeManLoc..yaw");
    float  pu3 = (float)conf.getDouble("team3.upgradeManLoc..pitch");
    double xs3 = conf.getDouble("team3.shopManLoc..x");
    double ys3 = conf.getDouble("team3.shopManLoc..y");
    double zs3 = conf.getDouble("team3.shopManLoc..z");
    float  ws3 = (float)conf.getDouble("team3.shopManLoc..yaw");
    float  ps3 = (float)conf.getDouble("team3.shopManLoc..pitch");
    double xi3 = conf.getDouble("team3.itemsSpawnLoc..x");
    double yi3 = conf.getDouble("team3.itemsSpawnLoc..y");
    double zi3 = conf.getDouble("team3.itemsSpawnLoc..z");
    float  wi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..yaw");
    float  pi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..pitch");

    double xu4 = conf.getDouble("team4.upgradeManLoc..x");
    double yu4 = conf.getDouble("team4.upgradeManLoc..y");
    double zu4 = conf.getDouble("team4.upgradeManLoc..z");
    float  wu4 = (float)conf.getDouble("team4.upgradeManLoc..yaw");
    float  pu4 = (float)conf.getDouble("team4.upgradeManLoc..pitch");
    double xs4 = conf.getDouble("team4.shopManLoc..x");
    double ys4 = conf.getDouble("team4.shopManLoc..y");
    double zs4 = conf.getDouble("team4.shopManLoc..z");
    float  ws4 = (float)conf.getDouble("team4.shopManLoc..yaw");
    float  ps4 = (float)conf.getDouble("team4.shopManLoc..pitch");
    double xi4 = conf.getDouble("team4.itemsSpawnLoc..x");
    double yi4 = conf.getDouble("team4.itemsSpawnLoc..y");
    double zi4 = conf.getDouble("team4.itemsSpawnLoc..z");
    float  wi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..yaw");
    float  pi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..pitch");

    double xu5 = conf.getDouble("team5.upgradeManLoc..x");
    double yu5 = conf.getDouble("team5.upgradeManLoc..y");
    double zu5 = conf.getDouble("team5.upgradeManLoc..z");
    float  wu5 = (float)conf.getDouble("team5.upgradeManLoc..yaw");
    float  pu5 = (float)conf.getDouble("team5.upgradeManLoc..pitch");
    double xs5 = conf.getDouble("team5.shopManLoc..x");
    double ys5 = conf.getDouble("team5.shopManLoc..y");
    double zs5 = conf.getDouble("team5.shopManLoc..z");
    float  ws5 = (float)conf.getDouble("team5.shopManLoc..yaw");
    float  ps5 = (float)conf.getDouble("team5.shopManLoc..pitch");
    double xi5 = conf.getDouble("team5.itemsSpawnLoc..x");
    double yi5 = conf.getDouble("team5.itemsSpawnLoc..y");
    double zi5 = conf.getDouble("team5.itemsSpawnLoc..z");
    float  wi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..yaw");
    float  pi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..pitch");

    double xu6 = conf.getDouble("team6.upgradeManLoc..x");
    double yu6 = conf.getDouble("team6.upgradeManLoc..y");
    double zu6 = conf.getDouble("team6.upgradeManLoc..z");
    float  wu6 = (float)conf.getDouble("team6.upgradeManLoc..yaw");
    float  pu6 = (float)conf.getDouble("team6.upgradeManLoc..pitch");
    double xs6 = conf.getDouble("team6.shopManLoc..x");
    double ys6 = conf.getDouble("team6.shopManLoc..y");
    double zs6 = conf.getDouble("team6.shopManLoc..z");
    float  ws6 = (float)conf.getDouble("team6.shopManLoc..yaw");
    float  ps6 = (float)conf.getDouble("team6.shopManLoc..pitch");
    double xi6 = conf.getDouble("team6.itemsSpawnLoc..x");
    double yi6 = conf.getDouble("team6.itemsSpawnLoc..y");
    double zi6 = conf.getDouble("team6.itemsSpawnLoc..z");
    float  wi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..yaw");
    float  pi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..pitch");

    double xu7 = conf.getDouble("team7.upgradeManLoc..x");
    double yu7 = conf.getDouble("team7.upgradeManLoc..y");
    double zu7 = conf.getDouble("team7.upgradeManLoc..z");
    float  wu7 = (float)conf.getDouble("team7.upgradeManLoc..yaw");
    float  pu7 = (float)conf.getDouble("team7.upgradeManLoc..pitch");
    double xs7 = conf.getDouble("team7.shopManLoc..x");
    double ys7 = conf.getDouble("team7.shopManLoc..y");
    double zs7 = conf.getDouble("team7.shopManLoc..z");
    float  ws7 = (float)conf.getDouble("team7.shopManLoc..yaw");
    float  ps7 = (float)conf.getDouble("team7.shopManLoc..pitch");
    double xi7 = conf.getDouble("team7.itemsSpawnLoc..x");
    double yi7 = conf.getDouble("team7.itemsSpawnLoc..y");
    double zi7 = conf.getDouble("team7.itemsSpawnLoc..z");
    float  wi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..yaw");
    float  pi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..pitch");

    double xu8 = conf.getDouble("team8.upgradeManLoc..x");
    double yu8 = conf.getDouble("team8.upgradeManLoc..y");
    double zu8 = conf.getDouble("team8.upgradeManLoc..z");
    float  wu8 = (float)conf.getDouble("team8.upgradeManLoc..yaw");
    float  pu8 = (float)conf.getDouble("team8.upgradeManLoc..pitch");
    double xs8 = conf.getDouble("team8.shopManLoc..x");
    double ys8 = conf.getDouble("team8.shopManLoc..y");
    double zs8 = conf.getDouble("team8.shopManLoc..z");
    float  ws8 = (float)conf.getDouble("team8.shopManLoc..yaw");
    float  ps8 = (float)conf.getDouble("team8.shopManLoc..pitch");
    double xi8 = conf.getDouble("team8.itemsSpawnLoc..x");
    double yi8 = conf.getDouble("team8.itemsSpawnLoc..y");
    double zi8 = conf.getDouble("team8.itemsSpawnLoc..z");
    float  wi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..yaw");
    float  pi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }
    double xdiamonds3 = conf.getInt("mapData.gems.diamonds3..locX");
    double ydiamonds3 =  conf.getInt("mapData.gems.diamonds3..locY");
    double zdiamonds3 =  conf.getInt("mapData.gems.diamonds3..locZ");
    Location getdiamonds3Location(){
        return new Location(world,xdiamonds3,ydiamonds3,zdiamonds3);
    }
    double xdiamonds4 = conf.getInt("mapData.gems.diamonds4..locX");
    double ydiamonds4 =  conf.getInt("mapData.gems.diamonds4..locY");
    double zdiamonds4 =  conf.getInt("mapData.gems.diamonds4..locZ");
    Location getdiamonds4Location(){
        return new Location(world,xdiamonds4,ydiamonds4,zdiamonds4);
    }
    double xdiamonds5 = conf.getInt("mapData.gems.diamonds5..locX");
    double ydiamonds5 =  conf.getInt("mapData.gems.diamonds5..locY");
    double zdiamonds5 =  conf.getInt("mapData.gems.diamonds5..locZ");
    Location getdiamonds5Location(){
        return new Location(world,xdiamonds5,ydiamonds5,zdiamonds5);
    }
    double xdiamonds6 = conf.getInt("mapData.gems.diamonds6..locX");
    double ydiamonds6 =  conf.getInt("mapData.gems.diamonds6..locY");
    double zdiamonds6 =  conf.getInt("mapData.gems.diamonds6..locZ");
    Location getdiamonds6Location(){
        return new Location(world,xdiamonds6,ydiamonds6,zdiamonds6);
    }
    double xdiamonds7 = conf.getInt("mapData.gems.diamonds7..locX");
    double ydiamonds7 =  conf.getInt("mapData.gems.diamonds7..locY");
    double zdiamonds7 =  conf.getInt("mapData.gems.diamonds7..locZ");
    Location getdiamonds7Location(){
        return new Location(world,xdiamonds7,ydiamonds7,zdiamonds7);
    }
    double xdiamonds8 = conf.getInt("mapData.gems.diamonds8..locX");
    double ydiamonds8 =  conf.getInt("mapData.gems.diamonds8..locY");
    double zdiamonds8 =  conf.getInt("mapData.gems.diamonds8..locZ");
    Location getdiamonds8Location(){
        return new Location(world,xdiamonds8,ydiamonds8,zdiamonds8);
    }



    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    Location getTeam3UpgradeManLocation(){return new Location(world,xu3,yu3,zu3,wu3,pu3);}
    Location getTeam3ShopManLocation(){return new Location(world,xs3,ys3,zs3,ws3,ps3);}
    Location getTeam3ItemSpawnLocation(){return new Location(world,xi3,yi3,zi3,wi3,pi3);}
    Location getTeam4UpgradeManLocation(){return new Location(world,xu4,yu4,zu4,wu4,pu4);}
    Location getTeam4ShopManLocation(){return new Location(world,xs4,ys4,zs4,ws4,ps4);}
    Location getTeam4ItemSpawnLocation(){return new Location(world,xi4,yi4,zi4,wi4,pi4);}
    Location getTeam5UpgradeManLocation(){return new Location(world,xu5,yu5,zu5,wu5,pu5);}
    Location getTeam5ShopManLocation(){return new Location(world,xs5,ys5,zs5,ws5,ps5);}
    Location getTeam5ItemSpawnLocation(){return new Location(world,xi5,yi5,zi5,wi5,pi5);}
    Location getTeam6UpgradeManLocation(){return new Location(world,xu6,yu6,zu6,wu6,pu6);}
    Location getTeam6ShopManLocation(){return new Location(world,xs6,ys6,zs6,ws6,ps6);}
    Location getTeam6ItemSpawnLocation(){return new Location(world,xi6,yi6,zi6,wi6,pi6);}
    Location getTeam7UpgradeManLocation(){return new Location(world,xu7,yu7,zu7,wu7,pu7);}
    Location getTeam7ShopManLocation(){return new Location(world,xs7,ys7,zs7,ws7,ps7);}
    Location getTeam7ItemSpawnLocation(){return new Location(world,xi7,yi7,zi7,wi7,pi7);}
    Location getTeam8UpgradeManLocation(){return new Location(world,xu8,yu8,zu8,wu8,pu8);}
    Location getTeam8ShopManLocation(){return new Location(world,xs8,ys8,zs8,ws8,ps8);}
    Location getTeam8ItemSpawnLocation(){return new Location(world,xi8,yi8,zi8,wi8,pi8);}

    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation(),getTeam3ItemSpawnLocation(),getTeam4ItemSpawnLocation(),getTeam5ItemSpawnLocation(),getTeam6ItemSpawnLocation(),getTeam7ItemSpawnLocation(),getTeam8ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation(),getTeam3UpgradeManLocation(),getTeam4UpgradeManLocation(),getTeam5UpgradeManLocation(),getTeam6UpgradeManLocation(),getTeam7UpgradeManLocation(),getTeam8UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation(),getTeam3ShopManLocation(),getTeam4ShopManLocation(),getTeam5ShopManLocation(),getTeam6ShopManLocation(),getTeam7ShopManLocation(),getTeam8ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location(),getdiamonds3Location(),getdiamonds4Location(),getdiamonds5Location(),getdiamonds6Location(),getdiamonds7Location(),getdiamonds8Location());
    }


    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }
    @Override
    List<UUID> getTeam3Players(){
        return team3Players;
    }
    @Override
    List<UUID> getTeam4Players(){
        return team4Players;
    }
    @Override
    List<UUID> getTeam5Players(){
        return team5Players;
    }
    @Override
    List<UUID> getTeam6Players(){
        return team6Players;
    }
    @Override
    List<UUID> getTeam7Players(){
        return team7Players;
    }

    @Override
    List<UUID> getTeam8Players(){
        return team8Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }

    @Override
    Location getSpawnTeam3() {
        return new Location(world,xT3,yT3,zT3,yawT3,pitchT3);
    }

    @Override
    Location getSpawnTeam4() {
        return new Location(world,xT4,yT4,zT4,yawT4,pitchT4);
    }

    @Override
    Location getSpawnTeam5() {
        return new Location(world,xT5,yT5,zT5,yawT5,pitchT5);
    }
    @Override
    Location getSpawnTeam6() {
        return new Location(world,xT6,yT6,zT6,yawT6,pitchT6);
    }
    @Override
    Location getSpawnTeam7() {
        return new Location(world,xT7,yT7,zT7,yawT7,pitchT7);
    }
    @Override
    Location getSpawnTeam8() {
        return new Location(world,xT8,yT8,zT8,yawT8,pitchT8);
    }


    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }

    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map22 extends MapData<Map22> {
    String getMapName(){
        return "map22";
    }
    public Map22(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map22.yml");
    Config conf = new Config(configManager);

    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
        if(team3Players.size()<teamSize){
            team3Players.add(p.getUniqueId());
            pdc.setTeam("team3");
            p.sendMessage(c.g+"You have joined "+team3Name);
            return;
        }
        if(team4Players.size()<teamSize){
            team4Players.add(p.getUniqueId());
            pdc.setTeam("team4");
            p.sendMessage(c.g+"You have joined "+team4Name);
            return;
        }
        if(team5Players.size()<teamSize){
            team5Players.add(p.getUniqueId());
            pdc.setTeam("team5");
            p.sendMessage(c.g+"You have joined "+team5Name);
            return;
        }
        if(team6Players.size()<teamSize){
            team6Players.add(p.getUniqueId());
            pdc.setTeam("team6");
            p.sendMessage(c.g+"You have joined "+team6Name);
            return;
        }
        if(team7Players.size()<teamSize){
            team7Players.add(p.getUniqueId());
            pdc.setTeam("team7");
            p.sendMessage(c.g+"You have joined "+team7Name);
            return;
        }
        if(team8Players.size()<teamSize){
            team8Players.add(p.getUniqueId());
            pdc.setTeam("team8");
            p.sendMessage(c.g+"You have joined "+team8Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids:team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids:team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }
        for(UUID ids:team3Players){
            if(ids.equals(id)){
                team3Players.remove(id);
                return;
            }
        }
        for(UUID ids:team4Players){
            if(ids.equals(id)){
                team4Players.remove(id);
                return;
            }
        }
        for(UUID ids:team5Players){
            if(ids.equals(id)){
                team5Players.remove(id);
                return;
            }
        }
        for(UUID ids:team6Players){
            if(ids.equals(id)){
                team6Players.remove(id);
                return;
            }
        }
        for(UUID ids:team7Players){
            if(ids.equals(id)){
                team7Players.remove(id);
                return;
            }
        }
        for(UUID ids:team8Players){
            if(ids.equals(id)){
                team8Players.remove(id);
                return;
            }
        }

    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;
        if(team3Players.contains(id))return team3Players;
        if(team4Players.contains(id))return team4Players;
        if(team5Players.contains(id))return team5Players;
        if(team6Players.contains(id))return team6Players;
        if(team7Players.contains(id))return team7Players;
        if(team8Players.contains(id))return team8Players;

        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    String team3Name = conf.getString("teams.name..team3Name");
    String team4Name = conf.getString("teams.name..team4Name");
    String team5Name = conf.getString("teams.name..team5Name");
    String team6Name = conf.getString("teams.name..team6Name");
    String team7Name = conf.getString("teams.name..team7Name");
    String team8Name = conf.getString("teams.name..team8Name");
    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();
    static  List<UUID> team3Players = new ArrayList<>();
    static  List<UUID> team4Players = new ArrayList<>();
    static  List<UUID> team5Players = new ArrayList<>();
    static  List<UUID> team6Players = new ArrayList<>();
    static  List<UUID> team7Players = new ArrayList<>();
    static  List<UUID> team8Players = new ArrayList<>();
    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }
    @Override
    List<UUID> getTeam3Players(){
        return team3Players;
    }
    @Override
    List<UUID> getTeam4Players(){
        return team4Players;
    }
    @Override
    List<UUID> getTeam5Players(){
        return team5Players;
    }
    @Override
    List<UUID> getTeam6Players(){
        return team6Players;
    }
    @Override
    List<UUID> getTeam7Players(){
        return team7Players;
    }
    @Override
    List<UUID> getTeam8Players(){
        return team8Players;
    }


    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");

    double xT3 =conf.getDouble("team3.spawn..x");
    double yT3 =conf.getDouble("team3.spawn..y");
    double zT3 =conf.getDouble("team3.spawn..z");
    float yawT3 = (float)conf.getDouble("team3.spawn..yaw");
    float pitchT3 = (float)conf.getDouble("team3.spawn..pitch");

    double xT4 = conf.getDouble("team4.spawn..x");
    double yT4 = conf.getDouble("team4.spawn..y");
    double zT4 = conf.getDouble("team4.spawn..z");
    float yawT4 =  (float)conf.getDouble("team4.spawn..yaw");
    float pitchT4 =  (float)conf.getDouble("team4.spawn..pitch");

    double xT5 = conf.getDouble("team5.spawn..x");
    double yT5 = conf.getDouble("team5.spawn..y");
    double zT5 = conf.getDouble("team5.spawn..z");
    float yawT5 = (float) conf.getDouble("team5.spawn..yaw");
    float pitchT5 =  (float)conf.getDouble("team5.spawn..pitch");

    double xT6 = conf.getDouble("team6.spawn..x");
    double yT6 = conf.getDouble("team6.spawn..y");
    double zT6 = conf.getDouble("team6.spawn..z");
    float yawT6 = (float)conf.getDouble("team6.spawn..yaw");
    float pitchT6 = (float)conf.getDouble("team6.spawn..pitch");

    double xT7 = conf.getDouble("team7.spawn..x");
    double yT7 = conf.getDouble("team7.spawn..y");
    double zT7 = conf.getDouble("team7.spawn..z");
    float yawT7 =  (float)conf.getDouble("team7.spawn..yaw");
    float pitchT7 =  (float)conf.getDouble("team7.spawn..pitch");

    double xT8 = conf.getDouble("team8.spawn..x");
    double yT8 = conf.getDouble("team8.spawn..y");
    double zT8 = conf.getDouble("team8.spawn..z");
    float yawT8 = (float)conf.getDouble("team8.spawn..yaw");
    float pitchT8 = (float)conf.getDouble("team8.spawn..pitch");


    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xu3 = conf.getDouble("team3.upgradeManLoc..x");
    double yu3 = conf.getDouble("team3.upgradeManLoc..y");
    double zu3 = conf.getDouble("team3.upgradeManLoc..z");
    float  wu3 = (float)conf.getDouble("team3.upgradeManLoc..yaw");
    float  pu3 = (float)conf.getDouble("team3.upgradeManLoc..pitch");
    double xs3 = conf.getDouble("team3.shopManLoc..x");
    double ys3 = conf.getDouble("team3.shopManLoc..y");
    double zs3 = conf.getDouble("team3.shopManLoc..z");
    float  ws3 = (float)conf.getDouble("team3.shopManLoc..yaw");
    float  ps3 = (float)conf.getDouble("team3.shopManLoc..pitch");
    double xi3 = conf.getDouble("team3.itemsSpawnLoc..x");
    double yi3 = conf.getDouble("team3.itemsSpawnLoc..y");
    double zi3 = conf.getDouble("team3.itemsSpawnLoc..z");
    float  wi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..yaw");
    float  pi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..pitch");

    double xu4 = conf.getDouble("team4.upgradeManLoc..x");
    double yu4 = conf.getDouble("team4.upgradeManLoc..y");
    double zu4 = conf.getDouble("team4.upgradeManLoc..z");
    float  wu4 = (float)conf.getDouble("team4.upgradeManLoc..yaw");
    float  pu4 = (float)conf.getDouble("team4.upgradeManLoc..pitch");
    double xs4 = conf.getDouble("team4.shopManLoc..x");
    double ys4 = conf.getDouble("team4.shopManLoc..y");
    double zs4 = conf.getDouble("team4.shopManLoc..z");
    float  ws4 = (float)conf.getDouble("team4.shopManLoc..yaw");
    float  ps4 = (float)conf.getDouble("team4.shopManLoc..pitch");
    double xi4 = conf.getDouble("team4.itemsSpawnLoc..x");
    double yi4 = conf.getDouble("team4.itemsSpawnLoc..y");
    double zi4 = conf.getDouble("team4.itemsSpawnLoc..z");
    float  wi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..yaw");
    float  pi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..pitch");

    double xu5 = conf.getDouble("team5.upgradeManLoc..x");
    double yu5 = conf.getDouble("team5.upgradeManLoc..y");
    double zu5 = conf.getDouble("team5.upgradeManLoc..z");
    float  wu5 = (float)conf.getDouble("team5.upgradeManLoc..yaw");
    float  pu5 = (float)conf.getDouble("team5.upgradeManLoc..pitch");
    double xs5 = conf.getDouble("team5.shopManLoc..x");
    double ys5 = conf.getDouble("team5.shopManLoc..y");
    double zs5 = conf.getDouble("team5.shopManLoc..z");
    float  ws5 = (float)conf.getDouble("team5.shopManLoc..yaw");
    float  ps5 = (float)conf.getDouble("team5.shopManLoc..pitch");
    double xi5 = conf.getDouble("team5.itemsSpawnLoc..x");
    double yi5 = conf.getDouble("team5.itemsSpawnLoc..y");
    double zi5 = conf.getDouble("team5.itemsSpawnLoc..z");
    float  wi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..yaw");
    float  pi5 = (float)conf.getDouble("team5.itemsSpawnManLoc..pitch");

    double xu6 = conf.getDouble("team6.upgradeManLoc..x");
    double yu6 = conf.getDouble("team6.upgradeManLoc..y");
    double zu6 = conf.getDouble("team6.upgradeManLoc..z");
    float  wu6 = (float)conf.getDouble("team6.upgradeManLoc..yaw");
    float  pu6 = (float)conf.getDouble("team6.upgradeManLoc..pitch");
    double xs6 = conf.getDouble("team6.shopManLoc..x");
    double ys6 = conf.getDouble("team6.shopManLoc..y");
    double zs6 = conf.getDouble("team6.shopManLoc..z");
    float  ws6 = (float)conf.getDouble("team6.shopManLoc..yaw");
    float  ps6 = (float)conf.getDouble("team6.shopManLoc..pitch");
    double xi6 = conf.getDouble("team6.itemsSpawnLoc..x");
    double yi6 = conf.getDouble("team6.itemsSpawnLoc..y");
    double zi6 = conf.getDouble("team6.itemsSpawnLoc..z");
    float  wi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..yaw");
    float  pi6 = (float)conf.getDouble("team6.itemsSpawnManLoc..pitch");

    double xu7 = conf.getDouble("team7.upgradeManLoc..x");
    double yu7 = conf.getDouble("team7.upgradeManLoc..y");
    double zu7 = conf.getDouble("team7.upgradeManLoc..z");
    float  wu7 = (float)conf.getDouble("team7.upgradeManLoc..yaw");
    float  pu7 = (float)conf.getDouble("team7.upgradeManLoc..pitch");
    double xs7 = conf.getDouble("team7.shopManLoc..x");
    double ys7 = conf.getDouble("team7.shopManLoc..y");
    double zs7 = conf.getDouble("team7.shopManLoc..z");
    float  ws7 = (float)conf.getDouble("team7.shopManLoc..yaw");
    float  ps7 = (float)conf.getDouble("team7.shopManLoc..pitch");
    double xi7 = conf.getDouble("team7.itemsSpawnLoc..x");
    double yi7 = conf.getDouble("team7.itemsSpawnLoc..y");
    double zi7 = conf.getDouble("team7.itemsSpawnLoc..z");
    float  wi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..yaw");
    float  pi7 = (float)conf.getDouble("team7.itemsSpawnManLoc..pitch");

    double xu8 = conf.getDouble("team8.upgradeManLoc..x");
    double yu8 = conf.getDouble("team8.upgradeManLoc..y");
    double zu8 = conf.getDouble("team8.upgradeManLoc..z");
    float  wu8 = (float)conf.getDouble("team8.upgradeManLoc..yaw");
    float  pu8 = (float)conf.getDouble("team8.upgradeManLoc..pitch");
    double xs8 = conf.getDouble("team8.shopManLoc..x");
    double ys8 = conf.getDouble("team8.shopManLoc..y");
    double zs8 = conf.getDouble("team8.shopManLoc..z");
    float  ws8 = (float)conf.getDouble("team8.shopManLoc..yaw");
    float  ps8 = (float)conf.getDouble("team8.shopManLoc..pitch");
    double xi8 = conf.getDouble("team8.itemsSpawnLoc..x");
    double yi8 = conf.getDouble("team8.itemsSpawnLoc..y");
    double zi8 = conf.getDouble("team8.itemsSpawnLoc..z");
    float  wi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..yaw");
    float  pi8 = (float)conf.getDouble("team8.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }
    double xdiamonds3 = conf.getInt("mapData.gems.diamonds3..locX");
    double ydiamonds3 =  conf.getInt("mapData.gems.diamonds3..locY");
    double zdiamonds3 =  conf.getInt("mapData.gems.diamonds3..locZ");
    Location getdiamonds3Location(){
        return new Location(world,xdiamonds3,ydiamonds3,zdiamonds3);
    }
    double xdiamonds4 = conf.getInt("mapData.gems.diamonds4..locX");
    double ydiamonds4 =  conf.getInt("mapData.gems.diamonds4..locY");
    double zdiamonds4 =  conf.getInt("mapData.gems.diamonds4..locZ");
    Location getdiamonds4Location(){
        return new Location(world,xdiamonds4,ydiamonds4,zdiamonds4);
    }
    double xdiamonds5 = conf.getInt("mapData.gems.diamonds5..locX");
    double ydiamonds5 =  conf.getInt("mapData.gems.diamonds5..locY");
    double zdiamonds5 =  conf.getInt("mapData.gems.diamonds5..locZ");
    Location getdiamonds5Location(){
        return new Location(world,xdiamonds5,ydiamonds5,zdiamonds5);
    }
    double xdiamonds6 = conf.getInt("mapData.gems.diamonds6..locX");
    double ydiamonds6 =  conf.getInt("mapData.gems.diamonds6..locY");
    double zdiamonds6 =  conf.getInt("mapData.gems.diamonds6..locZ");
    Location getdiamonds6Location(){
        return new Location(world,xdiamonds6,ydiamonds6,zdiamonds6);
    }
    double xdiamonds7 = conf.getInt("mapData.gems.diamonds7..locX");
    double ydiamonds7 =  conf.getInt("mapData.gems.diamonds7..locY");
    double zdiamonds7 =  conf.getInt("mapData.gems.diamonds7..locZ");
    Location getdiamonds7Location(){
        return new Location(world,xdiamonds7,ydiamonds7,zdiamonds7);
    }
    double xdiamonds8 = conf.getInt("mapData.gems.diamonds8..locX");
    double ydiamonds8 =  conf.getInt("mapData.gems.diamonds8..locY");
    double zdiamonds8 =  conf.getInt("mapData.gems.diamonds8..locZ");
    Location getdiamonds8Location(){
        return new Location(world,xdiamonds8,ydiamonds8,zdiamonds8);
    }


    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    Location getTeam3UpgradeManLocation(){return new Location(world,xu3,yu3,zu3,wu3,pu3);}
    Location getTeam3ShopManLocation(){return new Location(world,xs3,ys3,zs3,ws3,ps3);}
    Location getTeam3ItemSpawnLocation(){return new Location(world,xi3,yi3,zi3,wi3,pi3);}
    Location getTeam4UpgradeManLocation(){return new Location(world,xu4,yu4,zu4,wu4,pu4);}
    Location getTeam4ShopManLocation(){return new Location(world,xs4,ys4,zs4,ws4,ps4);}
    Location getTeam4ItemSpawnLocation(){return new Location(world,xi4,yi4,zi4,wi4,pi4);}
    Location getTeam5UpgradeManLocation(){return new Location(world,xu5,yu5,zu5,wu5,pu5);}
    Location getTeam5ShopManLocation(){return new Location(world,xs5,ys5,zs5,ws5,ps5);}
    Location getTeam5ItemSpawnLocation(){return new Location(world,xi5,yi5,zi5,wi5,pi5);}
    Location getTeam6UpgradeManLocation(){return new Location(world,xu6,yu6,zu6,wu6,pu6);}
    Location getTeam6ShopManLocation(){return new Location(world,xs6,ys6,zs6,ws6,ps6);}
    Location getTeam6ItemSpawnLocation(){return new Location(world,xi6,yi6,zi6,wi6,pi6);}
    Location getTeam7UpgradeManLocation(){return new Location(world,xu7,yu7,zu7,wu7,pu7);}
    Location getTeam7ShopManLocation(){return new Location(world,xs7,ys7,zs7,ws7,ps7);}
    Location getTeam7ItemSpawnLocation(){return new Location(world,xi7,yi7,zi7,wi7,pi7);}
    Location getTeam8UpgradeManLocation(){return new Location(world,xu8,yu8,zu8,wu8,pu8);}
    Location getTeam8ShopManLocation(){return new Location(world,xs8,ys8,zs8,ws8,ps8);}
    Location getTeam8ItemSpawnLocation(){return new Location(world,xi8,yi8,zi8,wi8,pi8);}


    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation(),getTeam3ItemSpawnLocation(),getTeam4ItemSpawnLocation(),getTeam5ItemSpawnLocation(),getTeam6ItemSpawnLocation(),getTeam7ItemSpawnLocation(),getTeam8ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation(),getTeam3UpgradeManLocation(),getTeam4UpgradeManLocation(),getTeam5UpgradeManLocation(),getTeam6UpgradeManLocation(),getTeam7UpgradeManLocation(),getTeam8UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation(),getTeam3ShopManLocation(),getTeam4ShopManLocation(),getTeam5ShopManLocation(),getTeam6ShopManLocation(),getTeam7ShopManLocation(),getTeam8ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location(),getdiamonds3Location(),getdiamonds4Location(),getdiamonds5Location(),getdiamonds6Location(),getdiamonds7Location(),getdiamonds8Location());
    }

    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }

    @Override
    Location getSpawnTeam3() {
        return new Location(world,xT3,yT3,zT3,yawT3,pitchT3);
    }

    @Override
    Location getSpawnTeam4() {
        return new Location(world,xT4,yT4,zT4,yawT4,pitchT4);
    }

    @Override
    Location getSpawnTeam5() {
        return new Location(world,xT5,yT5,zT5,yawT5,pitchT5);
    }
    @Override
    Location getSpawnTeam6() {
        return new Location(world,xT6,yT6,zT6,yawT6,pitchT6);
    }
    @Override
    Location getSpawnTeam7() {
        return new Location(world,xT7,yT7,zT7,yawT7,pitchT7);
    }
    @Override
    Location getSpawnTeam8() {
        return new Location(world,xT8,yT8,zT8,yawT8,pitchT8);
    }


    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }


    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map31 extends MapData<Map31> {
    String getMapName(){
        return "map31";
    }
    public Map31(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map31.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
        if(team3Players.size()<teamSize){
            team3Players.add(p.getUniqueId());
            pdc.setTeam("team3");
            p.sendMessage(c.g+"You have joined "+team3Name);
            return;
        }
        if(team4Players.size()<teamSize){
            team4Players.add(p.getUniqueId());
            pdc.setTeam("team4");
            p.sendMessage(c.g+"You have joined "+team4Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids:team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids:team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }
        for(UUID ids:team3Players){
            if(ids.equals(id)){
                team3Players.remove(id);
                return;
            }
        }
        for(UUID ids:team4Players){
            if(ids.equals(id)){
                team4Players.remove(id);
                return;
            }
        }


    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;
        if(team3Players.contains(id))return team3Players;
        if(team4Players.contains(id))return team4Players;


        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    String team3Name = conf.getString("teams.name..team3Name");
    String team4Name = conf.getString("teams.name..team4Name");

    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();
    static  List<UUID> team3Players = new ArrayList<>();
    static  List<UUID> team4Players = new ArrayList<>();


    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");

    double xT3 =conf.getDouble("team3.spawn..x");
    double yT3 =conf.getDouble("team3.spawn..y");
    double zT3 =conf.getDouble("team3.spawn..z");
    float yawT3 = (float)conf.getDouble("team3.spawn..yaw");
    float pitchT3 = (float)conf.getDouble("team3.spawn..pitch");

    double xT4 = conf.getDouble("team4.spawn..x");
    double yT4 = conf.getDouble("team4.spawn..y");
    double zT4 = conf.getDouble("team4.spawn..z");
    float yawT4 =  (float)conf.getDouble("team4.spawn..yaw");
    float pitchT4 =  (float)conf.getDouble("team4.spawn..pitch");



    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xu3 = conf.getDouble("team3.upgradeManLoc..x");
    double yu3 = conf.getDouble("team3.upgradeManLoc..y");
    double zu3 = conf.getDouble("team3.upgradeManLoc..z");
    float  wu3 = (float)conf.getDouble("team3.upgradeManLoc..yaw");
    float  pu3 = (float)conf.getDouble("team3.upgradeManLoc..pitch");
    double xs3 = conf.getDouble("team3.shopManLoc..x");
    double ys3 = conf.getDouble("team3.shopManLoc..y");
    double zs3 = conf.getDouble("team3.shopManLoc..z");
    float  ws3 = (float)conf.getDouble("team3.shopManLoc..yaw");
    float  ps3 = (float)conf.getDouble("team3.shopManLoc..pitch");
    double xi3 = conf.getDouble("team3.itemsSpawnLoc..x");
    double yi3 = conf.getDouble("team3.itemsSpawnLoc..y");
    double zi3 = conf.getDouble("team3.itemsSpawnLoc..z");
    float  wi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..yaw");
    float  pi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..pitch");

    double xu4 = conf.getDouble("team4.upgradeManLoc..x");
    double yu4 = conf.getDouble("team4.upgradeManLoc..y");
    double zu4 = conf.getDouble("team4.upgradeManLoc..z");
    float  wu4 = (float)conf.getDouble("team4.upgradeManLoc..yaw");
    float  pu4 = (float)conf.getDouble("team4.upgradeManLoc..pitch");
    double xs4 = conf.getDouble("team4.shopManLoc..x");
    double ys4 = conf.getDouble("team4.shopManLoc..y");
    double zs4 = conf.getDouble("team4.shopManLoc..z");
    float  ws4 = (float)conf.getDouble("team4.shopManLoc..yaw");
    float  ps4 = (float)conf.getDouble("team4.shopManLoc..pitch");
    double xi4 = conf.getDouble("team4.itemsSpawnLoc..x");
    double yi4 = conf.getDouble("team4.itemsSpawnLoc..y");
    double zi4 = conf.getDouble("team4.itemsSpawnLoc..z");
    float  wi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..yaw");
    float  pi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }
    double xdiamonds3 = conf.getInt("mapData.gems.diamonds3..locX");
    double ydiamonds3 =  conf.getInt("mapData.gems.diamonds3..locY");
    double zdiamonds3 =  conf.getInt("mapData.gems.diamonds3..locZ");
    Location getdiamonds3Location(){
        return new Location(world,xdiamonds3,ydiamonds3,zdiamonds3);
    }
    double xdiamonds4 = conf.getInt("mapData.gems.diamonds4..locX");
    double ydiamonds4 =  conf.getInt("mapData.gems.diamonds4..locY");
    double zdiamonds4 =  conf.getInt("mapData.gems.diamonds4..locZ");
    Location getdiamonds4Location(){
        return new Location(world,xdiamonds4,ydiamonds4,zdiamonds4);
    }



    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    Location getTeam3UpgradeManLocation(){return new Location(world,xu3,yu3,zu3,wu3,pu3);}
    Location getTeam3ShopManLocation(){return new Location(world,xs3,ys3,zs3,ws3,ps3);}
    Location getTeam3ItemSpawnLocation(){return new Location(world,xi3,yi3,zi3,wi3,pi3);}
    Location getTeam4UpgradeManLocation(){return new Location(world,xu4,yu4,zu4,wu4,pu4);}
    Location getTeam4ShopManLocation(){return new Location(world,xs4,ys4,zs4,ws4,ps4);}
    Location getTeam4ItemSpawnLocation(){return new Location(world,xi4,yi4,zi4,wi4,pi4);}

    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation(),getTeam3ItemSpawnLocation(),getTeam4ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation(),getTeam3UpgradeManLocation(),getTeam4UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation(),getTeam3ShopManLocation(),getTeam4ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location(),getdiamonds3Location(),getdiamonds4Location());
    }


    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }
    @Override
    List<UUID> getTeam3Players(){
        return team3Players;
    }
    @Override
    List<UUID> getTeam4Players(){
        return team4Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }

    @Override
    Location getSpawnTeam3() {
        return new Location(world,xT3,yT3,zT3,yawT3,pitchT3);
    }

    @Override
    Location getSpawnTeam4() {
        return new Location(world,xT4,yT4,zT4,yawT4,pitchT4);
    }

    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }


    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map32 extends MapData<Map32> {
    String getMapName(){
        return "map32";
    }
    public Map32(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map32.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
        if(team3Players.size()<teamSize){
            team3Players.add(p.getUniqueId());
            pdc.setTeam("team3");
            p.sendMessage(c.g+"You have joined "+team3Name);
            return;
        }
        if(team4Players.size()<teamSize){
            team4Players.add(p.getUniqueId());
            pdc.setTeam("team4");
            p.sendMessage(c.g+"You have joined "+team4Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids:team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids:team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }
        for(UUID ids:team3Players){
            if(ids.equals(id)){
                team3Players.remove(id);
                return;
            }
        }
        for(UUID ids:team4Players){
            if(ids.equals(id)){
                team4Players.remove(id);
                return;
            }
        }


    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;
        if(team3Players.contains(id))return team3Players;
        if(team4Players.contains(id))return team4Players;


        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    String team3Name = conf.getString("teams.name..team3Name");
    String team4Name = conf.getString("teams.name..team4Name");
    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();
    static  List<UUID> team3Players = new ArrayList<>();
    static  List<UUID> team4Players = new ArrayList<>();

    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");

    double xT3 =conf.getDouble("team3.spawn..x");
    double yT3 =conf.getDouble("team3.spawn..y");
    double zT3 =conf.getDouble("team3.spawn..z");
    float yawT3 = (float)conf.getDouble("team3.spawn..yaw");
    float pitchT3 = (float)conf.getDouble("team3.spawn..pitch");

    double xT4 = conf.getDouble("team4.spawn..x");
    double yT4 = conf.getDouble("team4.spawn..y");
    double zT4 = conf.getDouble("team4.spawn..z");
    float yawT4 =  (float)conf.getDouble("team4.spawn..yaw");
    float pitchT4 =  (float)conf.getDouble("team4.spawn..pitch");



    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xu3 = conf.getDouble("team3.upgradeManLoc..x");
    double yu3 = conf.getDouble("team3.upgradeManLoc..y");
    double zu3 = conf.getDouble("team3.upgradeManLoc..z");
    float  wu3 = (float)conf.getDouble("team3.upgradeManLoc..yaw");
    float  pu3 = (float)conf.getDouble("team3.upgradeManLoc..pitch");
    double xs3 = conf.getDouble("team3.shopManLoc..x");
    double ys3 = conf.getDouble("team3.shopManLoc..y");
    double zs3 = conf.getDouble("team3.shopManLoc..z");
    float  ws3 = (float)conf.getDouble("team3.shopManLoc..yaw");
    float  ps3 = (float)conf.getDouble("team3.shopManLoc..pitch");
    double xi3 = conf.getDouble("team3.itemsSpawnLoc..x");
    double yi3 = conf.getDouble("team3.itemsSpawnLoc..y");
    double zi3 = conf.getDouble("team3.itemsSpawnLoc..z");
    float  wi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..yaw");
    float  pi3 = (float)conf.getDouble("team3.itemsSpawnManLoc..pitch");

    double xu4 = conf.getDouble("team4.upgradeManLoc..x");
    double yu4 = conf.getDouble("team4.upgradeManLoc..y");
    double zu4 = conf.getDouble("team4.upgradeManLoc..z");
    float  wu4 = (float)conf.getDouble("team4.upgradeManLoc..yaw");
    float  pu4 = (float)conf.getDouble("team4.upgradeManLoc..pitch");
    double xs4 = conf.getDouble("team4.shopManLoc..x");
    double ys4 = conf.getDouble("team4.shopManLoc..y");
    double zs4 = conf.getDouble("team4.shopManLoc..z");
    float  ws4 = (float)conf.getDouble("team4.shopManLoc..yaw");
    float  ps4 = (float)conf.getDouble("team4.shopManLoc..pitch");
    double xi4 = conf.getDouble("team4.itemsSpawnLoc..x");
    double yi4 = conf.getDouble("team4.itemsSpawnLoc..y");
    double zi4 = conf.getDouble("team4.itemsSpawnLoc..z");
    float  wi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..yaw");
    float  pi4 = (float)conf.getDouble("team4.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }
    double xdiamonds3 = conf.getInt("mapData.gems.diamonds3..locX");
    double ydiamonds3 =  conf.getInt("mapData.gems.diamonds3..locY");
    double zdiamonds3 =  conf.getInt("mapData.gems.diamonds3..locZ");
    Location getdiamonds3Location(){
        return new Location(world,xdiamonds3,ydiamonds3,zdiamonds3);
    }
    double xdiamonds4 = conf.getInt("mapData.gems.diamonds4..locX");
    double ydiamonds4 =  conf.getInt("mapData.gems.diamonds4..locY");
    double zdiamonds4 =  conf.getInt("mapData.gems.diamonds4..locZ");
    Location getdiamonds4Location(){
        return new Location(world,xdiamonds4,ydiamonds4,zdiamonds4);
    }



    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    Location getTeam3UpgradeManLocation(){return new Location(world,xu3,yu3,zu3,wu3,pu3);}
    Location getTeam3ShopManLocation(){return new Location(world,xs3,ys3,zs3,ws3,ps3);}
    Location getTeam3ItemSpawnLocation(){return new Location(world,xi3,yi3,zi3,wi3,pi3);}
    Location getTeam4UpgradeManLocation(){return new Location(world,xu4,yu4,zu4,wu4,pu4);}
    Location getTeam4ShopManLocation(){return new Location(world,xs4,ys4,zs4,ws4,ps4);}
    Location getTeam4ItemSpawnLocation(){return new Location(world,xi4,yi4,zi4,wi4,pi4);}



    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation(),getTeam3ItemSpawnLocation(),getTeam4ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation(),getTeam3UpgradeManLocation(),getTeam4UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation(),getTeam3ShopManLocation(),getTeam4ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location(),getdiamonds3Location(),getdiamonds4Location());
    }

    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }
    @Override
    List<UUID> getTeam3Players(){
        return team3Players;
    }
    @Override
    List<UUID> getTeam4Players(){
        return team4Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }

    @Override
    Location getSpawnTeam3() {
        return new Location(world,xT3,yT3,zT3,yawT3,pitchT3);
    }

    @Override
    Location getSpawnTeam4() {
        return new Location(world,xT4,yT4,zT4,yawT4,pitchT4);
    }

    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }


    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map41 extends MapData<Map41> {
    String getMapName(){
        return "map41";
    }
    public Map41(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map41.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids:team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids:team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }



    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;



        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");

    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();

    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");





    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }




    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}


    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location());
    }

    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }


    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }


    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map42 extends MapData<Map42> {
    String getMapName(){
        return "map42";
    }
    public Map42(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map42.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids:team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids:team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }



    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;



        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();

    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");





    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");


    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }


    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location());
    }



    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }


    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }



    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map51 extends MapData<Map51> {
    String getMapName(){
        return "map51";
    }

    public Map51(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map51.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids:team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids:team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }



    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;



        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();


    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");





    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }



    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}
    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location());
    }



    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }


    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }


    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}
class Map52 extends MapData<Map52> {
    String getMapName(){
        return "map52";
    }

    public Map52(World world)
    {
        this.world = world;
    }
    ConfigManager configManager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map52.yml");
    Config conf = new Config(configManager);
    static  List<UUID> playersList = new ArrayList<>();
    @Override
    int getAmountOfPlayers(){
        return playersList.size();
    }
    @Override
    void addPlayer(Player p){
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        Colors c = new Colors();
        playersList.add(p.getUniqueId());
        if(team1Players.size()<teamSize){
            team1Players.add(p.getUniqueId());
            pdc.setTeam("team1");
            p.sendMessage(c.g+"You have joined "+team1Name);
            return;
        }
        if(team2Players.size()<teamSize){
            team2Players.add(p.getUniqueId());
            pdc.setTeam("team2");
            p.sendMessage(c.g+"You have joined "+team2Name);
            return;
        }
    }

    @Override
    void removePlayer(Player p) {
        UUID id = p.getUniqueId();
        if (playersList.contains(id)){
            playersList.remove(id);
        }
        for(UUID ids:team1Players){
            if(ids.equals(id)){
                team1Players.remove(id);
                return;
            }
        }
        for(UUID ids:team2Players){
            if(ids.equals(id)){
                team2Players.remove(id);
                return;
            }
        }



    }

    @Override
    List<UUID> playerTeamList(Player p) {
        UUID id = p.getUniqueId();
        if(team1Players.contains(id))return team1Players;
        if(team2Players.contains(id))return team2Players;



        return null;
    }

    String team1Name = conf.getString("teams.name..team1Name");
    String team2Name = conf.getString("teams.name..team2Name");
    static  List<UUID> team1Players = new ArrayList<>();
    static  List<UUID> team2Players = new ArrayList<>();

    World world;
    double xMain = conf.getDouble("spawn..x");
    double yMain = conf.getDouble("spawn..y");
    double zMain = conf.getDouble("spawn..z");
    float yawMain = (float)conf.getDouble("spawn..yaw");
    float pitchMain = (float)conf.getDouble("spawn..pitch");


    double xT1 =    conf.getDouble("team1.spawn..x");
    double yT1 =    conf.getDouble("team1.spawn..y");
    double zT1 =    conf.getDouble("team1.spawn..z");
    float yawT1 =   (float)conf.getDouble("team1.spawn..yaw");
    float pitchT1 = (float)conf.getDouble("team1.spawn..pitch");

    double xT2 = conf.getDouble("team2.spawn..x");
    double yT2 = conf.getDouble("team2.spawn..y");
    double zT2 = conf.getDouble("team2.spawn..z");
    float yawT2 =  (float)conf.getDouble("team2.spawn..yaw");
    float pitchT2 =  (float)conf.getDouble("team2.spawn..pitch");





    int timeBreakingBed = 20*conf.getInt("mapData.times..timeAfterBedsBreakSeconds");
    int timeFinalFight = 20*conf.getInt("mapData.times..timeAfterFinalFightStarts");
    int timeSpawnGems = 20*conf.getInt("mapData.times..timeEveryXSecondsGemsSpawns");
    int amountGems = conf.getInt("mapData.gems..amountOfGems");
    int timeSpawnGold = 20*conf.getInt("mapData.times..timeEveryXSecondsGoldSpawns");
    int amountGold = conf.getInt("mapData.gems..amountOfGold");
    int timeSpawnSilver = 20*conf.getInt("mapData.times..timeEveryXSecondsIronSpawns");
    int amountSilver = conf.getInt("mapData.gems..amountOfIron");
    int teamSize = conf.getInt("mapData.team..teamSize");


    double xu1 = conf.getDouble("team1.upgradeManLoc..x");
    double yu1 = conf.getDouble("team1.upgradeManLoc..y");
    double zu1 = conf.getDouble("team1.upgradeManLoc..z");
    float  wu1 = (float)conf.getDouble("team1.upgradeManLoc..yaw");
    float  pu1 = (float)conf.getDouble("team1.upgradeManLoc..pitch");
    double xs1 = conf.getDouble("team1.shopManLoc..x");
    double ys1 = conf.getDouble("team1.shopManLoc..y");
    double zs1 = conf.getDouble("team1.shopManLoc..z");
    float  ws1 = (float)conf.getDouble("team1.shopManLoc..yaw");
    float  ps1 = (float)conf.getDouble("team1.shopManLoc..pitch");
    double xi1 = conf.getDouble("team1.itemsSpawnLoc..x");
    double yi1 = conf.getDouble("team1.itemsSpawnLoc..y");
    double zi1 = conf.getDouble("team1.itemsSpawnLoc..z");
    float  wi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..yaw");
    float  pi1 = (float)conf.getDouble("team1.itemsSpawnManLoc..pitch");

    double xu2 = conf.getDouble("team2.upgradeManLoc..x");
    double yu2 = conf.getDouble("team2.upgradeManLoc..y");
    double zu2 = conf.getDouble("team2.upgradeManLoc..z");
    float  wu2 = (float)conf.getDouble("team2.upgradeManLoc..yaw");
    float  pu2 = (float)conf.getDouble("team2.upgradeManLoc..pitch");
    double xs2 = conf.getDouble("team2.shopManLoc..x");
    double ys2 = conf.getDouble("team2.shopManLoc..y");
    double zs2 = conf.getDouble("team2.shopManLoc..z");
    float  ws2 = (float)conf.getDouble("team2.shopManLoc..yaw");
    float  ps2 = (float)conf.getDouble("team2.shopManLoc..pitch");
    double xi2 = conf.getDouble("team2.itemsSpawnLoc..x");
    double yi2 = conf.getDouble("team2.itemsSpawnLoc..y");
    double zi2 = conf.getDouble("team2.itemsSpawnLoc..z");
    float  wi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..yaw");
    float  pi2 = (float)conf.getDouble("team2.itemsSpawnManLoc..pitch");

    double xlapis =  conf.getInt("mapData.gems.lapis..locX");
    double ylapis =  conf.getInt("mapData.gems.lapis..locY");
    double zlapis =  conf.getInt("mapData.gems.lapis..locZ");
    Location getlapisLocation(){
        return new Location(world,xlapis,ylapis,zlapis);
    }

    double xemerald =  conf.getInt("mapData.gems.emerald..locX");
    double yemerald =  conf.getInt("mapData.gems.emerald..locY");
    double zemerald =  conf.getInt("mapData.gems.emerald..locZ");
    Location getemeraldLocation(){
        return new Location(world,xemerald,yemerald,zemerald);
    }

    double xdiamonds1 = conf.getInt("mapData.gems.diamonds1..locX");
    double ydiamonds1 =  conf.getInt("mapData.gems.diamonds1..locY");
    double zdiamonds1 =  conf.getInt("mapData.gems.diamonds1..locZ");
    Location getdiamonds1Location(){
        return new Location(world,xdiamonds1,ydiamonds1,zdiamonds1);
    }
    double xdiamonds2 = conf.getInt("mapData.gems.diamonds2..locX");
    double ydiamonds2 =  conf.getInt("mapData.gems.diamonds2..locY");
    double zdiamonds2 =  conf.getInt("mapData.gems.diamonds2..locZ");
    Location getdiamonds2Location(){
        return new Location(world,xdiamonds2,ydiamonds2,zdiamonds2);
    }



    Location getTeam1UpgradeManLocation(){return new Location(world,xu1,yu1,zu1,wu1,pu1);}
    Location getTeam1ShopManLocation(){return new Location(world,xs1,ys1,zs1,ws1,ps1);}
    Location getTeam1ItemSpawnLocation(){return new Location(world,xi1,yi1,zi1,wi1,pi1);}
    Location getTeam2UpgradeManLocation(){return new Location(world,xu2,yu2,zu2,wu2,pu2);}
    Location getTeam2ShopManLocation(){return new Location(world,xs2,ys2,zs2,ws2,ps2);}
    Location getTeam2ItemSpawnLocation(){return new Location(world,xi2,yi2,zi2,wi2,pi2);}

    @Override
    List<Location> getItemSpawnsList() {
        return Arrays.asList(getTeam1ItemSpawnLocation(),getTeam2ItemSpawnLocation());
    }

    @Override
    List<Location> getUpgradeMansList() {
        return Arrays.asList(getTeam1UpgradeManLocation(),getTeam2UpgradeManLocation());

    }

    @Override
    List<Location> getShopMansList() {
        return Arrays.asList(getTeam1ShopManLocation(),getTeam2ShopManLocation());
    }

    @Override
    List<Location> getLapisSpawnLocations() {
        return Arrays.asList(getlapisLocation());
    }

    @Override
    List<Location> getEmeraldsSpawnLocations() {
        return Arrays.asList(getemeraldLocation());
    }

    @Override
    List<Location> getDiamondsSpawnLocations() {
        return Arrays.asList(getdiamonds1Location(),getdiamonds2Location());
    }



    @Override
    List<UUID> getAllPlayers() {
        return playersList;
    }

    @Override
    List<UUID> getTeam1Players(){
        return team1Players;
    }
    @Override
    List<UUID> getTeam2Players(){
        return team2Players;
    }


    @Override
    void zBz() {

    }

    @Override
    void startMap() {

    }

    @Override
    Location getSpawnMain() {
        return new Location(world,xMain,yMain,zMain,yawMain,pitchMain);
    }

    @Override
    Location getSpawnTeam1() {
        return new Location(world,xT1,yT1,zT1,yawT1,pitchT1);
    }

    @Override
    Location getSpawnTeam2() {
        return new Location(world,xT2,yT2,zT2,yawT2,pitchT2);
    }


    @Override
    int timeBreakingBed() {
        return timeBreakingBed;
    }

    @Override
    int timeFinalFight() {
        return timeFinalFight;
    }



    @Override
    int timeSpawnGold() {
        return timeSpawnGold;
    }

    @Override
    int amountGold() {
        return amountGold;
    }

    @Override
    int timeSpawnSilver() {
        return timeSpawnSilver;
    }

    @Override
    int amountSilver() {
        return amountSilver;
    }

    @Override
    int teamSize() {
        return teamSize;
    }



}


class IdontKnowYet{

    static HashMap<String,UUID> mapsIdAndItsType = new HashMap<>();
    static List<UUID> avaibleMaps =  new ArrayList<>();
    static List<UUID> currentlyplayedMaps =  new ArrayList<>();
    static List<UUID> mapsToBeCleardedMaps =  new ArrayList<>();

    static  List<UUID> fourVfourMaps = new ArrayList<>();
    static  List<UUID> twoVtwoMaps = new ArrayList<>();
    static  List<UUID> quadsMaps = new ArrayList<>();
    static  List<UUID> duosMaps = new ArrayList<>();
    static  List<UUID> solosMaps = new ArrayList<>();

    UUID getRandUUID(){
        return UUID.randomUUID();
    }
    UUID getMapID(String nameMap){
        return mapsIdAndItsType.getOrDefault(nameMap,null);
    }
    String getMapName(UUID mapId){
        for(String s: mapsIdAndItsType.keySet()){
            UUID id = mapsIdAndItsType.get(s);
            if(id==mapId){
                return s;
            }
        }
        return null;
    }
    void addToCurrentlyPlayed(UUID id){
        avaibleMaps.remove(id);
        currentlyplayedMaps.add(id);
    }
    void addToBeCleared(UUID id){
        currentlyplayedMaps.remove(id);
        mapsToBeCleardedMaps.add(id);
    }
    void addToCanBePlayed(UUID id){
        mapsToBeCleardedMaps.remove(id);
        avaibleMaps.add(id);
    }

    public MapData<?> d(String nameOfMap,World world){
        switch(nameOfMap){

            case"map11":return new Map11(world);
            case"map12":return new Map12(world);
            case"map21":return new Map21(world);
            case"map22":return new Map22(world);
            case"map31":return new Map31(world);
            case"map32":return new Map32(world);
            case"map41":return new Map41(world);
            case"map42":return new Map42(world);
            case"map51":return new Map51(world);
            case"map52":return new Map52(world);
            default:return null;

        }
    }
    void addList(String name,int amountOfTeams,boolean two2two){
        UUID id =getRandUUID();
        mapsIdAndItsType.put(name,id);
        avaibleMaps.add(id);
        switch (amountOfTeams){
            case 12:solosMaps.add(id);break;
            case 8:duosMaps.add(id);break;
            case 4:quadsMaps.add(id);break;
            case 2:{
                if(two2two){
                    twoVtwoMaps.add(id);
                }else{
                    fourVfourMaps.add(id);
                }
                break;
            }
            default:{
                mapsIdAndItsType.remove(id);
                avaibleMaps.remove(id);
                break;
            }
        }
    }
    boolean addLists(){
       addList("map11",12,false);
       addList("map12",12,false);
       addList("map21",8,false);
       addList("map22",8,false);
       addList("map31",4,false);
       addList("map32",4,false);
       addList("map41",2,true);
       addList("map42",2,true);
       addList("map51",2,false);
       addList("map52",2,false);
       return true;
    }
    public void startUP(){
       boolean listLoaded =  addLists();
       if(listLoaded){
           System.out.println("Maps lists created!");
       }else{
           System.out.println("There was an error while trying to create map lists!");
       }
    }

}
class JoinMap extends IdontKnowYet{
    Player p;
    String nameMap;

    public JoinMap(Player p,@NotNull String mapName){
        this.p = p;
        this.nameMap = mapName;

    }

    MapData<?> smth(){
        return d(nameMap,Bukkit.getWorld("world"));
    }


    int getMaxAmountsOfPlayer(){
        switch (nameMap){
            case"map11":
            case"map12":
                return 12;
            case"map21":
            case"map22":
                return 8;
            case"map31":
            case"map32":
                return 4;
            case"map41":
            case"map52":
            case"map51":
            case"map42":
                return 2;
            default:return 0;
        }
    }

    boolean join(){
        Colors c = new Colors();
        UUID id = getMapID(nameMap);
        if(id==null){
            p.sendMessage(c.r+"Wrong map name!");
            return false;
        }
        MapData<?> data = smth();
        int size = data.getAmountOfPlayers();
        if(size>=getMaxAmountsOfPlayer()){
            p.sendMessage(c.r+"Map is full! Try again later!");
            return false;
        }
        if(size==0){
            data.commonMethodForAllPages();
        }

        data.addPlayer(p);
        PlayerDataClass pdc = new PlayerDataClass(p.getLocation(),p);
        pdc.setCurrentMap(nameMap);
        p.teleport(data.getSpawnMain());
        p.sendMessage(c.y+"You have joined lobby!");
        return true;
    }



}
*/
