package com.example.cache;

public interface EvictionPolicy {
    void recordAccess(String key);

    void recordPut(String key);

    String evictKey();

    void removeKey(String key);
}
