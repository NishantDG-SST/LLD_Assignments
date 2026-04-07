package com.example.limiter;

public class RateLimitDecision {
    private final boolean allowed;
    private final long retryAfterMillis;

    public RateLimitDecision(boolean allowed, long retryAfterMillis) {
        this.allowed = allowed;
        this.retryAfterMillis = retryAfterMillis;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public long getRetryAfterMillis() {
        return retryAfterMillis;
    }

    public static RateLimitDecision allow() {
        return new RateLimitDecision(true, 0L);
    }

    public static RateLimitDecision deny(long retryAfterMillis) {
        return new RateLimitDecision(false, retryAfterMillis);
    }
}
