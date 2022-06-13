package bedwars.bedwarsmm;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.UUID;

abstract class PlayerData {

    Player p;
    Location loc;
    PlayerInventory inv;
    public PlayerData(Location location,Player player)
    {
     this.p = player;
     this.loc = location;
     this.inv = player.getInventory();
    }
    Player getPlayer(){
        return p;
    }
    Location getLocationPlayer(){
        return loc;
    }

    ItemStack[] getGear(){
        return p.getInventory().getArmorContents();
    }

    boolean setSword(ItemStack sword){

        for(int x = 0;x<36;x++){
            ItemStack item = inv.getItem(x);
            if(item==null)continue;
            if(!item.getType().name().contains("SWORD"))continue;
            inv.setItem(x,sword);
            return true;
        }
        int s = inv.firstEmpty();
        if(s<0)return false;
        inv.setItem(s,sword);
        return true;
    }
    boolean setHelmet(ItemStack item){
        inv.setHelmet(item);
        return true;
    }
    boolean setChestplate(ItemStack item){
        inv.setChestplate(item);
        return true;
    }
    boolean setLeggins(ItemStack item){
        inv.setLeggings(item);
        return true;
    }
    boolean setBoots(ItemStack item){
        inv.setBoots(item);
        return true;
    }

}


