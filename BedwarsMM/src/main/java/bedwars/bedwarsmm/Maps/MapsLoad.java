package bedwars.bedwarsmm.Maps;

import bedwars.bedwarsmm.Bedwars;
import bedwars.bedwarsmm.ConfigManager;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.*;

public class MapsLoad {

    private final ConfigManager cfg = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map11.yml");
    public boolean doesMapsExists(){
        if(cfg.getConfig().getConfigurationSection("MAPS")==null){
            return false;
        }
        return !cfg.getConfig().getConfigurationSection("MAPS").getKeys(false).isEmpty();
    }
    private static List<String> mapNames = new ArrayList<>();

    private static final HashMap<String, UUID> mapIDs = new HashMap<>();
    private static final HashMap<String, String> mapWorldNames = new HashMap<>();
    private static final HashMap<UUID, String> joinMessages = new HashMap<>();
    private static final HashMap<UUID, String> changeTeamMessages = new HashMap<>();
    private static final HashMap<UUID, String> arenaStartSoonMessages = new HashMap<>();
    private static final HashMap<UUID, String> arenaStartSoonCountingMessages = new HashMap<>();
    private static final HashMap<UUID, String> arenaStartedMessages = new HashMap<>();
    private static final HashMap<UUID, String> bedDestroySoonMessages = new HashMap<>();
    private static final HashMap<UUID, String> bedDestroySoonCountingMessages = new HashMap<>();
    private static final HashMap<UUID, String> bedDestroyedMessages = new HashMap<>();
    private static final HashMap<UUID, String> finalFightSoonMessages = new HashMap<>();
    private static final HashMap<UUID, String> finalFightCountingMessages = new HashMap<>();
    private static final HashMap<UUID, String> finalFightStartedMessages = new HashMap<>();
    private static final HashMap<UUID, String> killEveryoneSoonMessages = new HashMap<>();
    private static final HashMap<UUID, String> killEveryoneCountingMessages = new HashMap<>();
    private static final HashMap<UUID, String> killEveryoneStartedMessages = new HashMap<>();

    private static final HashMap<UUID, String> yourBedDestroyedMessages = new HashMap<>();
    private static final HashMap<UUID, String> enemyBedDestroyedMessages = new HashMap<>();
    private static final HashMap<UUID, String> yourTeamEliminatedMessages = new HashMap<>();
    private static final HashMap<UUID, String> enemyTeamEliminatedMessages = new HashMap<>();
    private static final HashMap<UUID, String> xkilledYMessages = new HashMap<>();


    private static final HashMap<UUID, Integer> howLongInLobbys = new HashMap<>();
    private static final HashMap<UUID, Integer> arenaStartSoonDelays = new HashMap<>();
    private static final HashMap<UUID, Integer> howLongTillBedDestroyeds = new HashMap<>();
    private static final HashMap<UUID, Integer> bedDestroyedSoonDelays = new HashMap<>();
    private static final HashMap<UUID, Integer> howLongTillFinalFights = new HashMap<>();
    private static final HashMap<UUID, Integer> finalFightSoonDelays = new HashMap<>();
    private static final HashMap<UUID, Integer> howLongTillKillEveryone = new HashMap<>();
    private static final HashMap<UUID, Integer> killEveryoneSoonDelays = new HashMap<>();



    private static final HashMap<UUID, Location> mapSpawnLocations = new HashMap<>();
    private static final HashMap<UUID, List<UUID>> gemsIdsPerMap = new HashMap<>();
    private static final HashMap<UUID, ItemStack> gemsMaterials = new HashMap<>();
    private static final HashMap<UUID, Integer> gemsRepeat = new HashMap<>();
    private static final HashMap<UUID, Location> gemsLocation = new HashMap<>();
    private static final HashMap<UUID,Integer> teamAmounts = new HashMap<>();
    private static final HashMap<UUID,ItemStack> teamBlocks = new HashMap<>();
    private static final HashMap<UUID,String> teamNames = new HashMap<>();
    private static final HashMap<UUID,Integer> teamSizes = new HashMap<>();
    private static final HashMap<UUID,Location> teamUpgrades = new HashMap<>();
    private static final HashMap<UUID,Location> teamShops = new HashMap<>();
    private static final HashMap<UUID,Location> teamBeds = new HashMap<>();
    private static final HashMap<UUID,Location> teamSpawns = new HashMap<>();
    private static final HashMap<UUID,Location> teamFinalFightLocs = new HashMap<>();
    private static final HashMap<UUID,List<UUID>> mapTeams = new HashMap<>();
    private static final HashMap<UUID,List<UUID>> teamGemSpawns = new HashMap<>();
    private static final HashMap<UUID,ItemStack> mapMaterials = new HashMap<>();

