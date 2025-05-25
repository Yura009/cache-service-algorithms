package com.example.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {
    int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};

    @Test
    void testIterativeFound() {
        assertEquals(3, BinarySearch.iterativeBinarySearch(sortedArray, 7));
        assertEquals(0, BinarySearch.iterativeBinarySearch(sortedArray, 1));
        assertEquals(7, BinarySearch.iterativeBinarySearch(sortedArray, 15));
    }

    @Test
    void testIterativeNotFound() {
        assertEquals(-1, BinarySearch.iterativeBinarySearch(sortedArray, 8));
        assertEquals(-1, BinarySearch.iterativeBinarySearch(sortedArray, 0));
    }

    @Test
    void testRecursiveFound() {
        assertEquals(3, BinarySearch.recursiveBinarySearch(sortedArray, 7, 0, sortedArray.length - 1));
        assertEquals(0, BinarySearch.recursiveBinarySearch(sortedArray, 1, 0, sortedArray.length - 1));
        assertEquals(7, BinarySearch.recursiveBinarySearch(sortedArray, 15, 0, sortedArray.length - 1));
    }

    @Test
    void testRecursiveNotFound() {
        assertEquals(-1, BinarySearch.recursiveBinarySearch(sortedArray, 6, 0, sortedArray.length - 1));
        assertEquals(-1, BinarySearch.recursiveBinarySearch(sortedArray, 100, 0, sortedArray.length - 1));
    }
}
