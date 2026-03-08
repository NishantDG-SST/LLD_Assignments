package com.example.map;

import java.util.HashMap;
import java.util.Map;

public class MarkerStyleFactory {

    private static final Map<String, MarkerStyle> CACHE = new HashMap<>();

    public static MarkerStyle getStyle(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "NF");

        if (!CACHE.containsKey(key)) {
            CACHE.put(key, new MarkerStyle(shape, color, size, filled));
        }

        return CACHE.get(key);
    }

    public static int getUniqueStyleCount() {
        return CACHE.size();
    }
}