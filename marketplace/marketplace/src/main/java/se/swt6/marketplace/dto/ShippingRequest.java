package se.swt6.marketplace.dto;

public class ShippingRequest {
    private String zipCode;
    private int quantity;

    // Getters and setters
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
