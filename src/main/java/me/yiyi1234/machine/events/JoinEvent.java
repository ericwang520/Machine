package me.yiyi1234.machine.events;

import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.sql.SQLLevels;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Machine.getInstance().levels.createPlayer(event.getPlayer());
    }
}
