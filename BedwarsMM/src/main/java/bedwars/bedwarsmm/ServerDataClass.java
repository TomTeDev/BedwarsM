package bedwars.bedwarsmm;

import org.bukkit.Location;

public class ServerDataClass extends ServerData{

    public ServerDataClass(){

    }

    @Override
    public Location getSpawnlocation() {
        return super.getSpawnlocation();
    }

    boolean isOnSpawn(Location loc){
        return ((loc.getX()>x1Spawn&&loc.getZ()>z1Spawn)&&(loc.getX()<x2Spawn&&loc.getZ()<z2Spawn));
    }
}
