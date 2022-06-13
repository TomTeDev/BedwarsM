package bedwars.bedwarsmm;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;


import java.io.*;
import java.util.*;
import java.util.logging.Level;
public class ConfigManager {
    private final Plugin plugin;
    private FileConfiguration fileConfig = null;
    private File file = null;
    private final Colors c = new Colors();
    String fileName;

    public ConfigManager(Plugin plugin,String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        saveDeafaultConfig();
    }

    public void reloadConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), fileName);
        }
        this.fileConfig = YamlConfiguration.loadConfiguration(this.file);
        InputStream defaultStream = this.plugin.getResource(fileName);
        if (defaultStream != null) {
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.fileConfig.setDefaults(yamlConfiguration);
        }
    }

    public FileConfiguration getConfig() {
        if (this.fileConfig == null) reloadConfig();
        return this.fileConfig;
    }

    public void saveConfig() {
        if (this.fileConfig == null || this.file == null) return;
        try {
            this.getConfig().save(file);
        } catch (IOException exception) {
            this.plugin.getLogger().log(Level.SEVERE, c.e + "Failed on trying to save "+fileName, exception);
        }
    }

    public void saveDeafaultConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), fileName);
        }
        if (!this.file.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }
}

class Config {
    ConfigManager cfg;
    public Config(ConfigManager configManager){
        this.cfg = configManager;
    }
    Colors c = new Colors();

    String errorMessage = c.e+"Missing value in path: ";
    String getString(String path){
      if(cfg.getConfig().contains(path)){
          String s = cfg.getConfig().getString(path);
          if (s==null||s.equals("")){
              return errorMessage+path;
          }else{
              return c.translate(s);
          }
      }else{
          return errorMessage+path;
      }
  }
    List<String> errorList = Arrays.asList(errorMessage);
    List<String> getListString(String path){
        if(cfg.getConfig().contains(path)){
            List<String> s = cfg.getConfig().getStringList(path);
            if(s==null||s.isEmpty()){
                List<String> failList = new ArrayList<>(errorList);
                failList.add(path);
                return failList;
            }else{
                List<String> list = new ArrayList<>();
                for (String string:s){
                    list.add(c.translate(string));
                }
                return list;
            }
        }else{
            List<String> failList = new ArrayList<>(errorList);
            failList.add(path);
            return failList;
        }
    }

    int getInt(String path){
        if(cfg.getConfig().contains(path)){
           return cfg.getConfig().getInt(path);
        }else{
            return 6660999;
        }
    }

    double getDouble(String path){
        if(cfg.getConfig().contains(path)){
            return cfg.getConfig().getDouble(path);
        }else{
            return 0.666666;
        }
    }
}


class LoadDataFromConfig {

