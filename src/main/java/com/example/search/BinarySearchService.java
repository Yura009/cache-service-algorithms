package com.example.search;

import com.example.sort.SortStrategy;

public class BinarySearchService {

    private final SortStrategy sortStrategy;

    public BinarySearchService(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public int search(int[] arr, int key, boolean useRecursive) {
        sortStrategy.sort(arr);

        if (useRecursive) {
            return BinarySearch.recursiveBinarySearch(arr, key, 0, arr.length - 1);
        } else {
            return BinarySearch.iterativeBinarySearch(arr, key);
        }
    }
}
