package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Donation;
import util.DBConnection;

public class DonationDAO {

    private Connection con;

    public DonationDAO() {
        con = DBConnection.getConnection();
    }

    // Add Donation
    public boolean addDonation(Donation donation) {

        boolean status = false;

        try {

            String sql = "INSERT INTO donations(donor_id,blood_group,units,donation_date) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donation.getDonorId());
            ps.setString(2, donation.getBloodGroup());
            ps.setInt(3, donation.getUnits());
            ps.setDate(4, donation.getDonationDate());

            int row = ps.executeUpdate();

            if (row > 0) {

                BloodStockDAO stockDAO = new BloodStockDAO();
                stockDAO.increaseStock(donation.getBloodGroup(), donation.getUnits());

                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Update Donation
    public boolean updateDonation(Donation donation) {

        boolean status = false;

        try {

            String sql = "UPDATE donations SET donor_id=?, blood_group=?, units=?, donation_date=? WHERE donation_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donation.getDonorId());
            ps.setString(2, donation.getBloodGroup());
            ps.setInt(3, donation.getUnits());
            ps.setDate(4, donation.getDonationDate());
            ps.setInt(5, donation.getDonationId());

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Delete Donation
    public boolean deleteDonation(int donationId) {

        boolean status = false;

        try {

            String sql = "DELETE FROM donations WHERE donation_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donationId);

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Get Donation By ID
    public Donation getDonationById(int donationId) {

        Donation donation = null;

        try {

            String sql = "SELECT * FROM donations WHERE donation_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donationId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                donation = new Donation();

                donation.setDonationId(rs.getInt("donation_id"));
                donation.setDonorId(rs.getInt("donor_id"));
                donation.setBloodGroup(rs.getString("blood_group"));
                donation.setUnits(rs.getInt("units"));
                donation.setDonationDate(rs.getDate("donation_date"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return donation;
    }

    // Get All Donations
    public List<Donation> getAllDonations() {

        List<Donation> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM donations ORDER BY donation_date DESC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Donation donation = new Donation();

                donation.setDonationId(rs.getInt("donation_id"));
                donation.setDonorId(rs.getInt("donor_id"));
                donation.setBloodGroup(rs.getString("blood_group"));
                donation.setUnits(rs.getInt("units"));
                donation.setDonationDate(rs.getDate("donation_date"));

                list.add(donation);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
 // Get Latest 5 Donations
    public List<Donation> getRecentDonations() {

        List<Donation> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM donations ORDER BY donation_date DESC LIMIT 5";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Donation donation = new Donation();

                donation.setDonationId(rs.getInt("donation_id"));
                donation.setDonorId(rs.getInt("donor_id"));
                donation.setBloodGroup(rs.getString("blood_group"));
                donation.setUnits(rs.getInt("units"));
                donation.setDonationDate(rs.getDate("donation_date"));

                list.add(donation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get Donations By Donor
    public List<Donation> getDonationsByDonor(int donorId) {

        List<Donation> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM donations WHERE donor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Donation donation = new Donation();

                donation.setDonationId(rs.getInt("donation_id"));
                donation.setDonorId(rs.getInt("donor_id"));
                donation.setBloodGroup(rs.getString("blood_group"));
                donation.setUnits(rs.getInt("units"));
                donation.setDonationDate(rs.getDate("donation_date"));

                list.add(donation);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Count Donations
    public int getDonationCount() {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM donations";

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