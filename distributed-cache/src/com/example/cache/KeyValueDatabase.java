package com.example.cache;

public interface KeyValueDatabase {
    String get(String key);

    void put(String key, String value);
}
