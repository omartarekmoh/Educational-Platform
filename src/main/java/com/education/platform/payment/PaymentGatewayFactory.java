package com.education.platform.payment;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class PaymentGatewayFactory {
    
    private final Map<String, PaymentGateway> paymentGateways;
    
    public PaymentGatewayFactory(Map<String, PaymentGateway> paymentGateways) {
        this.paymentGateways = paymentGateways;
    }
    
    public PaymentGateway getPaymentGateway(String gatewayType) {
        PaymentGateway gateway = paymentGateways.get(gatewayType.toLowerCase());
        if (gateway == null) {
            throw new IllegalArgumentException("Unsupported payment gateway: " + gatewayType);
        }
        return gateway;
    }
} 