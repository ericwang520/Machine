package me.yiyi1234.machine.items.PlantProcessing;

import me.yiyi1234.machine.Machine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlantSeparatorItem {
    public static ItemStack getdamagedWheatGrass() {
        return getItem("damagedWheatGrass");
    }
    public static ItemStack getwheatGrass() {
        return getItem("wheatGrass");
    }
    public static ItemStack getdamagedGoldBerries() {
        return getItem("damagedGoldBerries");
    }
    public static ItemStack getgoldBerries() {
        return getItem("goldBerries");
    }
    public static ItemStack getdamagedSilverBerries() {
        return getItem("damagedSilverBerries");
    }
    public static ItemStack getsilverBerries() {
        return getItem("silverBerries");
    }
    public static ItemStack getdamagedFiber() {
        return getItem("damagedFiber");
    }
    public static ItemStack getfiber() {
        return getItem("fiber");
    }
    public static ItemStack getgoldBerryJuice() {
        return getItem("goldBerryJuice");
    }
    public static ItemStack getsilverBerryJuice() {
        return getItem("silverBerryJuice");
    }
    public static ItemStack getnormalTurnCore() {
        return getItem("normalTurnCore");
    }
    public static ItemStack getrareTurnCore() {
        return getItem("rareTurnCore");
    }
    public static ItemStack getepicTurnCore() {
        return getItem("epicTurnCore");
    }

    public static ItemStack getItem(String itemName){
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().getPlantSeparator_Item().getString("PlantSeparator." + itemName + ".name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getPlantSeparator_Item().getStringList("PlantSeparator." + itemName + ".lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().getPlantSeparator_Item().getInt("PlantSeparator." + itemName + ".custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static String hex(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch) {
                builder.append("&" + c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
