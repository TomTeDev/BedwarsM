package bedwars.bedwarsmm.Maps;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class IsUpgradeDone {
    UUID mapid;
    UUID teamid;
    UUID upgradeID;
    static HashMap<UUID,HashMap<UUID, List<UUID>>> complexData = new HashMap<>();
    public IsUpgradeDone(UUID mapid, UUID teamID, UUID upgradeid){
        this.mapid = mapid;
        this.teamid = teamID;
        this.upgradeID = upgradeid;
    }
    public boolean getUpgradeDone(){
        if(!complexData.containsKey(mapid))return false;
        HashMap<UUID,List<UUID>> upgrades = complexData.get(mapid);
        if(!upgrades.containsKey(upgradeID))return false;
        List<UUID> teamsThatHaveDoneThatUpgrade = upgrades.get(upgradeID);
        return teamsThatHaveDoneThatUpgrade.contains(teamid);
    }

    public void setUpgradeDone(){
        if(complexData.containsKey(mapid)){
            HashMap<UUID,List<UUID>> upgrades = complexData.get(mapid);
            if(upgrades.containsKey(teamid)){
                List<UUID> teamsThatHaveDoneThatUpgrade = upgrades.get(upgradeID);
                if(!teamsThatHaveDoneThatUpgrade.contains(teamid)){
                    teamsThatHaveDoneThatUpgrade.add(teamid);
                    upgrades.put(upgradeID,teamsThatHaveDoneThatUpgrade);
                    complexData.put(mapid,upgrades);
                }
            }else{
                List<UUID> teamsThatHaveDoneThatUpgrade = new ArrayList<>();
                teamsThatHaveDoneThatUpgrade.add(teamid);
                upgrades.put(upgradeID,teamsThatHaveDoneThatUpgrade);
                complexData.put(mapid,upgrades);
            }

        }else{
            HashMap<UUID,List<UUID>> upgrades = new HashMap<>();
            List<UUID> teamsThatHaveDoneThatUpgrade = new ArrayList<>();
            teamsThatHaveDoneThatUpgrade.add(teamid);
            upgrades.put(upgradeID,teamsThatHaveDoneThatUpgrade);
            complexData.put(mapid,upgrades);
            }
        }
}

