package me.yiyi1234.machine.itemadderapi;

import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent;
import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.manager.MineralProcessing.MeltingFurnaceManager;
import me.yiyi1234.machine.core.MineralProcessing.MeltingFurnace;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

public class BlockBreak implements Listener {
    @EventHandler
    public void CustomBlockBreak(CustomBlockBreakEvent event) {
        String blockDisplayName = event.getCustomBlockItem().getItemMeta().getDisplayName();
        String configDisplayName = Machine.getInstance().getConfig().getString("MeltingFurnace.BlockDisplayName");
        if (blockDisplayName.contains(configDisplayName)) {

            for (Player playerOnline : Bukkit.getServer().getOnlinePlayers()) {
                if (playerOnline.getOpenInventory().getTitle().contains(Machine.getInstance().getConfig().getString("MeltingFurnace.UI_DisplayName"))) {
                    NamespacedKey key = new NamespacedKey(Machine.getInstance(), "blockLoc");
                    String getInvLoc = playerOnline.getOpenInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
                    String[] args = getInvLoc.split(",");
                    Location loc = new Location(Bukkit.getWorld(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                    if (loc.equals(event.getBlock().getLocation())) {
                        playerOnline.closeInventory();
                    }


                }
            }

            MeltingFurnace meltingFurnace =  MeltingFurnaceManager.getFurnace(event.getBlock().getLocation());
            if (meltingFurnace.getFuel() != null && meltingFurnace.getFuel().getType() != Material.AIR) {
                Bukkit.getWorld(event.getBlock().getWorld().getName()).dropItemNaturally(event.getBlock().getLocation(), MeltingFurnaceManager.getFurnace(event.getBlock().getLocation()).getFuel());
            }
            if (meltingFurnace.getIngredient() != null && meltingFurnace.getIngredient().getType() != Material.AIR) {
                Bukkit.getWorld(event.getBlock().getWorld().getName()).dropItemNaturally(event.getBlock().getLocation(), MeltingFurnaceManager.getFurnace(event.getBlock().getLocation()).getIngredient());
            }
            if (meltingFurnace.getResult() != null && meltingFurnace.getResult().getType() != Material.AIR) {
                Bukkit.getWorld(event.getBlock().getWorld().getName()).dropItemNaturally(event.getBlock().getLocation(), MeltingFurnaceManager.getFurnace(event.getBlock().getLocation()).getResult());
            }
            if (meltingFurnace.getCore() != null && meltingFurnace.getCore().getType() != Material.AIR) {
                Bukkit.getWorld(event.getBlock().getWorld().getName()).dropItemNaturally(event.getBlock().getLocation(), MeltingFurnaceManager.getFurnace(event.getBlock().getLocation()).getCore());
            }
            MeltingFurnaceManager.deleteFurnce(event.getBlock().getLocation());
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c成功拆除熔煉爐，請盡快撿起熔爐及熔爐內的物品。"));

        }

    }
}
