package model;

public class Patient {

    private int patientId;
    private int userId;
    private String name;
    private String bloodGroup;
    private int units;
    private String doctor;
    private String hospital;
    private String phone;

    // Default Constructor
    public Patient() {
    }

    // Parameterized Constructor
    public Patient(int patientId, int userId, String name, String bloodGroup,
                   int units, String doctor, String hospital, String phone) {

        this.patientId = patientId;
        this.userId = userId;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.units = units;
        this.doctor = doctor;
        this.hospital = hospital;
        this.phone = phone;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Optional: Useful for debugging
    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", units=" + units +
                ", doctor='" + doctor + '\'' +
                ", hospital='" + hospital + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}