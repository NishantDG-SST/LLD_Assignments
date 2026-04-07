package com.example.limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowCounter implements RateLimitAlgorithm {
    private final Map<String, Window> windows;

    public SlidingWindowCounter() {
        this.windows = new ConcurrentHashMap<>();
    }

    @Override
    public RateLimitDecision allow(String key, RateLimitRule rule, long nowMillis) {
        String stateKey = key + "|" + rule.getId();
        Window window = windows.computeIfAbsent(stateKey, k -> new Window());
        synchronized (window) {
            long windowSize = rule.getWindowMillis();
            if (window.windowStartMillis == 0L) {
                window.windowStartMillis = alignWindowStart(nowMillis, windowSize);
            }

            long elapsed = nowMillis - window.windowStartMillis;
            if (elapsed >= windowSize) {
                long windowsToShift = elapsed / windowSize;
                if (windowsToShift >= 2) {
                    window.previousCount.set(0);
                } else {
                    window.previousCount.set(window.currentCount.get());
                }
                window.currentCount.set(0);
                window.windowStartMillis = alignWindowStart(nowMillis, windowSize);
                elapsed = nowMillis - window.windowStartMillis;
            }

            double weight = 1.0 - (double) elapsed / (double) windowSize;
            double estimated = window.previousCount.get() * weight + window.currentCount.get();
            if (estimated + 1 > rule.getLimit()) {
                long retryAfter = window.windowStartMillis + windowSize - nowMillis;
                return RateLimitDecision.deny(Math.max(retryAfter, 0L));
            }

            window.currentCount.incrementAndGet();
            return RateLimitDecision.allow();
        }
    }

    private long alignWindowStart(long nowMillis, long windowSize) {
        return (nowMillis / windowSize) * windowSize;
    }

    private static class Window {
        private long windowStartMillis;
        private final AtomicInteger currentCount = new AtomicInteger();
        private final AtomicInteger previousCount = new AtomicInteger();
    }
}
