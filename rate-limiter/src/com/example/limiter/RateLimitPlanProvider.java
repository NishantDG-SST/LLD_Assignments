package com.example.limiter;

public interface RateLimitPlanProvider {
    RateLimitPlan getPlan(String key);
}
