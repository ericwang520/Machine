package me.yiyi1234.machine.manager.MineralProcessing;

import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.core.MineralProcessing.MeltingFurnace;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import java.util.HashMap;
import java.util.UUID;

public class MeltingFurnaceManager {
    private static HashMap<Location, MeltingFurnace> fur = new HashMap<>();

    public static MeltingFurnace addFurnace(Location loc, Player createPlayer) {
        if (fur.containsKey(loc))
            fur.remove(loc);
        MeltingFurnace furnace = new MeltingFurnace(loc, createPlayer);
        fur.put(loc, furnace);
        return furnace;
    }

    public static MeltingFurnace getFurnace(Location loc) {
        return fur.get(loc);
    }

    public static void loadFurnace() {
        int times = 0;

        if (Machine.getInstance().getMeltingFurnace_Data().getConfigurationSection("furnace") != null) {
            for (String furnaceLoc : Machine.getInstance().getMeltingFurnace_Data().getConfigurationSection("furnace").getKeys(false)) {
                MeltingFurnace.State state = MeltingFurnace.State.valueOf(Machine.getInstance().getMeltingFurnace_Data().getString("furnace." + furnaceLoc + ".State"));
                ItemStack fuel = Machine.getInstance().getMeltingFurnace_Data().getItemStack("furnace." + furnaceLoc + ".fuel");
                ItemStack ingredient = Machine.getInstance().getMeltingFurnace_Data().getItemStack("furnace." + furnaceLoc + ".ingredient");
                ItemStack result = Machine.getInstance().getMeltingFurnace_Data().getItemStack("furnace." + furnaceLoc + ".result");
                ItemStack core = Machine.getInstance().getMeltingFurnace_Data().getItemStack("furnace." + furnaceLoc + ".core");
                double fuelTime = Machine.getInstance().getMeltingFurnace_Data().getDouble("furnace." + furnaceLoc + ".fuelTime");
                double resultProgressBar = Machine.getInstance().getMeltingFurnace_Data().getDouble("furnace." + furnaceLoc + ".resultProgressBar");
                double temp = Machine.getInstance().getMeltingFurnace_Data().getDouble("furnace." + furnaceLoc + ".temp");
                UUID createPlayerUUID = UUID.fromString(Machine.getInstance().getMeltingFurnace_Data().getString("furnace." + furnaceLoc + ".createPlayerUUID"));

                // world,x=-230,y=96,z=296
                String[] split = furnaceLoc.split(",");

                World world = Bukkit.getWorld(split[0]);
                Location Loc = new Location(world, Integer.valueOf(split[1]), Integer.valueOf(split[2]), Integer.valueOf(split[3]));


                MeltingFurnace furnace = new MeltingFurnace(Loc, state, ingredient, fuel, result, core, fuelTime ,resultProgressBar,temp, createPlayerUUID);
                fur.put(Loc, furnace);
                times++;

            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f成功載入 " + times + " 個 熔煉爐 "));

    }
    public static void saveFurnace() {
        Machine.getInstance().getMeltingFurnace_Data().set("furnace", null);
        fur.keySet().stream().map(loc -> fur.get(loc)).forEach(f -> {
            String configLocation = f.getLoc().getWorld().getName() + "," + f.getLoc().getBlockX() + "," + f.getLoc().getBlockY() + "," + f.getLoc().getBlockZ();
            Machine.getInstance().getMeltingFurnace_Data().set("furnace." + configLocation + ".State", f.getState().toString());
            Machine.getInstance().getMeltingFurnace_Data().set("furnace." + configLocation + ".fuel", f.getFuel());
            Machine.getInstance().getMeltingFurnace_Data().set("furnace." + configLocation + ".ingredient", f.getIngredient());
            Machine.getInstance().getMeltingFurnace_Data().set("furnace." + configLocation + ".result", f.getResult());
            Machine.getInstance().getMeltingFurnace_Data().set("furnace." + configLocation + ".temp", f.getTemp());
            Machine.getInstance().getMeltingFurnace_Data().set("furnace." + configLocation + ".core", f.getCore());
            Machine.getInstance().getMeltingFurnace_Data().set("furnace." + configLocation + ".createPlayerUUID", f.getCreatePlayerUUID().toString());

        });
        Machine.getInstance().saveMeltingFurnace_Data();
    }
    public static void deleteFurnce(Location furnceLoc) {
        fur.remove(furnceLoc);
    }

}
