package me.yiyi1234.machine.levelsystem.mineralprocessing;


import me.yiyi1234.machine.Machine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineralLevel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            // Machine level minerallevel <give|take> <ID> <int> <Level | exp>
        if (args.length == 6) {
            if (args[0].equalsIgnoreCase("level") && args[1].equalsIgnoreCase("minerallevel")) {
                if (args[2].equalsIgnoreCase("give")) {
                    if (args[5].equalsIgnoreCase("level")) {
                        Player player = Bukkit.getPlayer(args[3]);
                        int getLevels = Machine.getInstance().levels.getLevels(player.getUniqueId(), "mineralLevel");
                        int plusLevels = Integer.parseInt(args[4]);
                        Machine.getInstance().levels.setLevels(player.getUniqueId(), "mineralLevel" , getLevels + plusLevels);

                    }
                    if (args[5].equalsIgnoreCase("exp")) {
                        Player player = Bukkit.getPlayer(args[3]);
                        Double getExp = Machine.getInstance().levels.getExp(player.getUniqueId(), "mineralExp");
                        Double plusExp = Double.parseDouble(args[4]);
                        Machine.getInstance().levels.setExp(player.getUniqueId(), "mineralExp" , getExp + plusExp);

                    }


                }else if (args[2].equalsIgnoreCase("take")) {
                    if (args[5].equalsIgnoreCase("level")) {
                        Player player = Bukkit.getPlayer(args[3]);
                        int getLevels = Machine.getInstance().levels.getLevels(player.getUniqueId(), "mineralLevel");
                        int minusLevels = Integer.parseInt(args[4]);

                        if (minusLevels > getLevels) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &c玩家 " + args[3] + " 只有 " + getLevels + " 等.."));
                            return false;
                        }
                        Machine.getInstance().levels.setLevels(player.getUniqueId(), "mineralLevel" , getLevels - minusLevels);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6系統&7] &a成功拿取玩家 " + args[3] + " 礦物加工等級 " + minusLevels + "等。 &7(目前玩家有 " + Machine.getInstance().levels.getLevels(player.getUniqueId(), "mineralLevel") + "等)"));

                    }
                    if (args[5].equalsIgnoreCase("exp")) {
                        Player player = Bukkit.getPlayer(args[3]);
                        Double getExp = Machine.getInstance().levels.getExp(player.getUniqueId(), "mineralExp");
                        Double minusExp = Double.parseDouble(args[4]);
                        Machine.getInstance().levels.setExp(player.getUniqueId(), "mineralExp" , getExp - minusExp);

                    }

                }

            }
        }

        return false;
    }


}
