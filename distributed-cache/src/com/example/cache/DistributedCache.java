package com.example.cache;

import java.util.List;

public class DistributedCache {
    private final List<CacheNode> nodes;
    private final DistributionStrategy distributionStrategy;
    private final KeyValueDatabase database;

    public DistributedCache(List<CacheNode> nodes,
                            DistributionStrategy distributionStrategy,
                            KeyValueDatabase database) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("nodes must not be empty");
        }
        this.nodes = nodes;
        this.distributionStrategy = distributionStrategy;
        this.database = database;
    }

    public String get(String key) {
        CacheNode node = nodeForKey(key);
        String value = node.get(key);
        if (value != null) {
            return value;
        }

        String dbValue = database.get(key);
        if (dbValue != null) {
            node.put(key, dbValue);
        }
        return dbValue;
    }

    public void put(String key, String value) {
        database.put(key, value);
        CacheNode node = nodeForKey(key);
        node.put(key, value);
    }

    private CacheNode nodeForKey(String key) {
        int index = distributionStrategy.getNodeIndex(key, nodes.size());
        return nodes.get(index);
    }
}
