package bedwars.bedwarsmm;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class SomeListeners implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        ServerDataClass sdc = new ServerDataClass();
        boolean isOnSpawn = sdc.isOnSpawn(e.getBlock().getLocation());
        if(!isOnSpawn)return;
        if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))return;
        e.setCancelled(true);
    }
    private ServerDataClass sdc = new ServerDataClass();
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){

        boolean isOnSpawn = sdc.isOnSpawn(e.getBlock().getLocation());
        if(!isOnSpawn)return;
        if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))return;
        e.setCancelled(true);
    }
    @EventHandler
    public void hunger(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

}
