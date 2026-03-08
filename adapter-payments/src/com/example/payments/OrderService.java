package com.example.payments;

import java.util.Objects;

public class OrderService {

    private final PaymentGateway paymentGateway;

    public OrderService(PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    public String placeOrder(String customerId, int amountCents) {
        System.out.println("Placing order for customer: " + customerId);
        String transactionId = paymentGateway.charge(customerId, amountCents);
        System.out.println("Payment successful: " + transactionId);
        return transactionId;
    }
}
