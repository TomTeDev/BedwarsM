package bedwars.bedwarsmm;

import bedwars.bedwarsmm.Maps.MapsLoad;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import java.lang.reflect.Field;
import java.util.*;

abstract class  NPCData{

    abstract int getSolosX();
    abstract int getSolosY();
    abstract int getSolosZ();
    abstract float getSolosYaw();
    abstract float getSolosPitch();

    abstract int getDuosX();
    abstract int getDuosY();
    abstract int getDuosZ();
    abstract float getDuosYaw();
    abstract float getDuosPitch();

    abstract int getQuadsX();
    abstract int getQuadsY();
    abstract int getQuadsZ();
    abstract float getQuadsYaw();
    abstract float getQuadsPitch();

    abstract int getTwoVTwoX();
    abstract int getTwoVTwoY();
    abstract int getTwoVTwoZ();
    abstract float getTwoVTwoYaw();
    abstract float getTwoVTwoPitch();

    abstract int getFourVFourX();
    abstract int getFourVFourY();
    abstract int getFourVFourZ();
    abstract float getFourVFourYaw();
    abstract float getFourVFourPitch();

    abstract String getSolosName();
    abstract String getDuosName();
    abstract String getQuadsName();
    abstract String getTwoVTwoName();
    abstract String getFourVFourName();


    abstract String getSolosTexture();
    abstract String getDuosTexture();
    abstract String getQuadsTexture();
    abstract String getTwoVTwoTexture();
    abstract String getFourVFourTexture();

    abstract String getSolosSignature();
    abstract String getDuosSignature();
    abstract String getQuadsSignature();
    abstract String getTwoVTwoSignature();
    abstract String getFourVFourSignature();

}
class NPCDataClass extends  NPCData{
    public NPCDataClass(){

    }

    @Override
    int getSolosX() {
        return 202;
    }

    @Override
    int getSolosY() {
        return 63;
    }

    @Override
    int getSolosZ() {
        return 5;
    }

    @Override
    float getSolosYaw() {
        return 0.5f;
    }

    @Override
    float getSolosPitch() {
        return 0.5f;
    }

    @Override
    int getDuosX() {
        return 203;
    }

    @Override
    int getDuosY() {
        return 63;
    }

    @Override
    int getDuosZ() {
        return 5;
    }

    @Override
    float getDuosYaw() {
        return 0.5f;
    }

    @Override
    float getDuosPitch() {
        return 0.5f;
    }

    @Override
    int getQuadsX() {
        return 204;
    }

    @Override
    int getQuadsY() {
        return 63;
    }

    @Override
    int getQuadsZ() {
        return 5;
    }

    @Override
    float getQuadsYaw() {
        return 0.5f;
    }

    @Override
    float getQuadsPitch() {
        return 0.5f;
    }

    @Override
    int getTwoVTwoX() {
        return 205;
    }

    @Override
    int getTwoVTwoY() {
        return 63;
    }

    @Override
    int getTwoVTwoZ() {
        return 5;
    }

    @Override
    float getTwoVTwoYaw() {
        return 0.5f;
    }

    @Override
    float getTwoVTwoPitch() {
        return 0.5f;
    }

    @Override
    int getFourVFourX() {
        return 206;
    }

    @Override
    int getFourVFourY() {
        return 63;
    }

    @Override
    int getFourVFourZ() {
        return 5;
    }

    @Override
    float getFourVFourYaw() {
        return 0.5f;
    }

    @Override
    float getFourVFourPitch() {
        return 0.5f;
    }

    @Override
    String getSolosName() {
        return "E";
    }

    @Override
    String getDuosName() {
        return "c";
    }

    @Override
    String getQuadsName() {
        return "d";
    }

    @Override
    String getTwoVTwoName() {
        return "f";
    }

    @Override
    String getFourVFourName() {
        return "g";
    }



