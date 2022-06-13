package bedwars.bedwarsmm;

import bedwars.bedwarsmm.Maps.MapHandler;
import bedwars.bedwarsmm.Maps.MapsLoad;
import bedwars.bedwarsmm.Maps.MapsSomething;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bedwars extends JavaPlugin {
    static Bedwars plugin;
    public static Bedwars getPlugin(){
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        LoadDataFromConfig ldfc = new LoadDataFromConfig();
        ldfc.isDataLoaded();
       // new IdontKnowYet().startUP();
        new NPC().startUP();
        registerEvents(this,new SomeListeners(),new MapsSomething(),new MapHandler());
        getCommand("join").setExecutor(new Commands());
        getCommand("spawn").setExecutor(new Commands());
        getCommand("debugg").setExecutor(new MapsSomething());
        MapsLoad ml = new MapsLoad();
        if(!ml.doesMapsExists()){
            System.out.println("Maps does not exist in maps config, disabling plugin");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        ml.loadMaps();

        MapsSomething ms = new MapsSomething();
        ms.load();
        NPC npc = new NPC();
        npc.loadMAPNPCTS();

        // Plugin startup logic

    }
    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);

        }
    }
    @Override
    public void onDisable() {
        plugin = null;

        // Plugin shutdown logic
    }
}
