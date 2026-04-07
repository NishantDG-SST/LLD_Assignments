package com.example.limiter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        RateLimitRule perMinute = new RateLimitRule("per-minute", 5, 60_000L);
        RateLimitRule perHour = new RateLimitRule("per-hour", 100, 3_600_000L);
        RateLimitPlan defaultPlan = new RateLimitPlan(Arrays.asList(perMinute, perHour));

        Map<String, RateLimitPlan> plans = new HashMap<>();
        RateLimitKeyResolver keyResolver = new CompositeKeyResolver();

        RequestContext context = new RequestContext("T1", "tenant-1", "api-key-1", "provider-A");
        String key = keyResolver.resolve(context);
        plans.put(key, defaultPlan);

        RateLimitPlanProvider planProvider = new StaticRateLimitPlanProvider(plans, defaultPlan);
        Clock clock = new SystemClock();

        RateLimiter fixedWindowLimiter = new RateLimiterService(
                keyResolver,
                planProvider,
                new FixedWindowCounter(),
                clock
        );

        RateLimiter slidingWindowLimiter = new RateLimiterService(
                keyResolver,
                planProvider,
                new SlidingWindowCounter(),
                clock
        );

        simulate("Fixed Window", fixedWindowLimiter, context);
        simulate("Sliding Window", slidingWindowLimiter, context);
    }

    private static void simulate(String label, RateLimiter limiter, RequestContext context) {
        System.out.println("\n" + label + " decisions:");
        for (int i = 1; i <= 7; i++) {
            RateLimitDecision decision = limiter.allow(context);
            System.out.println("Attempt " + i + " -> " + (decision.isAllowed() ? "ALLOWED" : "DENIED"));
        }
    }
}
