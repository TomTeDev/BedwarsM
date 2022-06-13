package bedwars.bedwarsmm.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UpgradesSorter {

    static HashMap<UUID, List<UUID>> upgradeWeapons = new HashMap<>();
    static HashMap<UUID,List<UUID>> upgradeArmor = new HashMap<>();
    static HashMap<UUID,List<UUID>> upgradeTools = new HashMap<>();
    static HashMap<UUID,List<UUID>> upgradeResources = new HashMap<>();
    static HashMap<UUID,List<UUID>> upgradeBuffs = new HashMap<>();
    static HashMap<UUID,List<UUID>> upgradeTraps = new HashMap<>();


    UUID mapid;
    public UpgradesSorter(UUID mapid){

        this.mapid = mapid;
    }


    public List<UUID> getUpgradesWeapons(){
        return upgradeWeapons.getOrDefault(mapid,new ArrayList<>());
    }
    public List<UUID> getUpgradesArmors(){
        return upgradeArmor.getOrDefault(mapid,new ArrayList<>());
    }
    public List<UUID> getUpgradesTools(){
        return upgradeTools.getOrDefault(mapid,new ArrayList<>());
    }
    public List<UUID> getUpgradesResources(){
        return upgradeResources.getOrDefault(mapid,new ArrayList<>());
    }
    public List<UUID> getUpgradesBuffs(){
        return upgradeBuffs.getOrDefault(mapid,new ArrayList<>());
    }
    public List<UUID> getUpgradesTraps(){
        return upgradeTraps.getOrDefault(mapid,new ArrayList<>());
    }
    public void addUpgradeWeapon(UUID upgradeID){
        List<UUID> list = getUpgradesWeapons();
        list.add(upgradeID);
        upgradeWeapons.put(mapid,list);
    }
    public void addUpgradeArmor(UUID upgradeID){
        List<UUID> list = getUpgradesArmors();
        list.add(upgradeID);
        upgradeArmor.put(mapid,list);
    }
    public void addUpgradeTool(UUID upgradeID){
        List<UUID> list = getUpgradesTools();
        list.add(upgradeID);
        upgradeTools.put(mapid,list);
    }
    public void addUpgradeResource(UUID upgradeID){
        List<UUID> list = getUpgradesResources();
        list.add(upgradeID);
        upgradeResources.put(mapid,list);
    }
    public void addUpgradePotion(UUID upgradeID){
        List<UUID> list = getUpgradesBuffs();
        list.add(upgradeID);
        upgradeBuffs.put(mapid,list);
    }
    public void addUpgradeTrap(UUID upgradeID){
        List<UUID> list = getUpgradesTraps();
        list.add(upgradeID);
        upgradeTraps.put(mapid,list);
    }

}
