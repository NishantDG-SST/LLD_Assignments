package com.example.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {
    private final Map<String, Boolean> order;

    public LRUEvictionPolicy() {
        this(LruConfig.defaultConfig());
    }

    public LRUEvictionPolicy(LruConfig config) {
        this.order = new LinkedHashMap<>(
                config.getInitialCapacity(),
                config.getLoadFactor(),
                config.isAccessOrder()
        );
    }

    @Override
    public void recordAccess(String key) {
        order.put(key, Boolean.TRUE);
    }

    @Override
    public void recordPut(String key) {
        order.put(key, Boolean.TRUE);
    }

    @Override
    public String evictKey() {
        Iterator<Map.Entry<String, Boolean>> it = order.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        String key = it.next().getKey();
        it.remove();
        return key;
    }

    @Override
    public void removeKey(String key) {
        order.remove(key);
    }
}
