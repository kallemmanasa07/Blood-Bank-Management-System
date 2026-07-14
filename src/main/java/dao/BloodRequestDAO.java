package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.BloodRequest;
import util.DBConnection;

public class BloodRequestDAO {

    private Connection con;

    public BloodRequestDAO() {
        con = DBConnection.getConnection();
    }

    // Add Blood Request
   
    public boolean addRequest(BloodRequest request) {

        boolean status = false;

        try {

            String sql = "INSERT INTO blood_requests(patient_id,blood_group,units,status) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, request.getPatientId());
            ps.setString(2, request.getBloodGroup());
            ps.setInt(3, request.getUnits());
            ps.setString(4, "Pending");

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Approve Request
 // Approve Request
    public boolean approveRequest(int requestId) {

        boolean status = false;

        try {

            // Get request details
            BloodRequest request = getRequestById(requestId);

            if (request == null) {
                System.out.println("Request not found.");
                return false;
            }

            System.out.println("Blood Group : " + request.getBloodGroup());
            System.out.println("Units Needed: " + request.getUnits());

            BloodStockDAO stockDAO = new BloodStockDAO();

            // Check stock availability
            boolean available = stockDAO.isBloodAvailable(
                    request.getBloodGroup(),
                    request.getUnits());

            System.out.println("Blood Available: " + available);

            if (available) {

                // Reduce stock
                boolean stockUpdated = stockDAO.decreaseStock(
                        request.getBloodGroup(),
                        request.getUnits());

                if (stockUpdated) {

                    // Update request status
                    String sql = "UPDATE blood_requests SET status=? WHERE request_id=?";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, "Approved");
                    ps.setInt(2, requestId);

                    int row = ps.executeUpdate();

                    if (row > 0) {
                        status = true;
                        System.out.println("Request Approved Successfully.");
                    }

                } else {
                    System.out.println("Unable to reduce blood stock.");
                }

            } else {
                System.out.println("Not enough blood stock.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
 // Reject Request
    public boolean rejectRequest(int requestId) {

        boolean status = false;

        try {

            String sql = "UPDATE blood_requests SET status=? WHERE request_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "Rejected");
            ps.setInt(2, requestId);

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    // Get Request By ID
    public BloodRequest getRequestById(int requestId) {

        BloodRequest request = null;

        try {

            String sql = "SELECT * FROM blood_requests WHERE request_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, requestId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                request = new BloodRequest();

                request.setRequestId(rs.getInt("request_id"));
                request.setPatientId(rs.getInt("patient_id"));
                request.setBloodGroup(rs.getString("blood_group"));
                request.setUnits(rs.getInt("units"));
                request.setStatus(rs.getString("status"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return request;
    }

    // Get All Requests
    public List<BloodRequest> getAllRequests() {

        List<BloodRequest> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM blood_requests ORDER BY request_id DESC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BloodRequest request = new BloodRequest();

                request.setRequestId(rs.getInt("request_id"));
                request.setPatientId(rs.getInt("patient_id"));
                request.setBloodGroup(rs.getString("blood_group"));
                request.setUnits(rs.getInt("units"));
                request.setStatus(rs.getString("status"));

                list.add(request);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
 // Get Latest 5 Blood Requests
    public List<BloodRequest> getRecentRequests() {

        List<BloodRequest> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM blood_requests ORDER BY request_id DESC LIMIT 5";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BloodRequest request = new BloodRequest();

                request.setRequestId(rs.getInt("request_id"));
                request.setPatientId(rs.getInt("patient_id"));
                request.setBloodGroup(rs.getString("blood_group"));
                request.setUnits(rs.getInt("units"));
                request.setStatus(rs.getString("status"));

                list.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get Requests By Status
    public List<BloodRequest> getRequestsByStatus(String status) {

        List<BloodRequest> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM blood_requests WHERE status=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BloodRequest request = new BloodRequest();

                request.setRequestId(rs.getInt("request_id"));
                request.setPatientId(rs.getInt("patient_id"));
                request.setBloodGroup(rs.getString("blood_group"));
                request.setUnits(rs.getInt("units"));
                request.setStatus(rs.getString("status"));

                list.add(request);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Delete Request
    public boolean deleteRequest(int requestId) {

        boolean status = false;

        try {

            String sql = "DELETE FROM blood_requests WHERE request_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, requestId);

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Count Requests
    public int getRequestCount() {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM blood_requests";

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
 // Count My Requests
    public int getMyRequestCount(int patientId) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM blood_requests WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
 // Count Approved Requests
    public int getApprovedCount(int patientId) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM blood_requests WHERE patient_id=? AND status='Approved'";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
 // Count Pending Requests
    public int getPendingCount(int patientId) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM blood_requests WHERE patient_id=? AND status='Pending'";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
 // Count Rejected Requests
    public int getRejectedCount(int patientId) {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM blood_requests WHERE patient_id=? AND status='Rejected'";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
 // Get Recent Requests By Patient
    public List<BloodRequest> getRecentRequestsByPatient(int patientId) {

        List<BloodRequest> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM blood_requests WHERE patient_id=? ORDER BY request_id DESC LIMIT 5";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                BloodRequest request = new BloodRequest();

                request.setRequestId(rs.getInt("request_id"));
                request.setPatientId(rs.getInt("patient_id"));
                request.setBloodGroup(rs.getString("blood_group"));
                request.setUnits(rs.getInt("units"));
                request.setStatus(rs.getString("status"));

                list.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    

}