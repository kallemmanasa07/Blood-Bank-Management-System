package model;

public class BloodStock {

    private int stockId;
    private String bloodGroup;
    private int units;

    // Default Constructor
    public BloodStock() {
    }

    // Parameterized Constructor
    public BloodStock(int stockId, String bloodGroup, int units) {
        this.stockId = stockId;
        this.bloodGroup = bloodGroup;
        this.units = units;
    }

    // Getters and Setters
    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
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

    // Optional: Useful for debugging
    @Override
    public String toString() {
        return "BloodStock{" +
                "stockId=" + stockId +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", units=" + units +
                '}';
    }
}