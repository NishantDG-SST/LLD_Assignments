package com.example.payments;

import java.util.UUID;

public class SafeCashClient {

    public String sendMoney(String customerId, int amountInCents) {
        System.out.println("SafeCash processing payment...");
        return "SAFE-" + UUID.randomUUID();
    }
}