    @Override
    String getSolosTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYzMDA4MDM3MzQ5MiwKICAicHJvZmlsZUlkIiA6ICI2ZWZlN2UzZWNlMTA0MmEyYjYyM2FiMmM0MzBjODZjMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSSUNFRkFSTUVSS0FUT1NBIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2VlYzZjOGViZjUxOWZjYmYxMDBhMzI4NThjNjBjYmNmOTMzYjMwYWNjMTcyODE4NTEwODc4NGQzY2IwODA1OWQiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
    }

    @Override
    String getDuosTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYzMDA4MDM3MzQ5MiwKICAicHJvZmlsZUlkIiA6ICI2ZWZlN2UzZWNlMTA0MmEyYjYyM2FiMmM0MzBjODZjMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSSUNFRkFSTUVSS0FUT1NBIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2VlYzZjOGViZjUxOWZjYmYxMDBhMzI4NThjNjBjYmNmOTMzYjMwYWNjMTcyODE4NTEwODc4NGQzY2IwODA1OWQiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
    }

    @Override
    String getQuadsTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxNzA2NzM5Mzk5MywKICAicHJvZmlsZUlkIiA6ICJjMzIwNmJmNjBhZDg0ZWZjOWY0YjFjNDgxNDQ2ZjA1MiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MThEYW4xNiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNmVlNDM0NzUxODNjMmE2NDcwMTU2MTU3OTg1Y2IxMzcwMjA2NzBmNjExNDc4NmY0ZDcyOGI5ZGU4ZjI5NDgwIgogICAgfQogIH0KfQ==";
    }

    @Override
    String getTwoVTwoTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxNzA2NzM5Mzk5MywKICAicHJvZmlsZUlkIiA6ICJjMzIwNmJmNjBhZDg0ZWZjOWY0YjFjNDgxNDQ2ZjA1MiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MThEYW4xNiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNmVlNDM0NzUxODNjMmE2NDcwMTU2MTU3OTg1Y2IxMzcwMjA2NzBmNjExNDc4NmY0ZDcyOGI5ZGU4ZjI5NDgwIgogICAgfQogIH0KfQ==";
    }

    @Override
    String getFourVFourTexture() {
        return "ewogICJ0aW1lc3RhbXAiIDogMTYxNzA2NzM5Mzk5MywKICAicHJvZmlsZUlkIiA6ICJjMzIwNmJmNjBhZDg0ZWZjOWY0YjFjNDgxNDQ2ZjA1MiIsCiAgInByb2ZpbGVOYW1lIiA6ICI0MThEYW4xNiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNmVlNDM0NzUxODNjMmE2NDcwMTU2MTU3OTg1Y2IxMzcwMjA2NzBmNjExNDc4NmY0ZDcyOGI5ZGU4ZjI5NDgwIgogICAgfQogIH0KfQ==";
    }

    @Override
    String getSolosSignature() {
        return "tO8KXAVVS3hf/ivAVALEycdg6iF5VoTpFAJsjt6QpSagItSMg6BExiUt7sggRxSLSdqf7shx1cNwwWegjrfAlDLy+lnTWZ5RDDWL1cJ5qqcCcyMWPHthlw9ET86ub89cMOqdcguW7SbYUlp0Zur18Q9H7oaFtD9nqOAjT8KZoUG7rd7KY3x3MlI/0K+giA5TPA4GRiZ7Le2eX97T23HHM0Nwb8KQPJpQdCz2ikUR3eS7sG3rRu1zaX41Ydmtio2ueUfNgEwxHARvgi/rbQxdPU4uEDNwE7RoTNFEfmKMzkgJRN76PAOZNc9D+9NIUyl4oohlQ9Ab4V5AvuEf1wQXLhSFieqRyhcqV//spSdoTZytbv1G4fDUkDGUPQfL31h30lpOe2mdlu6GqqjJPjIX52SmbmHiXburzjha3VT8KmsHKzX2glfI7J/oUxcgybMXvzYKtkLMkcvbigeCmdl5QtCtjQ0WTO+onQ72FMpjOLGQ9dwMHmuT73d+54xUTECgrcACZBFNGWE02IJWKm61tj0nYoTmVIk/fLv/QATvlFxRob1RzQrRqIh28EwQuL9zDaADx3MRPpJTxDumEbFd4n8NvqBIVimmVmPuQ2tWDPeD640aPF1vHZy4z1rt7sY6u/ocrwvF01vCOZB4kXo0aszl9ojXyLRJTVnll/2GKwY=";
    }

    @Override
    String getDuosSignature() {
        return "tO8KXAVVS3hf/ivAVALEycdg6iF5VoTpFAJsjt6QpSagItSMg6BExiUt7sggRxSLSdqf7shx1cNwwWegjrfAlDLy+lnTWZ5RDDWL1cJ5qqcCcyMWPHthlw9ET86ub89cMOqdcguW7SbYUlp0Zur18Q9H7oaFtD9nqOAjT8KZoUG7rd7KY3x3MlI/0K+giA5TPA4GRiZ7Le2eX97T23HHM0Nwb8KQPJpQdCz2ikUR3eS7sG3rRu1zaX41Ydmtio2ueUfNgEwxHARvgi/rbQxdPU4uEDNwE7RoTNFEfmKMzkgJRN76PAOZNc9D+9NIUyl4oohlQ9Ab4V5AvuEf1wQXLhSFieqRyhcqV//spSdoTZytbv1G4fDUkDGUPQfL31h30lpOe2mdlu6GqqjJPjIX52SmbmHiXburzjha3VT8KmsHKzX2glfI7J/oUxcgybMXvzYKtkLMkcvbigeCmdl5QtCtjQ0WTO+onQ72FMpjOLGQ9dwMHmuT73d+54xUTECgrcACZBFNGWE02IJWKm61tj0nYoTmVIk/fLv/QATvlFxRob1RzQrRqIh28EwQuL9zDaADx3MRPpJTxDumEbFd4n8NvqBIVimmVmPuQ2tWDPeD640aPF1vHZy4z1rt7sY6u/ocrwvF01vCOZB4kXo0aszl9ojXyLRJTVnll/2GKwY=";
    }

    @Override
    String getQuadsSignature() {
        return "vYqE0Jf28bmvoR3/frwJ3bvCL45V6a4VioXXiLuN7qi3k3dDiwLkktlZaRe2uyw+WbpUKHjKz/wYy20f1KK5j/MaMGq9StbrqTxaV0SgsYSdIK5tZKLZeUwJXSORH7DFEfqd3/+flWB2EEybycNTbqp+umUYXOvUVDtN34RoyZC1c85H5LlwlbDbyar5wdHCjjKoykI6Sg/sUH6Py6/w9HfPpaDykfeLkbUk0kQgcG+FAKZFDLZgOXHV4hyuzEkCfks2DELmoY6CGoFYlDTzELXbblyWhrloop8BYP9UXpObp2MgmmFFqhL2a1DSyS4TMO7PatyfqoWvLX7bcIaWmzvbFfgzPQMoVLjyiFqWLwdRpI+sYPU46hFz3PCV9a5DRSBRBOhhYm4gdh8AJcU6k/zCIhmHak4YHAl5wsyDi2QuHEIov4FqdeTPTCBgidIfncx5dCnUyj9VmPbOq9ZEAEyGYt8HSLN0/BEcGchPRmAiCUu3ICuGSow9n562LGnqdNRQJZgNt4tpO5n/oqkFA88rQLe4rOOIy26um8WLTU7Tj1Wq8UlEWQX0Zxu4gUKLfuMsgWTlS2tiNpsOITJaARHzOTYYuHPHt1KxnI3DPuIBzxW8/DyngaputGmbrbV7HhXiNE82oGQZDZ5LIfxAULeEdoG8lQo88R3sfWGmOTc=";
    }

    @Override
    String getTwoVTwoSignature() {
        return "vYqE0Jf28bmvoR3/frwJ3bvCL45V6a4VioXXiLuN7qi3k3dDiwLkktlZaRe2uyw+WbpUKHjKz/wYy20f1KK5j/MaMGq9StbrqTxaV0SgsYSdIK5tZKLZeUwJXSORH7DFEfqd3/+flWB2EEybycNTbqp+umUYXOvUVDtN34RoyZC1c85H5LlwlbDbyar5wdHCjjKoykI6Sg/sUH6Py6/w9HfPpaDykfeLkbUk0kQgcG+FAKZFDLZgOXHV4hyuzEkCfks2DELmoY6CGoFYlDTzELXbblyWhrloop8BYP9UXpObp2MgmmFFqhL2a1DSyS4TMO7PatyfqoWvLX7bcIaWmzvbFfgzPQMoVLjyiFqWLwdRpI+sYPU46hFz3PCV9a5DRSBRBOhhYm4gdh8AJcU6k/zCIhmHak4YHAl5wsyDi2QuHEIov4FqdeTPTCBgidIfncx5dCnUyj9VmPbOq9ZEAEyGYt8HSLN0/BEcGchPRmAiCUu3ICuGSow9n562LGnqdNRQJZgNt4tpO5n/oqkFA88rQLe4rOOIy26um8WLTU7Tj1Wq8UlEWQX0Zxu4gUKLfuMsgWTlS2tiNpsOITJaARHzOTYYuHPHt1KxnI3DPuIBzxW8/DyngaputGmbrbV7HhXiNE82oGQZDZ5LIfxAULeEdoG8lQo88R3sfWGmOTc=";
    }

    @Override
    String getFourVFourSignature() {
        return "vYqE0Jf28bmvoR3/frwJ3bvCL45V6a4VioXXiLuN7qi3k3dDiwLkktlZaRe2uyw+WbpUKHjKz/wYy20f1KK5j/MaMGq9StbrqTxaV0SgsYSdIK5tZKLZeUwJXSORH7DFEfqd3/+flWB2EEybycNTbqp+umUYXOvUVDtN34RoyZC1c85H5LlwlbDbyar5wdHCjjKoykI6Sg/sUH6Py6/w9HfPpaDykfeLkbUk0kQgcG+FAKZFDLZgOXHV4hyuzEkCfks2DELmoY6CGoFYlDTzELXbblyWhrloop8BYP9UXpObp2MgmmFFqhL2a1DSyS4TMO7PatyfqoWvLX7bcIaWmzvbFfgzPQMoVLjyiFqWLwdRpI+sYPU46hFz3PCV9a5DRSBRBOhhYm4gdh8AJcU6k/zCIhmHak4YHAl5wsyDi2QuHEIov4FqdeTPTCBgidIfncx5dCnUyj9VmPbOq9ZEAEyGYt8HSLN0/BEcGchPRmAiCUu3ICuGSow9n562LGnqdNRQJZgNt4tpO5n/oqkFA88rQLe4rOOIy26um8WLTU7Tj1Wq8UlEWQX0Zxu4gUKLfuMsgWTlS2tiNpsOITJaARHzOTYYuHPHt1KxnI3DPuIBzxW8/DyngaputGmbrbV7HhXiNE82oGQZDZ5LIfxAULeEdoG8lQo88R3sfWGmOTc=";
    }
}

