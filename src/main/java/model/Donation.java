package model;

import java.sql.Date;

public class Donation {

    private int donationId;
    private int donorId;
    private String bloodGroup;
    private int units;
    private Date donationDate;

    // Default Constructor
    public Donation() {
    }

    // Parameterized Constructor
    public Donation(int donationId, int donorId, String bloodGroup,
                    int units, Date donationDate) {
        this.donationId = donationId;
        this.donorId = donorId;
        this.bloodGroup = bloodGroup;
        this.units = units;
        this.donationDate = donationDate;
    }

    // Getters and Setters
    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
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

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    // Optional: Useful for debugging
    @Override
    public String toString() {
        return "Donation{" +
                "donationId=" + donationId +
                ", donorId=" + donorId +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", units=" + units +
                ", donationDate=" + donationDate +
                '}';
    }
}