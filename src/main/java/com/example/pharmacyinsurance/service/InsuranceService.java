package com.example.pharmacyinsurance.service;

import com.example.pharmacyinsurance.dto.InsurancePaymentRequest;
import com.example.pharmacyinsurance.dto.InsurancePaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class InsuranceService {

    private static final Logger log = LoggerFactory.getLogger(InsuranceService.class);
    private final RestTemplate restTemplate;

    public InsuranceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "insuranceCB", fallbackMethod = "verifyInsuranceFallback")
    @TimeLimiter(name = "insuranceTL")
    @Retry(name = "insuranceRetry")
    public CompletableFuture<InsurancePaymentResponse> verifyAndPay(InsurancePaymentRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Dang gui request xac thuc the BHYT: {}", request.getInsuranceCardNumber());

            // Gia lap endpoint he thong Bao Hiem ben thu 3 (cổng 8089)
            String insuranceServiceUrl = "http://localhost:8089/api/insurance/verify?card=" + request.getInsuranceCardNumber();

            // Goi sang he thong bao hiem
            restTemplate.getForObject(insuranceServiceUrl, String.class);

            // Neu thanh cong: duoc huong 80% bao hiem, benh nhan chi tra 20%
            double discountRate = 80.0;
            double finalPrice = request.getOriginalPrice() * 0.2;

            return new InsurancePaymentResponse(
                    request.getInsuranceCardNumber(),
                    request.getMedicineName(),
                    request.getOriginalPrice(),
                    finalPrice,
                    discountRate,
                    "VERIFIED_SUCCESS",
                    "Xac thuc the bao hiem thanh cong"
            );
        });
    }

    // Phuong thuc Fallback theo dung yeu cau de bai
    public CompletableFuture<InsurancePaymentResponse> verifyInsuranceFallback(InsurancePaymentRequest request, Throwable ex) {
        log.warn("Loi ket noi / Timeout he thong Bao Hiem. Chuyen sang phuong an du phong. Nguyen nhan: {}", ex.getMessage());

        // Tra ve gia thuoc goc chua chiet khau kem ghi chu "Xác thực bảo hiểm sau"
        InsurancePaymentResponse fallbackResponse = new InsurancePaymentResponse(
                request.getInsuranceCardNumber(),
                request.getMedicineName(),
                request.getOriginalPrice(),
                request.getOriginalPrice(), // Giu nguyen gia goc, khong chiet khau
                0.0,
                "INSURANCE_OFFLINE",
                "Xác thực bảo hiểm sau"
        );

        return CompletableFuture.completedFuture(fallbackResponse);
    }
}