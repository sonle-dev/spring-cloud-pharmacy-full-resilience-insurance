package com.example.pharmacyinsurance.controller;

import com.example.pharmacyinsurance.dto.InsurancePaymentRequest;
import com.example.pharmacyinsurance.dto.InsurancePaymentResponse;
import com.example.pharmacyinsurance.service.InsuranceService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/pharmacy/payment")
@RefreshScope
public class InsurancePaymentController {

    private final InsuranceService insuranceService;

    public InsurancePaymentController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @PostMapping("/insurance")
    public CompletableFuture<ResponseEntity<InsurancePaymentResponse>> payWithInsurance(@RequestBody InsurancePaymentRequest request) {
        return insuranceService.verifyAndPay(request)
                .thenApply(ResponseEntity::ok);
    }
}