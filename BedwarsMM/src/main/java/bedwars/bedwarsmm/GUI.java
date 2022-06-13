//package bedwars.bedwarsmm;
//
//import org.bukkit.Bukkit;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.bukkit.material.Bed;
//
//import javax.persistence.Id;
//import java.util.ArrayList;
//import java.util.List;
//
//abstract class GUIData{
//    public GUIData(){
//
//    }
//    ConfigManager manager = new ConfigManager(Bedwars.getPlugin(Bedwars.class),"gui.yml");
//    Config conf = new Config(manager);
//    String getMainGuiTitle(){
//        return conf.getString("gui.titles..main_gui_title");
//    }
//    String getSolosGuiTitle() {
//        return conf.getString("gui.titles..solos_gui_title");
//    }
//
//    String getDuosGuiTitle() {
//        return conf.getString("gui.titles..duos_gui_title");
//    }
//
//    String getQuadsGuiTitle() {
//        return conf.getString("gui.titles..quads_gui_title");
//    }
//
//    String getTwoVTwoGuiTitle() {
//        return conf.getString("gui.titles..twovtwo_gui_title");
//    }
//
//    String getFourVFourGuiTitle() {
//        return conf.getString("gui.titles..fourvfour_gui_title");
//    }
//
//    String getSolosDisplayName() {
//        return conf.getString("gui.displays.main..solos_display");
//    }
//    String getDuosDisplayName() {
//        return conf.getString("gui.displays.main..duos_display");
//    }
//    String getQuadsDisplayName() {
//        return conf.getString("gui.displays.main..quads_display");
//    }
//    String getTwoVTwoDisplayName() {
//        return conf.getString("gui.displays.main..twovtwo_display");
//    }
//    String getFourVFourDisplayName() {
//        return conf.getString("gui.displays.main..fourvfour_display");
//    }
//    String getCloseButtonDisplayName() {
//        return conf.getString("gui.displays.buttons..close_button");
//    }
//    String getGoBackDisplayName() {
//        return conf.getString("gui.displays.buttons..go_back_button");
//    }
//
//
//    String getSolosFirstDisplayName() {
//        return conf.getString("gui.displays.side..solos_first_display");
//    }
//
//    String getSolosSecondDisplayName() {
//        return conf.getString("gui.displays.side..solos_second_display");
//    }
//
//    String getDuosFirstDisplayName() {
//        return conf.getString("gui.displays.side..duos_first_display");
//    }
//
//    String getDuosSecondDisplayName() {
//        return conf.getString("gui.displays.side..duos_second_display");
//    }
//
//    String getQuadsFirstDisplayName() {
//        return conf.getString("gui.displays.side..quads_first_display");
//    }
//
//    String getQuadsSecondDisplayName() {
//        return conf.getString("gui.displays.side..quads_second_display");
//    }
//
//    String getTwoVTwoFirstDisplayName() {
//        return conf.getString("gui.displays.side..twovtwo_first_display");
//    }
//
//    String getTwoVTwoSecondDisplayName() {
//        return conf.getString("gui.displays.side..twovtwo_second_display");
//    }
//
//    String getFourVFourFirstDisplayName() {
//        return conf.getString("gui.displays.side..fourvfour_first_display");
//    }
//
//    String getFourVFourSecondDisplayName() {
//        return conf.getString("gui.displays.side..fourvfour_second_display");
//    }
//
//
//
//    List<String> getCloseButtonLore() {
//        return conf.getListString("gui.lores.buttons..close_button");
//    }
//    List<String> getGoBackLore() {
//        return conf.getListString("gui.lores.buttons..go_back_button");
//    }
//    List<String> getSolosLore() {
//        return conf.getListString("gui.lores.main..solos_lore");
//    }
//    List<String> getDuosLore() {
//        return conf.getListString("gui.lores.main..duos_lore");
//    }
//    List<String> getQuadsLore() {
//        return conf.getListString("gui.lores.main..quads_lore");
//    }
//    List<String> getTwoVTwoLore() {
//        return conf.getListString("gui.lores.main..twovtwo_lore");
//    }
//    List<String> getFourVFourLore() {
//        return conf.getListString("gui.lores.main..fourvfour_lore");
//    }
//
//
//    List<String> getSolosFirstLore() {
//        return conf.getListString("gui.lores.side..solos_first_lore");
//    }
//
//    List<String> getSolosSecondLore() {
//        return conf.getListString("gui.lores.side..solos_second_lore");
//    }
//
//    List<String> getDuosFirstLore() {
//        return conf.getListString("gui.lores.side..duos_first_lore");
//    }
//
//    List<String> getDuosSecondLore() {
//        return conf.getListString("gui.lores.side..duos_second_lore");
//    }
//
//    List<String> getQuadsFirstLore() {
//        return conf.getListString("gui.lores.side..quads_first_lore");
//    }
//
//    List<String> getQuadsSecondLore() {
//        return conf.getListString("gui.lores.side..quads_second_lore");
//    }
//
//    List<String> getTwoVTwoFirstLore() {
//        return conf.getListString("gui.lores.side..twovtwo_first_lore");
//    }
//
//    List<String> getTwoVTwoSecondLore() {
//        return conf.getListString("gui.lores.side..twovtwo_second_lore");
//    }
//
//    List<String> getFourVFourFirstLore() {
//        return conf.getListString("gui.lores.side..fourvfour_first_lore");
//    }
//
//    List<String> getFourVFourSecondLore() {
//        return conf.getListString("gui.lores.side..fourvfour_second_lore");
//    }
//
//
//
//}
//class GUITitles extends GUIData{
//    public GUITitles(){
//
//    }
//
//    @Override
//    String getMainGuiTitle() {
//        return super.getMainGuiTitle();
//    }
//    @Override
//    String getSolosGuiTitle() {
//        return super.getSolosGuiTitle();
//    }
//    @Override
//    String getDuosGuiTitle() {
//        return super.getDuosGuiTitle();
//    }
//    @Override
//    String getQuadsGuiTitle() {
//        return super.getQuadsGuiTitle();
//    }
//    @Override
//    String getTwoVTwoGuiTitle() {
//        return super.getTwoVTwoGuiTitle();
//    }
//    @Override
//    String getFourVFourGuiTitle() {
//        return super.getFourVFourGuiTitle();
//    }
//
//
//}
//class GUIDisplayNames extends GUIData{
//    public GUIDisplayNames(){
//
//    }
//
//    @Override
//    String getSolosDisplayName() {
//        return super.getSolosDisplayName();
//    }
//    @Override
//    String getDuosDisplayName() {
//        return super.getDuosDisplayName();
//    }
//    @Override
//    String getQuadsDisplayName() {
//        return super.getQuadsDisplayName();
//    }
//    @Override
//    String getTwoVTwoDisplayName() {
//        return super.getTwoVTwoDisplayName();
//    }
//    @Override
//    String getFourVFourDisplayName() {
//        return super.getFourVFourDisplayName();
//    }
//
//    @Override
//    String getSolosFirstDisplayName() {
//        return super.getSolosFirstDisplayName();
//    }
//    @Override
//    String getSolosSecondDisplayName() {
//        return super.getSolosSecondDisplayName();
//    }
//    @Override
//    String getDuosFirstDisplayName() {
//        return super.getDuosFirstDisplayName();
//    }
//    @Override
//    String getDuosSecondDisplayName() {
//        return super.getDuosSecondDisplayName();
//    }
//    @Override
//    String getQuadsFirstDisplayName() {
//        return super.getQuadsFirstDisplayName();
//    }
//    @Override
//    String getQuadsSecondDisplayName() {
//        return super.getQuadsSecondDisplayName();
//    }
//    @Override
//    String getTwoVTwoFirstDisplayName() {
//        return super.getTwoVTwoFirstDisplayName();
//    }
//    @Override
//    String getTwoVTwoSecondDisplayName() {
//        return super.getTwoVTwoSecondDisplayName();
//    }
//    @Override
//    String getFourVFourFirstDisplayName() {
//        return super.getFourVFourFirstDisplayName();
//    }
//    @Override
//    String getFourVFourSecondDisplayName() {
//        return super.getFourVFourSecondDisplayName();
//    }
//
//
//
//
//}
//class GUILores extends GUIData{
//    public GUILores(){
//
//    }
//
//    @Override
//    List<String> getSolosLore() {
//        return super.getSolosLore();
//    }
//    @Override
//    List<String> getDuosLore() {
//        return super.getDuosLore();
//    }
//    @Override
//    List<String> getQuadsLore() {
//        return super.getQuadsLore();
//    }
//    @Override
//    List<String> getTwoVTwoLore() {
//        return super.getTwoVTwoLore();
//    }
//    @Override
//    List<String> getFourVFourLore() {
//        return super.getFourVFourLore();
//    }
//
//    @Override
//    List<String> getSolosFirstLore() {
//        return super.getSolosFirstLore();
//    }
//    @Override
//    List<String> getSolosSecondLore() {
//        return super.getSolosSecondLore();
//    }
//    @Override
//    List<String> getDuosFirstLore() {
//        return super.getDuosFirstLore();
//    }
//    @Override
//    List<String> getDuosSecondLore() {
//        return super.getDuosSecondLore();
//    }
//    @Override
//    List<String> getQuadsFirstLore() {
//        return super.getQuadsFirstLore();
//    }
//    @Override
//    List<String> getQuadsSecondLore() {
//        return super.getQuadsSecondLore();
//    }
//    @Override
//    List<String> getTwoVTwoFirstLore() {
//        return super.getTwoVTwoFirstLore();
//    }
//    @Override
//    List<String> getTwoVTwoSecondLore() {
//        return super.getTwoVTwoSecondLore();
//    }
//    @Override
//    List<String> getFourVFourFirstLore() {
//        return super.getFourVFourFirstLore();
//    }
//    @Override
//    List<String> getFourVFourSecondLore() {
//        return super.getFourVFourSecondLore();
//    }
//
//}
//
//public class GUI implements Listener {
//
//
//    GUITitles titles = new GUITitles();
//    GUIDisplayNames dn = new GUIDisplayNames();
//    GUILores lores = new GUILores();
//    public Inventory invMain(){
//        int guiSize = 27;
//        Inventory inv = Bukkit.createInventory(null,guiSize,titles.getMainGuiTitle());
//        for (int x = 0;x<guiSize;x++){
//            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)7);
//
//            ItemMeta meta = item.getItemMeta();
//            meta.setDisplayName(" ");
//            meta.setLore(null);
//            item.setItemMeta(meta);
//            inv.setItem(x,item);
//        }
//        ItemStack item = new ItemStack(Material.GOLD_INGOT);
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(dn.getSolosDisplayName());
//        List<String> lore = lores.getSolosLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(11,item);
//
//        item = new ItemStack(Material.IRON_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getDuosDisplayName());
//        lore = lores.getDuosLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(12,item);
//
//        item = new ItemStack(Material.DIAMOND);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getQuadsDisplayName());
//        lore = lores.getQuadsLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(13,item);
//
//        item = new ItemStack(Material.EMERALD);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getTwoVTwoDisplayName());
//        lore = lores.getTwoVTwoLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(14,item);
//
//        item = new ItemStack(Material.REDSTONE);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getFourVFourDisplayName());
//        lore = lores.getFourVFourLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(15,item);
//
//
//
//        return inv;
//    }
//    public Inventory invSolos(){
//        int guiSize = 9;
//        Inventory inv = Bukkit.createInventory(null,guiSize,titles.getSolosGuiTitle());
//        for (int x = 0;x<guiSize;x++){
//            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)7);
//            ItemMeta meta = item.getItemMeta();
//            meta.setDisplayName(" ");
//            meta.setLore(null);
//            item.setItemMeta(meta);
//            inv.setItem(x,item);
//        }
//        ItemStack item = new ItemStack(Material.ARROW);
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(dn.getGoBackDisplayName());
//        List<String> lore = lores.getGoBackLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(0,item);
//
//        item = new ItemStack(Material.GOLD_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getDuosFirstDisplayName());
//        lore = lores.getDuosFirstLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(3,item);
//
//        item = new ItemStack(Material.IRON_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getDuosSecondDisplayName());
//        lore = lores.getDuosSecondLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(5,item);
//
//        item = new ItemStack(Material.BARRIER);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getCloseButtonDisplayName());
//        lore = lores.getCloseButtonLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(8,item);
//
//        return inv;
//    }
//    public Inventory invDuos(){
//        int guiSize = 9;
//        Inventory inv = Bukkit.createInventory(null,guiSize,titles.getDuosGuiTitle());
//        for (int x = 0;x<guiSize;x++){
//            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)7);
//            ItemMeta meta = item.getItemMeta();
//            meta.setDisplayName(" ");
//            meta.setLore(null);
//            item.setItemMeta(meta);
//            inv.setItem(x,item);
//        }
//
//        ItemStack item = new ItemStack(Material.ARROW);
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(dn.getGoBackDisplayName());
//        List<String> lore = lores.getGoBackLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(0,item);
//
//        item = new ItemStack(Material.GOLD_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getDuosFirstDisplayName());
//        lore = lores.getDuosFirstLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(3,item);
//
//        item = new ItemStack(Material.IRON_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getDuosSecondDisplayName());
//        lore = lores.getDuosSecondLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(5,item);
//
//        item = new ItemStack(Material.BARRIER);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getCloseButtonDisplayName());
//        lore = lores.getCloseButtonLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(8,item);
//
//
//        return inv;
//
//    }
//    public Inventory invQuads(){
//        int guiSize = 9;
//        Inventory inv = Bukkit.createInventory(null,guiSize,titles.getQuadsGuiTitle());
//        for (int x = 0;x<guiSize;x++){
//            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)7);
//            ItemMeta meta = item.getItemMeta();
//            meta.setDisplayName(" ");
//            meta.setLore(null);
//            item.setItemMeta(meta);
//            inv.setItem(x,item);
//        }
//
//        ItemStack item = new ItemStack(Material.ARROW);
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(dn.getGoBackDisplayName());
//        List<String> lore = lores.getGoBackLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(0,item);
//
//
//        item = new ItemStack(Material.GOLD_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getQuadsFirstDisplayName());
//        lore = lores.getQuadsFirstLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(3,item);
//
//        item = new ItemStack(Material.IRON_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getQuadsSecondDisplayName());
//        lore = lores.getQuadsSecondLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(5,item);
//
//        item = new ItemStack(Material.BARRIER);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getCloseButtonDisplayName());
//        lore = lores.getCloseButtonLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(8,item);
//
//        return inv;
//    }
//    public Inventory invTwoVTwo(){
//        int guiSize = 9;
//        Inventory inv = Bukkit.createInventory(null,guiSize,titles.getTwoVTwoGuiTitle());
//        for (int x = 0;x<guiSize;x++){
//            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)7);
//            ItemMeta meta = item.getItemMeta();
//            meta.setDisplayName(" ");
//            meta.setLore(null);
//            item.setItemMeta(meta);
//            inv.setItem(x,item);
//        }
//
//        ItemStack item = new ItemStack(Material.ARROW);
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(dn.getGoBackDisplayName());
//        List<String> lore = lores.getGoBackLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(0,item);
//
//
//        item = new ItemStack(Material.GOLD_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getTwoVTwoFirstDisplayName());
//        lore = lores.getTwoVTwoFirstLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(3,item);
//
//        item = new ItemStack(Material.IRON_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getTwoVTwoSecondDisplayName());
//        lore = lores.getTwoVTwoSecondLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(5,item);
//
//        item = new ItemStack(Material.BARRIER);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getCloseButtonDisplayName());
//        lore = lores.getCloseButtonLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(8,item);
//
//        return inv;
//    }
//    public Inventory invFourVFour(){
//        int guiSize = 9;
//        Inventory inv = Bukkit.createInventory(null,guiSize,titles.getFourVFourGuiTitle());
//        for (int x = 0;x<guiSize;x++){
//            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)7);
//            ItemMeta meta = item.getItemMeta();
//            meta.setDisplayName(" ");
//            meta.setLore(null);
//            item.setItemMeta(meta);
//            inv.setItem(x,item);
//        }
//
//        ItemStack item = new ItemStack(Material.ARROW);
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(dn.getGoBackDisplayName());
//        List<String> lore = lores.getGoBackLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(0,item);
//
//        item = new ItemStack(Material.GOLD_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getFourVFourFirstDisplayName());
//        lore = lores.getFourVFourFirstLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(3,item);
//
//        item = new ItemStack(Material.IRON_INGOT);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getFourVFourSecondDisplayName());
//        lore = lores.getFourVFourSecondLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(5,item);
//
//        item = new ItemStack(Material.BARRIER);
//        meta = item.getItemMeta();
//        meta.setDisplayName(dn.getCloseButtonDisplayName());
//        lore = lores.getCloseButtonLore();
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        inv.setItem(8,item);
//
//        return inv;
//    }
//
///*
//    public Inventory UPGradeInventory(){
//        int guiSize = 27;
//        Inventory inv = Bukkit.createInventory(null,guiSize,titles.getUpgradeTitle());
//        for (int x = 0;x<guiSize;x++){
//            ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)7);
//            ItemMeta meta = item.getItemMeta();
//            meta.setDisplayName(" ");
//            meta.setLore(null);
//            item.setItemMeta(meta);
//            inv.setItem(x,item);
//        }
//
//
//
//        return inv;
//    }*/
//
//    @EventHandler
//    public void onClick(InventoryClickEvent e){
//        String title = e.getView().getTitle();
//        if
//        (
//                !title.equals(titles.getMainGuiTitle())&&
//                !title.equals(titles.getSolosGuiTitle())&&
//                !title.equals(titles.getDuosGuiTitle())&&
//                !title.equals(titles.getQuadsGuiTitle())&&
//                !title.equals(titles.getTwoVTwoGuiTitle())&&
//                !title.equals(titles.getFourVFourGuiTitle())
//        )
//            return;
//
//        e.setCancelled(true);
//
//        if (e.getCurrentItem() == null)
//            return;
//        if (e.getCurrentItem().getItemMeta() == null)
//            return;
//
//        Player p = (Player) e.getWhoClicked();
//        String name = p.getName();
//        int s = e.getSlot();
//
//        if(title.equals(titles.getMainGuiTitle())){
//            if(s==11){p.openInventory(invSolos());return;}
//            if(s==12){p.openInventory(invDuos());return;}
//            if(s==13){p.openInventory(invQuads());return;}
//            if(s==14){p.openInventory(invTwoVTwo());return;}
//            if(s==15){p.openInventory(invFourVFour());return;}
//            return;
//        }
//        if(title.equals(titles.getSolosGuiTitle())){
//            JoinMap join = new JoinMap(p,"map11");
//            if(s==0){p.openInventory(invMain());return;}
//            if(s==3){join.join();return;}
//            if(s==5){p.closeInventory();return;}
//            if(s==8){p.closeInventory();return;}
//            return;
//        }
//        if(title.equals(titles.getDuosGuiTitle())){
//            if(s==0){p.openInventory(invMain());return;}
//            if(s==3){p.closeInventory();return;}
//            if(s==5){p.closeInventory();return;}
//            if(s==8){p.closeInventory();return;}
//            return;
//        }
//        if(title.equals(titles.getQuadsGuiTitle())){
//            if(s==0){p.openInventory(invMain());return;}
//            if(s==3){p.closeInventory();return;}
//            if(s==5){p.closeInventory();return;}
//            if(s==8){p.closeInventory();return;}
//            return;
//        }
//        if(title.equals(titles.getTwoVTwoGuiTitle())){
//            if(s==0){p.openInventory(invMain());return;}
//            if(s==3){p.closeInventory();return;}
//            if(s==5){p.closeInventory();return;}
//            if(s==8){p.closeInventory();return;}
//            return;
//        }
//        if(title.equals(titles.getFourVFourGuiTitle())){
//            if(s==0){p.openInventory(invMain());return;}
//            if(s==3){p.closeInventory();return;}
//            if(s==5){p.closeInventory();return;}
//            if(s==8){p.closeInventory();return;}
//            return;
//        }
//
//    }
//}
