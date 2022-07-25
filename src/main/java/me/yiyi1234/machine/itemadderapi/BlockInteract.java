package me.yiyi1234.machine.itemadderapi;

import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent;
import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.manager.MineralProcessing.MeltingFurnaceManager;
import me.yiyi1234.machine.items.MineralProcessing.MeltingFurnaceItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class BlockInteract implements Listener {
    public Map<UUID, Long> cooldowns = new HashMap<UUID, Long>();
    @EventHandler
    public void CustomBlockInteract(CustomBlockInteractEvent event) {
        Action clickAction = event.getAction();
        Player player = event.getPlayer();


        if (clickAction == Action.RIGHT_CLICK_BLOCK) {
            String displayName = Objects.requireNonNull(event.getCustomBlockItem().getItemMeta()).getDisplayName();

            if (displayName.contains(Machine.getInstance().getConfig().getString("MeltingFurnace.BlockDisplayName"))){
                if (cooldowns.containsKey(event.getPlayer().getUniqueId())) {
                    long time = cooldowns.get(event.getPlayer().getUniqueId());
                    if (time > System.currentTimeMillis()) {
                        event.getPlayer().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &c請放慢您的速度，稍後再嘗試一次。"));
                        event.setCancelled(true);
                        return;
                    }
                }
                cooldowns.put(event.getPlayer().getUniqueId(), System.currentTimeMillis() + 1000);

                event.getPlayer().openInventory(MeltingFurnaceManager.getFurnace(event.getBlockClicked().getLocation()).getFurnaceUI());


            }
        }
    }
}
