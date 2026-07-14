package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Patient;
import util.DBConnection;

public class PatientDAO {

    private Connection con;

    public PatientDAO() {
        con = DBConnection.getConnection();
    }

    // Add Patient
    public boolean addPatient(Patient patient) {

        boolean status = false;

        try {

            String sql = "INSERT INTO patients(user_id,name,blood_group,units,doctor,hospital,phone) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, patient.getUserId());
            ps.setString(2, patient.getName());
            ps.setString(3, patient.getBloodGroup());
            ps.setInt(4, patient.getUnits());
            ps.setString(5, patient.getDoctor());
            ps.setString(6, patient.getHospital());
            ps.setString(7, patient.getPhone());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Update Patient
    public boolean updatePatient(Patient patient) {

        boolean status = false;

        try {

            String sql = "UPDATE patients SET name=?, blood_group=?, units=?, doctor=?, hospital=?, phone=? WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, patient.getName());
            ps.setString(2, patient.getBloodGroup());
            ps.setInt(3, patient.getUnits());
            ps.setString(4, patient.getDoctor());
            ps.setString(5, patient.getHospital());
            ps.setString(6, patient.getPhone());
            ps.setInt(7, patient.getPatientId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Delete Patient
    public boolean deletePatient(int patientId) {

        boolean status = false;

        try {

            String sql = "DELETE FROM patients WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, patientId);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Get Patient by ID
    public Patient getPatientById(int patientId) {

        Patient patient = null;

        try {

            String sql = "SELECT * FROM patients WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setUserId(rs.getInt("user_id"));
                patient.setName(rs.getString("name"));
                patient.setBloodGroup(rs.getString("blood_group"));
                patient.setUnits(rs.getInt("units"));
                patient.setDoctor(rs.getString("doctor"));
                patient.setHospital(rs.getString("hospital"));
                patient.setPhone(rs.getString("phone"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return patient;
    }

    // Get All Patients
    public List<Patient> getAllPatients() {

        List<Patient> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM patients";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setUserId(rs.getInt("user_id"));
                patient.setName(rs.getString("name"));
                patient.setBloodGroup(rs.getString("blood_group"));
                patient.setUnits(rs.getInt("units"));
                patient.setDoctor(rs.getString("doctor"));
                patient.setHospital(rs.getString("hospital"));
                patient.setPhone(rs.getString("phone"));

                list.add(patient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search Patients by Blood Group
    public List<Patient> searchPatients(String bloodGroup) {

        List<Patient> list = new ArrayList<>();

        try {

            String sql = "SELECT * FROM patients WHERE blood_group=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bloodGroup);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient patient = new Patient();

                patient.setPatientId(rs.getInt("patient_id"));
                patient.setUserId(rs.getInt("user_id"));
                patient.setName(rs.getString("name"));
                patient.setBloodGroup(rs.getString("blood_group"));
                patient.setUnits(rs.getInt("units"));
                patient.setDoctor(rs.getString("doctor"));
                patient.setHospital(rs.getString("hospital"));
                patient.setPhone(rs.getString("phone"));

                list.add(patient);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Get Patient ID using User ID
    public int getPatientIdByUserId(int userId) {

        int patientId = 0;

        try {

            String sql = "SELECT patient_id FROM patients WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                patientId = rs.getInt("patient_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return patientId;
    }
    public int getPatientCount() {

        int count = 0;

        try {

            String sql = "SELECT COUNT(*) FROM patients";

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