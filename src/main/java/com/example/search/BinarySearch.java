package com.example.search;

public class BinarySearch {
    // Iterative binary search
    public static int iterativeBinarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Recursive binary search
    public static int recursiveBinarySearch(int[] arr, int key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == key) {
            return mid;
        } else if (arr[mid] < key) {
            return recursiveBinarySearch(arr, key, mid + 1, high);
        } else {
            return recursiveBinarySearch(arr, key, low, mid - 1);
        }
    }
}
