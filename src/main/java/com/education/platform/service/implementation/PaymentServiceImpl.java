package com.education.platform.service.implementation;

import com.education.platform.dto.PaymentRequestDTO;
import com.education.platform.dto.PaymentResponseDTO;
import com.education.platform.exception.ResourceNotFoundException;
import com.education.platform.service.CourseService;
import com.education.platform.service.PaymentService;
import com.education.platform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final UserService userService;
    private final CourseService courseService;

    @Override
    @Transactional
    public PaymentResponseDTO initiatePayment(PaymentRequestDTO requestDTO) {
        // Verify user exists
        userService.getUserById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Verify course exists
        courseService.getCourseById(requestDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Generate transaction ID
        String transactionId = UUID.randomUUID().toString();

        // TODO: Integrate with actual payment gateway (e.g., Paymob)
        // For now, return a mock response
        return PaymentResponseDTO.builder()
                .transactionId(transactionId)
                .status("PENDING")
                .amount(requestDTO.getAmount())
                .currency(requestDTO.getCurrency())
                .timestamp(LocalDateTime.now())
                .paymentMethod(requestDTO.getPaymentMethod())
                .redirectUrl("https://payment-gateway.com/pay/" + transactionId)
                .build();
    }

    @Override
    @Transactional
    public PaymentResponseDTO verifyPayment(String transactionId) {
        // TODO: Integrate with actual payment gateway
        // For now, return a mock response
        return PaymentResponseDTO.builder()
                .transactionId(transactionId)
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public PaymentResponseDTO getPaymentHistory(Long userId) {
        // TODO: Implement payment history retrieval
        // For now, return a mock response
        return PaymentResponseDTO.builder()
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public PaymentResponseDTO getPaymentStatus(String transactionId) {
        // TODO: Integrate with actual payment gateway
        // For now, return a mock response
        return PaymentResponseDTO.builder()
                .transactionId(transactionId)
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .build();
    }
} 