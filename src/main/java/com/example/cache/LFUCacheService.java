package com.example.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class LFUCacheService implements CacheService {
    private static final Logger logger = LoggerFactory.getLogger(LFUCacheService.class);
    private static final int MAX_SIZE = 100_000;
    private static final long EXPIRATION_TIME_MS = 5000;

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final Map<String, Long> lastAccess = new ConcurrentHashMap<>();
    private final Map<String, Integer> frequencies = new ConcurrentHashMap<>();

    private final AtomicLong evictionCount = new AtomicLong(0);
    private final AtomicLong totalPutTime = new AtomicLong(0);
    private final AtomicLong putCount = new AtomicLong(0);

    @Override
    public CacheEntry get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            lastAccess.put(key, System.currentTimeMillis());
            frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
        }
        return entry;
    }

    @Override
    public synchronized void put(String key, CacheEntry entry) {
        long start = System.nanoTime();

        if (cache.size() >= MAX_SIZE) {
            evict();
        }
        cache.put(key, entry);
        lastAccess.put(key, System.currentTimeMillis());
        frequencies.put(key, 1);

        long duration = System.nanoTime() - start;
        totalPutTime.addAndGet(duration);
        putCount.incrementAndGet();
    }

    private void evict() {
        long now = System.currentTimeMillis();

        List<String> expiredKeys = new ArrayList<>();
        for (String key : cache.keySet()) {
            if (now - lastAccess.getOrDefault(key, 0L) > EXPIRATION_TIME_MS) {
                expiredKeys.add(key);
            }
        }
        if (!expiredKeys.isEmpty()) {
            for (String key : expiredKeys) {
                removeKey(key);
            }
            return;
        }

        Optional<String> keyToRemove = frequencies.entrySet().stream()
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey);

        keyToRemove.ifPresent(this::removeKey);
    }

    private void removeKey(String key) {
        cache.remove(key);
        lastAccess.remove(key);
        frequencies.remove(key);
        evictionCount.incrementAndGet();
        logger.info("Evicted cache entry with key: {}", key);
    }

    @Override
    public long getEvictionCount() {
        return evictionCount.get();
    }

    @Override
    public double getAveragePutTimeMillis() {
        long puts = putCount.get();
        if (puts == 0) return 0;
        return totalPutTime.get() / (puts * 1_000_000.0);
    }
}
