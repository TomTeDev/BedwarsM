package bedwars.bedwarsmm;

import org.bukkit.Bukkit;
import org.bukkit.Location;

abstract class ServerData {

    ConfigManager manager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"server.yml");
    Config conf = new Config(manager);

    int getX1() {
        return conf.getInt("spawn.coordinates..x1");
    }
    int getZ1() {
        return conf.getInt("spawn.coordinates..z1");
    }
    int getX2() {
        return conf.getInt("spawn.coordinates..x2");
    }
    int getZ2() {
        return conf.getInt("spawn.coordinates..z2");
    }
    int getMainX() {
        return conf.getInt("spawn.coordinates..mainX");
    }
    int getMainY() {
        return conf.getInt("spawn.coordinates..mainY");
    }
    int getMainZ() {
        return conf.getInt("spawn.coordinates..mainZ");
    }
    int getMainYaw() {
        return conf.getInt("spawn.coordinates..mainYaw");
    }
    int getMainPitch() {
        return conf.getInt("spawn.coordinates..mainPitch");
    }

    String worldName = "world";
    double mainX = getMainX();
    double mainY =  getMainY();
    double mainZ = getMainZ();
    float mainYaw = (float)getMainYaw();
    float mainPitch = (float)getMainPitch();
    Location spawnlocation = new Location(Bukkit.getWorld(worldName),mainX,mainY,mainZ,mainYaw,mainPitch);

    int x1Spawn = getX1();
    int z1Spawn = getZ1();

    int x2Spawn = getX2();
    int z2Spawn = getZ2();

    Location getSpawnlocation(){
        return spawnlocation;
    }

}

