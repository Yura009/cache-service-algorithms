package com.example.cache;

import java.util.concurrent.atomic.AtomicLong;

public class AbstractCacheService implements CacheService {
    protected static final double NANOS_IN_MILLISECOND = 1_000_000.0;

    protected final AtomicLong evictionCount = new AtomicLong(0);
    protected final AtomicLong totalPutTime = new AtomicLong(0);
    protected final AtomicLong putCount = new AtomicLong(0);

    @Override
    public CacheEntry get(String key) {
        return null;
    }

    @Override
    public void put(String key, CacheEntry entry) {

    }

    @Override
    public long getEvictionCount() {
        return evictionCount.get();
    }

    @Override
    public double getAveragePutTimeMillis() {
        long puts = putCount.get();
        if (puts == 0) {
            return 0;
        }
        return totalPutTime.get() / (puts * NANOS_IN_MILLISECOND);
    }
}
