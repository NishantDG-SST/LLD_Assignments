package com.example.limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowCounter implements RateLimitAlgorithm {
    private final Map<String, Window> windows;

    public FixedWindowCounter() {
        this.windows = new ConcurrentHashMap<>();
    }

    @Override
    public RateLimitDecision allow(String key, RateLimitRule rule, long nowMillis) {
        String stateKey = key + "|" + rule.getId();
        Window window = windows.computeIfAbsent(stateKey, k -> new Window());
        synchronized (window) {
            long windowStart = window.windowStartMillis;
            long windowSize = rule.getWindowMillis();
            if (windowStart == 0L || nowMillis - windowStart >= windowSize) {
                window.windowStartMillis = nowMillis;
                window.count.set(0);
            }
            int current = window.count.incrementAndGet();
            if (current <= rule.getLimit()) {
                return RateLimitDecision.allow();
            }
            long retryAfter = windowStart + windowSize - nowMillis;
            return RateLimitDecision.deny(Math.max(retryAfter, 0L));
        }
    }

    private static class Window {
        private long windowStartMillis;
        private final AtomicInteger count = new AtomicInteger();
    }
}
