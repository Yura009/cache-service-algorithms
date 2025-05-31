package com.example.benchmark;

import com.example.sort.MergeSort;

import java.util.Random;

public class MergeSortBenchmark {

    private static final int ARRAY_SIZE = 1_000_000;
    private static final int[] largeUnsortedArray = new int[ARRAY_SIZE];
    private static final double NANOS_IN_MILLISECOND = 1_000_000.0;


    static {
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            largeUnsortedArray[i] = random.nextInt(ARRAY_SIZE * 10);
        }
    }

    public static void main(String[] args) {
        int[] arrayToSort = largeUnsortedArray.clone();

        long start = System.nanoTime();
        MergeSort.sort(arrayToSort);
        long duration = System.nanoTime() - start;

        System.out.println("Merge Sort on elements took: " + (duration / NANOS_IN_MILLISECOND) + " ms");
    }
}
