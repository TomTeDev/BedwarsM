package bedwars.bedwarsmm;

import bedwars.bedwarsmm.Maps.MapHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PacketReader{
public static Map<String, Channel> channels = new HashMap<String, Channel>();


public void inject(Player player){
        Bukkit.broadcastMessage("DEBUG33");
        if(channels.containsKey(player.getName()))return;
        CraftPlayer craftPlayer = (CraftPlayer) player;
        Channel channel = craftPlayer.getHandle().playerConnection.a().channel;
        if(channel.pipeline().get("PacketInjector")==null){
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<PacketPlayInUseEntity>() {
@Override
protected void decode(ChannelHandlerContext channel, PacketPlayInUseEntity packet, List<Object> arg) throws Exception{
        arg.add(packet);
        readPacket(player,packet);
        }
        });
        channels.put(player.getName(),channel);
        }
        }

public void readPacket (Player player, Packet<?> packet) {

        if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
        PacketPlayInUseEntity.EnumEntityUseAction ene = (PacketPlayInUseEntity.EnumEntityUseAction)getValue(packet, "action");
        int a = (int) getValue(packet,"a");
        switch (ene){
        case ATTACK:
        case INTERACT: {

        List<EntityPlayer>npc = NPC.npcts;
        for(EntityPlayer entityPlayer :npc){
        int id = entityPlayer.getId();
        if(id==a){

        openRightGui(player,entityPlayer.getName());
        break;
        }

        }
        PlayerDataClass pdc  = new PlayerDataClass(player.getLocation(),player);

        if(pdc.getCurrentMap()==null)break;

        UUID idMap = pdc.getCurrentMap();

        NPC npcClass = new NPC();
        List<EntityPlayer> listUpgrade =  npcClass.getUpgradeNpcs(idMap);
        a = (int) getValue(packet,"a");
        for(EntityPlayer ep :listUpgrade){

        if(ep.getId()==a){
        player.sendMessage("ITS UPGRADE BOII");
                MapHandler mh = new MapHandler();
                player.openInventory(mh.createUpgradeInventory(player));
        break;

        }
        }
        List<EntityPlayer> listShop = npcClass.getShopNpcs(idMap);
        a = (int) getValue(packet,"a");
        for(EntityPlayer ep :listShop){
        if(ep.getId()==a){
        player.sendMessage("ITS SHOP BOII");
        MapHandler mh = new MapHandler();
        player.openInventory(mh.createShopInventory(player));
        break;
        }
        }
        break;
        }
        case INTERACT_AT:{
        break;
        }
default:break;
        }
        }
        }
public void openRightGui(Player p,String npcName){
/*        GUI g = new GUI();
        NPCDataClass npcdata = new NPCDataClass();
       if(npcName.equals(npcdata.getSolosName())){p.openInventory(g.invSolos());return;}
       if(npcName.equals(npcdata.getDuosName())){p.openInventory(g.invDuos());return;}
       if(npcName.equals(npcdata.getQuadsName())){p.openInventory(g.invQuads());return;}
       if(npcName.equals(npcdata.getTwoVTwoName())){p.openInventory(g.invTwoVTwo());return;}
       if(npcName.equals(npcdata.getFourVFourName())){p.openInventory(g.invFourVFour());return;}*/
        }
private Object getValue(Object instance, String name){
        Object result  = null;
        try{
        Field field = instance.getClass().getDeclaredField(name);
        field.setAccessible(true);
        result = field.get(instance);
        field.setAccessible(false);
        }catch(Exception e){
        e.printStackTrace();
        }
        return result;
        }

        }