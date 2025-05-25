package com.example.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class GuavaLRUCacheService implements CacheService {
    private static final Logger logger = LoggerFactory.getLogger(GuavaLRUCacheService.class);
    private static final int MAX_SIZE = 100_000;
    private static final long EXPIRATION_TIME_MS = 5000;
    private final Cache<String, CacheEntry> cache;
    private final AtomicLong evictionCount = new AtomicLong(0);
    private final AtomicLong totalPutTime = new AtomicLong(0);
    private final AtomicLong putCount = new AtomicLong(0);

    public GuavaLRUCacheService() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(MAX_SIZE)
                .expireAfterAccess(EXPIRATION_TIME_MS, TimeUnit.MILLISECONDS)
                .removalListener((RemovalListener<String, CacheEntry>) notification -> {
                    if (notification.wasEvicted()) {
                        evictionCount.incrementAndGet();
                        logger.info("Evicted cache entry: {}", notification.getKey());
                    }
                })
                .build();

    }

    @Override
    public CacheEntry get(String key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void put(String key, CacheEntry entry) {
        long start = System.nanoTime();
        cache.put(key, entry);
        long duration = System.nanoTime() - start;
        totalPutTime.addAndGet(duration);
        putCount.incrementAndGet();
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