    private static final HashMap<UUID,String> upgradeDisplayName = new HashMap<>();
    private static final HashMap<UUID,String> upgradeTeksture = new HashMap<>();
    private static final HashMap<UUID,String> upgradeSignature = new HashMap<>();
    private static final HashMap<UUID,String> shopDisplayName = new HashMap<>();
    private static final HashMap<UUID,String> shopTeksture = new HashMap<>();
    private static final HashMap<UUID,String> shopSignature = new HashMap<>();



    public List<String> getMapNames(){
        return mapNames;
    }
    public HashMap<String,UUID> getMapIds(){
        return mapIDs;
    }
    public List<UUID>getMapIdsList(){
        List<UUID>ids = new ArrayList<>();
        for(UUID idMap: mapIDs.values()){
            ids.add(idMap);
        }
        return ids;
    }
    public UUID getMapId(String mapName){
       return mapIDs.getOrDefault(mapName,null);
    }
    public String getMapName(UUID mapID){
        for(String k: mapIDs.keySet()){
            UUID id = mapIDs.get(k);
            if(id.equals(mapID))return k;
        }
        return null;
    }
    public int getTeamAmount(UUID mapId){
        return teamAmounts.getOrDefault(mapId,0);
    }
    public String getWorldName(UUID mapId){
        if(getMapName(mapId)==null)return "world";
        return mapWorldNames.getOrDefault(getMapName(mapId),"world");

    }
    public String getWorldName(String mapName){
        return mapWorldNames.getOrDefault(mapName,"world");

    }
    public String getJoinMessage(UUID mapId){
        return joinMessages.getOrDefault(mapId,"NULL");
    }
    public String getChangeTeamMessage(UUID mapId){
        return changeTeamMessages.getOrDefault(mapId,"NULL");
    }
    public String getArenaStartSoonMessage(UUID mapId){
        return arenaStartSoonMessages.getOrDefault(mapId,"NULL");
    }
    public String getArenaStartSoonCountingMessage(UUID mapId){ return arenaStartSoonCountingMessages.getOrDefault(mapId,"NULL"); }
    public String getArenaStartedMessage(UUID mapId){
        return arenaStartedMessages.getOrDefault(mapId,"NULL");
    }
    public String getBedDestroySoonMessage(UUID mapId){
        return bedDestroySoonMessages.getOrDefault(mapId,"NULL");
    }
    public String getBedDestroyCountingMessage(UUID mapId){
        return bedDestroySoonCountingMessages.getOrDefault(mapId,"NULL");
    }
    public String getBedDestroyedMessage(UUID mapId){
        return bedDestroyedMessages.getOrDefault(mapId,"NULL");
    }
    public String getFinalFightSoonMessage(UUID mapId){
        return finalFightSoonMessages.getOrDefault(mapId,"NULL");
    }
    public String getFinalFightCountningMessage(UUID mapId){
        return finalFightCountingMessages.getOrDefault(mapId,"NULL");
    }
    public String getFinalFightStartedMessage(UUID mapId){
        return finalFightStartedMessages.getOrDefault(mapId,"NULL");
    }
    public String getKillEveryoneSoonMessage(UUID mapId){
        return killEveryoneSoonMessages.getOrDefault(mapId,"NULL");
    }
    public String getKillEveryoneCountningMessage(UUID mapId){
        return killEveryoneCountingMessages.getOrDefault(mapId,"NULL");
    }
    public String getKillEveryoneStartedMessage(UUID mapId){
        return killEveryoneStartedMessages.getOrDefault(mapId,"NULL");
    }
    public String getYourBedDestroyedMessage(UUID mapId){
        return yourBedDestroyedMessages.getOrDefault(mapId,"NULL");
    }
    public String getEnemyBedDestroyedMessage(UUID mapId){
        return enemyBedDestroyedMessages.getOrDefault(mapId,"NULL");
    }
    public String getYourTeamEliminatedMessage(UUID mapId){
        return yourTeamEliminatedMessages.getOrDefault(mapId,"NULL");
    }
    public String getEnemyTeamEliminatedMessage(UUID mapId){
        return enemyTeamEliminatedMessages.getOrDefault(mapId,"NULL");
    }
    public String getXKilledYMessage(UUID mapId){
        return xkilledYMessages.getOrDefault(mapId,"NULL");
    }
    public ItemStack getMapItem(UUID mapid){
        return mapMaterials.getOrDefault(mapid,new ItemStack(Material.POTATO));
    }
    public String getNpcUpgradeDisplayName(UUID mapid){
        return upgradeDisplayName.getOrDefault(mapid,"Upgrade");
    }
    public String getNpcUpgradeTexture(UUID mapid){
        return upgradeTeksture.getOrDefault(mapid,"");
    }
    public String getNpcUpgradeSignature(UUID mapid){
        return upgradeSignature.getOrDefault(mapid,"");
    }
    public String getNpcShopDisplayName(UUID mapid){
        return shopDisplayName.getOrDefault(mapid,"Shop");
    }
    public String getNpcShopTexture(UUID mapid){
        return shopTeksture.getOrDefault(mapid,"");
    }
    public String getNpcShopSignature(UUID mapid){
        return shopSignature.getOrDefault(mapid,"");
    }


