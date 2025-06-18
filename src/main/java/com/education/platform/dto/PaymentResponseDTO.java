package com.education.platform.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDTO {
    private String transactionId;
    private String status;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime timestamp;
    private String paymentMethod;
    private String errorMessage;
    private String redirectUrl;
} 