package me.yiyi1234.machine.sql;

import me.yiyi1234.machine.Machine;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLLevels {
    private Machine plugin;

    public SQLLevels(Machine plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Machine_levels (PlayerUUID VARCHAR(100), PlayerID VARCHAR(100), mineralLevel INTEGER, mineralExp DOUBLE, plantLevel INTEGER, plantExp DOUBLE, animalLevel INTEGER, animalExp DOUBLE, weavingLevel INTEGER ,weavingExp DOUBLE, magicLevel INTEGER, magicExp DOUBLE)");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            if (!playerExists(player.getUniqueId())) {
                PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT INTO Machine_levels (PlayerUUID VARCHAR(100), PlayerID VARCHAR(100), mineralLevel INTEGER, mineralExp DOUBLE, plantLevel INTEGER, plantExp DOUBLE, animalLevel INTEGER, animalExp DOUBLE, weavingLevel INTEGER ,weavingExp DOUBLE, magicLevel INTEGER, magicExp DOUBLE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
                ps.setString(1, player.getUniqueId().toString());
                ps.setString(2, player.getName());
                ps.setInt(3, 1);
                ps.setDouble(4, 0.0);
                ps.setInt(5, 1);
                ps.setDouble(6, 0.0);
                ps.setInt(7, 1);
                ps.setDouble(8, 0.0);
                ps.setInt(9, 1);
                ps.setDouble(10, 0.0);
                ps.setInt(11, 1);
                ps.setDouble(12, 0.0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(UUID uuid) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM Machine_levels WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
            return false;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Double getExp(UUID uuid, String columName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT ? FROM Machine_levels WHERE UUID=?");
            ps.setString(1, columName);
            ps.setString(2, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double exp;
                exp = rs.getDouble(columName);
                return exp;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public int getLevels(UUID uuid, String columName) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT ? FROM Machine_levels WHERE UUID=?");
            ps.setString(1, columName);
            ps.setString(2, uuid.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int levels;
                levels = rs.getInt(columName);
                return levels;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void setExp(UUID uuid, String columName, Double exp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE Machine_levels SET ?=? WHERE UUID=?");
            ps.setString(1, columName);
            ps.setDouble(2, exp);
            ps.setString(3, uuid.toString());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevels(UUID uuid, String columName, int level) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE Machine_levels SET ?=? WHERE UUID=?");
            ps.setString(1, columName);
            ps.setDouble(2, level);
            ps.setString(3, uuid.toString());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