public class NPC {
    static HashMap<UUID,List<EntityPlayer>> upgradeNpcsPerMap= new HashMap<>();
    public List<EntityPlayer> getUpgradeNpcs(UUID mapid){
        return upgradeNpcsPerMap.getOrDefault(mapid,new ArrayList<>());
    }
    public List<EntityPlayer> getShopNpcs(UUID mapid){
        return shopNpcsPerMap.getOrDefault(mapid,new ArrayList<>());
    }
    static HashMap<UUID,List<EntityPlayer>> shopNpcsPerMap= new HashMap<>();
    static List<EntityPlayer> npcts = new ArrayList<>();
    public boolean startUP(){
        NPCDataClass npcData = new NPCDataClass();


        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Objects.requireNonNull(Bukkit.getWorld("world"))).getHandle();
        GameProfile gameprofile = new GameProfile(UUID.randomUUID(), npcData.getSolosName());
        gameprofile.getProperties().put("texture", new Property("textures", npcData.getSolosTexture(), npcData.getSolosSignature()));
        PlayerInteractManager playerinteractmanager = new PlayerInteractManager(((CraftWorld)Bukkit.getWorld("world")).getHandle());
        EntityPlayer npc = new EntityPlayer(server, world, gameprofile,playerinteractmanager);
        npc.setLocation(npcData.getSolosX(),npcData.getSolosY(),npcData.getSolosZ(),npcData.getSolosYaw(), npcData.getSolosPitch());
       npcts.add(npc);

