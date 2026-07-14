package model;

import java.sql.Date;

public class Donor {

    private int donorId;
    private String name;
    private String gender;
    private int age;
    private String bloodGroup;
    private String phone;
    private String address;
    private Date lastDonation;

    // Default Constructor
    public Donor() {
    }

    // Parameterized Constructor
    public Donor(int donorId, String name, String gender, int age,
                 String bloodGroup, String phone,
                 String address, Date lastDonation) {

        this.donorId = donorId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.address = address;
        this.lastDonation = lastDonation;
    }

    // Getters and Setters
    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLastDonation() {
        return lastDonation;
    }

    public void setLastDonation(Date lastDonation) {
        this.lastDonation = lastDonation;
    }

    // Optional: Useful for debugging
    @Override
    public String toString() {
        return "Donor{" +
                "donorId=" + donorId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", lastDonation=" + lastDonation +
                '}';
    }
}