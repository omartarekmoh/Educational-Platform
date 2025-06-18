package com.education.platform.controller;

import com.education.platform.dto.PaymentRequestDTO;
import com.education.platform.dto.PaymentResponseDTO;
import com.education.platform.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/initiate")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<PaymentResponseDTO> initiatePayment(@Valid @RequestBody PaymentRequestDTO requestDTO) {
        PaymentResponseDTO response = paymentService.initiatePayment(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<PaymentResponseDTO> verifyPayment(@RequestParam String transactionId) {
        PaymentResponseDTO response = paymentService.verifyPayment(transactionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<PaymentResponseDTO> getPaymentHistory(@RequestParam Long userId) {
        PaymentResponseDTO response = paymentService.getPaymentHistory(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{transactionId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<PaymentResponseDTO> getPaymentStatus(@PathVariable String transactionId) {
        PaymentResponseDTO response = paymentService.getPaymentStatus(transactionId);
        return ResponseEntity.ok(response);
    }
} 