package bedwars.bedwarsmm;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(!(sender instanceof Player))return false;
        Player p = (((Player) sender).getPlayer());
        Colors c = new Colors();

        if(s.equals("join")
        ){
       /*     GUI g = new GUI();
            p.openInventory(g.invMain());*/
        }
        if(s.equals("spawn")){

            ServerDataClass sdc = new ServerDataClass();
            p.teleport(sdc.getSpawnlocation());
            p.sendMessage(c.g+"You are now on spawn!");


            NPC npc = new NPC();
            npc.addNpcPackets(p);

        }

        return false;
    }
}
