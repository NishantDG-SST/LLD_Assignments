package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        Map<String, PaymentGateway> registry = new HashMap<>();

        registry.put("fastpay", new FastPayAdapter(new FastPayClient()));
        registry.put("safecash", new SafeCashAdapter(new SafeCashClient()));

        OrderService fastOrderService = new OrderService(registry.get("fastpay"));
        fastOrderService.placeOrder("CUST-101", 5000);

        System.out.println();

        OrderService safeOrderService = new OrderService(registry.get("safecash"));
        safeOrderService.placeOrder("CUST-202", 7500);
    }
}