        server = ((CraftServer) Bukkit.getServer()).getServer();
        world = ((CraftWorld) Objects.requireNonNull(Bukkit.getWorld("world"))).getHandle();
        gameprofile = new GameProfile(UUID.randomUUID(), npcData.getDuosName());
        gameprofile.getProperties().put("texture", new Property("textures", npcData.getDuosTexture(), npcData.getDuosSignature()));
        playerinteractmanager = new PlayerInteractManager(((CraftWorld)Bukkit.getWorld("world")).getHandle());
        npc = new EntityPlayer(server, world, gameprofile,playerinteractmanager);
        npc.setLocation(npcData.getDuosX(),npcData.getDuosY(),npcData.getDuosZ(),npcData.getDuosYaw(), npcData.getDuosPitch());
        npcts.add(npc);

        server = ((CraftServer) Bukkit.getServer()).getServer();
        world = ((CraftWorld) Objects.requireNonNull(Bukkit.getWorld("world"))).getHandle();
        gameprofile = new GameProfile(UUID.randomUUID(), npcData.getQuadsName());
        gameprofile.getProperties().put("texture", new Property("textures", npcData.getQuadsTexture(), npcData.getQuadsSignature()));
        playerinteractmanager = new PlayerInteractManager(((CraftWorld)Bukkit.getWorld("world")).getHandle());
        npc = new EntityPlayer(server, world, gameprofile,playerinteractmanager);
        npc.setLocation(npcData.getQuadsX(),npcData.getQuadsY(),npcData.getQuadsZ(),npcData.getQuadsYaw(), npcData.getQuadsPitch());
        npcts.add(npc);

