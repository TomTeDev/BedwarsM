package bedwars.bedwarsmm.Maps;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Upgrades {

    static HashMap<UUID,ItemStack> upgradesItemStack = new HashMap<>();
    static HashMap<UUID,Integer> upgradesSlots = new HashMap<>();
    static HashMap<UUID,UpgradeType> upgradesUpgradeTypes = new HashMap<>();
    static HashMap<UUID,Integer> upgradesEnchantLvl = new HashMap<>();
    static HashMap<UUID,EnchantType> upgradesEnchantTypes = new HashMap<>();
    static HashMap<UUID,List<UUID>> upgradesBonusResources = new HashMap<>();
    static HashMap<UUID,String> upgradesActivatedMessage = new HashMap<>();
    static HashMap<UUID,String> upgradesUnactivatedMessage = new HashMap<>();


    static HashMap<UUID,ItemStack> bonusResourceItems = new HashMap<>();
    static HashMap<UUID,Location> bonusResourceLocation = new HashMap<>();
    static HashMap<UUID,Integer> bonusResourceRepeat = new HashMap<>();
    private UUID upid;
    public  Upgrades(UUID upid){
        this.upid = upid;
    }

    private void printError(String messsage){
        System.out.println("           ");
        System.out.println("There was an error with Team Upgrades located in BEDWARS plugin");
        System.out.println(messsage);
        System.out.println("            ");

    }


    public ItemStack getUpgradeItem(){
        if(upgradesItemStack.containsKey(upid)){
            return upgradesItemStack.get(upid);
        }else{
            printError("Missing Item, set to obsidian");
            return new ItemStack(Material.OBSIDIAN);
        }
    }
    public int getUpgradeSlot(){
        if(upgradesSlots.containsKey(upid)){
            return upgradesSlots.get(upid);
        }else{
            printError("Missing slot, set to 0");
            return 0;
        }
    }
    public UpgradeType getUpgradeType(){
        if(upgradesUpgradeTypes.containsKey(upid)){
            return upgradesUpgradeTypes.get(upid);
        }else{
            printError("Missing upgrade type, set to null");
            return null;
        }
    }
    public String getActivatedMessage() {
        return upgradesActivatedMessage.getOrDefault(upid,"");
    }
    public String getUnactivatedMessage() {
        return upgradesUnactivatedMessage.getOrDefault(upid,"");
    }
    public int getUpgradeEnchLvl(){
        if(upgradesEnchantLvl.containsKey(upid)){
            return upgradesEnchantLvl.get(upid);
        }else{
            printError("Missing enchant lvl, set to 1");
            return 1;
        }
    }
    public EnchantType getEnchantType(){
        if(upgradesEnchantTypes.containsKey(upid)){
            return upgradesEnchantTypes.get(upid);
        }else{
            printError("Missing enchantTypes, set to null");
            return null;
        }

    }
    public List<UUID> getBonnusResources(){
        if(upgradesBonusResources.containsKey(upid)){
            return upgradesBonusResources.get(upid);
        }else{
            return new ArrayList<>();
        }
    }
    public BonusResource getBonnusResource(UUID bonusResourceID){
        return new BonusResource(bonusResourceID);

    }
    class BonusResource{
        private UUID id;
        public  BonusResource(UUID resourceid){
            this.id = resourceid;
        }
        public ItemStack getItemStack(){
            if(bonusResourceItems.containsKey(id)){
                return bonusResourceItems.get(id);
            }else{
                printError("Missing Item in bonus resources, set to obsidian");
                return new ItemStack(Material.OBSIDIAN);
            }
        }
        public Location getLocation(){
            if(bonusResourceLocation.containsKey(id)){
                return bonusResourceLocation.get(id);
            }else{
                printError("Missing location in bonus resources, set to null");
                return null;
            }
        }
        public int getRepeatTime(){
            if(bonusResourceRepeat.containsKey(id)){
                return bonusResourceRepeat.get(id);
            }else{
                printError("Missing repeat time in bonus resources, set to 200");
                return 4000;
            }
        }


    }
    public EnchantType enchantParser(String stringValue){
        String value = stringValue.toLowerCase();
        if(value.contains("sharpness")){return EnchantType.SHARPNESS;}
        if(value.contains("protection")){return EnchantType.PROTECTION;}
        if(value.contains("fire_aspect")){return EnchantType.FIRE_ASPECT;}
        if(value.contains("fire")&&value.contains("aspect")){return EnchantType.FIRE_ASPECT;}
        if(value.contains("fire_protection")){return EnchantType.FIRE_PROTECTION;}
        if(value.contains("fire")&&value.contains("protection")){return EnchantType.FIRE_PROTECTION;}
        if(value.contains("efficiency")){return EnchantType.EFFICIENCY;}
        if(value.contains("thorns")){return EnchantType.THORNS;}
        if(value.contains("knock_back")){return EnchantType.KNOCK_BACK;}
        if(value.contains("power")){return EnchantType.POWER;}
        if(value.contains("puch")){return EnchantType.PUNCH;}
        if(value.contains("flame")){return EnchantType.FLAME;}
        return null;
    }
    enum EnchantType{
        SHARPNESS,
        PROTECTION,
        FIRE_PROTECTION,
        FIRE_ASPECT,
        EFFICIENCY,
        THORNS,
        KNOCK_BACK,
        POWER,
        PUNCH,
        FLAME


    }
    public UpgradeType upgradeParser(String stringValue) {
        String value = stringValue.toLowerCase();
        if(value.contains("armor")){return UpgradeType.ARMOR_ENCHANT;}
        if(value.contains("sword")){return UpgradeType.SWORD_ENCHANT;}
        if(value.contains("bow")){return UpgradeType.BOW_ENCHANT;}
        if(value.contains("pickaxe")){return UpgradeType.PICKAXE_ENCHANT;}
        if(value.contains("axe")){return UpgradeType.AXE_ENCHANT;}
        if(value.contains("trap")){return UpgradeType.TRAP;}
        if(value.contains("potion")){return UpgradeType.POTION;}
        if(value.contains("resources")){return UpgradeType.RESOURCES;}
        return null;
    }
    enum UpgradeType {
        ARMOR_ENCHANT,
        SWORD_ENCHANT,
        BOW_ENCHANT,
        PICKAXE_ENCHANT,
        AXE_ENCHANT,
        TRAP,
        POTION,
        RESOURCES
    }



}
