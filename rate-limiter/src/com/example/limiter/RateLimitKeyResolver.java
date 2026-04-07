package com.example.limiter;

public interface RateLimitKeyResolver {
    String resolve(RequestContext context);
}
