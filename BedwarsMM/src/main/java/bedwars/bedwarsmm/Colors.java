package bedwars.bedwarsmm;

import org.bukkit.ChatColor;

public class Colors {
    ChatColor e = ChatColor.DARK_RED;
    ChatColor r = ChatColor.RED;
    ChatColor b = ChatColor.BLUE;
    ChatColor y = ChatColor.YELLOW;
    ChatColor a = ChatColor.AQUA;
    ChatColor g = ChatColor.GREEN;
    public String translate(String msg){
       return ChatColor.translateAlternateColorCodes('&',msg);
    }



}
