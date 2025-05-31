package com.example.benchmark;

import com.example.sort.InsertionSort;

import java.util.Random;

public class InsertionSortBenchmark {
    private static final double NANOS_IN_MILLISECOND = 1_000_000.0;

    public static void main(String[] args) {
        int size = 100_000;
        int[] array = new int[size];
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(size * 10);
        }

        int[] arrayToSort = array.clone();

        long start = System.nanoTime();
        InsertionSort.sort(arrayToSort);
        long duration = System.nanoTime() - start;

        System.out.println("Insertion Sort on elements took: " + (duration / NANOS_IN_MILLISECOND) + " ms");
    }

}
