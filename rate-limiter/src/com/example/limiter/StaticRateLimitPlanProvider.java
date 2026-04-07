package com.example.limiter;

import java.util.Map;

public class StaticRateLimitPlanProvider implements RateLimitPlanProvider {
    private final Map<String, RateLimitPlan> plans;
    private final RateLimitPlan defaultPlan;

    public StaticRateLimitPlanProvider(Map<String, RateLimitPlan> plans, RateLimitPlan defaultPlan) {
        this.plans = plans;
        this.defaultPlan = defaultPlan;
    }

    @Override
    public RateLimitPlan getPlan(String key) {
        RateLimitPlan plan = plans.get(key);
        return plan != null ? plan : defaultPlan;
    }
}