    //ArenaStartSoon                             FinalFightSoonDelay
    //  |                                              |
    //  |                                              |
    //|||HowLongInLobby|||HowLongTillBedDestroyed|||HowLongTillFinalFight|||KillEveryOneWhoSurvived|||
    //  |                                                   |
    //  |                                                   |
    //  |                                                   |
    //BedStartSoon                                       You betterStartFightingDelay
    public int getLobbyDelayTicks(UUID mapId){
        return (howLongInLobbys.getOrDefault(mapId,6000)==0) ? 6000:(howLongInLobbys.getOrDefault(mapId,300)*20);
    }
    public int getArenaStartSoonTicks(UUID mapId){
        return (arenaStartSoonDelays.getOrDefault(mapId,6000)==0) ? 2400:(arenaStartSoonDelays.getOrDefault(mapId,120)*20);
    }
    public int getHowLongTillBedDestroyedTicks(UUID mapId){
        return (howLongTillBedDestroyeds.getOrDefault(mapId,6000)==0) ? 6000:(howLongTillBedDestroyeds.getOrDefault(mapId,300)*20);
    }
    public int getBedDestroyedSoonDelayTicks(UUID mapId){
        return (bedDestroyedSoonDelays.getOrDefault(mapId,6000)==0) ? 2400:(bedDestroyedSoonDelays.getOrDefault(mapId,120)*20);
    }
    public int getHowLongTillFinalFightsTicks(UUID mapId){
        return (howLongTillFinalFights.getOrDefault(mapId,6000)==0) ? 6000:(howLongTillFinalFights.getOrDefault(mapId,300)*20);
    }
    public int getFinalFightSoonDelayTicks(UUID mapId){
        return (finalFightSoonDelays.getOrDefault(mapId,6000)==0) ? 2400:(finalFightSoonDelays.getOrDefault(mapId,120)*20);
    }
    public int getHowLongTillKillEveryoneTicks(UUID mapId){
        return (howLongTillKillEveryone.getOrDefault(mapId,6000)==0) ? 6000:(howLongTillKillEveryone.getOrDefault(mapId,300)*20);
    }
    public int getFinalKillEveryoneDelayTicks(UUID mapId){
        return (killEveryoneSoonDelays.getOrDefault(mapId,6000)==0) ? 2400:(killEveryoneSoonDelays.getOrDefault(mapId,120)*20);
    }



