package com.example.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class GuavaLRUCacheService extends AbstractCacheService implements CacheService {
    private static final Logger logger = LoggerFactory.getLogger(GuavaLRUCacheService.class);
    private static final int MAX_SIZE = 100_000;
    private static final long EXPIRATION_TIME_MS = 5000;
    private final Cache<String, CacheEntry> cache;

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
}
