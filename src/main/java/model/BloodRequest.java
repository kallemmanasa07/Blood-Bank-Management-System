package model;

public class BloodRequest {

    private int requestId;
    private int patientId;
    private String bloodGroup;
    private int units;
    private String status;

    // Default Constructor
    public BloodRequest() {
    }

    // Parameterized Constructor
    public BloodRequest(int requestId, int patientId, String bloodGroup, int units, String status) {
        this.requestId = requestId;
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.units = units;
        this.status = status;
    }

    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: Useful for debugging
    @Override
    public String toString() {
        return "BloodRequest{" +
                "requestId=" + requestId +
                ", patientId=" + patientId +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", units=" + units +
                ", status='" + status + '\'' +
                '}';
    }
}