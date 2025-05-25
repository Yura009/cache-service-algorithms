package com.example.sort;

public class MergeSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] arr) {
        MergeSort.sort(arr);
    }
}
