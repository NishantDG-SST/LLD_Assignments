package com.example.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private final int capacity;
    private final EvictionPolicy evictionPolicy;
    private final Map<String, String> data;

    public CacheNode(int capacity, EvictionPolicy evictionPolicy) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.data = new HashMap<>();
    }

    public String get(String key) {
        if (!data.containsKey(key)) {
            return null;
        }
        evictionPolicy.recordAccess(key);
        return data.get(key);
    }

    public void put(String key, String value) {
        if (data.containsKey(key)) {
            data.put(key, value);
            evictionPolicy.recordPut(key);
            return;
        }

        if (data.size() >= capacity) {
            String evicted = evictionPolicy.evictKey();
            if (evicted != null) {
                data.remove(evicted);
            }
        }

        data.put(key, value);
        evictionPolicy.recordPut(key);
    }

    public int size() {
        return data.size();
    }
}
