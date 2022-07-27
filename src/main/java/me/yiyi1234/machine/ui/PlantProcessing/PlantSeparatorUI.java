package me.yiyi1234.machine.ui.PlantProcessing;

import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.core.MineralProcessing.MeltingFurnace;
import me.yiyi1234.machine.core.PlantProcessing.PlantSeparator;
import me.yiyi1234.machine.items.MineralProcessing.MeltingFurnaceItems;
import me.yiyi1234.machine.items.PlantProcessing.PlantSeparatorItem;
import me.yiyi1234.machine.manager.MineralProcessing.MeltingFurnaceManager;
import me.yiyi1234.machine.manager.PlantProcessing.PlantSeparatorManager;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlantSeparatorUI implements Listener {
    public Map<UUID, Long> cooldowns = new HashMap<UUID, Long>();

    @EventHandler
    public void clickEvent(InventoryClickEvent event) {
        String UI_DisplayName = Machine.getInstance().getConfig().getString("PlantSeparator.UI_DisplayName");
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
                PlantSeparator getPlantSeparator = PlantSeparatorManager.getPlantSeparator(loc);

//                植物 (plant) 10 分解物 (result) 13 瓶子 (bottle) 19 汁液 (resultJuice) 22 經驗 (exp) 34 燃料 (fuel) 37 核心 (core) 40
                if (event.getSlot() == 10 || event.getSlot() == 13 || event.getSlot() == 19 || event.getSlot() == 37 || event.getSlot() == 40 || event.getSlot() == 22) {


                    if (cooldowns.containsKey(event.getWhoClicked().getUniqueId())) {
                        long time = cooldowns.get(event.getWhoClicked().getUniqueId());
                        if (time > System.currentTimeMillis()) {
                            event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &c請放慢您的速度，稍後再嘗試一次。"));
                            event.setCancelled(true);
                            return;
                        }
                    }
                    cooldowns.put(event.getWhoClicked().getUniqueId(), System.currentTimeMillis() + 100);
                    if (event.getSlot() == 13 || event.getSlot() == 22) {
                        if (!event.getAction().equals(InventoryAction.PICKUP_ALL)) {
                            event.setCancelled(true);
                        }
                    }
                    ItemStack cursorItem = event.getCursor();

                    ItemStack damagedWheatGrass = PlantSeparatorItem.getdamagedWheatGrass();
                    ItemStack wheatGrass = PlantSeparatorItem.getwheatGrass();
                    ItemStack damagedGoldBerries = PlantSeparatorItem.getdamagedGoldBerries();
                    ItemStack goldBerries = PlantSeparatorItem.getgoldBerries();
                    ItemStack damagedSilverBerries = PlantSeparatorItem.getdamagedSilverBerries();
                    ItemStack silverBerries = PlantSeparatorItem.getsilverBerries();

                    ItemStack goldBerryJuice = PlantSeparatorItem.getgoldBerryJuice();
                    ItemStack silverBerryJuice = PlantSeparatorItem.getsilverBerryJuice();

                    ItemStack damagedFiber = PlantSeparatorItem.getdamagedFiber();
                    ItemStack fiber = PlantSeparatorItem.getfiber();


                    if (event.getSlot() == 10) {
                        if (cursorItem.getType() != Material.AIR) {

                            if (getPlantSeparator.getResult() != null && getPlantSeparator.getResult().getAmount() + cursorItem.getAmount() > 64) {
                                event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                event.setCancelled(true);
                                return;
                            }
                            if (getPlantSeparator.getResultJuice() != null && getPlantSeparator.getResultJuice().getAmount() + cursorItem.getAmount() > 64) {
                                event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                event.setCancelled(true);
                                return;
                            }
                            if (cursorItem.isSimilar(damagedWheatGrass) || cursorItem.isSimilar(wheatGrass) || cursorItem.isSimilar(damagedGoldBerries) || cursorItem.isSimilar(goldBerries) || cursorItem.isSimilar(damagedSilverBerries) || cursorItem.isSimilar(silverBerries)) {


                                if (cursorItem.isSimilar(damagedWheatGrass)) {
                                    if (getPlantSeparator.getResult() != null && getPlantSeparator.getResult().getType() != Material.AIR) {
                                        if (!getPlantSeparator.getResult().isSimilar(damagedFiber) || getPlantSeparator.getResultJuice() != null) {
                                            event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                            event.setCancelled(true);
                                            return;
                                        }
                                    }
                                }

                                if (cursorItem.isSimilar(wheatGrass)) {
                                    if (getPlantSeparator.getResult() != null && getPlantSeparator.getResult().getType() != Material.AIR) {
                                        if (!getPlantSeparator.getResult().isSimilar(fiber) || getPlantSeparator.getResultJuice() != null) {
                                            event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                            event.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                                if (cursorItem.isSimilar(damagedGoldBerries)) {
                                    if (getPlantSeparator.getResult() != null && getPlantSeparator.getResult().getType() != Material.AIR) {
                                        if (!getPlantSeparator.getResult().isSimilar(damagedFiber)) {
                                            event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                            event.setCancelled(true);
                                            return;
                                        }
                                    }
                                    if (getPlantSeparator.getResultJuice() != null && getPlantSeparator.getResultJuice().getType() != Material.AIR) {
                                        if (!getPlantSeparator.getResultJuice().isSimilar(goldBerryJuice)) {
                                            event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                            event.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }else {
                                event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c這不是一個熔煉物。"));
                                event.setCancelled(true);
                            }

                            if (cursorItem.isSimilar(goldBerries)) {
                                if (getPlantSeparator.getResult() != null && getPlantSeparator.getResult().getType() != Material.AIR) {
                                    if (!getPlantSeparator.getResult().isSimilar(fiber)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                }
                                if (getPlantSeparator.getResultJuice() != null && getPlantSeparator.getResultJuice().getType() != Material.AIR) {
                                    if (!getPlantSeparator.getResultJuice().isSimilar(goldBerryJuice)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                }
                            }

                            if (cursorItem.isSimilar(damagedSilverBerries)) {
                                if (getPlantSeparator.getResult() != null && getPlantSeparator.getResult().getType() != Material.AIR) {
                                    if (!getPlantSeparator.getResult().isSimilar(damagedFiber)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                }
                                if (getPlantSeparator.getResultJuice() != null && getPlantSeparator.getResultJuice().getType() != Material.AIR) {
                                    if (!getPlantSeparator.getResultJuice().isSimilar(silverBerryJuice)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                }
                            }
                            if (cursorItem.isSimilar(silverBerries)) {
                                if (getPlantSeparator.getResult() != null && getPlantSeparator.getResult().getType() != Material.AIR) {
                                    if (!getPlantSeparator.getResult().isSimilar(fiber)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                }
                                if (getPlantSeparator.getResultJuice() != null && getPlantSeparator.getResultJuice().getType() != Material.AIR) {
                                    if (!getPlantSeparator.getResultJuice().isSimilar(silverBerryJuice)) {
                                        event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先將植物分解器內的纖維或是汁液取出後再嘗試一次。"));
                                        event.setCancelled(true);
                                        return;
                                    }
                                }
                            }


                        }
                    }

                    if (event.getSlot() == 19) {
                        if (event.getCursor().getType() != Material.GLASS_BOTTLE && event.getCursor().getType() != Material.AIR) {
                            event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c此欄位只能放置 玻璃瓶。"));
                            event.setCancelled(true);
                        }
                    }
                    if (event.getSlot() == 37) {

                        ItemStack coalShards = MeltingFurnaceItems.getCoalShards();
                        ItemStack coal = MeltingFurnaceItems.getCoal();
                        ItemStack NormalBiodiesel = MeltingFurnaceItems.getNormalBiodiesel();
                        ItemStack RareBiodiesel = MeltingFurnaceItems.getRareBiodiesel();
                        ItemStack EpicBiodiesel = MeltingFurnaceItems.getEpicBiodiesel();
                        if (cursorItem != null && cursorItem.getType() != Material.AIR) {

                            if (cursorItem.isSimilar(coalShards) || cursorItem.isSimilar(coal) || cursorItem.isSimilar(NormalBiodiesel) || cursorItem.isSimilar(RareBiodiesel) || cursorItem.isSimilar(EpicBiodiesel)) {
                            } else {
                                event.setCancelled(true);
                            }
                        }
                    }
                    if (event.getSlot() == 40) {
                        if (getPlantSeparator.getCurrentState() == PlantSeparator.State.RUNNING) {
                            event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c請先停止熔煉爐運作在崁入或拔除核心。"));
                            event.setCancelled(true);
                            return;
                        }
                        if (event.getCursor().equals(PlantSeparatorItem.getnormalTurnCore()) || event.getCursor().equals(PlantSeparatorItem.getrareTurnCore()) || event.getCursor().equals(PlantSeparatorItem.getepicTurnCore())) {

                            if (event.getClickedInventory().getItem(40) != null) {
                                event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &c您已經放入過一個 " + event.getClickedInventory().getItem(40).getItemMeta().getDisplayName()));

                                event.setCancelled(true);

                            } else {
                                if (event.getCursor().equals(PlantSeparatorItem.getnormalTurnCore())) {
                                    event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &6成功崁入 " + PlantSeparatorItem.getnormalTurnCore().getItemMeta().getDisplayName()));
//
                                } else if (event.getCursor().equals(PlantSeparatorItem.getrareTurnCore())) {
                                    event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &6成功崁入 " + PlantSeparatorItem.getrareTurnCore().getItemMeta().getDisplayName()));
//
                                } else if (event.getCursor().equals(PlantSeparatorItem.getepicTurnCore())) {
                                    event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &6成功崁入 " + PlantSeparatorItem.getepicTurnCore().getItemMeta().getDisplayName()));
//
                                }

                            }

                        } else {
                            if (!event.getAction().equals(InventoryAction.PICKUP_ALL)) {
                                event.setCancelled(true);
                            }

                            if (event.getAction().equals(InventoryAction.PICKUP_ALL)) {
                                if (event.getClickedInventory().getItem(40).equals(PlantSeparatorItem.getnormalTurnCore())) {
                                    event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &6成功拔出 " + PlantSeparatorItem.getnormalTurnCore().getItemMeta().getDisplayName()));
                                } else if (event.getClickedInventory().getItem(40).equals(PlantSeparatorItem.getrareTurnCore())) {
                                    event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &6成功拔出 " + PlantSeparatorItem.getrareTurnCore().getItemMeta().getDisplayName()));
                                } else if (event.getClickedInventory().getItem(40).equals(PlantSeparatorItem.getepicTurnCore())) {
                                    event.getWhoClicked().sendMessage(PlantSeparatorItem.hex("&7[&6系統&7] &6成功拔出 " + PlantSeparatorItem.getepicTurnCore().getItemMeta().getDisplayName()));

                                }
                            }
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
        String UI_DisplayName = Machine.getInstance().getConfig().getString("PlantSeparator.UI_DisplayName");
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
