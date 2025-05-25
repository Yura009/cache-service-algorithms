package com.example.cache;

public interface CacheService {
    CacheEntry get(String key);
    void put(String key, CacheEntry entry);
    long getEvictionCount();
    double getAveragePutTimeMillis();
}