    public Location getMapSpawn(UUID mapid){
        return mapSpawnLocations.getOrDefault(mapid,new Location(Bukkit.getWorld(getWorldName(mapid)),0,0,0));
    }
    public List<UUID> getMapGems(UUID mapid){
        return gemsIdsPerMap.getOrDefault(mapid,new ArrayList<>());
    }
    public ItemStack getGemMaterial(UUID gemId){
        return gemsMaterials.getOrDefault(gemId,new ItemStack(new MaterialData(Material.getMaterial(35),(byte)14).getItemType()));
    }
    public int getGemRepeat(UUID gemid){
        return gemsRepeat.getOrDefault(gemid,20);
    }
    public Location getGemSpawnLocation(UUID gemid){
        return gemsLocation.getOrDefault(gemid,new Location(Bukkit.getWorld("world"),0,0,0));
    }
    public ItemStack getTeamBlock(UUID teamiD){
        return teamBlocks.getOrDefault(teamiD,new ItemStack(new MaterialData(Material.getMaterial(35),(byte)14).getItemType()));
    }
    public String getTeamName(UUID teamid){
        return teamNames.getOrDefault(teamid,"Null");
    }
    public int getTeamSize(UUID teamid){
        return teamSizes.getOrDefault(teamid,2);
    }
    public Location getTeamUpgradeManLoc(UUID teamid){
        return teamUpgrades.getOrDefault(teamid,new Location(Bukkit.getWorld("world"),0,0,0));
    }
    public Location getTeamShopManLoc(UUID teamid){
        return teamShops.getOrDefault(teamid,new Location(Bukkit.getWorld("world"),0,0,0));
    }
    public Location getTeamBedLoc(UUID teamid){
        return teamBeds.getOrDefault(teamid,new Location(Bukkit.getWorld("world"),0,0,0));
    }
    public Location getTeamSpawnLoc(UUID teamid){
        return teamSpawns.getOrDefault(teamid,new Location(Bukkit.getWorld("world"),0,0,0));
    }
    public Location getTeamFinalLoc(UUID teamid){
        return teamFinalFightLocs.getOrDefault(teamid,new Location(Bukkit.getWorld("world"),0,0,0));
    }
    public List<UUID> getTeamsForThatMap(UUID mapID){
        return mapTeams.getOrDefault(mapID,new ArrayList<>());
    }
    public List<UUID> getTeamGems(UUID teamid){
        return teamGemSpawns.getOrDefault(teamid,new ArrayList<>());
    }



