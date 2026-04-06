package com.example.cache;

public class ModuloDistributionStrategy implements DistributionStrategy {

    @Override
    public int getNodeIndex(String key, int numberOfNodes) {
        if (numberOfNodes <= 0) {
            throw new IllegalArgumentException("numberOfNodes must be positive");
        }
        int hash = key == null ? 0 : key.hashCode();
        return Math.abs(hash) % numberOfNodes;
    }
}
