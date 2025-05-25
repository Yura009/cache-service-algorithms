package com.example.cache;

public class CacheEntry {
    private final String value;

    public CacheEntry(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CacheEntry{" +
                "value='" + value + '\'' +
                '}';
    }
}