    public Colors c = new Colors();
    public boolean isDataLoaded() {

        ConfigManager cfgGui = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"gui.yml");
        ConfigManager cfgServ = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"server.yml");
        ConfigManager cfg = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"map11.yml");

        //////////////////////////////////////////////////////////////////////////////////
        //                                                                              //
        //    //          //      /////////    ////////////////     /////////           //
        //    //                 //                  //            //                   //
        //    //          //       /////             //              /////              //
        //    //          //           //            //                  //             //
        //    ////////    //     ////////            //            ////////             //
        //                                                                              //
        //////////////////////////////////////////////////////////////////////////////////
        checkMap11(cfg);
        checkGuiData(cfgGui);
        checkServData(cfgServ);



        return true;
    }
    void checkMap11(ConfigManager cfg){

        String path =null;

        path = "MAPS";


        if(!containsPath(cfg,path)){
            cfg.getConfig().createSection(path);
            cfg.saveConfig();
        }
        if(!sectionContainAny(cfg,path)){

            cfg.getConfig().createSection(path+".map1");
            cfg.getConfig().createSection(path+".map2");
            cfg.saveConfig();
        }



        path = "MAPS";
        for(String s:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
            path = "MAPS";
            //s equals  "map1", "map2" etc.
            String thePath = path+"."+s;

            checkNameAndWorld(cfg,thePath,"world","Unknown World Name");


            //POINT1//HERE YOU MIGHT HAVE TO ADD NEW SECTION
            List<String> sectionMapList = new ArrayList<>();
            String mapBase = "map_base";
            sectionMapList.add(mapBase);
            String mapGuItem = "map_gui_item";
            sectionMapList.add(mapGuItem);
            String gems = "gems";
            sectionMapList.add(gems);
            String teams = "teams";
            sectionMapList.add(teams);
            String npc = "npc";
            sectionMapList.add(npc);
            String upgrades  ="upgrades";
            sectionMapList.add(upgrades);
            String traps  ="traps";
            sectionMapList.add(traps);

            path ="MAPS."+s;
            checkSection(cfg,path,sectionMapList);


            path = "MAPS."+s;
            for(String ss:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                path = "MAPS."+s;
                //POINT2// HERE you are creating sections(from POINT1) and setting values/new sections for them
                if(ss.equals(mapBase)){
                    List<String> sectionMapBaseList = new ArrayList<>();
                    String mapTimings = "map_timings";
                    sectionMapBaseList.add(mapTimings);
                    String mapSpawnCoordinates = "map_spawn_coordinates";
                    sectionMapBaseList.add(mapSpawnCoordinates);


                    path ="MAPS."+s+"."+ss;
                    checkSection(cfg,path,sectionMapBaseList);

                    for(String sss:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                        path ="MAPS."+s+"."+ss;
                        if(sss.equals(mapTimings)){
                            List<String> sectionMapTimingsList = new ArrayList<>();
                            String messages = "messages";
                            sectionMapTimingsList.add(messages);
                            String delays = "delays";
                            sectionMapTimingsList.add(delays);
                            path = "MAPS."+s+"."+ss+"."+sss;
                            checkSection(cfg,path,sectionMapTimingsList);
                            for(String ssss:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                                path = "MAPS."+s+"."+ss+"."+sss;
                                String pathBase = "MAPS."+s+"."+ss+"."+sss+"."+ssss;
                                if (ssss.equals(messages)){

                                    path = pathBase+".joinMessage";
                                    checkString(cfg,path,"You have join lobby!");

                                    path = pathBase+".changeTeamMessage";
                                    checkString(cfg,path,"Your team now is: ");

                                    path = pathBase+".arenaStartSoonMessage";
                                    checkString(cfg,path,"Arena is starting soon!");
                                    path = pathBase+".arenaStartSoonCountingMessage";
                                    checkString(cfg,path,"Arena will start in:");
                                    path = pathBase+".arenaStartedMessage";
                                    checkString(cfg,path,"Arena started, good luck!");
                                    path = pathBase+".bedsDestroySoonMessage";
                                    checkString(cfg,path,"Bed will be destroyed soon, start fighting!");
                                    path = pathBase+".bedsDestroySoonCountingMessage";
                                    checkString(cfg,path,"Bed will be destroyed in:");
                                    path = pathBase+".bedsDestroyedMessage";
                                    checkString(cfg,path,"Beds destroyed!");
                                    path = pathBase+".finalFightSoonMessage";
                                    checkString(cfg,path,"Final fight will start soon!");
                                    path = pathBase+".finalFightCountingMessage";
                                    checkString(cfg,path,"Final fight starting in:");
                                    path = pathBase+".finalFightStartedMessage";
                                    checkString(cfg,path,"Final fight started!");
                                    path = pathBase+".killEveryoneSoonMessage";
                                    checkString(cfg,path,"Start fighting, u are making God of war ANGRY!");
                                    path = pathBase+".killEveryoneCountingMessage";
                                    checkString(cfg,path,"Map will end in:");
                                    path = pathBase+".killEveryoneStartedMessage";
                                    checkString(cfg,path,"Map has ended!");
                                    path = pathBase+".yourBedDestroyedMessage";
                                    checkString(cfg,path,"You bed got destroyed");
                                    path = pathBase+".enemyBedDestroyedMessage";
                                    checkString(cfg,path,"<teamXname> bed got destroyed");
                                    path = pathBase+".yourTeamEliminatedMessage";
                                    checkString(cfg,path,"Your team got eliminated!");
                                    path = pathBase+".enemyTeamEliminatedMessage";
                                    checkString(cfg,path,"<teamXname> got eliminated!");
                                    path = pathBase+".xKILLEDyMessage";
                                    checkString(cfg,path,"<PlayerX> was killed by <playerY>!");
                                }
                                if (ssss.equals(delays)){

                                    path = pathBase+".howLongInLobby";
                                    checkInt(cfg,path,300);

                                    path = pathBase+".arenaStartSoonDelay";
                                    checkInt(cfg,path,60);

                                    path = pathBase+".howLongTillBedDestroyed";
                                    checkInt(cfg,path,180);

                                    path = pathBase+".bedDestroyedSoonDelay";
                                    checkInt(cfg,path,60);

                                    path = pathBase+".howLongTillFinalFight";
                                    checkInt(cfg,path,120);

                                    path = pathBase+".finalFightSoonDelay";
                                    checkInt(cfg,path,60);

                                    path = pathBase+".howLongTillKillEveryone";
                                    checkInt(cfg,path,120);

                                    path = pathBase+".killEveryoneSoonDelay";
                                    checkInt(cfg,path,60);
                                }
                            }
                        }
                        if(sss.equals(mapSpawnCoordinates)){
                            path = "MAPS."+s+"."+ss+"."+sss;
                            cfgCheckFive(cfg,path);
                        }
                    }
                }
                if(ss.equals(mapGuItem)){
                    path = "MAPS."+s+"."+ss+".material";
                    cfgCheckMaterial(cfg,path,"DIRT");
                    path = "MAPS."+s+"."+ss+".displayName";
                    cfgCheckMaterial(cfg,path,"MapItemName");
                    path = "MAPS."+s+"."+ss+".lore";
                    List<String> lore = new ArrayList<>();
                    lore.add("  ");
                    lore.add("Thats some");
                    lore.add("text lines");
                    lore.add("  ");
                    checkList(cfg,path,lore);
                }
                if(ss.equals(gems)){
                    path = "MAPS."+s+"."+ss;
                    if(!sectionContainAny(cfg,path)){
                        List<String> gemsList = new ArrayList<>();
                        String gemOne = "gem_one";
                        gemsList.add(gemOne);
                        String gemTwo = "gem_two";
                        gemsList.add(gemTwo);
                        checkSection(cfg,path,gemsList);
                    }
                    for(String sss:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                        path = "MAPS."+s+"."+ss+"."+sss;
                        checkGems(cfg,path);
                    }
                }
                if(ss.equals(teams)){
                String bedrockPath = "MAPS."+s+".";
                checkteams(cfg,bedrockPath);
                }
                if(ss.equals(npc)){
                    path = "MAPS."+s+"."+ss;
                    List<String> npcList = new ArrayList<>();
                    String shop = "shop_npc";
                    npcList.add(shop);
                    String upgrade = "upgrade_npc";
                    npcList.add(upgrade);
                    if(!sectionContainAny(cfg,path)){
                        checkSection(cfg,path,npcList);
                    }
                    for(String sss:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                        path = "MAPS."+s+"."+ss;
                        if(sss.equals(shop)){
                            path = "MAPS."+s+"."+npc+"."+sss+".display_name";
                            checkString(cfg,path,"Shop");
                            path = "MAPS."+s+"."+npc+"."+sss+".texture";
                            checkString(cfg,path,"ewogICJ0aW1lc3RhbXAiIDogMTYzMDc3MjUyMTc3MiwKICAicHJvZmlsZUlkIiA6ICIxYjYwZmI0Njc0NmU0MjczYjBlYWI0MWJkNjMxYzRjOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVOb29kbGVBcm1zIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UzZmFkZWIxYzJlNmFkMTllMWQzMDRhNmQwZjY1YjEwY2FlYmQyZDlhOTNiZWMwMjJhNTdjZWE1MTU5NWFkYjkiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==");
                            path = "MAPS."+s+"."+npc+"."+sss+".signature";
                            checkString(cfg,path,"Pp9IS2fsJGGSyEGbG1em+N4/dEJRWqi0hdjDDy//Igbux15jgjMLxbNUQLctAQBzxIYPhSmXWdYtgAMlkqF+jTohTPlFKrg3uCuXbVo6bZyzWQBtZVz36LnvJ3xZkWrMavFyc3vVctOWXl0jRng7pNM/2f1sK5AT/y2i3rEyFuJLZ8BPlDaVGlBvihPfbVqNVNNzx7gMspH3S168cTQXVD+S3ypWDwfUhPzYhzCoE2BtkbMviRe6R3Pia2Grp5V78CmgDuIOMO0KH1qKoxU64lYJHAPT9biuGKE3SknrGKFueZH0JzH01ieFkNHnkSSQK2dkMSCtm9w/JyCCswq3igZoNq1I7g0pTPG4nrqG1+6QcLRyxJkDYhCc8DEMtYMy/re3jdhXwBFTdCVLCtiFtlSzXt8nnSkUNvMo8Iw+ZHQ5b5iZrFN2SK+e3AujO0kd+R26ARUaB+/8mwgGNdkF0NvYLCngFXZDVxUUAwbGQGIHT9G2y1JSnswMwWPuvYwdyRFYysqxb6/GeQi46jy1H3q0Mp455oPQNZT7qhUSF8sxIeKAXgqvmn1ISsDU60ExBQCzX+ip8zOMXd9TyW1Opbm27Jf/OLuTlwFs6wTHERPZaauGF/QoilMOjSgZwU1JqXIqVb8rU7Gwgi+Va/mb7bNSPHHwgB8dfxm/EIeDnpU=");
                        }
                        if(sss.equals(upgrade)){
                            path = "MAPS."+s+"."+npc+"."+sss+".display_name";
                            checkString(cfg,path,"Upgrades");
                            path = "MAPS."+s+"."+npc+"."+sss+".texture";
                            checkString(cfg,path,"ewogICJ0aW1lc3RhbXAiIDogMTYzMDc3MjYzMzQyOSwKICAicHJvZmlsZUlkIiA6ICI5NGFkNGM2NDdlODM0ZWNiYWQ4ODE0ZmQ5YjE5MDc3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJTMlNoYWRvd2JyaWdodCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82YjlhMzI2MDlmOGE0ODA4ZmJlZTRhOGMyN2UyMGFlMDEzOGYwYjA0M2VlMTliMWM2OGMyNWIwYThiZjdkZmYzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=");
                            path = "MAPS."+s+"."+npc+"."+sss+".signature";
                            checkString(cfg,path,"lcyft5MvTbGu1CFreh5lInuaKAaDMSlqTRZu4Ro4MShv4Ntacz+zY2TggBjqYA67ePWpHsDwG1lkh72gGn6t9vbE3vOIHtmU+KZI76uLiq8PI4rcydEH4Q1i1HCB0Ere+YxeIdQPlaYgmSqm7BMynmeL3MCoOJep7XUT5oQYH0XoEAvGyav+oLC4ciU5yW+Ts0mKfzpSp54JV++ez3vUw7uOFv3vADTjBLJEkNqtwsBWLleey9MYroPOzsE1IIwUYoCsrmI9bcGVLu6y2uqv6y+I8Sma8WubGLDFHaTfagdkQp99JTt2GYLVkEtrUM9Rm3g+ge8HPwL166s90rPvbe4SkWC0pvMIIH/CWepz/pyb8zaxlC9ByTn4i3Ruo3k7+euUoyGRvd+oYWpdm5poUcjcYfJEFeMW8wWwn3w3gGDM47SAWS6v5xb0hSOHNUY4/WRTVfvHXUogvTPrpUxBMbXjHwVwzhAGVinVrhCk0PaEaIJv8L7n5G3G34j/5bQaYZ+Et0XNXrAa1+cIyiMvfqIs5UxgbP5oxiL2WUR1gjr6RhCsFEST0/SJbQeHAp3701H8FlShr7LsflHV104Ea4kpelB2vTnTPeeaTdtvalthqZ/nW+N2W8DCFaZ7jir7/oXhPg7q3ME1fu2KH9aX6EYOVJY7SeEaqYot0E4Ob4A=");
                        }

                    }
                }
                if(ss.equals(upgrades)){
                    path = "MAPS."+s+"."+ss;
                    List<String> npcList = new ArrayList<>();
                    String upgrade1 = "upgrade1";
                    npcList.add(upgrade1);
                    if(!sectionContainAny(cfg,path)){
                        checkSection(cfg,path,npcList);
                    }
                    for(String sss:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                        path = "MAPS."+s+"."+ss;
                        if(sss.equals(shop)){
                            path = "MAPS."+s+"."+npc+"."+sss+".display_name";
                            checkString(cfg,path,"Shop");
                            path = "MAPS."+s+"."+npc+"."+sss+".texture";
                            checkString(cfg,path,"ewogICJ0aW1lc3RhbXAiIDogMTYzMDc3MjUyMTc3MiwKICAicHJvZmlsZUlkIiA6ICIxYjYwZmI0Njc0NmU0MjczYjBlYWI0MWJkNjMxYzRjOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVOb29kbGVBcm1zIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UzZmFkZWIxYzJlNmFkMTllMWQzMDRhNmQwZjY1YjEwY2FlYmQyZDlhOTNiZWMwMjJhNTdjZWE1MTU5NWFkYjkiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==");
                            path = "MAPS."+s+"."+npc+"."+sss+".signature";
                            checkString(cfg,path,"Pp9IS2fsJGGSyEGbG1em+N4/dEJRWqi0hdjDDy//Igbux15jgjMLxbNUQLctAQBzxIYPhSmXWdYtgAMlkqF+jTohTPlFKrg3uCuXbVo6bZyzWQBtZVz36LnvJ3xZkWrMavFyc3vVctOWXl0jRng7pNM/2f1sK5AT/y2i3rEyFuJLZ8BPlDaVGlBvihPfbVqNVNNzx7gMspH3S168cTQXVD+S3ypWDwfUhPzYhzCoE2BtkbMviRe6R3Pia2Grp5V78CmgDuIOMO0KH1qKoxU64lYJHAPT9biuGKE3SknrGKFueZH0JzH01ieFkNHnkSSQK2dkMSCtm9w/JyCCswq3igZoNq1I7g0pTPG4nrqG1+6QcLRyxJkDYhCc8DEMtYMy/re3jdhXwBFTdCVLCtiFtlSzXt8nnSkUNvMo8Iw+ZHQ5b5iZrFN2SK+e3AujO0kd+R26ARUaB+/8mwgGNdkF0NvYLCngFXZDVxUUAwbGQGIHT9G2y1JSnswMwWPuvYwdyRFYysqxb6/GeQi46jy1H3q0Mp455oPQNZT7qhUSF8sxIeKAXgqvmn1ISsDU60ExBQCzX+ip8zOMXd9TyW1Opbm27Jf/OLuTlwFs6wTHERPZaauGF/QoilMOjSgZwU1JqXIqVb8rU7Gwgi+Va/mb7bNSPHHwgB8dfxm/EIeDnpU=");
                        }
                        if(sss.equals(upgrade)){
                            path = "MAPS."+s+"."+npc+"."+sss+".display_name";
                            checkString(cfg,path,"Upgrades");
                            path = "MAPS."+s+"."+npc+"."+sss+".texture";
                            checkString(cfg,path,"ewogICJ0aW1lc3RhbXAiIDogMTYzMDc3MjYzMzQyOSwKICAicHJvZmlsZUlkIiA6ICI5NGFkNGM2NDdlODM0ZWNiYWQ4ODE0ZmQ5YjE5MDc3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJTMlNoYWRvd2JyaWdodCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82YjlhMzI2MDlmOGE0ODA4ZmJlZTRhOGMyN2UyMGFlMDEzOGYwYjA0M2VlMTliMWM2OGMyNWIwYThiZjdkZmYzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=");
                            path = "MAPS."+s+"."+npc+"."+sss+".signature";
                            checkString(cfg,path,"lcyft5MvTbGu1CFreh5lInuaKAaDMSlqTRZu4Ro4MShv4Ntacz+zY2TggBjqYA67ePWpHsDwG1lkh72gGn6t9vbE3vOIHtmU+KZI76uLiq8PI4rcydEH4Q1i1HCB0Ere+YxeIdQPlaYgmSqm7BMynmeL3MCoOJep7XUT5oQYH0XoEAvGyav+oLC4ciU5yW+Ts0mKfzpSp54JV++ez3vUw7uOFv3vADTjBLJEkNqtwsBWLleey9MYroPOzsE1IIwUYoCsrmI9bcGVLu6y2uqv6y+I8Sma8WubGLDFHaTfagdkQp99JTt2GYLVkEtrUM9Rm3g+ge8HPwL166s90rPvbe4SkWC0pvMIIH/CWepz/pyb8zaxlC9ByTn4i3Ruo3k7+euUoyGRvd+oYWpdm5poUcjcYfJEFeMW8wWwn3w3gGDM47SAWS6v5xb0hSOHNUY4/WRTVfvHXUogvTPrpUxBMbXjHwVwzhAGVinVrhCk0PaEaIJv8L7n5G3G34j/5bQaYZ+Et0XNXrAa1+cIyiMvfqIs5UxgbP5oxiL2WUR1gjr6RhCsFEST0/SJbQeHAp3701H8FlShr7LsflHV104Ea4kpelB2vTnTPeeaTdtvalthqZ/nW+N2W8DCFaZ7jir7/oXhPg7q3ME1fu2KH9aX6EYOVJY7SeEaqYot0E4Ob4A=");
                        }

                    }
                }
                if(ss.equals(traps)){
                    path = "MAPS."+s+"."+ss;
                    List<String> npcList = new ArrayList<>();
                    String shop = "shop_npc";
                    npcList.add(shop);
                    String upgrade = "upgrade_npc";
                    npcList.add(upgrade);
                    if(!sectionContainAny(cfg,path)){
                        checkSection(cfg,path,npcList);
                    }
                    for(String sss:cfg.getConfig().getConfigurationSection(path).getKeys(false)){
                        path = "MAPS."+s+"."+ss;
                        if(sss.equals(shop)){
                            path = "MAPS."+s+"."+npc+"."+sss+".display_name";
                            checkString(cfg,path,"Shop");
                            path = "MAPS."+s+"."+npc+"."+sss+".texture";
                            checkString(cfg,path,"ewogICJ0aW1lc3RhbXAiIDogMTYzMDc3MjUyMTc3MiwKICAicHJvZmlsZUlkIiA6ICIxYjYwZmI0Njc0NmU0MjczYjBlYWI0MWJkNjMxYzRjOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVOb29kbGVBcm1zIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2UzZmFkZWIxYzJlNmFkMTllMWQzMDRhNmQwZjY1YjEwY2FlYmQyZDlhOTNiZWMwMjJhNTdjZWE1MTU5NWFkYjkiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==");
                            path = "MAPS."+s+"."+npc+"."+sss+".signature";
                            checkString(cfg,path,"Pp9IS2fsJGGSyEGbG1em+N4/dEJRWqi0hdjDDy//Igbux15jgjMLxbNUQLctAQBzxIYPhSmXWdYtgAMlkqF+jTohTPlFKrg3uCuXbVo6bZyzWQBtZVz36LnvJ3xZkWrMavFyc3vVctOWXl0jRng7pNM/2f1sK5AT/y2i3rEyFuJLZ8BPlDaVGlBvihPfbVqNVNNzx7gMspH3S168cTQXVD+S3ypWDwfUhPzYhzCoE2BtkbMviRe6R3Pia2Grp5V78CmgDuIOMO0KH1qKoxU64lYJHAPT9biuGKE3SknrGKFueZH0JzH01ieFkNHnkSSQK2dkMSCtm9w/JyCCswq3igZoNq1I7g0pTPG4nrqG1+6QcLRyxJkDYhCc8DEMtYMy/re3jdhXwBFTdCVLCtiFtlSzXt8nnSkUNvMo8Iw+ZHQ5b5iZrFN2SK+e3AujO0kd+R26ARUaB+/8mwgGNdkF0NvYLCngFXZDVxUUAwbGQGIHT9G2y1JSnswMwWPuvYwdyRFYysqxb6/GeQi46jy1H3q0Mp455oPQNZT7qhUSF8sxIeKAXgqvmn1ISsDU60ExBQCzX+ip8zOMXd9TyW1Opbm27Jf/OLuTlwFs6wTHERPZaauGF/QoilMOjSgZwU1JqXIqVb8rU7Gwgi+Va/mb7bNSPHHwgB8dfxm/EIeDnpU=");
                        }
                        if(sss.equals(upgrade)){
                            path = "MAPS."+s+"."+npc+"."+sss+".display_name";
                            checkString(cfg,path,"Upgrades");
                            path = "MAPS."+s+"."+npc+"."+sss+".texture";
                            checkString(cfg,path,"ewogICJ0aW1lc3RhbXAiIDogMTYzMDc3MjYzMzQyOSwKICAicHJvZmlsZUlkIiA6ICI5NGFkNGM2NDdlODM0ZWNiYWQ4ODE0ZmQ5YjE5MDc3YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJTMlNoYWRvd2JyaWdodCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82YjlhMzI2MDlmOGE0ODA4ZmJlZTRhOGMyN2UyMGFlMDEzOGYwYjA0M2VlMTliMWM2OGMyNWIwYThiZjdkZmYzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=");
                            path = "MAPS."+s+"."+npc+"."+sss+".signature";
                            checkString(cfg,path,"lcyft5MvTbGu1CFreh5lInuaKAaDMSlqTRZu4Ro4MShv4Ntacz+zY2TggBjqYA67ePWpHsDwG1lkh72gGn6t9vbE3vOIHtmU+KZI76uLiq8PI4rcydEH4Q1i1HCB0Ere+YxeIdQPlaYgmSqm7BMynmeL3MCoOJep7XUT5oQYH0XoEAvGyav+oLC4ciU5yW+Ts0mKfzpSp54JV++ez3vUw7uOFv3vADTjBLJEkNqtwsBWLleey9MYroPOzsE1IIwUYoCsrmI9bcGVLu6y2uqv6y+I8Sma8WubGLDFHaTfagdkQp99JTt2GYLVkEtrUM9Rm3g+ge8HPwL166s90rPvbe4SkWC0pvMIIH/CWepz/pyb8zaxlC9ByTn4i3Ruo3k7+euUoyGRvd+oYWpdm5poUcjcYfJEFeMW8wWwn3w3gGDM47SAWS6v5xb0hSOHNUY4/WRTVfvHXUogvTPrpUxBMbXjHwVwzhAGVinVrhCk0PaEaIJv8L7n5G3G34j/5bQaYZ+Et0XNXrAa1+cIyiMvfqIs5UxgbP5oxiL2WUR1gjr6RhCsFEST0/SJbQeHAp3701H8FlShr7LsflHV104Ea4kpelB2vTnTPeeaTdtvalthqZ/nW+N2W8DCFaZ7jir7/oXhPg7q3ME1fu2KH9aX6EYOVJY7SeEaqYot0E4Ob4A=");
                        }

                    }
                }
            }
        }



    }
    boolean containsPath(ConfigManager cfg,String path){
        return cfg.getConfig().contains(path);
    }
    boolean containsSection(ConfigManager cfg,String path){
        return cfg.getConfig().getConfigurationSection(path) != null;
    }
    void checkSection(ConfigManager cfg,String path,List<String> sectionParts){
        for(String s: sectionParts){
            String sectionPath = path+"."+s;
            if(!cfg.getConfig().contains(sectionPath)){
                cfg.getConfig().createSection(sectionPath);
            }
        }
        cfg.saveConfig();
    }
    boolean sectionContainAny(ConfigManager cfg,String path){
        return cfg.getConfig().getConfigurationSection(path).getKeys(false) != null && !cfg.getConfig().getConfigurationSection(path).getKeys(false).isEmpty();
    }
    void checkNameAndWorld(ConfigManager cfg,String path, String worldName,String mapName){
       String thePath =  path + ".map_name";
        checkString(cfg,thePath,mapName);
        cfg.saveConfig();
        thePath = path+".world_name";
        checkString(cfg,thePath,worldName);
        cfg.saveConfig();
    }
    void checkList(ConfigManager cfg,String path,List<String> value){
        if (!cfg.getConfig().contains(path)){
            System.out.println(c.r + "There is no value assigned to "+path);
            cfg.getConfig().set(path, value);
            cfg.saveConfig();
        }

    }
    void checkString(ConfigManager cfg,String path,String value){
        if (!cfg.getConfig().contains(path)||cfg.getConfig().getString(path)==null||!cfg.getConfig().isSet(path)){
            System.out.println(c.r + "There is no value assigned to "+path);
            cfg.getConfig().set(path, value);
            cfg.saveConfig();
        }
    }
    void checkInt(ConfigManager cfg,String path,int value){
        if (!cfg.getConfig().contains(path)){
            System.out.println(c.r + "There is no value assigned to "+path);
            cfg.getConfig().set(path, value);
            cfg.saveConfig();
        }
    }

    void checkGems(ConfigManager cfg,String bedrockPath){

            String path = null;
            path = bedrockPath+".gem_material_id";
            cfgCheck(cfg,path,"DIRT");
            cfgCheckMaterial(cfg,path,"DIRT");
            path = bedrockPath+".amount";
            cfgCheck(cfg,path,3);
            path = bedrockPath+".every_x_seconds_drop";
            cfgCheck(cfg,path,30);
            path = bedrockPath+".items_spawn_loc";
            cfgCheckThree(cfg,path);
            cfg.saveConfig();


    }
    void checkteams(ConfigManager cfg,String bedrockPath){
        List<String> baseTeamNames = new ArrayList<>(Arrays.asList("team1","team2"));
        if(!cfg.getConfig().contains(bedrockPath+"teams")){
            System.out.println("THERE ARE NO TEAMS IN "+cfg.getConfig().getName()+" CONFIG!");
            for (String s: baseTeamNames){
                cfg.getConfig().createSection(bedrockPath+"teams."+s);
            }

            cfg.saveConfig();
            ///
        }
        if(cfg.getConfig().getConfigurationSection(bedrockPath+"teams").getKeys(false).size()<1){
            for (String s: baseTeamNames){
                cfg.getConfig().createSection(bedrockPath+"teams."+s);
            }
            ///
            cfg.saveConfig();
            ///
        }
        for (String str : cfg.getConfig().getConfigurationSection(bedrockPath+"teams").getKeys(false)) {
            String path = null;
            path = bedrockPath+"teams."+str+".block_type_id";
            cfgCheckMaterial(cfg,path,"DIRT");
            path = bedrockPath+"teams."+str+".name";
            cfgCheck(cfg,path,"Team BLUE");
            path = bedrockPath+"teams."+str+".size";
            cfgCheck(cfg,path,2);
            path =bedrockPath+"teams."+str+".upgrade_npc_loc";
            cfgCheckFive(cfg,path);
            path =bedrockPath+"teams."+str+".shop_npc_loc";
            cfgCheckFive(cfg,path);
            path =bedrockPath+"teams."+str+".bed_loc";
            cfgCheckFive(cfg,path);
            path =bedrockPath+"teams."+str+".team_spawn_loc";
            cfgCheckFive(cfg,path);
            path =bedrockPath+"teams."+str+".team_final_loc";
            cfgCheckFive(cfg,path);

            path =bedrockPath+"teams."+str+".items_spawning";
            if(!cfg.getConfig().contains(path)){
                cfg.getConfig().createSection(path);
            }
            if(cfg.getConfig().getConfigurationSection(path).getKeys(false).size()<1){
                List<String> baseNames = new ArrayList<>(Arrays.asList("item_one","item_two"));
                for (String s:baseNames){
                    String basePath = path+"."+s;
                    cfg.getConfig().createSection(basePath);
                    cfg.saveConfig();
                }
                for(String s:baseNames){
                    String basePath = path+"."+s;

                    String pathD =basePath+".material_type";
                    cfgCheckMaterial(cfg,pathD,"DIRT");
                    pathD =basePath+".amount";
                    cfg.getConfig().set(pathD,5);
                    pathD =basePath+".every_x_seconds_spawn";
                    cfg.getConfig().set(pathD,30);
                    pathD =basePath+".location";
                    cfgCheckThree(cfg,pathD);
                }
                ///
                cfg.saveConfig();
                ///
            }


        }
    }

    void checkGuiData(ConfigManager cfgGui){

        checkString(cfgGui,"gui.titles..main_gui_title", "Choose mode");
        checkString(cfgGui,"gui.titles..solos_gui_title", "Maps with solo teams");
        checkString(cfgGui,"gui.titles..duos_gui_title", "Maps with duo teams");
        checkString(cfgGui,"gui.titles..quads_gui_title", "Maps with quads teams");
        checkString(cfgGui,"gui.titles..twovtwo_gui_title", "4v4 maps");
        checkString(cfgGui,"gui.titles..fourvfour_gui_title", "4v4 maps");
        checkString(cfgGui,"gui.displays.main..solos_display", "Discover solo maps");
        checkString(cfgGui,"gui.displays.main..duos_display", "Discover duo maps");
        checkString(cfgGui,"gui.displays.main..quads_display", "Discover quad maps");
        checkString(cfgGui,"gui.displays.main..twovtwo_display", "Discover 2v2 maps");
        checkString(cfgGui,"gui.displays.main..fourvfour_display", "Discover 4v4 maps");
        checkString(cfgGui,"gui.displays.buttons..close_button", "Close");
        checkString(cfgGui,"gui.displays.buttons..go_back_button", "Go back");
        checkString(cfgGui,"gui.displays.side..solos_first_display", "1V12 MAP NAME");
        checkString(cfgGui,"gui.displays.side..solos_second_display", "1v12Map name");
        checkString(cfgGui,"gui.displays.side..duos_first_display", "duo map name");
        checkString(cfgGui,"gui.displays.side..duos_second_display", "DUO MAP NAME");
        checkString(cfgGui,"gui.displays.side..quads_first_display", "QUAD MAP NAME");
        checkString(cfgGui,"gui.displays.side..quads_second_display", "quad map name");
        checkString(cfgGui,"gui.displays.side..twovtwo_first_display", "2v2 map name");
        checkString(cfgGui,"gui.displays.side..twovtwo_second_display", "2V2 MAP NAME");
        checkString(cfgGui,"gui.displays.side..fourvfour_first_display", "4v4 MAP NAME");
        checkString(cfgGui,"gui.displays.side..fourvfour_second_display", "4V4 map name");
        checkList(cfgGui,"gui.lores.buttons..close_button", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.buttons..go_back_button", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.main..solos_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.main..duos_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.main..quads_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.main..twovtwo_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.main..fourvfour_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..solos_first_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..solos_second_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..duos_first_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..duos_second_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..quads_first_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..quads_second_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..twovtwo_first_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..twovtwo_second_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..fourvfour_first_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
        checkList(cfgGui,"gui.lores.side..fourvfour_second_lore", new ArrayList<String>(Arrays.asList("Just", "base","list","values")));
    }
    void checkServData(ConfigManager cfgServ){


        checkInt(cfgServ,"spawn.coordinates..x1", -200);
        checkInt(cfgServ,"spawn.coordinates..z1", -200);
        checkInt(cfgServ,"spawn.coordinates..x2", 200);
        checkInt(cfgServ,"spawn.coordinates..z2", 200);
        checkInt(cfgServ,"spawn.coordinates..mainX", 0);
        checkInt(cfgServ,"spawn.coordinates..mainY", 73);
        checkInt(cfgServ,"spawn.coordinates..mainZ", 0);
        checkInt(cfgServ,"spawn.coordinates..mainYaw", 30);
        checkInt(cfgServ,"spawn.coordinates..mainPitch", 180);
    }

    public void cfgCheck(ConfigManager cfg,String path, String value){
        if(!cfg.getConfig().contains(path)||!cfg.getConfig().isSet(path)){
            System.out.println("Missing arguments near path: "+path);
            cfg.getConfig().set(path,value);
            cfg.saveConfig();
        }
    }
    public void cfgCheck(ConfigManager cfg,String path, int value){
        if(!cfg.getConfig().contains(path)||!cfg.getConfig().isSet(path)) {
            System.out.println("Missing arguments near path: " + path);
            cfg.getConfig().set(path, value);
            cfg.saveConfig();
            cfg.saveDeafaultConfig();
        }
    }
    public void cfgCheck(ConfigManager cfg,String path, double value) {
        if (!cfg.getConfig().contains(path)||!cfg.getConfig().isSet(path)) {
            System.out.println("Missing arguments near path: " + path);
            cfg.getConfig().set(path, value);
            cfg.saveConfig();

        }
    }
    public void cfgCheck(ConfigManager cfg,String path, List<String> value) {
        if (!cfg.getConfig().contains(path)) {
            System.out.println("Missing arguments near path: " + path);
            cfg.getConfig().set(path, value);
            cfg.saveConfig();

        }
    }
    public void cfgCheckMaterial(ConfigManager cfg,String path, String value) {
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
                    System.out.println("Wrong id near path: "+path+" changed to "+value+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value);
                    cfg.saveConfig();
                    return;
                }
                try{
                    Material m = Material.getMaterial(id);
                }catch (Exception e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value);
                    cfg.saveConfig();
                    return;
                }
            }else{

                int id = 0;
                try{
                    id = Integer.parseInt(s[0]);
                }catch (NumberFormatException e){
                    System.out.println("Wrong id near path: "+path+" changed to 35:14 (red wool), make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value);
                    cfg.saveConfig();
                    return;
                }
                byte b = 0;
                try{
                    b = Byte.parseByte(s[1]);
                }catch (NumberFormatException e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value);
                    cfg.saveConfig();
                    return;
                }
                try{
                    Material m = Material.getMaterial(id);
                    MaterialData mData = new MaterialData(m,b);
                }catch (Exception e){
                    System.out.println("Wrong id near path: "+path+" changed to "+value+", make sure u are using numeric id like 5 - for planks or 35:13 for different variety of the same block in this case wool");
                    cfg.getConfig().set(path, value);
                    cfg.saveConfig();
                    return;
                }

            }
        }
        else{
            try{
                Material m = Material.matchMaterial(materialString);
            }catch (Exception e){
                System.out.println("Wrong id near path: "+path+" changed to "+value+" Please use proper material name!");
                cfg.getConfig().set(path,value);
            }

        }

        cfg.saveConfig();
    }

    public void cfgCheckThree(ConfigManager cfg,String path){
        if(!cfg.getConfig().contains(path)){
            cfg.getConfig().createSection(path);
        }
        if(cfg.getConfig().getConfigurationSection(path).getKeys(false).isEmpty()){
            System.out.println("(1)Empty path next to: "+path);

            String baseIL =  path+".";
            String fpath = baseIL+"X";
            cfg.getConfig().set(fpath,10.0);
            fpath = baseIL+"Y";
            cfg.getConfig().set(fpath,73.0);
            fpath = baseIL+"Z";
            cfg.getConfig().set(fpath,10.0);

        }else{
            int x = 0;
            for (String string : cfg.getConfig().getConfigurationSection(path).getKeys(false)) {
                x++;
                if(x==4){
                    break;
                }
                if(x==1){
                    if(!string.equals("X")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".X",10.0);
                    }
                }
                if(x==2){
                    if(!string.equals("Y")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".Y",10.0);
                    }
                }
                if(x==3){
                    if(!string.equals("Z")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".Z",10.0);
                    }
                }
            }
        }
        cfg.saveConfig();
    }
    public void cfgCheckFive(ConfigManager cfg,String path){
        if(!cfg.getConfig().contains(path)||cfg.getConfig().get(path)==null){
            cfg.getConfig().createSection(path);
        }
        if(cfg.getConfig().getConfigurationSection(path).getKeys(false).isEmpty()){
            System.out.println("Empty path next to: "+path);
            String baseIL =  path+".";
            path = baseIL+"X";
            cfg.getConfig().set(path,10.0);
            path = baseIL+"Y";
            cfg.getConfig().set(path,73.0);
            path = baseIL+"Z";
            cfg.getConfig().set(path,10.0);
            path = baseIL+"YAW";
            cfg.getConfig().set(path,10.0);
            path = baseIL+"PITCH";
            cfg.getConfig().set(path,10.0);

        }else{

            int x = 0;
            for (String string : cfg.getConfig().getConfigurationSection(path).getKeys(false)) {
                x++;
                if(x==6){
                    break;
                }
                if(x==1){
                    if(!string.equals("X")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".X",10.0);
                    }
                }
                if(x==2){
                    if(!string.equals("Y")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".Y",10.0);
                    }
                }
                if(x==3){
                    if(!string.equals("Z")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".Z",10.0);
                    }
                }
                if(x==4){
                    if(!string.equals("YAW")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".YAW",10.0);
                    }
                }
                if(x==5){
                    if(!string.equals("PITCH")){
                        System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                        cfg.getConfig().set(string,null);
                        cfg.getConfig().set(path+".PITCH",10.0);
                    }
                }
            }
        }

        cfg.saveConfig();
    }
    public void locCheckThreeMultiple(ConfigManager cfg, String path, String baseType, @Nullable String baseTypeTwo){
        if(!cfg.getConfig().contains(path)){
            cfg.getConfig().createSection(path);
            System.out.println("Nie prawda ze zawiera section!");
        }
        if(cfg.getConfig().getConfigurationSection(path).getKeys(false).isEmpty()){

            System.out.println("Empty path next to: "+path);
            String baseIL =  path+"."+baseType+".";
            String pathT = baseIL+"X";
            cfg.getConfig().set(pathT,10.0);
            pathT = baseIL+"Y";
            cfg.getConfig().set(pathT,73.0);
            pathT = baseIL+"Z";
            cfg.getConfig().set(pathT,10.0);


            if(baseTypeTwo!=null){
                String baseILTwo =  path+"."+baseTypeTwo+".";
                String pathD = baseILTwo+"X";
                cfg.getConfig().set(pathD,10.0);
                pathD = baseILTwo+"Y";
                cfg.getConfig().set(pathD,73.0);
                pathD = baseILTwo+"Z";
                cfg.getConfig().set(pathD,10.0);
            }

        }else{
            int x = 0;
            for (String string : cfg.getConfig().getConfigurationSection(path).getKeys(false)) {
                if(!cfg.getConfig().contains(path+"."+string)){
                    cfg.getConfig().createSection(path+"."+string);
                }
                if(cfg.getConfig().getConfigurationSection(path+"."+string).getKeys(false).isEmpty()){
                    String baseIL =  path+"."+string+".";
                    path = baseIL+"X";
                    cfg.getConfig().set(path,10.0);
                    path = baseIL+"Y";
                    cfg.getConfig().set(path,73.0);
                    path = baseIL+"Z";
                    cfg.getConfig().set(path,10.0);

                }else{
                    for(String s:cfg.getConfig().getConfigurationSection(path+"."+string).getKeys(false)){
                        x++;
                        if(x==4){
                            break;
                        }
                        if(x==1){
                            if(!s.equals("X")){
                                System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".X",10.0);
                            }
                        }
                        if(x==2){
                            if(!s.equals("Y")){
                                System.out.println("Path value should equal 'Y' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".Y",73.0);
                            }
                        }
                        if(x==3){
                            if(!s.equals("Z")){
                                System.out.println("Path value should equal 'Z' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".Z",10.0);
                            }
                        }
                    }
                }
            }
        }
        cfg.saveConfig();
    }
    public void locCheckFiveMultiple(ConfigManager cfg,String path,String baseType,@Nullable String baseTypeTwo){
        if(!cfg.getConfig().contains(path)){
            cfg.getConfig().createSection(path);
        }
        if(cfg.getConfig().getConfigurationSection(path).getKeys(false).isEmpty()){
            System.out.println("Empty path next to: "+path);
            String baseIL =  path+"."+baseType+".";
            String pathT = baseIL+"X";
            cfg.getConfig().set(pathT,10.0);
            pathT = baseIL+"Y";
            cfg.getConfig().set(pathT,73.0);
            pathT = baseIL+"Z";
            cfg.getConfig().set(pathT,10.0);
            pathT = baseIL+"YAW";
            cfg.getConfig().set(pathT,10.0);
            pathT = baseIL+"PITCH";
            cfg.getConfig().set(pathT,10.0);
            if(baseTypeTwo!=null){
                String baseILTwo =  path+"."+baseTypeTwo+".";
                String pathD = baseILTwo+"X";
                cfg.getConfig().set(pathD,10.0);
                pathD = baseILTwo+"Y";
                cfg.getConfig().set(pathD,73.0);
                pathD = baseILTwo+"Z";
                cfg.getConfig().set(pathD,10.0);
                pathD = baseILTwo+"YAW";
                cfg.getConfig().set(pathD,10.0);
                pathD = baseILTwo+"PITCH";
                cfg.getConfig().set(pathD,10.0);
            }

        }else{
            int x = 0;

            for (String string : cfg.getConfig().getConfigurationSection(path).getKeys(false)) {
                if(!cfg.getConfig().contains(path+"."+string)){
                    cfg.getConfig().createSection(path+"."+string);
                }
                if(cfg.getConfig().getConfigurationSection(path+"."+string).getKeys(false).isEmpty()){
                    String baseIL =  path+"."+string+".";
                    path = baseIL+"X";
                    cfg.getConfig().set(path,10.0);
                    path = baseIL+"Y";
                    cfg.getConfig().set(path,73.0);
                    path = baseIL+"Z";
                    cfg.getConfig().set(path,10.0);
                    path = baseIL+"YAW";
                    cfg.getConfig().set(path,10.0);
                    path = baseIL+"PITCH";
                    cfg.getConfig().set(path,10.0);

                }else{
                    for(String s:cfg.getConfig().getConfigurationSection(path+"."+string).getKeys(false)){
                        x++;
                        if(x==6){
                            break;
                        }
                        if(x==1){
                            if(!s.equals("X")){
                                System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".X",10.0);

                            }
                        }
                        if(x==2){
                            if(!s.equals("Y")){
                                System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".Y",10.0);

                            }
                        }
                        if(x==3){
                            if(!s.equals("Z")){
                                System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".Z",10.0);

                            }
                        }
                        if(x==4){
                            if(!s.equals("YAW")){
                                System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".YAW",10.0);

                            }
                        }
                        if(x==5){
                            if(!s.equals("PITCH")){
                                System.out.println("Path value should equal 'X' and it equals "+string+" near path: "+path);
                                cfg.getConfig().set(string,null);
                                cfg.getConfig().set(path+".PITCH",10.0);

                            }
                        }
                    }
                }
            }
        }
        cfg.saveConfig();
    }
    }




