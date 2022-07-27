package me.yiyi1234.machine.core.PlantProcessing;

import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.core.MineralProcessing.MeltingFurnace;
import me.yiyi1234.machine.items.MineralProcessing.MeltingFurnaceItems;
import me.yiyi1234.machine.items.PlantProcessing.PlantSeparatorItem;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlantSeparator {

    private Location plantSeparatorLoc; // 位置
    private State currentState;
    private ItemStack core; // 核心
    private ItemStack plant; // 植物
    private ItemStack bottle; // 瓶子
    private ItemStack fuel; // 燃料
    private ItemStack result; // 分解物
    private ItemStack resultJuice; // 汁液
    private double exp; // 經驗
    private double fuelTime; // 燃料剩餘時間
    private double resultProgressBar; // 製作時間
    private UUID createPlayerUUID;
    private Inventory plantSeparatorUI;

    public enum State {
        IDLE, RUNNING
    }

    public PlantSeparator(Location loc, Player createPlayer) {
        this(loc, State.IDLE, null, null, null, null, null, null, 0.0, 0.0, 0.0, createPlayer.getUniqueId());
    }

    public PlantSeparator(Location location, State state, ItemStack core, ItemStack plant, ItemStack bottle, ItemStack fuel, ItemStack result, ItemStack resultJuice, double exp, double fuelTime, double resultProgressBar, UUID createPlayerUUID) {
        this.plantSeparatorLoc = location;
        this.currentState = state;
        this.core = core;
        this.plant = plant;
        this.bottle = bottle;
        this.fuel = fuel;
        this.result = result;
        this.resultJuice = resultJuice;
        this.exp = exp;
        this.fuelTime = fuelTime;
        this.resultProgressBar = resultProgressBar;
        this.createPlayerUUID = createPlayerUUID;
        setPlantSeparatorUI();
        refresh();
        run();
    }

    public void setPlantSeparatorUI() {
        String DisplayName = "&f" + Machine.getInstance().getConfig().getString("PlantSeparator.UI_DisplayName");
        plantSeparatorUI = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', DisplayName));

        if (getPlant() != null && !getPlant().getType().isAir()) {
            plantSeparatorUI.setItem(10, getPlant());
        }
        if (getFuel() != null && !getFuel().getType().isAir()) {
            plantSeparatorUI.setItem(37, getFuel());
        }
        if (getBottle() != null && !getBottle().getType().isAir()) {
            plantSeparatorUI.setItem(19, getBottle());
        }
        if (getResult() != null && !getResult().getType().isAir()) {
            plantSeparatorUI.setItem(13, getResult());
        }
        if (getResultJuice() != null && !getResultJuice().getType().isAir()) {
            plantSeparatorUI.setItem(22, getResultJuice());
        }
        if (getCore() != null && !getCore().getType().isAir()) {
            plantSeparatorUI.setItem(40, getCore());
        }

        ItemStack loc = new ItemStack(Material.BARRIER);
        ItemMeta locMeta = loc.getItemMeta();
        locMeta.setCustomModelData(1);
        loc.setItemMeta(locMeta);
        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "blockLoc");
        ItemMeta meta = loc.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, this.getPlantSeparatorLoc().getWorld().getName() + "," + this.getPlantSeparatorLoc().getBlockX() + "," + this.getPlantSeparatorLoc().getBlockY() + "," + this.getPlantSeparatorLoc().getBlockZ());
        meta.setDisplayName(MeltingFurnaceItems.hex("&6植物分解器使用說明"));
        List<String> lore = new ArrayList<>();
        lore.add(MeltingFurnaceItems.hex("  &71. 若想啟動植物分解器，請將 分解物、玻璃瓶 及 燃料 放入植物分解機內。"));
        lore.add(MeltingFurnaceItems.hex("     &7放入後植物分解機將自動啟動。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &72. 植物分解機內建防呆機制，若非該欄位的素材，將&c無法放入&7植物分解機內。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &73. 崁入核心能加入分解速度及燃料消耗，若您想崁入核心，"));
        lore.add(MeltingFurnaceItems.hex("  &7   請在植物分解機非運作時崁入。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &74. 區域卸載時，植物分解機仍會持續運作，只需補充熔煉物及燃料。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &75. &c&l若發現任何複製或是機器不正常的情形，請盡快聯繫服主，"));
        lore.add(MeltingFurnaceItems.hex("     &c&l否則將會面臨伺服器停權等相關處置。"));
        meta.setLore(lore);

        loc.setItemMeta(meta);
        plantSeparatorUI.setItem(0, loc);


        ItemStack tempBar = new ItemStack(Material.BOWL);
        ItemMeta tempBarMeta = tempBar.getItemMeta();

        tempBarMeta.setCustomModelData(9);
        tempBarMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6經驗值"));
        tempBar.setItemMeta(tempBarMeta);
        plantSeparatorUI.setItem(34, tempBar);
    }

    public void refresh() {
        Machine.getInstance().getServer().getScheduler().runTaskTimer(Machine.getInstance(), () -> {

            core = plantSeparatorUI.getItem(40);
            plant = plantSeparatorUI.getItem(10);
            resultJuice = plantSeparatorUI.getItem(22);
            bottle = plantSeparatorUI.getItem(19);
            result = plantSeparatorUI.getItem(13);
            fuel = plantSeparatorUI.getItem(37);


        }, 0, 5);
    }

    public void run() {
        Machine.getInstance().getServer().getScheduler().runTaskTimer(Machine.getInstance(), () -> {
            switch (currentState) {
                case RUNNING:
                    fuelTime--;
                    if (plant != null && !plant.isSimilar(PlantSeparatorItem.getdamagedWheatGrass()) && !plant.isSimilar(PlantSeparatorItem.getwheatGrass())) {
                        if (plant != null && plant.getType() != Material.AIR && bottle != null && bottle.getType() != Material.AIR) {

                            resultProgressBar++;
                        }
                    }else {
                        if (plant != null && plant.getType() != Material.AIR) {

                            resultProgressBar++;
                        }
                    }
                    if (plant != null && !plant.isSimilar(PlantSeparatorItem.getdamagedWheatGrass()) && !plant.isSimilar(PlantSeparatorItem.getwheatGrass())) {
                        if (fuelTime <= 0 || plant == null || bottle == null || plant.getType() == Material.AIR || bottle.getType() == Material.AIR) {
                            setCurrentState(State.IDLE);
                        }
                    } else {
                        if (fuelTime <= 0 || plant == null || plant.getType() == Material.AIR) {
                            setCurrentState(State.IDLE);
                        }
                    }
                    break;
                case IDLE:
                    if (fuelTime <= 0 && fuel != null && fuel.getType() != Material.AIR && plant != null && plant.getType() != Material.AIR) {
                        if (plant != null && plant.isSimilar(PlantSeparatorItem.getdamagedWheatGrass()) || plant.isSimilar(PlantSeparatorItem.getwheatGrass())) {
                            NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
                            fuelTime = fuelTime + fuel.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.DOUBLE);

                            fuel.setAmount(fuel.getAmount() - 1);
                            setCurrentState(State.RUNNING);
                        } else if (bottle != null && bottle.getType() != Material.AIR) {
                            NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
                            fuelTime = fuelTime + fuel.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.DOUBLE);


                            fuel.setAmount(fuel.getAmount() - 1);
                            setCurrentState(State.RUNNING);
                        }
                    } else {
                        resultProgressBar = 0;
                    }
                    if (plant != null && !plant.isSimilar(PlantSeparatorItem.getdamagedWheatGrass()) && !plant.isSimilar(PlantSeparatorItem.getwheatGrass())) {
                        if (fuelTime > 0 && plant != null && bottle != null && plant.getType() != Material.AIR && bottle.getType() != Material.AIR) {
                            setCurrentState(State.RUNNING);
                        }
                    } else {
                        if (fuelTime > 0 && plant != null && plant.getType() != Material.AIR) {
                            setCurrentState(State.RUNNING);
                        }
                    }
                    if (fuelTime > 0) {
                        fuelTime--;
                    }

            }
            if (core != null) {
                ItemStack air = new ItemStack(Material.AIR);
                ItemStack progessBar1 = new ItemStack(Material.BOWL);
                ItemMeta progessMeta = progessBar1.getItemMeta();
                progessMeta.setDisplayName(MeltingFurnaceItems.hex("&6進度條"));
                progessMeta.setCustomModelData(14);
                progessBar1.setItemMeta(progessMeta);

                ItemStack progessBar2 = new ItemStack(Material.BOWL);
                ItemMeta progess2Meta = progessBar2.getItemMeta();
                progess2Meta.setDisplayName(MeltingFurnaceItems.hex("&6進度條"));
                progess2Meta.setCustomModelData(15);
                progessBar2.setItemMeta(progess2Meta);

                ItemStack progessBar3 = new ItemStack(Material.BOWL);
                ItemMeta progess3Meta = progessBar3.getItemMeta();
                progess3Meta.setDisplayName(MeltingFurnaceItems.hex("&6進度條"));
                progess3Meta.setCustomModelData(16);
                progessBar3.setItemMeta(progess3Meta);

                if (core.isSimilar(PlantSeparatorItem.getnormalTurnCore())) {

                    if (resultProgressBar == 0) {
                        plantSeparatorUI.setItem(20, air);
                    }
                    if (resultProgressBar > 0 && resultProgressBar <= 1.66) {
                        plantSeparatorUI.setItem(20, progessBar1);
                    }
                    if (resultProgressBar > 1.66 && resultProgressBar < 3.33) {
                        plantSeparatorUI.setItem(20, progessBar2);
                    }
                    if (resultProgressBar >= 3.33) {
                        plantSeparatorUI.setItem(20, progessBar3);
                        resultProgressBar = resultProgressBar - 3.33;
                        finishItem();
                    }

                }

                if (core.isSimilar(PlantSeparatorItem.getrareTurnCore())) {
                    if (resultProgressBar == 0) {
                        plantSeparatorUI.setItem(20, air);
                    }
                    if (resultProgressBar > 0 && resultProgressBar <= 1.25) {
                        plantSeparatorUI.setItem(20, progessBar1);
                    }
                    if (resultProgressBar > 1.25 && resultProgressBar < 2.5) {
                        plantSeparatorUI.setItem(20, progessBar2);
                    }
                    if (resultProgressBar >= 2.5) {
                        plantSeparatorUI.setItem(20, progessBar3);
                        resultProgressBar = resultProgressBar - 2.5;
                        finishItem();
                    }

                }

                if (core.isSimilar(PlantSeparatorItem.getepicTurnCore())) {
                    if (resultProgressBar == 0) {
                        plantSeparatorUI.setItem(20, air);
                    }
                    if (resultProgressBar > 0 && resultProgressBar <= 0.83) {
                        plantSeparatorUI.setItem(20, progessBar1);
                    }
                    if (resultProgressBar > 0.83 && resultProgressBar < 1.66) {
                        plantSeparatorUI.setItem(20, progessBar2);
                    }
                    if (resultProgressBar >= 1.66) {
                        plantSeparatorUI.setItem(20, progessBar3);
                        resultProgressBar = resultProgressBar - 1.66;
                        finishItem();
                    }


                }


            } else {
                ItemStack air = new ItemStack(Material.AIR);


                ItemStack progessBar1 = new ItemStack(Material.BOWL);
                ItemMeta progessMeta = progessBar1.getItemMeta();
                progessMeta.setDisplayName(MeltingFurnaceItems.hex("&6進度條"));
                progessMeta.setCustomModelData(14);
                progessBar1.setItemMeta(progessMeta);

                ItemStack progessBar2 = new ItemStack(Material.BOWL);
                ItemMeta progess2Meta = progessBar2.getItemMeta();
                progess2Meta.setDisplayName(MeltingFurnaceItems.hex("&6進度條"));
                progess2Meta.setCustomModelData(15);
                progessBar2.setItemMeta(progess2Meta);

                ItemStack progessBar3 = new ItemStack(Material.BOWL);
                ItemMeta progess3Meta = progessBar3.getItemMeta();
                progess3Meta.setDisplayName(MeltingFurnaceItems.hex("&6進度條"));
                progess3Meta.setCustomModelData(16);
                progessBar3.setItemMeta(progess3Meta);


                if (resultProgressBar == 0) {
                    plantSeparatorUI.setItem(20, air);
                }
                if (resultProgressBar > 0 && resultProgressBar <= 2.5) {
                    plantSeparatorUI.setItem(20, progessBar1);
                }
                if (resultProgressBar > 1.25 && resultProgressBar < 5) {
                    plantSeparatorUI.setItem(20, progessBar2);
                }
                if (resultProgressBar >= 5) {
                    plantSeparatorUI.setItem(20, progessBar3);
                    resultProgressBar = resultProgressBar - 5;
                    finishItem();
                }

            }


        }, 0, 20);
    }

    public void finishItem() {

        if (plant.isSimilar(PlantSeparatorItem.getdamagedWheatGrass())) {
            if (result == null) {
                plantSeparatorUI.setItem(13, PlantSeparatorItem.getdamagedFiber());
            } else {
                result.setAmount(result.getAmount() + 1);
            }
        }
        if (plant.isSimilar(PlantSeparatorItem.getwheatGrass())) {
            if (result == null) {
                plantSeparatorUI.setItem(13, PlantSeparatorItem.getfiber());
            } else {
                result.setAmount(result.getAmount() + 1);
            }
        }
        if (plant.isSimilar(PlantSeparatorItem.getdamagedGoldBerries())) {
            bottle.setAmount(bottle.getAmount() - 1);
            if (result == null) {
                plantSeparatorUI.setItem(13, PlantSeparatorItem.getdamagedFiber());
            } else {
                result.setAmount(result.getAmount() + 1);
            }

            if (resultJuice == null) {
                plantSeparatorUI.setItem(22, PlantSeparatorItem.getgoldBerryJuice());
            } else {
                resultJuice.setAmount(resultJuice.getAmount() + 1);
            }
        }
        if (plant.isSimilar(PlantSeparatorItem.getgoldBerries())) {
            bottle.setAmount(bottle.getAmount() - 1);
            if (result == null) {
                plantSeparatorUI.setItem(13, PlantSeparatorItem.getfiber());
            } else {
                result.setAmount(result.getAmount() + 1);
            }

            if (resultJuice == null) {
                plantSeparatorUI.setItem(22, PlantSeparatorItem.getgoldBerryJuice());
            } else {
                resultJuice.setAmount(resultJuice.getAmount() + 1);
            }
        }
        if (plant.isSimilar(PlantSeparatorItem.getdamagedSilverBerries())) {
            bottle.setAmount(bottle.getAmount() - 1);
            if (result == null) {
                plantSeparatorUI.setItem(13, PlantSeparatorItem.getdamagedFiber());
            } else {
                result.setAmount(result.getAmount() + 1);
            }

            if (resultJuice == null) {
                plantSeparatorUI.setItem(22, PlantSeparatorItem.getsilverBerryJuice());
            } else {
                resultJuice.setAmount(resultJuice.getAmount() + 1);
            }
        }
        if (plant.isSimilar(PlantSeparatorItem.getsilverBerries())) {
            bottle.setAmount(bottle.getAmount() - 1);
            if (result == null) {
                plantSeparatorUI.setItem(13, PlantSeparatorItem.getfiber());
            } else {
                result.setAmount(result.getAmount() + 1);
            }

            if (resultJuice == null) {
                plantSeparatorUI.setItem(22, PlantSeparatorItem.getsilverBerryJuice());
            } else {
                resultJuice.setAmount(resultJuice.getAmount() + 1);
            }
        }
        plant.setAmount(plant.getAmount() - 1);
    }

    public Inventory getPlantSeparatorUI() {
        return plantSeparatorUI;
    }

    public Location getPlantSeparatorLoc() {
        return plantSeparatorLoc;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State state) {
        currentState = state;
    }

    public ItemStack getCore() {
        return core;
    }

    public void setCore(ItemStack core) {
        this.core = core;
    }

    public ItemStack getPlant() {
        return plant;
    }

    public void setPlant(ItemStack plant) {
        this.plant = plant;
    }

    public ItemStack getBottle() {
        return bottle;
    }

    public void setBottle(ItemStack bottle) {
        this.bottle = bottle;
    }

    public ItemStack getFuel() {
        return fuel;
    }

    public void setFuel(ItemStack fuel) {
        this.fuel = fuel;
    }

    public ItemStack getResult() {
        return result;
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public ItemStack getResultJuice() {
        return resultJuice;
    }

    public void setResultJuice(ItemStack resultJuice) {
        this.resultJuice = resultJuice;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getFuelTime() {
        return fuelTime;
    }

    public void setFuelTime(double fuelTime) {
        this.fuelTime = fuelTime;
    }

    public double getResultProgressBar() {
        return resultProgressBar;
    }

    public void setResultProgressBar(double resultProgressBar) {
        this.resultProgressBar = resultProgressBar;
    }

    public UUID getCreatePlayerUUID() {
        return createPlayerUUID;
    }

    public void setCreatePlayerUUID(UUID createPlayerUUID) {
        this.createPlayerUUID = createPlayerUUID;
    }
}
