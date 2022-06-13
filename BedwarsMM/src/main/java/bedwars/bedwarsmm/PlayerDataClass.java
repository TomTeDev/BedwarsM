package bedwars.bedwarsmm;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataClass extends PlayerData{
    static HashMap<UUID,UUID> currentMap = new HashMap<>();
    static HashMap<UUID,UUID> currentTeamMap = new HashMap<>();
    public void setCurrentMap(UUID mapid){
        UUID id = p.getUniqueId();
        currentMap.put(id,mapid);
    }

    public void removeCurrentMap(){
        currentMap.remove(p.getUniqueId());
    }

    public UUID getCurrentMap(){
        return currentMap.getOrDefault(p.getUniqueId(),null);
    }

    public void setTeam(UUID teamId){
        UUID id = p.getUniqueId();
        currentTeamMap.put(id,teamId);
    }
    public void removeTeam(){
        currentTeamMap.remove(p.getUniqueId());
    }
    public UUID getTeam(){
        return currentTeamMap.getOrDefault(p.getUniqueId(),null);
    }
    public PlayerDataClass(Location location, Player player) {
        super(location, player);
    }
}