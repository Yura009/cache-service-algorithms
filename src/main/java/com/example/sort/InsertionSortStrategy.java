package com.example.sort;

public class InsertionSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] arr) {
        InsertionSort.sort(arr);
    }
}
