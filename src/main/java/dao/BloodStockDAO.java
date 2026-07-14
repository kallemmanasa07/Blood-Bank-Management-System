package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.BloodStock;
import util.DBConnection;

public class BloodStockDAO {

    private Connection con;

    public BloodStockDAO() {
        con = DBConnection.getConnection();
    }

    // Add Blood Stock
    public boolean addBloodStock(BloodStock stock) {

        boolean status = false;

        try {

            // Check if blood group already exists
            String checkSql = "SELECT * FROM blood_stock WHERE blood_group=?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, stock.getBloodGroup());

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                // Update existing units
                String updateSql = "UPDATE blood_stock SET units = units + ? WHERE blood_group=?";
                PreparedStatement updatePs = con.prepareStatement(updateSql);

                updatePs.setInt(1, stock.getUnits());
                updatePs.setString(2, stock.getBloodGroup());

                status = updatePs.executeUpdate() > 0;

            } else {

                // Insert new blood group
                String insertSql = "INSERT INTO blood_stock(blood_group, units) VALUES(?,?)";
                PreparedStatement insertPs = con.prepareStatement(insertSql);

                insertPs.setString(1, stock.getBloodGroup());
                insertPs.setInt(2, stock.getUnits());

                status = insertPs.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Update Blood Stock
    public boolean updateBloodStock(BloodStock stock) {

        boolean status = false;

        try {

            String sql = "UPDATE blood_stock SET blood_group=?, units=? WHERE stock_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, stock.getBloodGroup());
            ps.setInt(2, stock.getUnits());
            ps.setInt(3, stock.getStockId());

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Delete Blood Stock
    public boolean deleteBloodStock(int stockId) {

        boolean status = false;

        try {

            String sql = "DELETE FROM blood_stock WHERE stock_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, stockId);

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
 // Search Blood Stock by Blood Group
    public List<BloodStock> searchBloodStock(String bloodGroup) {

        List<BloodStock> list = new ArrayList<>();

        try {

            String sql;

            PreparedStatement ps;

            if (bloodGroup == null || bloodGroup.trim().isEmpty()) {

                sql = "SELECT * FROM blood_stock ORDER BY stock_id ASC";

                ps = con.prepareStatement(sql);

            } else {

                sql = "SELECT * FROM blood_stock WHERE blood_group=? ORDER BY stock_id ASC";

                ps = con.prepareStatement(sql);
                ps.setString(1, bloodGroup);

            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BloodStock stock = new BloodStock();

                stock.setStockId(rs.getInt("stock_id"));
                stock.setBloodGroup(rs.getString("blood_group"));
                stock.setUnits(rs.getInt("units"));

                list.add(stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get Blood Stock By ID
    public BloodStock getBloodStockById(int stockId) {

        BloodStock stock = null;

        try {

            String sql = "SELECT * FROM blood_stock WHERE stock_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, stockId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                stock = new BloodStock();

                stock.setStockId(rs.getInt("stock_id"));
                stock.setBloodGroup(rs.getString("blood_group"));
                stock.setUnits(rs.getInt("units"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stock;
    }

    // Get All Blood Stock
    public List<BloodStock> getAllBloodStock() {

        List<BloodStock> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM blood_stock ORDER BY stock_id ASC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BloodStock stock = new BloodStock();

                stock.setStockId(rs.getInt("stock_id"));
                stock.setBloodGroup(rs.getString("blood_group"));
                stock.setUnits(rs.getInt("units"));

                list.add(stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // Get Units by Blood Group
    public int getUnitsByBloodGroup(String bloodGroup) {

        int units = 0;

        try {

            String sql = "SELECT units FROM blood_stock WHERE blood_group=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bloodGroup);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                units = rs.getInt("units");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return units;
    }

    // Increase Stock
    public boolean increaseStock(String bloodGroup, int units) {

        boolean status = false;

        try {

            String sql = "UPDATE blood_stock SET units = units + ? WHERE blood_group=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, units);
            ps.setString(2, bloodGroup);

            int row = ps.executeUpdate();

            if (row > 0) {

                status = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Decrease Stock
    public boolean decreaseStock(String bloodGroup, int units) {

        boolean status = false;

        try {

            String sql = "UPDATE blood_stock SET units = units - ? WHERE blood_group=? AND units>=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, units);
            ps.setString(2, bloodGroup);
            ps.setInt(3, units);

            int row = ps.executeUpdate();

            if (row > 0) {

                status = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Check Availability
    public boolean isBloodAvailable(String bloodGroup, int units) {

        boolean available = false;

        try {

            String sql = "SELECT units FROM blood_stock WHERE blood_group=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bloodGroup);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (rs.getInt("units") >= units) {

                    available = true;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return available;
    }
 // Total Blood Units Available
    public int getTotalBloodUnits() {

        int total = 0;

        try {

            String sql = "SELECT SUM(units) FROM blood_stock";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    // Get Low Blood Stock (Units <= 5)
    public List<BloodStock> getLowStock() {

        List<BloodStock> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM blood_stock WHERE units <= 5 ORDER BY units ASC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BloodStock stock = new BloodStock();

                stock.setStockId(rs.getInt("stock_id"));
                stock.setBloodGroup(rs.getString("blood_group"));
                stock.setUnits(rs.getInt("units"));

                list.add(stock);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    }