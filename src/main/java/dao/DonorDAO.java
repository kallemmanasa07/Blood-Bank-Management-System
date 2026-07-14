package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Donor;
import util.DBConnection;

public class DonorDAO {

    private Connection con;

    public DonorDAO() {
        con = DBConnection.getConnection();
    }

    // Add Donor
    public boolean addDonor(Donor donor) {

        boolean status = false;

        try {

            String sql = "INSERT INTO donors(name,gender,age,blood_group,phone,address,last_donation) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, donor.getName());
            ps.setString(2, donor.getGender());
            ps.setInt(3, donor.getAge());
            ps.setString(4, donor.getBloodGroup());
            ps.setString(5, donor.getPhone());
            ps.setString(6, donor.getAddress());
            ps.setDate(7, donor.getLastDonation());

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Update Donor
    public boolean updateDonor(Donor donor) {

        boolean status = false;

        try {

            String sql = "UPDATE donors SET name=?,gender=?,age=?,blood_group=?,phone=?,address=?,last_donation=? WHERE donor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, donor.getName());
            ps.setString(2, donor.getGender());
            ps.setInt(3, donor.getAge());
            ps.setString(4, donor.getBloodGroup());
            ps.setString(5, donor.getPhone());
            ps.setString(6, donor.getAddress());
            ps.setDate(7, donor.getLastDonation());
            ps.setInt(8, donor.getDonorId());

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Delete Donor
    public boolean deleteDonor(int donorId) {

        boolean status = false;

        try {

            String sql = "DELETE FROM donors WHERE donor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donorId);

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Get Donor By ID
    public Donor getDonorById(int donorId) {

        Donor donor = null;

        try {

            String sql = "SELECT * FROM donors WHERE donor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donorId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                donor = new Donor();

                donor.setDonorId(rs.getInt("donor_id"));
                donor.setName(rs.getString("name"));
                donor.setGender(rs.getString("gender"));
                donor.setAge(rs.getInt("age"));
                donor.setBloodGroup(rs.getString("blood_group"));
                donor.setPhone(rs.getString("phone"));
                donor.setAddress(rs.getString("address"));
                donor.setLastDonation(rs.getDate("last_donation"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return donor;
    }

    // Get All Donors
    public List<Donor> getAllDonors() {

        List<Donor> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM donors";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Donor donor = new Donor();

                donor.setDonorId(rs.getInt("donor_id"));
                donor.setName(rs.getString("name"));
                donor.setGender(rs.getString("gender"));
                donor.setAge(rs.getInt("age"));
                donor.setBloodGroup(rs.getString("blood_group"));
                donor.setPhone(rs.getString("phone"));
                donor.setAddress(rs.getString("address"));
                donor.setLastDonation(rs.getDate("last_donation"));

                list.add(donor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search Donors by Blood Group
    public List<Donor> searchByBloodGroup(String bloodGroup) {

        List<Donor> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM donors WHERE blood_group=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bloodGroup);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Donor donor = new Donor();

                donor.setDonorId(rs.getInt("donor_id"));
                donor.setName(rs.getString("name"));
                donor.setGender(rs.getString("gender"));
                donor.setAge(rs.getInt("age"));
                donor.setBloodGroup(rs.getString("blood_group"));
                donor.setPhone(rs.getString("phone"));
                donor.setAddress(rs.getString("address"));
                donor.setLastDonation(rs.getDate("last_donation"));

                list.add(donor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Count Total Donors
    public int getDonorCount() {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM donors";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}