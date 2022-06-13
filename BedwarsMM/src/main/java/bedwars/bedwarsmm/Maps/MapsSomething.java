package bedwars.bedwarsmm.Maps;


import bedwars.bedwarsmm.PacketReader;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class MapsSomething extends MapsLoad implements Listener, CommandExecutor {
    public void load(){
        getSomeMaxValues();

        getMapsGUI();

        LinkedList<UUID> amList = new LinkedList<>();
        for(List<UUID> list:amountOfTeamsPerMaps.values()){
            amList.addAll(list);
        }
        if(amList.size()>0){
            tA = amList.size();
            getTeamAmountInv(amList,tA);
        }

        if(showTeamMixGUi){
            LinkedList<UUID> siList = new LinkedList<>();
            for(List<UUID> list:teamSizePerMaps.values()){
                siList.addAll(list);
            }
            if(siList.size()>0){
                tS = siList.size();
                getTeamSizeInv(siList,tS);
            }


            LinkedList<UUID> miList = new LinkedList<>(mixedTeamSizeMap);
            if(miList.size()>0){
                tM = miList.size();
                getTeamMixedInv(miList, tM);
            }

        }else{
            LinkedList<UUID> siList = new LinkedList<>();
            for(List<UUID> list:teamSizePerMaps.values()){
                siList.addAll(list);
            }
            siList.addAll(mixedTeamSizeMap);
            if(siList.size()>0){
                tS = siList.size();
                tM = 1;
                getTeamSizeInv(siList,tS);
            }

        }

    }
    private static HashMap<Integer,List<UUID>> amountOfTeamsPerMaps = new HashMap<>();
    private static HashMap<Integer,List<UUID>> teamSizePerMaps = new HashMap<>();
    private static List<UUID> mixedTeamSizeMap = new ArrayList<>();
    void getSomeMaxValues(){

        for(UUID  id :getMapIds().values()){
            int y = getTeamAmount(id);
            List<UUID> listOfMapIdsWithSameAmountOfTeams = amountOfTeamsPerMaps.getOrDefault(y, new ArrayList<>());
            listOfMapIdsWithSameAmountOfTeams.add(id);
            amountOfTeamsPerMaps.put(y,listOfMapIdsWithSameAmountOfTeams);

            List<UUID> teams = getTeamsForThatMap(id);
            int tempX = 0;
            int i = 0;
            for(UUID idTeamu:teams){
                int teamSize = getTeamSize(idTeamu);
                if(i!=0){
                    if(tempX!=teamSize){
                        //add to mixedMaps;
                        mixedTeamSizeMap.add(id);
                        break;
                    }
                }
                tempX = teamSize;
                i++;
                if(i==teams.size()){
                    List<UUID> XteamSizeList = teamSizePerMaps.getOrDefault(teamSize,new ArrayList<>());
                    XteamSizeList.add(id);
                    teamSizePerMaps.put(teamSize,XteamSizeList);
                    //add to XTeamSizeMap
                }
            }
        }


    }
    private static ItemStack getRandomItemStack(){
        ItemStack item = new ItemStack(Material.STRING);
        ItemMeta meta = item.getItemMeta();
        Random random = new Random();
        double  d = random.nextDouble();
        double  e = random.nextDouble();
        double  b = random.nextDouble();
        meta.setDisplayName("next D:"+d);
        List<String> lore = new ArrayList<>();
        lore.add("e "+e);
        lore.add("b "+b);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    static int mainInvSize = 27;
    static String mainInvTitle = "maininvtitle";
    static ItemStack mainInvBackGroundItem =  new ItemStack(Material.REDSTONE);
    static ItemStack mainInvTeamAmountItem =  getRandomItemStack();
    static boolean showTeamAmountGUi = true;
    static ItemStack mainInvTeamSizeItem  =  getRandomItemStack();
    static boolean showTeamSizeGUi = true;
    static ItemStack mainInvTeamMixedItem =  getRandomItemStack();
    static boolean showTeamMixGUi = true;
    static int amountInvSize = 36;
    static String amountInvTitle = "amountinvtitle";
    static ItemStack amountInvBackGroundItem =  new ItemStack(Material.GLASS);
    static int tA = 5;
    static Inventory[] teamAmountInventory = new Inventory[tA];
    static int sizeInvSize = 45;
    static String sizeInvTitle = "SizeiNV";
    static ItemStack sizeInvBackGroundItem =  getRandomItemStack();
    static int tS = 5;
    static Inventory[] teamSizeInventory = new Inventory[tS];
    static int mixInvSize =  27;
    static String mixInvTitle = "mixed";
    static ItemStack mixInvBackGroundItem =  getRandomItemStack();
    static int tM = 5;
    static Inventory[] teamMixInventory = new Inventory[tM];

    static  String playerInfo = "Free slots: <slots>";
    static ItemStack goNextButton=  new ItemStack(Material.LAPIS_ORE);
    static ItemStack goBackButton =  new ItemStack(Material.ARROW);
    static Inventory mainGui;
    private  void getMapsGUI(){
        Inventory inv = Bukkit.createInventory(null,mainInvSize,mainInvTitle);
        for(int x = 0;x<mainInvSize;x++){
            ItemStack item = mainInvBackGroundItem.clone();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,mainInvBackGroundItem);
        }
        int x =0;
        if (showTeamMixGUi)x++;
        if(showTeamAmountGUi)x++;
        if(showTeamMixGUi)x++;
        switch (mainInvSize){
            case 27:
            case 18:{
                if(x==3){
                    inv.setItem(11,mainInvTeamSizeItem);
                    inv.setItem(13,mainInvTeamAmountItem);
                    inv.setItem(15,mainInvTeamMixedItem);
                }else if(x==2){
                    int smth = 0;
                    if(showTeamSizeGUi){
                        inv.setItem(12,mainInvTeamSizeItem);
                        smth++;
                    }
                    if(smth!=0){
                        if(showTeamAmountGUi){
                            inv.setItem(14,mainInvTeamAmountItem);
                        }
                        if(showTeamMixGUi){
                            inv.setItem(14,mainInvTeamMixedItem);
                        }
                    }else{
                        inv.setItem(12,mainInvTeamAmountItem);
                        inv.setItem(14,mainInvTeamMixedItem);
                    }
                }else if(x==1){
                    if(showTeamSizeGUi){
                        inv.setItem(14,mainInvTeamSizeItem);
                    }
                    if(showTeamAmountGUi){
                        inv.setItem(14,mainInvTeamAmountItem);
                    }
                    if(showTeamMixGUi){
                        inv.setItem(14,mainInvTeamMixedItem);
                    }

                }
                break;
            }
            case 36:
            case 45:{
                if(x==3){
                    inv.setItem(20,mainInvTeamSizeItem);
                    inv.setItem(22,mainInvTeamAmountItem);
                    inv.setItem(24,mainInvTeamMixedItem);
                }else if(x==2){
                    int smth = 0;
                    if(showTeamSizeGUi){
                        inv.setItem(21,mainInvTeamSizeItem);
                        smth++;
                    }
                    if(smth!=0){
                        if(showTeamAmountGUi){
                            inv.setItem(23,mainInvTeamAmountItem);
                        }
                        if(showTeamMixGUi){
                            inv.setItem(23,mainInvTeamMixedItem);
                        }
                    }else{
                        inv.setItem(21,mainInvTeamAmountItem);
                        inv.setItem(23,mainInvTeamMixedItem);
                    }
                }else if(x==1){
                    if(showTeamSizeGUi){
                        inv.setItem(23,mainInvTeamSizeItem);
                    }
                    if(showTeamAmountGUi){
                        inv.setItem(23,mainInvTeamAmountItem);
                    }
                    if(showTeamMixGUi){
                        inv.setItem(23,mainInvTeamMixedItem);
                    }

                }
                break;
            }
            case 54:{
                if(x==3){
                    inv.setItem(29,mainInvTeamSizeItem);
                    inv.setItem(31,mainInvTeamAmountItem);
                    inv.setItem(33,mainInvTeamMixedItem);
                }else if(x==2){
                    int smth = 0;
                    if(showTeamSizeGUi){
                        inv.setItem(30,mainInvTeamSizeItem);
                        smth++;
                    }
                    if(smth!=0){
                        if(showTeamAmountGUi){
                            inv.setItem(32,mainInvTeamAmountItem);
                        }
                        if(showTeamMixGUi){
                            inv.setItem(32,mainInvTeamMixedItem);
                        }
                    }else{
                        inv.setItem(30,mainInvTeamAmountItem);
                        inv.setItem(32,mainInvTeamMixedItem);
                    }
                }else if(x==1){
                    if(showTeamSizeGUi){
                        inv.setItem(32,mainInvTeamSizeItem);
                    }
                    if(showTeamAmountGUi){
                        inv.setItem(32,mainInvTeamAmountItem);
                    }
                    if(showTeamMixGUi){
                        inv.setItem(32,mainInvTeamMixedItem);
                    }

                }
                break;
            }
            default:{
                if(x==3){
                    inv.setItem(2,mainInvTeamSizeItem);
                    inv.setItem(4,mainInvTeamAmountItem);
                    inv.setItem(6,mainInvTeamMixedItem);
                }else if(x==2){
                    int smth = 0;
                    if(showTeamSizeGUi){
                        inv.setItem(3,mainInvTeamSizeItem);
                        smth++;
                    }
                    if(smth!=0){
                        if(showTeamAmountGUi){
                            inv.setItem(5,mainInvTeamAmountItem);
                        }
                        if(showTeamMixGUi){
                            inv.setItem(5,mainInvTeamMixedItem);
                        }
                    }else{
                        inv.setItem(3,mainInvTeamAmountItem);
                        inv.setItem(5,mainInvTeamMixedItem);
                    }
                }else if(x==1){
                    if(showTeamSizeGUi){
                        inv.setItem(5,mainInvTeamSizeItem);
                    }
                    if(showTeamAmountGUi){
                        inv.setItem(5,mainInvTeamAmountItem);
                    }
                    if(showTeamMixGUi){
                        inv.setItem(5,mainInvTeamMixedItem);
                    }

                }
                break;
            }
        }
        mainGui = inv;
    }
    private void getTeamAmountInv(LinkedList<UUID> mapIdsGet,int mapAmountGet){
        LinkedList<UUID> linkedList = mapIdsGet;
        int mapAmount = mapAmountGet;
        int size = amountInvSize;
        String title = amountInvTitle;
        ItemStack bckGroundItem = amountInvBackGroundItem;
        Inventory inv = Bukkit.createInventory(null,size,title);
        for(int x = 0;x<size;x++){
            ItemStack item = bckGroundItem.clone();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }

        switch (size){
            case 9:{
                int maxSlot = 2;
                int goBackSlot = 0;
                int goNextSlot = 8;
                int l = 0;

                int slot = 1;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamAmountInventory[l] = inv;
                            l++;
                            getTeamAmountInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamAmountInventory!=null){

                        if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }


                        teamAmountInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 18:{
                int maxSlot = 11;
                int goBackSlot = 9;
                int goNextSlot = 17;
                int l = 0;

                int slot = 1;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){
                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamAmountInventory[l] = inv;
                            l++;
                            getTeamAmountInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamAmountInventory!=null){

      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamAmountInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 27:{
                int maxSlot = 11;
                int goBackSlot = 18;
                int goNextSlot = 26;
                int l = 0;

                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){
                            l++;
                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamAmountInventory[l] = inv;
                            l++;
                            getTeamAmountInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if (teamAmountInventory!=null){

      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamAmountInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 36:{
                int maxSlot = 20;
                int goBackSlot = 27;
                int goNextSlot = 35;
                int l = 0;

                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){
                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamAmountInventory[l] = inv;
                            l++;
                            getTeamAmountInv(mapIdsGet,mapAmount);

                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamAmountInventory!=null){
      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamAmountInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 45:{
                int maxSlot = 29;
                int goBackSlot = 36;
                int goNextSlot = 44;
                int l = 0;

                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamAmountInventory[l] = inv;
                            l++;
                            getTeamAmountInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamAmountInventory!=null){

      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamAmountInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 54:{
                int slot = 10;
                int seven = mapAmount/7+1;
                int l = 0;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=38&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(45,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(53,itemBukkit);


                            teamAmountInventory[l] = inv;
                            l++;
                            getTeamAmountInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamAmountInventory!=null){

                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(45,itemBukkit);
                        teamAmountInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
        }

    }
    private void getTeamSizeInv(LinkedList<UUID> mapIdsGet,int mapAmountGet){
        LinkedList<UUID> linkedList = mapIdsGet;
        int mapAmount = mapAmountGet;
        int size = sizeInvSize;
        String title = sizeInvTitle;
        ItemStack bckGroundItem = sizeInvBackGroundItem;

        Inventory inv = Bukkit.createInventory(null,size,title);
        for(int x = 0;x<size;x++){
            ItemStack item = bckGroundItem.clone();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }

        switch (size){
            case 9:{
                int maxSlot = 2;
                int goBackSlot = 0;
                int goNextSlot = 8;
                int l = 0;

                int slot = 1;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){
                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamSizeInventory[l] = inv;
                            l++;
                            getTeamSizeInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){


                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamSizeInventory!=null){
      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamSizeInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 18:{
                int maxSlot = 11;
                int goBackSlot = 9;
                int goNextSlot = 17;
                int l = 0;

                int slot = 1;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamSizeInventory[l] = inv;
                            l++;
                            getTeamSizeInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamSizeInventory!=null){

      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamSizeInventory[l] = inv;
                        l++;

                    }

                }
                break;
            }
            case 27:{
                int maxSlot = 11;
                int goBackSlot = 18;
                int goNextSlot = 26;
                int l = 0;

                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamSizeInventory[l] = inv;
                            l++;
                            getTeamSizeInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamSizeInventory!=null){

      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamSizeInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 36:{
                int maxSlot = 20;
                int goBackSlot = 27;
                int goNextSlot = 35;
                int l = 0;


                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){
                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamSizeInventory[l] = inv;
                            l++;
                            getTeamSizeInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamSizeInventory!=null){

      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamSizeInventory[l] = inv;
                        l++;
                    }


                }
                break;
            }
            case 45:{
                int maxSlot = 29;
                int goBackSlot = 36;
                int goNextSlot = 44;
                int l = 0;


                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamSizeInventory[l] = inv;
                            l++;
                            getTeamSizeInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamSizeInventory!=null){

      if(l>0){
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l-1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goBackSlot,itemBukkit);
                        }
                        teamSizeInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
            case 54:{
                int slot = 10;
                int seven = mapAmount/7+1;
                int l = 0;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=38&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(45,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(53,itemBukkit);


                            teamSizeInventory[l] = inv;
                            l++;
                            getTeamSizeInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(teamSizeInventory!=null){

                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(45,itemBukkit);
                        teamSizeInventory[l] = inv;
                        l++;
                    }

                }
                break;
            }
        }

    }
    private void getTeamMixedInv(LinkedList<UUID> mapIdsGet,int mapAmountGet){
        LinkedList<UUID> linkedList = mapIdsGet;
        int mapAmount = mapAmountGet;
        int size = mixInvSize;
        String title = mixInvTitle;
        ItemStack bckGroundItem = mixInvBackGroundItem;

        Inventory inv = Bukkit.createInventory(null,size,title);
        for(int x = 0;x<size;x++){
            ItemStack item = bckGroundItem.clone();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            meta.setLore(null);
            item.setItemMeta(meta);
            inv.setItem(x,item);
        }

        switch (size){
            case 9:{
                int maxSlot = 2;
                int goBackSlot = 0;
                int goNextSlot = 8;
                int l = 0;

                int slot = 1;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamMixInventory[l] = inv;
                            l++;
                            getTeamMixedInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(l>0){
                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(goBackSlot,itemBukkit);
                    }

                    teamMixInventory[l] = inv;
                    l++;
                }
                break;
            }
            case 18:{
                int maxSlot = 11;
                int goBackSlot = 9;
                int goNextSlot = 17;
                int l = 0;

                int slot = 1;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamMixInventory[l] = inv;
                            l++;
                            getTeamMixedInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(l>0){
                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(goBackSlot,itemBukkit);
                    }
                    teamMixInventory[l] = inv;
                    l++;
                }
                break;
            }
            case 27:{
                int maxSlot = 11;
                int goBackSlot = 18;
                int goNextSlot = 26;
                int l = 0;

                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamMixInventory[l] = inv;
                            l++;
                            getTeamMixedInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(l>0){
                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(goBackSlot,itemBukkit);
                    }
                    teamMixInventory[l] = inv;
                    l++;
                }
                break;
            }
            case 36:{
                int maxSlot = 20;
                int goBackSlot = 27;
                int goNextSlot = 35;
                int l = 0;

                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamMixInventory[l] = inv;
                            l++;
                            getTeamMixedInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(l>0){
                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(goBackSlot,itemBukkit);
                    }
                    teamMixInventory[l] = inv;
                    l++;
                }
                break;
            }
            case 45:{
                int maxSlot = 29;
                int goBackSlot = 36;
                int goNextSlot = 44;
                int l = 0;

                int slot = 10;
                int seven = mapAmount/7+1;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=maxSlot&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(goBackSlot,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(goNextSlot,itemBukkit);


                            teamMixInventory[l] = inv;
                            l++;
                            getTeamMixedInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;
                    if(l>0){
                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(goBackSlot,itemBukkit);
                    }
                    teamMixInventory[l] = inv;
                    l++;
                }
                break;
            }
            case 54:{
                int slot = 10;
                int seven = mapAmount/7+1;
                int l = 0;
                if(mapAmount>0){

                    for(int xS = 0;xS<seven;xS++){

                        if(slot>=38&&mapAmount>0){

                            if(l>0){
                                //add go back button;
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setInt("page",(l-1));
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(45,itemBukkit);
                            }
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goNextButton.clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setInt("page",(l+1));
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(53,itemBukkit);


                            teamMixInventory[l] = inv;
                            l++;
                            getTeamMixedInv(mapIdsGet,mapAmount);
                            break;
                        }


                        if(mapAmount>=7){

                            for(int i= 0; i<7;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount = mapAmount-7;
                        }
                        if(mapAmount==6){

                            for(int i= 0; i<6;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==5){

                            slot++;
                            for(int i= 0; i<5;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==4){

                            slot++;
                            for(int i= 0; i<4;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==3){

                            slot++;
                            slot++;
                            for(int i= 0; i<3;i++){
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;

                        }
                        if(mapAmount==2){

                            slot++;
                            slot++;
                            for(int i= 0; i<2;i++){
                                if(slot==4||slot==13||slot==22||slot==31){
                                    slot++;
                                }
                                net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                                NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                                tag.setString("mapid",linkedList.get(0).toString());
                                item.setTag(tag);
                                ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                                inv.setItem(slot,itemBukkit);
                                slot++;
                                linkedList.remove(0);
                            }
                            mapAmount=0;
                        }
                        if(mapAmount==1){

                            slot++;
                            slot++;
                            slot++;
                            net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(getMapItem(linkedList.get(0)).clone());
                            NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                            tag.setString("mapid",linkedList.get(0).toString());
                            item.setTag(tag);
                            ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                            inv.setItem(slot,itemBukkit);

                        }
                    }
                    //add go back button;

                    if(l>0){
                        net.minecraft.server.v1_8_R3.ItemStack item = CraftItemStack.asNMSCopy(goBackButton.clone());
                        NBTTagCompound tag= (item.hasTag()) ? item.getTag() : new NBTTagCompound();
                        tag.setInt("page",(l-1));
                        item.setTag(tag);
                        ItemStack itemBukkit = CraftItemStack.asBukkitCopy(item);
                        inv.setItem(45,itemBukkit);
                    }
                    teamMixInventory[l] = inv;
                    l++;
                }
                break;
            }
        }

    }


    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        String title  = e.getView().getTitle();
        if(!title.equals(mainInvTitle)&&
                !title.equals(amountInvTitle)&&
                !title.equals(sizeInvTitle)&&
                !title.equals(mixInvTitle))
            return;
        if(!e.getClickedInventory().getType().equals(InventoryType.CHEST))return;
        e.setCancelled(true);
        if (e.getCurrentItem() == null)
            return;
        if (e.getCurrentItem().getItemMeta() == null)
            return;
        ItemStack clickedItem = e.getCurrentItem();

        Player p = (Player) e.getWhoClicked();
        String name = p.getName();

        if(title.equals(mainInvTitle)){
            if(clickedItem.isSimilar(mainInvTeamSizeItem)){
                if(teamSizeInventory.length>0){
                    Inventory baseInv = teamSizeInventory[0];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                return;
            }
            if(clickedItem.isSimilar(mainInvTeamAmountItem)){
                if(teamAmountInventory.length>0){
                    Inventory baseInv = teamAmountInventory[0];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                return;
            }
            if(clickedItem.isSimilar(mainInvTeamMixedItem)){
                if(teamMixInventory.length>0){
                    Inventory baseInv = teamMixInventory[0];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                return;
            }
            return;
        }
        if(title.equals(amountInvTitle)||title.equals(sizeInvTitle)||title.equals(mixInvTitle)){
            ItemStack someItem = clickedItem.clone();
            net.minecraft.server.v1_8_R3.ItemStack nmsSomeItem = CraftItemStack.asNMSCopy(clickedItem);
            nmsSomeItem.setTag(null);
            someItem = CraftItemStack.asBukkitCopy(nmsSomeItem);
            if(clickedItem.isSimilar(goBackButton)){
                net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(clickedItem);
                if(!nmsItem.hasTag()){
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED+"An error occured, try again in a while!#MissingTag");
                    return;
                }
                NBTTagCompound tag = nmsItem.getTag();
                int page = tag.getInt("page");
                if(title.equals(amountInvTitle)){
                    Inventory baseInv = teamAmountInventory[page];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                if(title.equals(sizeInvTitle)){
                    Inventory baseInv = teamSizeInventory[page];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                if(title.equals(mixInvTitle)){
                    Inventory baseInv = teamMixInventory[page];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }

                return;
            }
            if(clickedItem.isSimilar(goNextButton)){
                net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(clickedItem);
                if(!nmsItem.hasTag()){
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED+"An error occured, try again in a while!#MissingTag");
                    return;
                }
                NBTTagCompound tag = nmsItem.getTag();
                int page = tag.getInt("page");
                if(title.equals(amountInvTitle)){
                    Inventory baseInv = teamAmountInventory[page];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                if(title.equals(sizeInvTitle)){
                    Inventory baseInv = teamSizeInventory[page];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                if(title.equals(mixInvTitle)){
                    Inventory baseInv = teamMixInventory[page];
                    if(baseInv==null){
                        p.closeInventory();
                        return;
                    }
                    Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
                    inv.setContents(baseInv.getContents());
                    p.openInventory(flushInv(inv));
                }
                return;
            }
            if(!someItem.isSimilar(amountInvBackGroundItem)){

                net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(clickedItem);
                if(!nmsItem.hasTag()){
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED+"An error occured, try again in a while!#MissingTag");
                    return;
                }
                NBTTagCompound tag = nmsItem.getTag();
                String idString = tag.getString("mapid");
                if(idString==null){
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED+"An error occured, try again in a while!#MissingTagNull");
                    return;
                }
                UUID id  = UUID.fromString(idString);
                MapHandler mh = new MapHandler();
                mh.joinMap(p,id);
                //movePlayerToThatMap;
                return;
            }
        }




    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        PacketReader reader = new PacketReader();

        reader.inject(p);
    }

    public Inventory flushInv(Inventory inv){
        System.out.println("Flush");
        Inventory inventory = Bukkit.createInventory(null,inv.getSize(),inv.getTitle());
        ItemStack[] items = inv.getContents();
        MapHandler mh = new MapHandler();
        for (ItemStack item : items){
            net.minecraft.server.v1_8_R3.ItemStack item2 = CraftItemStack.asNMSCopy(item);
            item2.setTag(null);
            ItemStack item1 = CraftItemStack.asBukkitCopy(item2);
            if(item1.isSimilar(amountInvBackGroundItem))continue;
            if(item1.isSimilar(sizeInvBackGroundItem))continue;
            if(item1.isSimilar(mixInvBackGroundItem))continue;
            if(item1.isSimilar(goBackButton))continue;
            if(item1.isSimilar(goNextButton))continue;
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            lore.add("");
            net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            if(!nmsItem.hasTag())continue;
            NBTTagCompound tag = nmsItem.getTag();
            String mapidString = tag.getString("mapid");
            if(mapidString==null)continue;
            UUID mapid = UUID.fromString(mapidString);
            int current = mh.getCurrentAmountOfPlayersOnMap(mapid);
            int max = mh.getMaxAmountOfPlayersPerMap(mapid);

            int freeSlots = max-current;
            lore.add(playerInfo.replaceAll("<slots>",String.valueOf(freeSlots)));
            meta.setLore(lore);
            item.setItemMeta(meta);

        }
        inventory.setContents(items);
        return inventory;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))return false;
        Player p = (Player) commandSender;
        if(s.equals("debugg")){
            Inventory baseInv = mainGui;
            Inventory inv = Bukkit.createInventory(null,baseInv.getSize(),baseInv.getTitle());
            inv.setContents(baseInv.getContents());
            p.openInventory(inv);
        }
        return false;
    }
}