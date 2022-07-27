package me.yiyi1234.machine.itemadderapi;

import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ConnectItemadder implements Listener {
    @EventHandler
    public void LoadDataEvent(ItemsAdderLoadDataEvent event) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &6成功與 ItemsAdder 串接。"));
    }
}
