package me.yiyi1234.machine;

import me.yiyi1234.machine.events.JoinEvent;
import me.yiyi1234.machine.itemadderapi.BlockBreak;
import me.yiyi1234.machine.itemadderapi.BlockInteract;
import me.yiyi1234.machine.itemadderapi.BlockPlace;
import me.yiyi1234.machine.itemadderapi.ConnectItemadder;
import me.yiyi1234.machine.manager.MineralProcessing.MeltingFurnaceManager;
import me.yiyi1234.machine.manager.PlantProcessing.PlantSeparatorManager;
import me.yiyi1234.machine.sql.MySQL;
import me.yiyi1234.machine.sql.SQLLevels;
import me.yiyi1234.machine.ui.MineralProcessing.MeltingFurnaceUI;
import me.yiyi1234.machine.commands.MachineCommand;
import me.yiyi1234.machine.ui.PlantProcessing.PlantSeparatorUI;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class Machine extends JavaPlugin {
    private FileConfiguration config;
    private FileConfiguration meltingFurnace_Data;
    public FileConfiguration meltingFurnace_Item;
    public FileConfiguration plantSeparator_Data;
    public FileConfiguration plantSeparator_Item;
    public static Machine instance;
    public MeltingFurnaceUI MeltingFurnaceUI;
    public MySQL SQL;
    public SQLLevels levels;

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.MeltingFurnaceUI = new MeltingFurnaceUI();
        setInstance(this);
        config();
        meltingFurnace_File();
        setMeltingFurnace_Item();
        setPlantSeparator_Data();
        setPlantSeparator_Item();

        this.SQL = new MySQL();
        this.levels = new SQLLevels(this);

        try {
            SQL.connect();
        } catch (SQLException throwables) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &c資料庫連線失敗"));
            this.getServer().getPluginManager().disablePlugin(this);
        }

        if (SQL.isConnected()) {
            levels.createTable();
            this.getServer().getPluginManager().registerEvents(new ConnectItemadder(), this);
            this.getServer().getPluginManager().registerEvents(new BlockPlace(), this);
            this.getServer().getPluginManager().registerEvents(new BlockInteract(), this);
            this.getServer().getPluginManager().registerEvents(new BlockBreak(), this);
            this.getServer().getPluginManager().registerEvents(new MeltingFurnaceUI(), this);
            this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
            this.getServer().getPluginManager().registerEvents(new PlantSeparatorUI(), this);
            MeltingFurnaceManager.loadFurnace();
            PlantSeparatorManager.loadPlantSeparator();
            getCommand("Machine").setExecutor(new MachineCommand());
            getCommand("Machine").setTabCompleter(new MachineCommand());
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "－－－－－－－－－－－－－－－－－－－－－－");
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Machine &c成功啟動"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&f依依#1350 製作"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "－－－－－－－－－－－－－－－－－－－－－－");
            autoSave();
        }



    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MeltingFurnaceManager.saveFurnace();
        PlantSeparatorManager.savePlantSeparator();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &a成功儲存所有資料至資料庫。"));
    }

    public void config() {
        // 判斷是否有資料夾
        File file = new File(this.getDataFolder().getAbsolutePath() + "/config.yml");
        if (!file.exists()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f正在生成 config.yml"));
            this.getDataFolder().mkdir();
            this.saveResource("config.yml", false);
        }


        config = YamlConfiguration.loadConfiguration(file);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void autoSave() {
        new BukkitRunnable() {
            public void run() {
                MeltingFurnaceManager.saveFurnace();
                PlantSeparatorManager.savePlantSeparator();
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &a成功儲存所有資料至資料庫。"));
            }
        }.runTaskTimer(this, 6000, 6000);
    }



    public void meltingFurnace_File() {
        // 判斷是否有資料夾
        File data = new File(this.getDataFolder().getAbsolutePath() + "/MineralProcessing/MeltingFurnace/data.yml");

        if (!data.exists()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f正在生成 MeltingFurnace/data.yml"));
            this.getDataFolder().mkdir();
        }

        meltingFurnace_Data = YamlConfiguration.loadConfiguration(data);

        try {
            meltingFurnace_Data.save(data);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void saveMeltingFurnace_Data() {
        File file = new File(this.getDataFolder().getAbsolutePath() + "/MineralProcessing/MeltingFurnace/data.yml");
        try {
            meltingFurnace_Data.save(file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f無法儲存資料到 " + file.getName()));
        }
    }


    public void setMeltingFurnace_Item() {
        // 判斷是否有資料夾
        File item = new File(this.getDataFolder().getAbsolutePath() + "/MineralProcessing/MeltingFurnace/item.yml");

        if (!item.exists()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f正在生成 MeltingFurnace/item.yml"));

            try {
                FileUtils.copyInputStreamToFile(this.getResource("meltingfurnaceitem.yml"), new File(this.getDataFolder().getAbsolutePath() + "/MineralProcessing/MeltingFurnace/item.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        meltingFurnace_Item = YamlConfiguration.loadConfiguration(item);


    }
    public void setPlantSeparator_Item() {
        // 判斷是否有資料夾
        File item = new File(this.getDataFolder().getAbsolutePath() + "/PlantProcessing/PlantSeparator/item.yml");

        if (!item.exists()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f正在生成 PlantSeparator/item.yml"));

            try {
                FileUtils.copyInputStreamToFile(this.getResource("plantseparatoritem.yml"), new File(this.getDataFolder().getAbsolutePath() + "/PlantProcessing/PlantSeparator/item.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        plantSeparator_Item = YamlConfiguration.loadConfiguration(item);


    }
    public void setPlantSeparator_Data() {
        // 判斷是否有資料夾
        File data = new File(this.getDataFolder().getAbsolutePath() + "/PlantProcessing/PlantSeparator/data.yml");

        if (!data.exists()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f正在生成 PlantSeparator/data.yml"));
            this.getDataFolder().mkdir();
        }

        plantSeparator_Data = YamlConfiguration.loadConfiguration(data);

        try {
            plantSeparator_Data.save(data);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void savePlantSeparator_Data() {
        File file = new File(this.getDataFolder().getAbsolutePath() + "/PlantProcessing/PlantSeparator/data.yml");
        try {
            plantSeparator_Data.save(file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7<&6Machine&7> &f無法儲存資料到 " + file.getName()));
        }
    }



    public FileConfiguration getMeltingFurnace_Item() { return meltingFurnace_Item; }
    public FileConfiguration getMeltingFurnace_Data() {
        return meltingFurnace_Data;
    }
    public FileConfiguration getplantSeparator_Data() { return plantSeparator_Data; }
    public FileConfiguration getPlantSeparator_Item() { return plantSeparator_Item; }

    private void setInstance(Machine instance) {
        Machine.instance = instance;
    }

    public static Machine getInstance() {
        return instance;
    }


}
