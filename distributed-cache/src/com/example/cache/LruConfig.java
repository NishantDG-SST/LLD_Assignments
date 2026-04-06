package com.example.cache;

public final class LruConfig {
    private final int initialCapacity;
    private final float loadFactor;
    private final boolean accessOrder;

    public LruConfig(int initialCapacity, float loadFactor, boolean accessOrder) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.accessOrder = accessOrder;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public float getLoadFactor() {
        return loadFactor;
    }

    public boolean isAccessOrder() {
        return accessOrder;
    }

    public static LruConfig defaultConfig() {
        return new LruConfig(16, 0.75f, true);
    }
}
