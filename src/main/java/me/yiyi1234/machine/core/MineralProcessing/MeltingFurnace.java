package me.yiyi1234.machine.core.MineralProcessing;


import me.yiyi1234.machine.Machine;
import me.yiyi1234.machine.items.MineralProcessing.MeltingFurnaceItems;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MeltingFurnace {
    // 熔爐位置 (loc)
    // 是否正在運作
    // 熔煉物剩餘數量
    // 燃料剩餘數量
    // 成品剩餘數量 (不能讓玩家放東西)
    // 燃料剩餘時間
    // 礦物製作進度條
    // 溫度條

    private Location furnaceLoc;
    private State currState;
    private ItemStack ingredient;
    private ItemStack fuel;
    private ItemStack result;
    private ItemStack core;
    private double fuelTime;
    private double resultProgressBar;
    private double temp;
    private UUID createPlayerUUID;
    // 是否有開啟機器

    private Inventory furnaceUI;


    public MeltingFurnace(Location loc, Player createPlayer) {
        this(loc, State.IDLE, null, null, null, null, 0.0, 0.0, 0.0, createPlayer.getUniqueId());
    }

    public enum State {
        IDLE, RUNNING, DONE;
    }

    public MeltingFurnace(Location loc, State state, ItemStack ingredient, ItemStack fuel, ItemStack result, ItemStack core, double fuelTime, double resultProgressBar, double temp, UUID createPlayerUUID) {
        this.furnaceLoc = loc;
        this.currState = state;
        this.ingredient = ingredient;
        this.fuel = fuel;
        this.result = result;
        this.core = core;
        this.fuelTime = fuelTime;
        this.resultProgressBar = resultProgressBar;
        this.temp = temp;
        this.createPlayerUUID = createPlayerUUID;
        setFurnaceUI();
        refresh();
        run();


    }

    public void setFurnaceUI() {
        String DisplayName = "&f" + Machine.getInstance().getConfig().getString("MeltingFurnace.UI_DisplayName");
        furnaceUI = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', DisplayName));

        if (getIngredient() != null && !getIngredient().getType().isAir()) {
            furnaceUI.setItem(19, getIngredient());
        }
        if (getFuel() != null && !getFuel().getType().isAir()) {
            furnaceUI.setItem(37, getFuel());
        }
        if (getResult() != null && !getResult().getType().isAir()) {
            furnaceUI.setItem(31, getResult());
        }
        if (getCore() != null && !getCore().getType().isAir()) {
            ItemStack core = getCore();
            furnaceUI.setItem(13, core);
        }
        ItemStack loc = new ItemStack(Material.BARRIER);
        ItemMeta locMeta = loc.getItemMeta();
        locMeta.setCustomModelData(1);
        loc.setItemMeta(locMeta);
        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "blockLoc");
        ItemMeta meta = loc.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, this.getLoc().getWorld().getName() + "," + this.getLoc().getBlockX() + "," + this.getLoc().getBlockY() + "," + this.getLoc().getBlockZ());
        meta.setDisplayName(MeltingFurnaceItems.hex("&6熔煉爐使用說明"));
        List<String> lore = new ArrayList<>();
        lore.add(MeltingFurnaceItems.hex("  &71. 若想啟動熔煉爐，請將 熔煉物 及 燃料 放入熔煉爐內。"));
        lore.add(MeltingFurnaceItems.hex("     &7放入後熔煉爐將自動啟動。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &72. 熔煉爐內建防呆機制，若非該欄位的素材，將&c無法放入&7熔煉爐內。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &73. 崁入核心能減少熔煉速度及燃料消耗，若您想崁入核心，"));
        lore.add(MeltingFurnaceItems.hex("  &7   請在熔煉爐非運作時崁入。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &74. 區域卸載時，熔煉爐仍會持續運作，只需補充熔煉物及燃料。"));
        lore.add(MeltingFurnaceItems.hex(" "));
        lore.add(MeltingFurnaceItems.hex("  &75. &c&l若發現任何複製或是機器不正常的情形，請盡快聯繫服主，"));
        lore.add(MeltingFurnaceItems.hex("     &c&l否則將會面臨伺服器停權等相關處置。"));
        meta.setLore(lore);

        loc.setItemMeta(meta);
        furnaceUI.setItem(0, loc);


        ItemStack tempBar = new ItemStack(Material.BOWL);
        ItemMeta tempBarMeta = tempBar.getItemMeta();

        tempBarMeta.setCustomModelData(9);
        tempBarMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6溫度條"));
        tempBar.setItemMeta(tempBarMeta);
        furnaceUI.setItem(34, tempBar);
    }

    public State getState() {
        return currState;
    }

    public void setCurrState(State state) {
        currState = state;
    }

    public ItemStack getIngredient() {
        return ingredient;
    }

    public Inventory getFurnaceUI() {
        return furnaceUI;
    }

    public ItemStack getFuel() {
        return fuel;
    }

    public ItemStack getResult() {
        return result;
    }

    public ItemStack getCore() {
        return core;
    }

    public void setFuelTime(double time) {
        fuelTime = time;
    }

    public double getFuelTime() {
        return fuelTime;
    }

    public void setResultProgressBar(double time) {
        resultProgressBar = time;
    }

    public double getResultProgressBar() {
        return resultProgressBar;
    }

    public void setTemp(double setTemp) {
        temp = setTemp;
    }

    public double getTemp() {
        return temp;
    }

    public Location getLoc() {
        return furnaceLoc;
    }

    public UUID getCreatePlayerUUID() {
        return createPlayerUUID;
    }

    public void setCreatePlayerUUID(UUID createPlayerUUID) {
        this.createPlayerUUID = createPlayerUUID;
    }


    public void refresh() {
        Machine.getInstance().getServer().getScheduler().runTaskTimer(Machine.getInstance(), () -> {

            core = furnaceUI.getItem(13);
            ingredient = furnaceUI.getItem(19);
            result = furnaceUI.getItem(31);
            fuel = furnaceUI.getItem(37);




        }, 0, 5);
    }


    public void run() {
        Machine.getInstance().getServer().getScheduler().runTaskTimer(Machine.getInstance(), () -> {



            switch (currState) {
                case RUNNING:
                    ItemStack itemStack = new ItemStack(Material.BOWL);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName(MeltingFurnaceItems.hex("&7剩餘燃燒時間: &f" + fuelTime));
                    itemMeta.setCustomModelData(10);
                    itemStack.setItemMeta(itemMeta);

                    furnaceUI.setItem(28, itemStack);
                    fuelTime--;
                    if ( ingredient != null && ingredient.getType() != Material.AIR) {
                        resultProgressBar++;
                    }
                    if (fuelTime <= 0 || ingredient == null || ingredient.getType() == Material.AIR) {
                        setCurrState(State.IDLE);

                    }
                    break;
                case IDLE:
                    if (fuelTime <= 0 && fuel != null && fuel.getType() != Material.AIR && ingredient != null && ingredient.getType() != Material.AIR) {
                        NamespacedKey key = new NamespacedKey(Machine.getInstance(), "burntime");
                        fuelTime = fuelTime + fuel.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.DOUBLE);


                        fuel.setAmount(fuel.getAmount() - 1);
                        setCurrState(State.RUNNING);

                    }else {
                        resultProgressBar = 0;
                    }

                    if (fuelTime > 0 && ingredient != null && ingredient.getType() != Material.AIR) {
                        setCurrState(State.RUNNING);
                    }
                    ItemStack air = new ItemStack(Material.AIR);


                    if (fuelTime <= 0) {
                        furnaceUI.setItem(28, air);
                    }else {
                        fuelTime--;
                        ItemStack fuelItem = new ItemStack(Material.BOWL);
                        ItemMeta fuelItemMeta = fuelItem.getItemMeta();
                        fuelItemMeta.setDisplayName(MeltingFurnaceItems.hex("&7剩餘燃燒時間: &f" + fuelTime));
                        fuelItemMeta.setCustomModelData(10);
                        fuelItem.setItemMeta(fuelItemMeta);

                        furnaceUI.setItem(28, fuelItem);
                    }
                    furnaceUI.setItem(29, air);
            }
            //  核心=13  熔煉物=19 成果物=31 燃料=37
            if (core != null && core.getType() != Material.AIR) {

                if (core.isSimilar(MeltingFurnaceItems.getNormalCore())) {
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
                        furnaceUI.setItem(29, air);
                    }
                    if (resultProgressBar > 0 && resultProgressBar <= 1.66) {
                        furnaceUI.setItem(29, progessBar1);
                    }
                    if (resultProgressBar > 1.66 && resultProgressBar < 3.33) {
                        furnaceUI.setItem(29, progessBar2);
                    }
                    if (resultProgressBar >= 3.33) {
                        furnaceUI.setItem(29, progessBar3);
                    }

                    if (resultProgressBar >= 3.33) {
                        resultProgressBar = resultProgressBar - 3.33;
                        if (ingredient != null && ingredient.getType() != Material.AIR) {
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
                            if (ingredient.isSimilar(hematite)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, hematiteIngot);
                                }
                            }
                            if (ingredient.isSimilar(hematiteShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, hematiteNuggets);
                                }
                            }
                            if (ingredient.isSimilar(whiteCopper)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, whitecopperIngot);
                                }
                            }
                            if (ingredient.isSimilar(whitecopperShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, whitecopperNuggets);
                                }
                            }
                            if (ingredient.isSimilar(siderite)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, sideriteIngot);
                                }
                            }
                            if (ingredient.isSimilar(sideriteShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, sideriteNuggets);
                                }
                            }
                            if (ingredient.isSimilar(emvine)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, emvineIngot);
                                }
                            }
                            if (ingredient.isSimilar(emvineShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, emvineNuggets);
                                }
                            }
                        }
                    }
                }


                if (core.isSimilar(MeltingFurnaceItems.getRareCore())) {

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
                        furnaceUI.setItem(29, air);
                    }
                    if (resultProgressBar > 0 && resultProgressBar <= 1.25) {
                        furnaceUI.setItem(29, progessBar1);
                    }
                    if (resultProgressBar > 1.25 && resultProgressBar < 2.5) {
                        furnaceUI.setItem(29, progessBar2);
                    }
                    if (resultProgressBar >= 2.5) {
                        furnaceUI.setItem(29, progessBar3);
                    }




                    if (resultProgressBar >= 2.5) {
                        resultProgressBar = resultProgressBar - 2.5;
                        if (ingredient != null && ingredient.getType() != Material.AIR) {
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
                            if (ingredient.isSimilar(hematite)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, hematiteIngot);
                                }
                            }
                            if (ingredient.isSimilar(hematiteShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, hematiteNuggets);
                                }
                            }
                            if (ingredient.isSimilar(whiteCopper)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, whitecopperIngot);
                                }
                            }
                            if (ingredient.isSimilar(whitecopperShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, whitecopperNuggets);
                                }
                            }
                            if (ingredient.isSimilar(siderite)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, sideriteIngot);
                                }
                            }
                            if (ingredient.isSimilar(sideriteShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, sideriteNuggets);
                                }
                            }
                            if (ingredient.isSimilar(emvine)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, emvineIngot);
                                }
                            }
                            if (ingredient.isSimilar(emvineShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, emvineNuggets);
                                }
                            }
                        }
                    }
                }

                if (core.isSimilar(MeltingFurnaceItems.getEpicCore())) {

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
                        furnaceUI.setItem(29, air);
                    }
                    if (resultProgressBar > 0 && resultProgressBar <= 0.83) {
                        furnaceUI.setItem(29, progessBar1);
                    }
                    if (resultProgressBar > 0.83 && resultProgressBar < 1.66) {
                        furnaceUI.setItem(29, progessBar2);
                    }
                    if (resultProgressBar >= 1.66) {
                        furnaceUI.setItem(29, progessBar3);
                    }





                    if (resultProgressBar >= 1.66) {
                        resultProgressBar = resultProgressBar - 1.66;
                        if (ingredient != null && ingredient.getType() != Material.AIR) {
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
                            if (ingredient.isSimilar(hematite)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, hematiteIngot);
                                }
                            }
                            if (ingredient.isSimilar(hematiteShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, hematiteNuggets);
                                }
                            }
                            if (ingredient.isSimilar(whiteCopper)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, whitecopperIngot);
                                }
                            }
                            if (ingredient.isSimilar(whitecopperShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, whitecopperNuggets);
                                }
                            }
                            if (ingredient.isSimilar(siderite)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, sideriteIngot);
                                }
                            }
                            if (ingredient.isSimilar(sideriteShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, sideriteNuggets);
                                }
                            }
                            if (ingredient.isSimilar(emvine)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, emvineIngot);
                                }
                            }
                            if (ingredient.isSimilar(emvineShards)) {
                                ingredient.setAmount(ingredient.getAmount() - 1);
                                if (getResult() != null && getResult().getType() != Material.AIR) {

                                    ItemStack itemStack = furnaceUI.getItem(31);
                                    itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                    furnaceUI.setItem(31, itemStack);

                                } else {
                                    furnaceUI.setItem(31, emvineNuggets);
                                }
                            }
                        }
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
                    furnaceUI.setItem(29, air);
                }
                if (resultProgressBar > 0 && resultProgressBar <= 2.5) {
                    furnaceUI.setItem(29, progessBar1);
                }
                if (resultProgressBar > 1.25 && resultProgressBar < 5) {
                    furnaceUI.setItem(29, progessBar2);
                }
                if (resultProgressBar >= 5) {
                    furnaceUI.setItem(29, progessBar3);
                }

                if (resultProgressBar >= 5) {

                    if (ingredient != null && ingredient.getType() != Material.AIR) {


                        resultProgressBar = resultProgressBar - 5;

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
                        if (ingredient.isSimilar(hematite)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, hematiteIngot);
                            }
                        }
                        if (ingredient.isSimilar(hematiteShards)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, hematiteNuggets);
                            }
                        }
                        if (ingredient.isSimilar(whiteCopper)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, whitecopperIngot);
                            }
                        }
                        if (ingredient.isSimilar(whitecopperShards)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, whitecopperNuggets);
                            }
                        }
                        if (ingredient.isSimilar(siderite)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, sideriteIngot);
                            }
                        }
                        if (ingredient.isSimilar(sideriteShards)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, sideriteNuggets);
                            }
                        }
                        if (ingredient.isSimilar(emvine)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, emvineIngot);
                            }
                        }
                        if (ingredient.isSimilar(emvineShards)) {
                            ingredient.setAmount(ingredient.getAmount() - 1);
                            if (getResult() != null && getResult().getType() != Material.AIR) {

                                ItemStack itemStack = furnaceUI.getItem(31);
                                itemStack.setAmount(furnaceUI.getItem(31).getAmount() + 1);
                                furnaceUI.setItem(31, itemStack);

                            } else {
                                furnaceUI.setItem(31, emvineNuggets);
                            }
                        }

                    }
                }
            }

        }, 0, 20);


    }
}
