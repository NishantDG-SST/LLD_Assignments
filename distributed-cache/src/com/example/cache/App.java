package com.example.cache;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        KeyValueDatabase database = new InMemoryDatabase();
        database.put("user:1", "Alice");
        database.put("user:2", "Bob");

        int nodesCount = 3;
        int nodeCapacity = 2;
        LruConfig lruConfig = new LruConfig(16, 0.75f, true);
        EvictionPolicy evictionPolicy = new LRUEvictionPolicy(lruConfig);
        List<CacheNode> nodes = new ArrayList<>();
        for (int i = 0; i < nodesCount; i++) {
            nodes.add(new CacheNode(nodeCapacity, evictionPolicy));
        }

        DistributedCache cache = new DistributedCache(
                nodes,
                new ModuloDistributionStrategy(),
                database
        );

        System.out.println("user:1 -> " + cache.get("user:1"));
        System.out.println("user:2 -> " + cache.get("user:2"));
        System.out.println("user:3 -> " + cache.get("user:3"));

        cache.put("user:3", "Carol");
        System.out.println("user:3 -> " + cache.get("user:3"));
    }
}
