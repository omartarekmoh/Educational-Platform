package com.education.platform.payment;

import java.math.BigDecimal;

public interface PaymentGateway {
    String createPaymentSession(String orderId, BigDecimal amount, String currency);
    boolean verifyPayment(String paymentId);
    String getPaymentStatus(String paymentId);
    boolean refundPayment(String paymentId, BigDecimal amount);
} 