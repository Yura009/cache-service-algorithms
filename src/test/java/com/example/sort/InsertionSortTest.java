package com.example.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionSortTest {

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testReversedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testRandomArray() {
        int[] arr = {5, 2, 9, 1, 3};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 9}, arr);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 2, 3, 3, 4, 8}, arr);
    }
}