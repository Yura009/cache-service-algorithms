package com.example.search;

import com.example.sort.MergeSortStrategy;
import com.example.sort.SortStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinarySearchServiceTest {

    private BinarySearchService service;
    private int[] arr;
    private int key;

    @BeforeEach
    void setUp() {
        SortStrategy strategy = new MergeSortStrategy();
        service = new BinarySearchService(strategy);
        arr = new int[]{5, 3, 8, 1, 9, 2};
        key = 8;
    }

    @Test
    void testIterativeSearch() {
        int result = service.search(arr.clone(), key, false);
        assertTrue(result >= 0);
    }

    @Test
    void testRecursiveSearch() {
        int result = service.search(arr.clone(), key, true);
        assertTrue(result >= 0);
    }
}
