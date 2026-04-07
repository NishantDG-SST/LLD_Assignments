package com.example.limiter;

public class CompositeKeyResolver implements RateLimitKeyResolver {
    @Override
    public String resolve(RequestContext context) {
        String customer = nullSafe(context.getCustomerId());
        String tenant = nullSafe(context.getTenantId());
        String apiKey = nullSafe(context.getApiKey());
        String provider = nullSafe(context.getProvider());
        return "customer=" + customer + "|tenant=" + tenant + "|apiKey=" + apiKey + "|provider=" + provider;
    }

    private String nullSafe(String value) {
        return value == null ? "-" : value;
    }
}
