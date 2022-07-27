package me.yiyi1234.machine.manager.PlantProcessing;

import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.core.PlantProcessing.PlantSeparator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PlantSeparatorManager {
    private static HashMap<Location, PlantSeparator> plantSeparatorHashMap = new HashMap<>();

    public static PlantSeparator addPlantSeparator(Location loc, Player createPlayer) {
        if (plantSeparatorHashMap.containsKey(loc)) {
            plantSeparatorHashMap.remove(loc);
        }
        PlantSeparator plantSeparator = new PlantSeparator(loc, createPlayer);
        plantSeparatorHashMap.put(loc, plantSeparator);
        return plantSeparator;
    }

    public static PlantSeparator getPlantSeparator(Location loc) {
        return plantSeparatorHashMap.get(loc);
    }

    public static void loadPlantSeparator() {
        int times = 0;
        if (Machine.getInstance().getplantSeparator_Data().getConfigurationSection("plantSeparator") != null) {
            for (String plantSeparatorLoc : Machine.getInstance().getplantSeparator_Data().getConfigurationSection("plantSeparator").getKeys(false)) {
                PlantSeparator.State state = PlantSeparator.State.valueOf(Machine.getInstance().getplantSeparator_Data().getString("plantSeparator." + plantSeparatorLoc + ".State"));
                ItemStack core = Machine.getInstance().getplantSeparator_Data().getItemStack("plantSeparator." + plantSeparatorLoc + ".core");
                ItemStack plant = Machine.getInstance().getplantSeparator_Data().getItemStack("plantSeparator." + plantSeparatorLoc + ".plant");
                ItemStack bottle = Machine.getInstance().getplantSeparator_Data().getItemStack("plantSeparator." + plantSeparatorLoc + ".bottle");
                ItemStack fuel = Machine.getInstance().getplantSeparator_Data().getItemStack("plantSeparator." + plantSeparatorLoc + ".fuel");
                ItemStack result = Machine.getInstance().getplantSeparator_Data().getItemStack("plantSeparator." + plantSeparatorLoc + ".result");
                ItemStack resultJuice = Machine.getInstance().getplantSeparator_Data().getItemStack("plantSeparator." + plantSeparatorLoc + ".resultJuice");
                double exp = Machine.getInstance().getplantSeparator_Data().getDouble("plantSeparator." + plantSeparatorLoc + ".exp");
                double fuelTime = Machine.getInstance().getplantSeparator_Data().getDouble("plantSeparator." + plantSeparatorLoc + ".fuelTime");
                double resultProgressBar = Machine.getInstance().getplantSeparator_Data().getDouble("plantSeparator." + plantSeparatorLoc + ".resultProgressBar");
                UUID createPlayerUUID = UUID.fromString(Machine.getInstance().getplantSeparator_Data().getString("plantSeparator." + plantSeparatorLoc + ".createPlayerUUID"));
                String[] split = plantSeparatorLoc.split(",");
                World world = Bukkit.getWorld(split[0]);
                Location location = new Location(world, Integer.valueOf(split[1]), Integer.valueOf(split[2]), Integer.valueOf(split[3]));

                PlantSeparator plantSeparator = new PlantSeparator(location,state,core,plant,bottle,fuel,result,resultJuice,exp,fuelTime,resultProgressBar,createPlayerUUID);
                plantSeparatorHashMap.put(location, plantSeparator);
                times++;
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f成功載入 " + times + " 個 植物分解機 "));

    }

    public static void savePlantSeparator() {
        Machine.getInstance().getplantSeparator_Data().set("plantSeparator", null);
        plantSeparatorHashMap.keySet().stream().map(location -> plantSeparatorHashMap.get(location)).forEach(f -> {
            String configLocation = f.getPlantSeparatorLoc().getWorld().getName() + "," + f.getPlantSeparatorLoc().getBlockX() + "," + f.getPlantSeparatorLoc().getBlockY() + "," + f.getPlantSeparatorLoc().getBlockZ();
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".State", f.getCurrentState().toString());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".core", f.getCore());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".plant", f.getPlant());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".bottle", f.getBottle());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".fuel", f.getFuel());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".result", f.getResult());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".resultJuice", f.getResultJuice());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".exp", f.getExp());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".fuelTime", f.getFuelTime());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".resultProgressBar", f.getResultProgressBar());
            Machine.getInstance().getplantSeparator_Data().set("plantSeparator." + configLocation + ".createPlayerUUID", f.getCreatePlayerUUID().toString());
        });
        Machine.getInstance().savePlantSeparator_Data();
    }

    public static void deletePlantSeparator(Location plantSeparatorLoc) {
        plantSeparatorHashMap.remove(plantSeparatorLoc);
    }
}
