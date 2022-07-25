package me.yiyi1234.machine.items.MineralProcessing;

import me.yiyi1234.machine.Machine;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeltingFurnaceItems {


    // 核心
    public static ItemStack getNormalCore() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.NormalCore.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.NormalCore.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.NormalCore.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRareCore() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RareCore.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RareCore.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RareCore.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getEpicCore() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.EpicCore.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.EpicCore.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.EpicCore.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }

    // 熔煉物
    public static ItemStack getRawHematite() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawHematite.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawHematite.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawHematite.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRawHematiteShards() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawHematiteShards.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawHematiteShards.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawHematiteShards.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRawWhitecopper() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawWhitecopper.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawWhitecopper.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawWhitecopper.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getRawWhitecopperShards() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawWhitecopperShards.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawWhitecopperShards.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawWhitecopperShards.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRawSiderite() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawSiderite.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawSiderite.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawSiderite.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRawSideriteShards() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawSideriteShards.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawSideriteShards.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawSideriteShards.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRawEmvine() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawEmvine.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawEmvine.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawEmvine.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRawEmvineShards() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RawEmvineShards.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RawEmvineShards.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RawEmvineShards.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }


    // 成果物
    public static ItemStack getHematiteIngot() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.HematiteIngot.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.HematiteIngot.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.HematiteIngot.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getHematiteNuggets() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.HematiteNugget.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.HematiteNugget.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.HematiteNugget.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getWhitecopperIngot() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.WhiteCopperIngot.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.WhiteCopperIngot.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.WhiteCopperIngot.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getWhitecopperNuggets() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.WhiteCopperNuggets.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.WhiteCopperNuggets.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.WhiteCopperNuggets.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getSideriteIngot() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.SideriteIngot.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.SideriteIngot.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.SideriteIngot.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getSideriteNuggets() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.SideriteNuggets.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.SideriteNuggets.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.SideriteNuggets.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getEmvineIngot() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.EmvineIngot.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.EmvineIngot.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.EmvineIngot.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getEmvineNuggets() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();
        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.EmvineNuggets.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.EmvineNuggets.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.EmvineNuggets.custom-model-data");

        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(custommodeldata);
        item.setItemMeta(itemMeta);
        return item;
    }


    // 燃料


    public static ItemStack getCoalShards() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();

        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE,3.5);

        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.CoalShards.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.CoalShards.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        itemMeta.setLore(lore);
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.CoalShards.custom-model-data");
        itemMeta.setCustomModelData(custommodeldata);

        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getCoal() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();

        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE,6.0);

        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.Coal.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.Coal.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        itemMeta.setLore(lore);
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.Coal.custom-model-data");
        itemMeta.setCustomModelData(custommodeldata);

        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getNormalBiodiesel() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();

        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE,6.0);

        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.NormalBiodiesel.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.NormalBiodiesel.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        itemMeta.setLore(lore);
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.NormalBiodiesel.custom-model-data");
        itemMeta.setCustomModelData(custommodeldata);

        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getRareBiodiesel() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();

        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE,11.0);

        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.RareBiodiesel.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.RareBiodiesel.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        itemMeta.setLore(lore);
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.RareBiodiesel.custom-model-data");
        itemMeta.setCustomModelData(custommodeldata);

        item.setItemMeta(itemMeta);
        return item;
    }
    public static ItemStack getEpicBiodiesel() {
        ItemStack item = new ItemStack(Material.BOWL);
        ItemMeta itemMeta = item.getItemMeta();

        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE,21.0);

        String name = Machine.getInstance().meltingFurnace_Item.getString("MeltingFurnace.EpicBiodiesel.name");
        itemMeta.setDisplayName(hex(name));
        List<String> configLore = Machine.getInstance().getMeltingFurnace_Item().getStringList("MeltingFurnace.EpicBiodiesel.lore");
        List<String> lore = new ArrayList<>();
        for (String i : configLore) {
            lore.add(hex(i));
        }
        itemMeta.setLore(lore);
        int custommodeldata = Machine.getInstance().meltingFurnace_Item.getInt("MeltingFurnace.EpicBiodiesel.custom-model-data");
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
