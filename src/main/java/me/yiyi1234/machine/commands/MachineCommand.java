package me.yiyi1234.machine.commands;

import me.yiyi1234.machine.items.PlantProcessing.PlantSeparatorItem;
import me.yiyi1234.machine.manager.MineralProcessing.MeltingFurnaceManager;
import me.yiyi1234.machine.items.MineralProcessing.MeltingFurnaceItems;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import java.util.ArrayList;
import java.util.List;

public class MachineCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("machine.commands")) {
            if (args.length < 1) {
                TextComponent helpMessage = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c若您需要指令幫助介面請點擊此訊息或輸入 &7/machine help"));
                helpMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/machine help"));
                Player player = (Player) sender;
                player.spigot().sendMessage(helpMessage);
                return true;
            }
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(MeltingFurnaceItems.hex("&bMACHINE 機械插件幫助介面"));
                sender.sendMessage(MeltingFurnaceItems.hex("&e拿取物品 &7- /machine item <機械名稱> <物品類別> <物品名稱> <數量>"));

            }

            if (args[0].equalsIgnoreCase("save")) {
                MeltingFurnaceManager.saveFurnace();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功儲存資料到資料庫。"));

            }


            if (args[0].equalsIgnoreCase("item")) {
                if (args[1].equalsIgnoreCase("MeltingFurnace")) {

                    if (args[2].equalsIgnoreCase("fuel")) {
                        Player player = (Player) sender;
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);

                            ItemStack coalShards = MeltingFurnaceItems.getCoalShards();
                            ItemStack coal = MeltingFurnaceItems.getCoal();
                            ItemStack NormalBiodiesel = MeltingFurnaceItems.getNormalBiodiesel();
                            ItemStack RareBiodiesel = MeltingFurnaceItems.getRareBiodiesel();
                            ItemStack EpicBiodiesel = MeltingFurnaceItems.getEpicBiodiesel();

                            if (args[3].equalsIgnoreCase("煤炭碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + coalShards.getItemMeta().getDisplayName()));
                                coalShards.setAmount(amount);
                                player.getInventory().addItem(coalShards);
                            }
                            if (args[3].equalsIgnoreCase("煤炭")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + coal.getItemMeta().getDisplayName()));
                                coal.setAmount(amount);
                                player.getInventory().addItem(coal);
                            }
                            if (args[3].equalsIgnoreCase("初級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + NormalBiodiesel.getItemMeta().getDisplayName()));
                                NormalBiodiesel.setAmount(amount);
                                player.getInventory().addItem(NormalBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("中級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + RareBiodiesel.getItemMeta().getDisplayName()));
                                RareBiodiesel.setAmount(amount);
                                player.getInventory().addItem(RareBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("高級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + EpicBiodiesel.getItemMeta().getDisplayName()));
                                EpicBiodiesel.setAmount(amount);
                                player.getInventory().addItem(EpicBiodiesel);
                            }


                        } else if (args.length == 4) {
                            ItemStack coalShards = MeltingFurnaceItems.getCoalShards();
                            ItemStack coal = MeltingFurnaceItems.getCoal();
                            ItemStack NormalBiodiesel = MeltingFurnaceItems.getNormalBiodiesel();
                            ItemStack RareBiodiesel = MeltingFurnaceItems.getRareBiodiesel();
                            ItemStack EpicBiodiesel = MeltingFurnaceItems.getEpicBiodiesel();

                            if (args[3].equalsIgnoreCase("煤炭碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + coalShards.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(coalShards);
                            }
                            if (args[3].equalsIgnoreCase("煤炭")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + coal.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(coal);
                            }
                            if (args[3].equalsIgnoreCase("初級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + NormalBiodiesel.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(NormalBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("中級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + RareBiodiesel.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(RareBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("高級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + EpicBiodiesel.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(EpicBiodiesel);
                            }

                        }
                    }

                    if (args[2].equalsIgnoreCase("Ingredient")) {
                        Player player = (Player) sender;
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);
                            ItemStack hematite = MeltingFurnaceItems.getRawHematite();
                            ItemStack hematiteShards = MeltingFurnaceItems.getRawHematiteShards();
                            ItemStack whiteCopper = MeltingFurnaceItems.getRawWhitecopper();
                            ItemStack whitecopperShards = MeltingFurnaceItems.getRawWhitecopperShards();
                            ItemStack siderite = MeltingFurnaceItems.getRawSiderite();
                            ItemStack sideriteShards = MeltingFurnaceItems.getRawSideriteShards();
                            ItemStack rawEmvine = MeltingFurnaceItems.getRawEmvine();
                            ItemStack rawEmvineShards = MeltingFurnaceItems.getRawEmvineShards();

                            if (args[3].equalsIgnoreCase("赤鐵原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + hematite.getItemMeta().getDisplayName()));
                                hematite.setAmount(amount);
                                player.getInventory().addItem(hematite);
                            }
                            if (args[3].equalsIgnoreCase("赤鐵原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + hematiteShards.getItemMeta().getDisplayName()));
                                hematiteShards.setAmount(amount);
                                player.getInventory().addItem(hematiteShards);
                            }
                            if (args[3].equalsIgnoreCase("白銅原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + whiteCopper.getItemMeta().getDisplayName()));
                                whiteCopper.setAmount(amount);
                                player.getInventory().addItem(whiteCopper);
                            }
                            if (args[3].equalsIgnoreCase("白銅原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + whitecopperShards.getItemMeta().getDisplayName()));
                                whitecopperShards.setAmount(amount);
                                player.getInventory().addItem(whitecopperShards);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + siderite.getItemMeta().getDisplayName()));
                                siderite.setAmount(amount);
                                player.getInventory().addItem(siderite);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + sideriteShards.getItemMeta().getDisplayName()));
                                sideriteShards.setAmount(amount);
                                player.getInventory().addItem(sideriteShards);
                            }
                            if (args[3].equalsIgnoreCase("翠藤原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + rawEmvine.getItemMeta().getDisplayName()));
                                rawEmvine.setAmount(amount);
                                player.getInventory().addItem(rawEmvine);
                            }
                            if (args[3].equalsIgnoreCase("翠藤原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + rawEmvineShards.getItemMeta().getDisplayName()));
                                rawEmvineShards.setAmount(amount);
                                player.getInventory().addItem(rawEmvineShards);
                            }

                        } else if (args.length == 4) {
                            ItemStack hematite = MeltingFurnaceItems.getRawHematite();
                            ItemStack hematiteShards = MeltingFurnaceItems.getRawHematiteShards();
                            ItemStack whiteCopper = MeltingFurnaceItems.getRawWhitecopper();
                            ItemStack whitecopperShards = MeltingFurnaceItems.getRawWhitecopperShards();
                            ItemStack siderite = MeltingFurnaceItems.getRawSiderite();
                            ItemStack sideriteShards = MeltingFurnaceItems.getRawSideriteShards();
                            ItemStack rawEmvine = MeltingFurnaceItems.getRawEmvine();
                            ItemStack rawEmvineShards = MeltingFurnaceItems.getRawEmvineShards();


                            if (args[3].equalsIgnoreCase("赤鐵原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + hematite.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(hematite);
                            }
                            if (args[3].equalsIgnoreCase("赤鐵原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + hematiteShards.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(hematiteShards);
                            }
                            if (args[3].equalsIgnoreCase("白銅原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + whiteCopper.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(whiteCopper);
                            }
                            if (args[3].equalsIgnoreCase("白銅原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + whitecopperShards.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(whitecopperShards);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + siderite.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(siderite);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + sideriteShards.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(sideriteShards);
                            }
                            if (args[3].equalsIgnoreCase("翠藤原礦")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + rawEmvine.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(rawEmvine);
                            }
                            if (args[3].equalsIgnoreCase("翠藤原礦碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + rawEmvineShards.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(rawEmvineShards);
                            }
                        }

                    }

                    if (args[2].equalsIgnoreCase("result")) {
                        Player player = (Player) sender;
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);
                            ItemStack hematiteIngot = MeltingFurnaceItems.getHematiteIngot();
                            ItemStack hematiteNuggets = MeltingFurnaceItems.getHematiteNuggets();
                            ItemStack whitecopperIngot = MeltingFurnaceItems.getWhitecopperIngot();
                            ItemStack whitecopperNuggets = MeltingFurnaceItems.getWhitecopperNuggets();
                            ItemStack sideriteIngot = MeltingFurnaceItems.getSideriteIngot();
                            ItemStack sideriteNuggets = MeltingFurnaceItems.getSideriteNuggets();
                            ItemStack emvineIngot = MeltingFurnaceItems.getEmvineIngot();
                            ItemStack emvineNuggets = MeltingFurnaceItems.getEmvineNuggets();

                            if (args[3].equalsIgnoreCase("赤鐵錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + hematiteIngot.getItemMeta().getDisplayName()));
                                hematiteIngot.setAmount(amount);
                                player.getInventory().addItem(hematiteIngot);
                            }
                            if (args[3].equalsIgnoreCase("赤鐵粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + hematiteNuggets.getItemMeta().getDisplayName()));
                                hematiteNuggets.setAmount(amount);
                                player.getInventory().addItem(hematiteNuggets);
                            }

                            if (args[3].equalsIgnoreCase("白銅錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + whitecopperIngot.getItemMeta().getDisplayName()));
                                whitecopperIngot.setAmount(amount);
                                player.getInventory().addItem(whitecopperIngot);
                            }

                            if (args[3].equalsIgnoreCase("白銅粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + whitecopperNuggets.getItemMeta().getDisplayName()));
                                whitecopperNuggets.setAmount(amount);
                                player.getInventory().addItem(whitecopperNuggets);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + sideriteIngot.getItemMeta().getDisplayName()));
                                sideriteIngot.setAmount(amount);
                                player.getInventory().addItem(sideriteIngot);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + sideriteNuggets.getItemMeta().getDisplayName()));
                                sideriteNuggets.setAmount(amount);
                                player.getInventory().addItem(sideriteNuggets);
                            }
                            if (args[3].equalsIgnoreCase("翠藤錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + emvineIngot.getItemMeta().getDisplayName()));
                                emvineIngot.setAmount(amount);
                                player.getInventory().addItem(emvineIngot);
                            }
                            if (args[3].equalsIgnoreCase("翠藤粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + emvineNuggets.getItemMeta().getDisplayName()));
                                emvineNuggets.setAmount(amount);
                                player.getInventory().addItem(emvineNuggets);
                            }


                        } else if (args.length == 4) {
                            ItemStack hematiteIngot = MeltingFurnaceItems.getHematiteIngot();
                            ItemStack hematiteNuggets = MeltingFurnaceItems.getHematiteNuggets();
                            ItemStack whitecopperIngot = MeltingFurnaceItems.getWhitecopperIngot();
                            ItemStack whitecopperNuggets = MeltingFurnaceItems.getWhitecopperNuggets();
                            ItemStack sideriteIngot = MeltingFurnaceItems.getSideriteIngot();
                            ItemStack sideriteNuggets = MeltingFurnaceItems.getSideriteNuggets();
                            ItemStack emvineIngot = MeltingFurnaceItems.getEmvineIngot();
                            ItemStack emvineNuggets = MeltingFurnaceItems.getEmvineNuggets();

                            if (args[3].equalsIgnoreCase("赤鐵錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + hematiteIngot.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(hematiteIngot);
                            }
                            if (args[3].equalsIgnoreCase("赤鐵粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + hematiteNuggets.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(hematiteNuggets);
                            }
                            if (args[3].equalsIgnoreCase("白銅錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + whitecopperIngot.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(whitecopperIngot);
                            }
                            if (args[3].equalsIgnoreCase("白銅粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + whitecopperNuggets.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(whitecopperNuggets);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + sideriteIngot.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(sideriteIngot);
                            }
                            if (args[3].equalsIgnoreCase("菱鐵粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + sideriteNuggets.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(sideriteNuggets);
                            }
                            if (args[3].equalsIgnoreCase("翠藤錠")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + emvineIngot.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(emvineIngot);
                            }
                            if (args[3].equalsIgnoreCase("翠藤粒")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + emvineNuggets.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(emvineNuggets);
                            }
                        }
                    }

                    if (args[2].equalsIgnoreCase("core")) {
                        Player player = (Player) sender;
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);

                            if (args[3].equalsIgnoreCase("初級熔煉核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + MeltingFurnaceItems.getNormalCore().getItemMeta().getDisplayName()));
                                ItemStack normalCore = MeltingFurnaceItems.getNormalCore();
                                normalCore.setAmount(amount);
                                player.getInventory().addItem(normalCore);
                            }
                            if (args[3].equalsIgnoreCase("中級熔煉核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + MeltingFurnaceItems.getRareCore().getItemMeta().getDisplayName()));
                                ItemStack rareCore = MeltingFurnaceItems.getRareCore();
                                rareCore.setAmount(amount);
                                player.getInventory().addItem(rareCore);

                            }
                            if (args[3].equalsIgnoreCase("高級熔煉核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + MeltingFurnaceItems.getEpicCore().getItemMeta().getDisplayName()));
                                ItemStack epicCore = MeltingFurnaceItems.getEpicCore();
                                epicCore.setAmount(amount);
                                player.getInventory().addItem(epicCore);
                            }

                        } else if (args.length == 4) {
                            if (args[3].equalsIgnoreCase("初級熔煉核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + MeltingFurnaceItems.getNormalCore().getItemMeta().getDisplayName()));
                                player.getInventory().addItem(MeltingFurnaceItems.getNormalCore());
                            }
                            if (args[3].equalsIgnoreCase("中級熔煉核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + MeltingFurnaceItems.getRareCore().getItemMeta().getDisplayName()));
                                player.getInventory().addItem(MeltingFurnaceItems.getRareCore());
                            }
                            if (args[3].equalsIgnoreCase("高級熔煉核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + MeltingFurnaceItems.getEpicCore().getItemMeta().getDisplayName()));
                                player.getInventory().addItem(MeltingFurnaceItems.getEpicCore());
                            }
                        }


                    }
                }
                if (args[1].equalsIgnoreCase("PlantSeparator")) {
                    Player player = (Player) sender;
                    if (args[2].equalsIgnoreCase("core")) {

                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);

                            if (args[3].equalsIgnoreCase("初級轉動核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + PlantSeparatorItem.getnormalTurnCore().getItemMeta().getDisplayName()));
                                ItemStack normalCore = PlantSeparatorItem.getnormalTurnCore();
                                normalCore.setAmount(amount);
                                player.getInventory().addItem(normalCore);
                            }
                            if (args[3].equalsIgnoreCase("中級轉動核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + PlantSeparatorItem.getrareTurnCore().getItemMeta().getDisplayName()));
                                ItemStack rareCore = PlantSeparatorItem.getrareTurnCore();
                                rareCore.setAmount(amount);
                                player.getInventory().addItem(rareCore);

                            }
                            if (args[3].equalsIgnoreCase("高級轉動核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + PlantSeparatorItem.getepicTurnCore().getItemMeta().getDisplayName()));
                                ItemStack epicCore = PlantSeparatorItem.getepicTurnCore();
                                epicCore.setAmount(amount);
                                player.getInventory().addItem(epicCore);
                            }

                        } else if (args.length == 4) {
                            if (args[3].equalsIgnoreCase("初級轉動核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + PlantSeparatorItem.getnormalTurnCore().getItemMeta().getDisplayName()));
                                player.getInventory().addItem(PlantSeparatorItem.getnormalTurnCore());
                            }
                            if (args[3].equalsIgnoreCase("中級轉動核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + PlantSeparatorItem.getrareTurnCore().getItemMeta().getDisplayName()));
                                player.getInventory().addItem(PlantSeparatorItem.getrareTurnCore());
                            }
                            if (args[3].equalsIgnoreCase("高級轉動核心")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + PlantSeparatorItem.getepicTurnCore().getItemMeta().getDisplayName()));
                                player.getInventory().addItem(PlantSeparatorItem.getepicTurnCore());
                            }
                        }
                    }
                    if (args[2].equalsIgnoreCase("plant")) {
                        ItemStack damagedWheatGrass = PlantSeparatorItem.getdamagedWheatGrass();
                        ItemStack wheatGrass = PlantSeparatorItem.getwheatGrass();
                        ItemStack damagedGoldBerries = PlantSeparatorItem.getdamagedGoldBerries();
                        ItemStack goldBerries = PlantSeparatorItem.getgoldBerries();
                        ItemStack damagedSilverBerries = PlantSeparatorItem.getdamagedSilverBerries();
                        ItemStack silverBerries = PlantSeparatorItem.getsilverBerries();
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);
                            if (args[3].equalsIgnoreCase("破損的小麥草")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + damagedWheatGrass.getItemMeta().getDisplayName()));
                                damagedWheatGrass.setAmount(amount);
                                player.getInventory().addItem(damagedWheatGrass);
                            }
                            if (args[3].equalsIgnoreCase("小麥草")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + wheatGrass.getItemMeta().getDisplayName()));
                                wheatGrass.setAmount(amount);
                                player.getInventory().addItem(wheatGrass);
                            }
                            if (args[3].equalsIgnoreCase("破損的金莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + damagedGoldBerries.getItemMeta().getDisplayName()));
                                damagedGoldBerries.setAmount(amount);
                                player.getInventory().addItem(damagedGoldBerries);
                            }
                            if (args[3].equalsIgnoreCase("金莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + goldBerries.getItemMeta().getDisplayName()));
                                goldBerries.setAmount(amount);
                                player.getInventory().addItem(goldBerries);
                            }
                            if (args[3].equalsIgnoreCase("破損的銀莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + damagedSilverBerries.getItemMeta().getDisplayName()));
                                damagedSilverBerries.setAmount(amount);
                                player.getInventory().addItem(damagedSilverBerries);
                            }
                            if (args[3].equalsIgnoreCase("銀莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + silverBerries.getItemMeta().getDisplayName()));
                                silverBerries.setAmount(amount);
                                player.getInventory().addItem(silverBerries);
                            }
                        } else if (args.length == 4) {
                            if (args[3].equalsIgnoreCase("破損的小麥草")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + damagedWheatGrass.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(damagedWheatGrass);
                            }
                            if (args[3].equalsIgnoreCase("小麥草")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + wheatGrass.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(wheatGrass);
                            }
                            if (args[3].equalsIgnoreCase("破損的金莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + damagedGoldBerries.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(damagedGoldBerries);
                            }
                            if (args[3].equalsIgnoreCase("金莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + goldBerries.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(goldBerries);
                            }
                            if (args[3].equalsIgnoreCase("破損的銀莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + damagedSilverBerries.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(damagedSilverBerries);
                            }
                            if (args[3].equalsIgnoreCase("銀莓")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + silverBerries.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(silverBerries);
                            }
                        }

                    }
                    if (args[2].equalsIgnoreCase("resultjuice")) {
                        ItemStack goldBerryJuice = PlantSeparatorItem.getgoldBerryJuice();
                        ItemStack silverBerryJuice = PlantSeparatorItem.getsilverBerryJuice();
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);
                            if (args[3].equalsIgnoreCase("金莓汁液")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + goldBerryJuice.getItemMeta().getDisplayName()));
                                goldBerryJuice.setAmount(amount);
                                player.getInventory().addItem(goldBerryJuice);
                            }
                            if (args[3].equalsIgnoreCase("銀莓汁液")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + silverBerryJuice.getItemMeta().getDisplayName()));
                                silverBerryJuice.setAmount(amount);
                                player.getInventory().addItem(silverBerryJuice);
                            }
                        } else if (args.length == 4) {
                            if (args[3].equalsIgnoreCase("金莓汁液")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + goldBerryJuice.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(goldBerryJuice);
                            }
                            if (args[3].equalsIgnoreCase("銀莓汁液")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + silverBerryJuice.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(silverBerryJuice);
                            }
                        }
                    }
                    if (args[2].equalsIgnoreCase("result")) {
                        ItemStack damagedFiber = PlantSeparatorItem.getdamagedWheatGrass();
                        ItemStack fiber = PlantSeparatorItem.getwheatGrass();
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);
                            if (args[3].equalsIgnoreCase("破損的植物纖維")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + damagedFiber.getItemMeta().getDisplayName()));
                                damagedFiber.setAmount(amount);
                                player.getInventory().addItem(damagedFiber);
                            }
                            if (args[3].equalsIgnoreCase("植物纖維")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + fiber.getItemMeta().getDisplayName()));
                                fiber.setAmount(amount);
                                player.getInventory().addItem(fiber);
                            }

                        } else if (args.length == 4) {
                            if (args[3].equalsIgnoreCase("破損的植物纖維")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + damagedFiber.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(damagedFiber);
                            }
                            if (args[3].equalsIgnoreCase("植物纖維")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + fiber.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(fiber);
                            }

                        }
                    }
                    if (args[2].equalsIgnoreCase("bottle")) {
                        ItemStack bottle = new ItemStack(Material.GLASS_BOTTLE);
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);
                            if (args[3].equalsIgnoreCase("玻璃瓶")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 玻璃瓶"));
                                bottle.setAmount(amount);
                                player.getInventory().addItem(bottle);
                            }
                        } else if (args.length == 4) {
                            if (args[3].equalsIgnoreCase("玻璃瓶")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 玻璃瓶"));
                                player.getInventory().addItem(bottle);
                            }
                        }
                    }
                    if (args[2].equalsIgnoreCase("fuel")) {
                        if (args.length == 5) {
                            int amount = Integer.valueOf(args[4]);

                            ItemStack coalShards = MeltingFurnaceItems.getCoalShards();
                            ItemStack coal = MeltingFurnaceItems.getCoal();
                            ItemStack NormalBiodiesel = MeltingFurnaceItems.getNormalBiodiesel();
                            ItemStack RareBiodiesel = MeltingFurnaceItems.getRareBiodiesel();
                            ItemStack EpicBiodiesel = MeltingFurnaceItems.getEpicBiodiesel();

                            if (args[3].equalsIgnoreCase("煤炭碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + coalShards.getItemMeta().getDisplayName()));
                                coalShards.setAmount(amount);
                                player.getInventory().addItem(coalShards);
                            }
                            if (args[3].equalsIgnoreCase("煤炭")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + coal.getItemMeta().getDisplayName()));
                                coal.setAmount(amount);
                                player.getInventory().addItem(coal);
                            }
                            if (args[3].equalsIgnoreCase("初級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + NormalBiodiesel.getItemMeta().getDisplayName()));
                                NormalBiodiesel.setAmount(amount);
                                player.getInventory().addItem(NormalBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("中級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + RareBiodiesel.getItemMeta().getDisplayName()));
                                RareBiodiesel.setAmount(amount);
                                player.getInventory().addItem(RareBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("高級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f" + amount + " &a個 " + EpicBiodiesel.getItemMeta().getDisplayName()));
                                EpicBiodiesel.setAmount(amount);
                                player.getInventory().addItem(EpicBiodiesel);
                            }


                        } else if (args.length == 4) {
                            ItemStack coalShards = MeltingFurnaceItems.getCoalShards();
                            ItemStack coal = MeltingFurnaceItems.getCoal();
                            ItemStack NormalBiodiesel = MeltingFurnaceItems.getNormalBiodiesel();
                            ItemStack RareBiodiesel = MeltingFurnaceItems.getRareBiodiesel();
                            ItemStack EpicBiodiesel = MeltingFurnaceItems.getEpicBiodiesel();

                            if (args[3].equalsIgnoreCase("煤炭碎片")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + coalShards.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(coalShards);
                            }
                            if (args[3].equalsIgnoreCase("煤炭")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + coal.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(coal);
                            }
                            if (args[3].equalsIgnoreCase("初級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + NormalBiodiesel.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(NormalBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("中級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + RareBiodiesel.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(RareBiodiesel);
                            }
                            if (args[3].equalsIgnoreCase("高級生物柴油")) {
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功獲取 &f1 &a個 " + EpicBiodiesel.getItemMeta().getDisplayName()));
                                player.getInventory().addItem(EpicBiodiesel);
                            }

                        }
                    }
                }
            }
        } else {
            sender.sendMessage(MeltingFurnaceItems.hex("&7[&6系統&7] &c您沒有權限！"));
        }


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("machine.commands")) {
            if (args.length == 1) {
                List<String> arguments = new ArrayList<>();
                arguments.add("help");
                arguments.add("item");
                arguments.add("save");
                return arguments;
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("item")) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("MeltingFurnace");
                    arguments.add("PlantSeparator");
                    return arguments;

                }
            } else if (args.length == 3) {

                if (args[1].equalsIgnoreCase("MeltingFurnace")) {

                    List<String> arguments = new ArrayList<>();
                    arguments.add("core");
                    arguments.add("ingredient");
                    arguments.add("fuel");
                    arguments.add("result");
                    return arguments;

                }
                if (args[1].equalsIgnoreCase("PlantSeparator")) {

                    List<String> arguments = new ArrayList<>();
                    arguments.add("core");
                    arguments.add("result");
                    arguments.add("resultJuice");
                    arguments.add("fuel");
                    arguments.add("plant");
                    arguments.add("bottle");
                    return arguments;

                }
            } else if (args.length == 4) {
                if (args[1].equalsIgnoreCase("PlantSeparator")) {
                    if (args[2].equalsIgnoreCase("core")) {
                        List<String> arguments = new ArrayList<>();
                        arguments.add("初級轉動核心");
                        arguments.add("中級轉動核心");
                        arguments.add("高級轉動核心");
                        return arguments;
                    }
                    if (args[2].equalsIgnoreCase("bottle")) {
                        List<String> arguments = new ArrayList<>();
                        arguments.add("玻璃瓶");
                        return arguments;

                    }
                    if (args[2].equalsIgnoreCase("result")) {
                        List<String> arguments = new ArrayList<>();
                        arguments.add("破損的植物纖維");
                        arguments.add("植物纖維");
                        return arguments;
                    }
                    if (args[2].equalsIgnoreCase("resultJuice")) {
                        List<String> arguments = new ArrayList<>();
                        arguments.add("金莓汁液");
                        arguments.add("銀莓汁液");
                        return arguments;
                    }
                    if (args[2].equalsIgnoreCase("fuel")) {
                        List<String> arguments = new ArrayList<>();
                        arguments.add("煤炭碎片");
                        arguments.add("煤炭");
                        arguments.add("初級生物柴油");
                        arguments.add("中級生物柴油");
                        arguments.add("高級生物柴油");
                        return arguments;

                    }
                    if (args[2].equalsIgnoreCase("plant")) {
                        List<String> arguments = new ArrayList<>();
                        arguments.add("破損的小麥草");
                        arguments.add("小麥草");
                        arguments.add("破損的金莓");
                        arguments.add("金莓");
                        arguments.add("破損的銀莓");
                        arguments.add("銀莓");
                        return arguments;
                    }
                }
                if (args[1].equalsIgnoreCase("MeltingFurnace")) {
                    if (args[2].equalsIgnoreCase("core")) {

                        List<String> arguments = new ArrayList<>();
                        arguments.add("初級熔煉核心");
                        arguments.add("中級熔煉核心");
                        arguments.add("高級熔煉核心");
                        return arguments;

                    }
                    if (args[2].equalsIgnoreCase("ingredient")) {

                        List<String> arguments = new ArrayList<>();
                        arguments.add("赤鐵原礦");
                        arguments.add("赤鐵原礦碎片");
                        arguments.add("白銅原礦");
                        arguments.add("白銅原礦碎片");
                        arguments.add("菱鐵原礦");
                        arguments.add("菱鐵原礦碎片");
                        arguments.add("翠藤原礦");
                        arguments.add("翠藤原礦碎片");
                        return arguments;

                    }
                    if (args[2].equalsIgnoreCase("fuel")) {

                        List<String> arguments = new ArrayList<>();
                        arguments.add("煤炭碎片");
                        arguments.add("煤炭");
                        arguments.add("初級生物柴油");
                        arguments.add("中級生物柴油");
                        arguments.add("高級生物柴油");
                        return arguments;

                    }
                    if (args[2].equalsIgnoreCase("result")) {

                        List<String> arguments = new ArrayList<>();
                        arguments.add("赤鐵錠");
                        arguments.add("赤鐵粒");
                        arguments.add("白銅錠");
                        arguments.add("白銅粒");
                        arguments.add("菱鐵錠");
                        arguments.add("菱鐵粒");
                        arguments.add("翠藤錠");
                        arguments.add("翠藤粒");
                        return arguments;

                    }
                }
            } else if (args.length == 5) {
                if (args[3].equalsIgnoreCase("赤鐵礦") || args[3].equalsIgnoreCase("赤鐵礦碎片") || args[3].equalsIgnoreCase("白銅礦") || args[3].equalsIgnoreCase("白銅礦碎片") || args[3].equalsIgnoreCase("菱鐵礦") || args[3].equalsIgnoreCase("菱鐵礦碎片") || args[3].equalsIgnoreCase("白鉛鋁礦") || args[3].equalsIgnoreCase("白鉛鋁碎片") || args[3].equalsIgnoreCase("赤鐵錠") || args[3].equalsIgnoreCase("赤鐵粒") || args[3].equalsIgnoreCase("白銅錠") || args[3].equalsIgnoreCase("白銅粒") || args[3].equalsIgnoreCase("菱鐵錠") || args[3].equalsIgnoreCase("菱鐵粒") || args[3].equalsIgnoreCase("白鉛鋁錠") || args[3].equalsIgnoreCase("白鉛鋁粒") || args[3].equalsIgnoreCase("初級熔煉核心") || args[3].equalsIgnoreCase("中級熔煉核心") || args[3].equalsIgnoreCase("高級熔煉核心") || args[3].equalsIgnoreCase("煤炭粉") || args[3].equalsIgnoreCase("煤炭塊") || args[3].equalsIgnoreCase("初級生物柴油") || args[3].equalsIgnoreCase("中級生物柴油") || args[3].equalsIgnoreCase("高級生物柴油")) {
                    List<String> arguments = new ArrayList<>();
                    arguments.add("<數量>");
                    return arguments;
                }
            }
        }

        return null;
    }
}
