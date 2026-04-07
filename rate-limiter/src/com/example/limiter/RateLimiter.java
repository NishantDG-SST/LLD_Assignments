package com.example.limiter;

public interface RateLimiter {
    RateLimitDecision allow(RequestContext context);
}