        server = ((CraftServer) Bukkit.getServer()).getServer();
        world = ((CraftWorld) Objects.requireNonNull(Bukkit.getWorld("world"))).getHandle();
        gameprofile = new GameProfile(UUID.randomUUID(), npcData.getTwoVTwoName());
        gameprofile.getProperties().put("texture", new Property("textures", npcData.getTwoVTwoTexture(), npcData.getTwoVTwoSignature()));
        playerinteractmanager = new PlayerInteractManager(((CraftWorld)Bukkit.getWorld("world")).getHandle());
        npc = new EntityPlayer(server, world, gameprofile,playerinteractmanager);
        npc.setLocation(npcData.getTwoVTwoX(),npcData.getTwoVTwoY(),npcData.getTwoVTwoZ(),npcData.getTwoVTwoYaw(), npcData.getTwoVTwoPitch());
        npcts.add(npc);

        server = ((CraftServer) Bukkit.getServer()).getServer();
        world = ((CraftWorld) Objects.requireNonNull(Bukkit.getWorld("world"))).getHandle();
        gameprofile = new GameProfile(UUID.randomUUID(), npcData.getFourVFourName());
        gameprofile.getProperties().put("texture", new Property("textures", npcData.getFourVFourTexture(), npcData.getFourVFourSignature()));
        playerinteractmanager = new PlayerInteractManager(((CraftWorld)Bukkit.getWorld("world")).getHandle());
        npc = new EntityPlayer(server, world, gameprofile,playerinteractmanager);
        npc.setLocation(npcData.getFourVFourX(),npcData.getFourVFourY(),npcData.getFourVFourZ(),npcData.getFourVFourYaw(), npcData.getFourVFourPitch());
        npcts.add(npc);
       return true;
    }
    private MapsLoad ml = new MapsLoad();
    private String[] getNpcUpgradeData(UUID mapid){
        String[] strings = new String[3];
        strings[0]= ml.getNpcUpgradeDisplayName(mapid);
        strings[1]= ml.getNpcUpgradeTexture(mapid);
        strings[2]= ml.getNpcUpgradeSignature(mapid);
        return strings;
    }
    private String[] getNpcShopData(UUID mapid){
        String[] strings = new String[3];
        strings[0]=  ml.getNpcShopDisplayName(mapid);
        strings[1]= ml.getNpcShopTexture(mapid);
        strings[2]= ml.getNpcShopSignature(mapid);
        return strings;
    }

    public void loadMAPNPCTS(){
        for(UUID mapid:ml.getMapIdsList()){
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            String bukkitWorld = ml.getWorldName(mapid);
            WorldServer world = ((CraftWorld) Objects.requireNonNull(Bukkit.getWorld(bukkitWorld))).getHandle();
            PlayerInteractManager playerinteractmanager = null;
            GameProfile gameprofile = null;
            List<EntityPlayer> upgradeNpcs = new ArrayList<>();
            List<EntityPlayer> shopNpcs = new ArrayList<>();
            List<UUID> teamList = ml.getTeamsForThatMap(mapid);
            String[] stringsUpgrade = getNpcUpgradeData(mapid);
            String[] stringsShop = getNpcShopData(mapid);
            for(UUID teamid:teamList){

                Location upgradeLoc = ml.getTeamUpgradeManLoc(teamid);
                Location shopLoc = ml.getTeamShopManLoc(teamid);
                playerinteractmanager = new PlayerInteractManager(((CraftWorld)Bukkit.getWorld(bukkitWorld)).getHandle());
                gameprofile = new GameProfile(UUID.randomUUID(), stringsUpgrade[0]);
                gameprofile.getProperties().put("texture", new Property("textures",stringsUpgrade[1],stringsUpgrade[2]));
                EntityPlayer npc = new EntityPlayer(server, world, gameprofile,playerinteractmanager);
                npc.setLocation(upgradeLoc.getX(),upgradeLoc.getY(),upgradeLoc.getZ(),upgradeLoc.getYaw(),upgradeLoc.getPitch());
                upgradeNpcs.add(npc);
                npc=null;
                gameprofile=null;
                playerinteractmanager=null;

                playerinteractmanager = new PlayerInteractManager(((CraftWorld)Bukkit.getWorld(bukkitWorld)).getHandle());
                gameprofile = new GameProfile(UUID.randomUUID(), stringsShop[0]);
                gameprofile.getProperties().put("texture", new Property("textures",stringsShop[1],stringsShop[2]));
                npc = new EntityPlayer(server, world, gameprofile,playerinteractmanager);
                npc.setLocation(shopLoc.getX(),shopLoc.getY(),shopLoc.getZ(),shopLoc.getYaw(),shopLoc.getPitch());
                shopNpcs.add(npc);
            }
            upgradeNpcsPerMap.put(mapid,upgradeNpcs);
            shopNpcsPerMap.put(mapid,shopNpcs);
        }
    }

    public void sendMapNpc(Player p,UUID mapid){
        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        List<EntityPlayer> npcs = getUpgradeNpcs(mapid);
        List<EntityPlayer> npcs2 = getShopNpcs(mapid);

        for(EntityPlayer npc:npcs){
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            //connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc,(byte)npc.pitch));
            DataWatcher watcher = npc.getDataWatcher();
            byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.watch(10, (byte) 127);
            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId() , watcher, true));
        }
        for(EntityPlayer npc:npcs2){
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            //connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc,(byte)npc.pitch));
            DataWatcher watcher = npc.getDataWatcher();
            byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.watch(10, (byte) 127);
            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId() , watcher, true));
        }
    }


    public  void addNpcPackets (Player player){
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        for(EntityPlayer npc:npcts){
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            //connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc,(byte)npc.pitch));
            DataWatcher watcher = npc.getDataWatcher();
            byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40;
            watcher.watch(10, (byte) 127);
            connection.sendPacket(new PacketPlayOutEntityMetadata(npc.getId() , watcher, true));

        }



    }
}
