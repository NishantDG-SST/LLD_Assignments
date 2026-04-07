package com.example.limiter;

import java.util.Collections;
import java.util.List;

public class RateLimitPlan {
    private final List<RateLimitRule> rules;

    public RateLimitPlan(List<RateLimitRule> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    public List<RateLimitRule> getRules() {
        return rules;
    }
}
