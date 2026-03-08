package com.example.payments;

import java.util.UUID;

public class FastPayClient {

    public String makePayment(String user, int cents) {
        System.out.println("FastPay processing payment...");
        return "FAST-" + UUID.randomUUID();
    }
}
