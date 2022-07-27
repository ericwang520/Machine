package me.yiyi1234.machine.itemadderapi;

import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent;
import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.manager.MineralProcessing.MeltingFurnaceManager;
import me.yiyi1234.machine.manager.PlantProcessing.PlantSeparatorManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class BlockPlace implements Listener {
    @EventHandler
    public void CustomBlockPlace(CustomBlockPlaceEvent event) {
        ItemStack itemStack = event.getItemInHand();
        String blockDisplayName = itemStack.getItemMeta().getDisplayName();
        if (blockDisplayName.contains(Machine.getInstance().getConfig().getString("MeltingFurnace.BlockDisplayName"))){
            MeltingFurnaceManager.addFurnace(event.getBlock().getLocation(), event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功建立熔煉爐。"));
        }

        if (blockDisplayName.contains(Machine.getInstance().getConfig().getString("PlantSeparator.BlockDisplayName"))) {
            PlantSeparatorManager.addPlantSeparator(event.getBlock().getLocation(), event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功建立植物分解機。"));
        }
    }
}
