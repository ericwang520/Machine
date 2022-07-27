package me.yiyi1234.machine.ui.MineralProcessing;

import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.manager.MineralProcessing.MeltingFurnaceManager;
import me.yiyi1234.machine.core.MineralProcessing.MeltingFurnace;
import me.yiyi1234.machine.items.MineralProcessing.MeltingFurnaceItems;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MeltingFurnaceUI implements Listener {
    public Map<UUID, Long> cooldowns = new HashMap<UUID, Long>();

    @EventHandler
    public void clickEvent(InventoryClickEvent event) {

        String UI_DisplayName = Machine.getInstance().getConfig().getString("MeltingFurnace.UI_DisplayName");

        if (event.getView().getTitle().contains(UI_DisplayName)) {
            if (event.getClickedInventory() == null) {
                return;
            }
            if (event.isShiftClick()) {
                event.setCancelled(true);
                return;
            }






            if (event.getClickedInventory().equals(event.getView().getTopInventory())) {
                ItemStack data = event.getClickedInventory().getItem(0);
                NamespacedKey key = new NamespacedKey(Machine.getInstance(), "blockLoc");

                String locString = data.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
                String[] args = locString.split(",");
                Location loc = new Location(Bukkit.getWorld(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                MeltingFurnace getfurnace = MeltingFurnaceManager.getFurnace(loc);


                //  核心=13  熔煉物=19 成果物=31 燃料=37
                if (event.getSlot() == 13 || event.getSlot() == 19 || event.getSlot() == 37 || event.getSlot() == 31) {

                    if (cooldowns.containsKey(event.getWhoClicked().getUniqueId())) {
                        long time = cooldowns.get(event.getWhoClicked().getUniqueId());
                        if (time > System.currentTimeMillis()) {
                            event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &c請放慢您的速度，稍後再嘗試一次。"));
                            event.setCancelled(true);
                            return;
                        }
                    }
                    cooldowns.put(event.getWhoClicked().getUniqueId(), System.currentTimeMillis() + 100);

                    if (event.getSlot() == 19) {

                        ItemStack cursorItem = event.getCursor();

                        ItemStack hematite = MeltingFurnaceItems.getRawHematite();
                        ItemStack hematiteShards = MeltingFurnaceItems.getRawHematiteShards();
                        ItemStack whiteCopper = MeltingFurnaceItems.getRawWhitecopper();
                        ItemStack whitecopperShards = MeltingFurnaceItems.getRawWhitecopperShards();
                        ItemStack siderite = MeltingFurnaceItems.getRawSiderite();
                        ItemStack sideriteShards = MeltingFurnaceItems.getRawSideriteShards();
                        ItemStack emvine = MeltingFurnaceItems.getRawEmvine();
                        ItemStack emvineShards = MeltingFurnaceItems.getRawEmvineShards();
                        // result
                        ItemStack hematiteIngot = MeltingFurnaceItems.getHematiteIngot();
                        ItemStack hematiteNuggets = MeltingFurnaceItems.getHematiteNuggets();
                        ItemStack whitecopperIngot = MeltingFurnaceItems.getWhitecopperIngot();
                        ItemStack whitecopperNuggets = MeltingFurnaceItems.getWhitecopperNuggets();
                        ItemStack sideriteIngot = MeltingFurnaceItems.getSideriteIngot();
                        ItemStack sideriteNuggets = MeltingFurnaceItems.getSideriteNuggets();
                        ItemStack emvineIngot = MeltingFurnaceItems.getEmvineIngot();
                        ItemStack emvineNuggets = MeltingFurnaceItems.getEmvineNuggets();

                        if (cursorItem.getType() != Material.AIR) {
                            if (cursorItem.isSimilar(hematite) || cursorItem.isSimilar(hematiteShards) || cursorItem.isSimilar(whiteCopper) || cursorItem.isSimilar(whitecopperShards) || cursorItem.isSimilar(siderite) || cursorItem.isSimilar(sideriteShards) || cursorItem.isSimilar(emvine) || cursorItem.isSimilar(emvineShards)) {

                                if (getfurnace.getResult() != null && getfurnace.getResult().getType() != Material.AIR) {
                                    if (getfurnace.getResult().getAmount() + cursorItem.getAmount() > 64) {
                                        event.setCancelled(true);
                                    }
                                    if (cursorItem.isSimilar(hematite) && !getfurnace.getResult().isSimilar(hematiteIngot)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                    if (cursorItem.isSimilar(hematiteShards) && !getfurnace.getResult().isSimilar(hematiteNuggets)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                    if (cursorItem.isSimilar(whiteCopper) && !getfurnace.getResult().isSimilar(whitecopperIngot)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                    if (cursorItem.isSimilar(whitecopperShards) && !getfurnace.getResult().isSimilar(whitecopperNuggets)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                    if (cursorItem.isSimilar(siderite) && !getfurnace.getResult().isSimilar(sideriteIngot)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                    if (cursorItem.isSimilar(sideriteShards) && !getfurnace.getResult().isSimilar(sideriteNuggets)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                    if (cursorItem.isSimilar(emvine) && !getfurnace.getResult().isSimilar(emvineIngot)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                    if (cursorItem.isSimilar(emvineShards) && !getfurnace.getResult().isSimilar(emvineNuggets)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將熔煉爐內的成品取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                }


                            } else {
                                event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c這個東西無法熔煉。"));
                                event.setCancelled(true);
                                return;
                            }
                        }
                    }

                    if (event.getSlot() == 37) {
                        ItemStack cursorItem = event.getCursor();

                        ItemStack coalShards = MeltingFurnaceItems.getCoalShards();
                        ItemStack coal = MeltingFurnaceItems.getCoal();
                        ItemStack NormalBiodiesel = MeltingFurnaceItems.getNormalBiodiesel();
                        ItemStack RareBiodiesel = MeltingFurnaceItems.getRareBiodiesel();
                        ItemStack EpicBiodiesel = MeltingFurnaceItems.getEpicBiodiesel();

                        if (cursorItem.getType() != Material.AIR) {

                            if (cursorItem.isSimilar(coalShards) || cursorItem.isSimilar(coal) || cursorItem.isSimilar(NormalBiodiesel) || cursorItem.isSimilar(RareBiodiesel) || cursorItem.isSimilar(EpicBiodiesel)) {
                            } else {
                                event.setCancelled(true);
                            }
                        }


                    }


                    // core
                    if (event.getSlot() == 13) {
                        if (getfurnace.getState() == MeltingFurnace.State.RUNNING) {
                            event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先停止熔煉爐運作在崁入或拔除核心。"));
                            event.setCancelled(true);
                            return;
                        }
                        if (event.getCursor().equals(MeltingFurnaceItems.getNormalCore()) || event.getCursor().equals(MeltingFurnaceItems.getRareCore()) || event.getCursor().equals(MeltingFurnaceItems.getEpicCore())) {

                            if (event.getClickedInventory().getItem(13) != null) {
                                event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &c您已經放入過一個 " + event.getClickedInventory().getItem(13).getItemMeta().getDisplayName()));

                                event.setCancelled(true);

                            } else {
                                if (event.getCursor().equals(MeltingFurnaceItems.getNormalCore())) {
                                    event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &6成功崁入 " + MeltingFurnaceItems.getNormalCore().getItemMeta().getDisplayName()));
//
                                } else if (event.getCursor().equals(MeltingFurnaceItems.getRareCore())) {
                                    event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &6成功崁入 " + MeltingFurnaceItems.getRareCore().getItemMeta().getDisplayName()));
//
                                } else if (event.getCursor().equals(MeltingFurnaceItems.getEpicCore())) {
                                    event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &6成功崁入 " + MeltingFurnaceItems.getEpicCore().getItemMeta().getDisplayName()));
//
                                }

                            }

                        } else {
                            if (!event.getAction().equals(InventoryAction.PICKUP_ALL)) {

                                event.setCancelled(true);
                            }

                            if (event.getAction().equals(InventoryAction.PICKUP_ALL)) {
                                if (event.getClickedInventory().getItem(13).equals(MeltingFurnaceItems.getNormalCore())) {
                                    event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &6成功拔出 " + MeltingFurnaceItems.getNormalCore().getItemMeta().getDisplayName()));
                                } else if (event.getClickedInventory().getItem(13).equals(MeltingFurnaceItems.getRareCore())) {
                                    event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &6成功拔出 " + MeltingFurnaceItems.getRareCore().getItemMeta().getDisplayName()));
                                } else if (event.getClickedInventory().getItem(13).equals(MeltingFurnaceItems.getEpicCore())) {
                                    event.getWhoClicked().sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &6成功拔出 " + MeltingFurnaceItems.getEpicCore().getItemMeta().getDisplayName()));

                                }
                            }
                        }
                    }


                    // result
                    if (event.getSlot() == 31) {
                        if (!event.getAction().equals(InventoryAction.PICKUP_ALL)) {
                            event.setCancelled(true);
                        }
                    }

                } else {
                    event.setCancelled(true);
                }
            }


        }


    }



    @EventHandler
    public void dragEvent(InventoryDragEvent event) {
        String UI_DisplayName = Machine.getInstance().getConfig().getString("MeltingFurnace.UI_DisplayName");
        if (event.getView().getTitle().contains(UI_DisplayName)) {

            if (event.getInventory().getType() == InventoryType.PLAYER) {
                return;
            }
            Set<Integer> rawslot = event.getRawSlots();
            int[] dragslots = rawslot.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();

            for (int i = 0; i < dragslots.length; i++) {
                if (dragslots[i] <= 54) {
                    event.setCancelled(true);
                }
            }


        }


    }


}
