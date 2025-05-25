package com.example.benchmark;

import com.example.search.BinarySearch;

import java.util.Random;

public class BinarySearchBenchmark {

    private static final int[] largeSortedArray = new int[1_000_000];

    static {
        for (int i = 0; i < largeSortedArray.length; i++) {
            largeSortedArray[i] = i * 2;
        }
    }

    public static void main(String[] args) {
        int target = new Random().nextInt(2_000_000);

        long startIterative = System.nanoTime();
        int resultIterative = BinarySearch.iterativeBinarySearch(largeSortedArray, target);
        long durationIterative = System.nanoTime() - startIterative;

        long startRecursive = System.nanoTime();
        int resultRecursive = BinarySearch.recursiveBinarySearch(largeSortedArray, target, 0, largeSortedArray.length - 1);
        long durationRecursive = System.nanoTime() - startRecursive;

        System.out.println("Target: " + target);
        System.out.println("Iterative result index: " + resultIterative);
        System.out.println("Recursive result index: " + resultRecursive);
        System.out.println("Iterative Binary Search took: " + (durationIterative / 1_000_000.0) + " ms");
        System.out.println("Recursive Binary Search took: " + (durationRecursive / 1_000_000.0) + " ms");
    }
}
