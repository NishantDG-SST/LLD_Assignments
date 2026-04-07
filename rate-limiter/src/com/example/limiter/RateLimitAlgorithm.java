package com.example.limiter;

public interface RateLimitAlgorithm {
    RateLimitDecision allow(String key, RateLimitRule rule, long nowMillis);
}