    public void loadMaps(){
        if(cfg.getConfig().getConfigurationSection("MAPS")==null||cfg.getConfig().getConfigurationSection("MAPS").getKeys(false).isEmpty()){
            System.out.println("For plugin to be working, at least one map is required!");
            Bukkit.getPluginManager().disablePlugin(Bedwars.getPlugin(Bedwars.class));
            return;
        }
        for(String s:cfg.getConfig().getConfigurationSection("MAPS").getKeys(false)){
            String path = "MAPS."+s;

            String namePath = path+".map_name";
            String mapName = getString(cfg,namePath);
            if(mapName==null){
                System.out.println("Map name can not be null near path "+namePath);
                Bukkit.getPluginManager().disablePlugin(Bedwars.getPlugin(Bedwars.class));
                return;
            }
            String worldPath = path+".world_name";
            String worldName = getString(cfg,worldPath);
            if(worldName==null){
                worldName = "world";
            }
            if(worldName==null||worldName.equals("")){
                mapWorldNames.put(worldName,"world");
            }else{
                mapWorldNames.put(worldName,worldName);
            }
            UUID idMap = UUID.randomUUID();
            mapNames.add(mapName);
            mapIDs.put(mapName,idMap);


            baseSectionCheck(path,cfg,5);
            for(String sg:cfg.getConfig().getConfigurationSection("MAPS."+s).getKeys(false)){
                if(sg.equals("map_base")){
                    String pathtwo = path+".map_base";
                    baseSectionCheck(pathtwo,cfg,2);
                    for(String z:cfg.getConfig().getConfigurationSection(pathtwo).getKeys(false)){
                        if(z.equals("map_timings")){
                            String somePath = pathtwo+".map_timings.messages";

                            String finalPath = somePath+".joinMessage";
                            joinMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".changeTeamMessage";
                            changeTeamMessages.put(idMap,getString(cfg,finalPath));


                            finalPath = somePath+".arenaStartSoonMessage";
                            arenaStartSoonMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".arenaStartSoonCountingMessage";
                            arenaStartSoonCountingMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".arenaStartedMessage";
                            arenaStartedMessages.put(idMap,getString(cfg,finalPath));

                            finalPath = somePath+".bedsDestroySoonMessage";
                            bedDestroySoonMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".bedsDestroySoonCountingMessage";
                            bedDestroySoonCountingMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".bedsDestroyedMessage";
                            bedDestroyedMessages.put(idMap,getString(cfg,finalPath));

                            finalPath = somePath+".finalFightSoonMessage";
                            finalFightSoonMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".finalFightCountingMessage";
                            finalFightCountingMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".finalFightStartedMessage";
                            finalFightStartedMessages.put(idMap,getString(cfg,finalPath));

                            finalPath = somePath+".killEveryoneSoonMessage";
                            killEveryoneSoonMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".killEveryoneCountingMessage";
                            killEveryoneCountingMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".killEveryoneStartedMessage";
                            killEveryoneStartedMessages.put(idMap,getString(cfg,finalPath));



                            finalPath = somePath+".yourBedDestroyedMessage";
                            yourBedDestroyedMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".enemyBedDestroyedMessage";
                            enemyBedDestroyedMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".yourTeamEliminatedMessage";
                            yourTeamEliminatedMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".enemyTeamEliminatedMessage";
                            enemyTeamEliminatedMessages.put(idMap,getString(cfg,finalPath));
                            finalPath = somePath+".xKILLEDyMessage";
                            xkilledYMessages.put(idMap,getString(cfg,finalPath));


                            somePath = pathtwo+".map_timings.delays";
                            finalPath = somePath+".howLongInLobby";
                            howLongInLobbys.put(idMap,getInt(cfg,finalPath));
                            finalPath = somePath+".arenaStartSoonDelay";
                            arenaStartSoonDelays.put(idMap,getInt(cfg,finalPath));
                            finalPath = somePath+".howLongTillBedDestroyed";
                            howLongTillBedDestroyeds.put(idMap,getInt(cfg,finalPath));
                            finalPath = somePath+".bedDestroyedSoonDelay";
                            bedDestroyedSoonDelays.put(idMap,getInt(cfg,finalPath));
                            finalPath = somePath+".howLongTillFinalFight";
                            howLongTillFinalFights.put(idMap,getInt(cfg,finalPath));
                            finalPath = somePath+".finalFightSoonDelay";
                            finalFightSoonDelays.put(idMap,getInt(cfg,finalPath));
                            finalPath = somePath+".howLongTillKillEveryone";
                            howLongTillKillEveryone.put(idMap,getInt(cfg,finalPath));
                            finalPath = somePath+".killEveryoneSoonDelay";
                            killEveryoneSoonDelays.put(idMap,getInt(cfg,finalPath));

                        }
                        if(z.equals("map_spawn_coordinates")){
                            String finalPathBase = pathtwo+".map_spawn_coordinates";
                            String finalPath = finalPathBase+".X";
                            double x = getDouble(cfg,finalPath);
                            finalPath = finalPathBase+".Y";
                            double y = getDouble(cfg,finalPath);
                            finalPath = finalPathBase+".Z";
                            double zCord = getDouble(cfg,finalPath);
                            finalPath = finalPathBase+".YAW";
                            float yaw =(float) getDouble(cfg,finalPath);
                            finalPath = finalPathBase+".PITCH";
                            float pitch =(float) getDouble(cfg,finalPath);
                            Location loc = new Location(Bukkit.getWorld(worldName),x,y,zCord,yaw,pitch);
                            mapSpawnLocations.put(idMap,loc);
                        }

                    }
                }
                if(sg.equals("map_gui_item")){
                    String pathtwo = path+".map_gui_item";
                    String finalPath = pathtwo+".material";
                    Material m = getMaterial(cfg,finalPath,Material.DIRT);
                    ItemStack item = new ItemStack(m);
                    finalPath = pathtwo+".displayName";
                    String displayName = getString(cfg,finalPath);
                    finalPath = pathtwo+".lore";
                    List<String> lore = getList(cfg,finalPath);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(displayName);
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    mapMaterials.put(idMap,item);
                }
                if(sg.equals("gems")){
                    if(cfg.getConfig().getConfigurationSection(path+".gems")==null||cfg.getConfig().getConfigurationSection(path+".gems").getKeys(false).isEmpty()){
                        return;
                    }
                    List<UUID> gemsIds = new ArrayList<>();
                    for(String ss:cfg.getConfig().getConfigurationSection(path+".gems").getKeys(false)){
                        String  basepath  = path+".gems."+ss;
                        UUID id = UUID.randomUUID();
                        String finalPath = basepath+".gem_material_id";
                        Material m = getMaterial(cfg,finalPath,Material.DIRT);
                        finalPath = basepath+".amount";
                        int amount = getInt(cfg,finalPath);
                        ItemStack item =  new ItemStack(m,amount);
                        gemsMaterials.put(id,item);
                        finalPath = basepath+".every_x_seconds_drop";
                        int repeat = getInt(cfg,finalPath);
                        gemsRepeat.put(id,repeat*20);
                        finalPath = basepath+".items_spawn_loc.X";
                        double x = getDouble(cfg,finalPath);
                        finalPath = basepath+".items_spawn_loc.Y";
                        double y = getDouble(cfg,finalPath);
                        finalPath = basepath+".items_spawn_loc.Z";
                        double z = getDouble(cfg,finalPath);
                        Location loc = new Location(Bukkit.getWorld(worldName),x,y,z);
                        gemsLocation.put(id,loc);
                        gemsIds.add(id);
                    }
                    gemsIdsPerMap.put(idMap,gemsIds);


                }
                if(sg.equals("teams")){
                    String pathSection =path+".teams";
                    if(cfg.getConfig().getConfigurationSection(pathSection)==null||cfg.getConfig().getConfigurationSection(pathSection).getKeys(false).size()<2){
                        System.out.println("There are required at least two teams on one map!Missing some near path: "+pathSection);
                        Bukkit.getPluginManager().disablePlugin(Bedwars.getPlugin(Bedwars.class));
                        return;
                    }
                    List<UUID> teamsIdS = new ArrayList<>();
                    for(String ss:cfg.getConfig().getConfigurationSection(pathSection).getKeys(false)){
                        teamAmounts.put(idMap,cfg.getConfig().getConfigurationSection(pathSection).getKeys(false).size());
                        String newPath = pathSection+"."+ss;
                        UUID teamID = UUID.randomUUID();
                        teamsIdS.add(teamID);
                        String finalPath = newPath+".block_type_id";
                        ItemStack block = new ItemStack(getMaterial(cfg,finalPath,Material.DIRT));
                        teamBlocks.put(teamID,block);
                        finalPath = newPath+".name";
                        String teamName = getString(cfg,finalPath);
                        teamNames.put(teamID,teamName);
                        finalPath = newPath+".size";
                        int teamSize = getInt(cfg,finalPath);
                        teamSizes.put(teamID,teamSize);
                        finalPath = newPath+".upgrade_npc_loc.X";
                        double xu = getDouble(cfg,finalPath);
                        finalPath = newPath+".upgrade_npc_loc.Y";
                        double yu = getDouble(cfg,finalPath);
                        finalPath = newPath+".upgrade_npc_loc.Z";
                        double zu = getDouble(cfg,finalPath);
                        finalPath = newPath+".upgrade_npc_loc.YAW";
                        float yawu = (float)getDouble(cfg,finalPath);
                        finalPath = newPath+".upgrade_npc_loc.PITCH";
                        float pitchu = (float)getDouble(cfg,finalPath);
                        Location locUpgrade = new Location(Bukkit.getWorld(worldName),xu,yu,zu,yawu,pitchu);
                        teamUpgrades.put(teamID,locUpgrade);
                        finalPath = newPath+".shop_npc_loc.X";
                        double xs = getDouble(cfg,finalPath);
                        finalPath = newPath+".shop_npc_loc.Y";
                        double ys = getDouble(cfg,finalPath);
                        finalPath = newPath+".shop_npc_loc.Z";
                        double zs = getDouble(cfg,finalPath);
                        finalPath = newPath+".shop_npc_loc.YAW";
                        float yaws = (float)getDouble(cfg,finalPath);
                        finalPath = newPath+".shop_npc_loc.PITCH";
                        float pitchs = (float)getDouble(cfg,finalPath);
                        Location locShop = new Location(Bukkit.getWorld(worldName),xs,ys,zs,yaws,pitchs);
                        teamShops.put(teamID,locShop);
                        finalPath = newPath+".bed_loc.X";
                        double xb = getDouble(cfg,finalPath);
                        finalPath = newPath+".bed_loc.Y";
                        double yb = getDouble(cfg,finalPath);
                        finalPath = newPath+".bed_loc.Z";
                        double zb = getDouble(cfg,finalPath);
                        finalPath = newPath+".bed_loc.YAW";
                        float yawb = (float)getDouble(cfg,finalPath);
                        finalPath = newPath+".bed_loc.PITCH";
                        float pitchb =(float) getDouble(cfg,finalPath);
                        Location locBed = new Location(Bukkit.getWorld(worldName),xb,yb,zb,yawb,pitchb);
                        teamBeds.put(teamID,locBed);

                        finalPath = newPath+".team_spawn_loc.X";
                        double xsp = getDouble(cfg,finalPath);
                        finalPath = newPath+".team_spawn_loc.Y";
                        double ysp = getDouble(cfg,finalPath);
                        finalPath = newPath+".team_spawn_loc.Z";
                        double zsp = getDouble(cfg,finalPath);
                        finalPath = newPath+".team_spawn_loc.YAW";
                        float yawsp = (float)getDouble(cfg,finalPath);
                        finalPath = newPath+".team_spawn_loc.PITCH";
                        float pitchsp = (float)getDouble(cfg,finalPath);
                        Location locTeamSpawn = new Location(Bukkit.getWorld(worldName),xsp,ysp,zsp,yawsp,pitchsp);
                        teamSpawns.put(teamID,locTeamSpawn);

                        finalPath = newPath+".team_final_loc.X";
                        double xfp = getDouble(cfg,finalPath);
                        finalPath = newPath+".team_final_loc.Y";
                        double yfp = getDouble(cfg,finalPath);
                        finalPath = newPath+".team_final_loc.Z";
                        double zfp = getDouble(cfg,finalPath);
                        finalPath = newPath+".team_final_loc.YAW";
                        float yawfp = (float)getDouble(cfg,finalPath);
                        finalPath = newPath+".team_final_loc.PITCH";
                        float pitchfp = (float)getDouble(cfg,finalPath);
                        Location locTeamFinal = new Location(Bukkit.getWorld(worldName),xfp,yfp,zfp,yawfp,pitchfp);
                        teamFinalFightLocs.put(teamID,locTeamFinal);

                        finalPath = newPath+".items_spawning";
                        if(cfg.getConfig().getConfigurationSection(finalPath)!=null){
                            List<UUID> itemSpawnList = new ArrayList<>();
                            for (String sss:cfg.getConfig().getConfigurationSection(finalPath).getKeys(false)){
                                String someKindOfPath = finalPath+"."+sss;
                                UUID id = UUID.randomUUID();
                                itemSpawnList.add(id);
                                String thePath = someKindOfPath+".material_type";
                                Material mat = getMaterial(cfg,thePath,Material.DIRT);
                                thePath = someKindOfPath+".amount";
                                int amount = getInt(cfg,thePath);
                                ItemStack item =  new ItemStack(mat,amount);
                                thePath = someKindOfPath+".every_x_seconds_spawn";
                                int repeat = getInt(cfg,thePath);
                                thePath = someKindOfPath+".location.X";
                                double x = getDouble(cfg,thePath);
                                thePath = someKindOfPath+".location.Y";
                                double y = getDouble(cfg,thePath);
                                thePath = someKindOfPath+".location.Z";
                                double z = getDouble(cfg,thePath);
                                Location loc = new Location(Bukkit.getWorld(worldName),x,y,z);
                                gemsLocation.put(id,loc);
                                gemsRepeat.put(id,repeat*20);
                                gemsMaterials.put(id,item);
                            }
                            teamGemSpawns.put(teamID,itemSpawnList);
                        }else{
                            System.out.println("There are no custom item spawns created for some team around path: "+finalPath);
                        }
                    }
                    mapTeams.put(idMap,teamsIdS);
                }
                if(sg.equals("npc")){
                    path = "MAPS."+s+"."+sg;
                    for(String sss: cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                        if(sss.equals("upgrade_npc")){
                            path = "MAPS."+s+"."+sg+"."+sss;
                            String basePath = path+".display_name";
                            String displayname = getString(cfg,basePath);
                            basePath = path+".texture";
                            String texture = getString(cfg,basePath);
                            basePath = path+".signature";
                            String signature = getString(cfg,basePath);
                            upgradeDisplayName.put(idMap,displayname);
                            upgradeTeksture.put(idMap,texture);
                            upgradeSignature.put(idMap,signature);
                        }
                        if(sss.equals("shop_npc")){
                            path = "MAPS."+s+"."+sg+"."+sss;
                            String basePath = path+".display_name";
                            String displayname = getString(cfg,basePath);
                            basePath = path+".texture";
                            String texture = getString(cfg,basePath);
                            basePath = path+".signature";
                            String signature = getString(cfg,basePath);
                            shopDisplayName.put(idMap,displayname);
                            shopTeksture.put(idMap,texture);
                            shopSignature.put(idMap,signature);
                        }

                    }
                }
            }



        }
    }
    private String getString(ConfigManager cfg,String path){
        try{
            String s = cfg.getConfig().getString(path);
            return s;
        }catch (Exception e){
            System.out.println("Wrong value next to path: "+path);
        }
        return null;
    }
    private int getInt(ConfigManager cfg,String path){
        try{
            int s = cfg.getConfig().getInt(path);
            return s;
        }catch (Exception e){
            System.out.println("Wrong value next to path: "+path);
        }
        return 2;
    }
    private double getDouble(ConfigManager cfg,String path){
        try{
            double s = cfg.getConfig().getDouble(path);
            return s;
        }catch (Exception e){
            System.out.println("Wrong value next to path: "+path);
        }
        return 15.0;
    }
    private List<String> getList(ConfigManager cfg,String path){
        try{
            List<String> s = cfg.getConfig().getStringList(path);
            return s;
        }catch (Exception e){
            System.out.println("Wrong value next to path: "+path);
        }
        return new ArrayList<>();
    }
    public Material getMaterial(ConfigManager cfg,String path, Material value) {
        if (!cfg.getConfig().contains(path)||!cfg.getConfig().isSet(path)||cfg.getConfig().getString(path)==null) {
            System.out.println("Missing arguments near path: " + path);
            cfg.getConfig().set(path, value);
            cfg.saveConfig();

        }
        String materialString = cfg.getConfig().getString(path);
        if(materialString.contains(":")){
            String[] s =  cfg.getConfig().getString(path).split(":");
            if(s.length==1){
                int id = 0;
                try{
                    id = Integer.parseInt(s[0]);
                }catch (NumberFormatException e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value.toString()+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value.toString());
                    cfg.saveConfig();
                    return value;
                }
                Material m = value;
                try{
                    m = Material.getMaterial(id);
                }catch (Exception e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value.toString()+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value.toString());
                    cfg.saveConfig();
                    return value;
                }
               return m;
            }else{

                int id = 0;
                try{
                    id = Integer.parseInt(s[0]);
                }catch (NumberFormatException e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value.toString()+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value.toString());
                    cfg.saveConfig();
                    return value;
                }
                byte b = 0;
                try{
                    b = Byte.parseByte(s[1]);
                }catch (NumberFormatException e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value.toString()+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value.toString());
                    cfg.saveConfig();
                    return value;
                }
                MaterialData mData = new MaterialData(value);
                try{
                    Material m = Material.getMaterial(id);
                    mData = new MaterialData(m,b);
                }catch (Exception e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value.toString()+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value.toString());
                    cfg.saveConfig();
                    return value;
                }
             return mData.getItemType();
            }
        }
        else{
            Material m = value;
            try{
                m = Material.getMaterial(materialString);
            }catch (Exception e){
                System.out.println("Wrong id near path: "+path+" changed to "+value.toString()+" Please use proper material name!");
                cfg.getConfig().set(path,value.toString());
            }
            return m;

        }
    }

    private void baseSectionCheck(String path,ConfigManager cfg,int sectionSize){
        if(cfg.getConfig().getConfigurationSection(path)==null){
            System.out.println("Missing base values near path: "+path+" allowing plugin to work properly, in "+cfg.getConfig().getName()+" config!#NullSections");
            Bukkit.getPluginManager().disablePlugin(Bedwars.getPlugin(Bedwars.class));
        }
        if(cfg.getConfig().getConfigurationSection(path).getKeys(false).isEmpty()){
            System.out.println("Missing base values near path: "+path+" allowing plugin to work properly, in "+cfg.getConfig().getName()+" config!#EmptySections");
            Bukkit.getPluginManager().disablePlugin(Bedwars.getPlugin(Bedwars.class));
        }
        if(cfg.getConfig().getConfigurationSection(path).getKeys(false).size()<sectionSize){
            System.out.println("Missing base values near path: "+path+" allowing plugin to work properly, in "+cfg.getConfig().getName()+" config!#MissingSections");
            Bukkit.getPluginManager().disablePlugin(Bedwars.getPlugin(Bedwars.class));
        }

    }
}

