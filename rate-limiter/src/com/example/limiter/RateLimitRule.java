package com.example.limiter;

public class RateLimitRule {
    private final String id;
    private final int limit;
    private final long windowMillis;

    public RateLimitRule(String id, int limit, long windowMillis) {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be positive");
        }
        if (windowMillis <= 0) {
            throw new IllegalArgumentException("windowMillis must be positive");
        }
        this.id = id;
        this.limit = limit;
        this.windowMillis = windowMillis;
    }

    public String getId() {
        return id;
    }

    public int getLimit() {
        return limit;
    }

    public long getWindowMillis() {
        return windowMillis;
    }
}
