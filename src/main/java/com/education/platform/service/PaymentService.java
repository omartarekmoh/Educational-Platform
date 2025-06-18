package com.education.platform.service;

import com.education.platform.dto.PaymentRequestDTO;
import com.education.platform.dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO initiatePayment(PaymentRequestDTO requestDTO);
    PaymentResponseDTO verifyPayment(String transactionId);
    PaymentResponseDTO getPaymentHistory(Long userId);
    PaymentResponseDTO getPaymentStatus(String transactionId);
} 