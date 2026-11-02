package com.example.pharmacyinsurance.dto;

public class InsurancePaymentRequest {
    private String insuranceCardNumber;
    private String medicineName;
    private double originalPrice;

    public InsurancePaymentRequest() {
    }

    public InsurancePaymentRequest(String insuranceCardNumber, String medicineName, double originalPrice) {
        this.insuranceCardNumber = insuranceCardNumber;
        this.medicineName = medicineName;
        this.originalPrice = originalPrice;
    }

    public String getInsuranceCardNumber() {
        return insuranceCardNumber;
    }

    public void setInsuranceCardNumber(String insuranceCardNumber) {
        this.insuranceCardNumber = insuranceCardNumber;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
}