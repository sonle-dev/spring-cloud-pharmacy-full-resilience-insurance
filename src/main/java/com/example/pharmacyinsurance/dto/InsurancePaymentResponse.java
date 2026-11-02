package com.example.pharmacyinsurance.dto;

import java.time.LocalDateTime;

public class InsurancePaymentResponse {
    private String insuranceCardNumber;
    private String medicineName;
    private double originalPrice;
    private double finalPrice;
    private double discountRate;
    private String status;
    private String note;
    private String timestamp;

    public InsurancePaymentResponse() {
    }

    public InsurancePaymentResponse(String insuranceCardNumber, String medicineName, double originalPrice,
                                    double finalPrice, double discountRate, String status, String note) {
        this.insuranceCardNumber = insuranceCardNumber;
        this.medicineName = medicineName;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.discountRate = discountRate;
        this.status = status;
        this.note = note;
        this.timestamp = LocalDateTime.now().toString();
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

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}