package com.example.limiter;

import java.util.List;

public class RateLimiterService implements RateLimiter {
    private final RateLimitKeyResolver keyResolver;
    private final RateLimitPlanProvider planProvider;
    private final RateLimitAlgorithm algorithm;
    private final Clock clock;

    public RateLimiterService(RateLimitKeyResolver keyResolver,
                              RateLimitPlanProvider planProvider,
                              RateLimitAlgorithm algorithm,
                              Clock clock) {
        this.keyResolver = keyResolver;
        this.planProvider = planProvider;
        this.algorithm = algorithm;
        this.clock = clock;
    }

    @Override
    public RateLimitDecision allow(RequestContext context) {
        String key = keyResolver.resolve(context);
        RateLimitPlan plan = planProvider.getPlan(key);
        List<RateLimitRule> rules = plan.getRules();
        long nowMillis = clock.nowMillis();
        for (RateLimitRule rule : rules) {
            RateLimitDecision decision = algorithm.allow(key, rule, nowMillis);
            if (!decision.isAllowed()) {
                return decision;
            }
        }
        return RateLimitDecision.allow();
    }
}
