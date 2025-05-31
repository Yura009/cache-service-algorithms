package com.example;

import com.example.cache.*;
import com.example.search.BinarySearch;
import com.example.sort.MergeSort;
import com.example.sort.InsertionSort;
import com.example.tree.BinaryTreeTraversal;
import com.example.tree.TreeNode;

import java.util.Arrays;

public class CacheServiceAlgorithmsApplication {

    public static void main(String[] args) {
        demoCache(new LFUCacheService(), "LFU Cache");
        demoCache(new GuavaLRUCacheService(), "Guava LRU Cache");

        demoSorts();
        demoBinarySearch();
        demoBinaryTree();
    }

    private static void demoCache(CacheService cacheService, String type) {
        System.out.println("\n=== " + type + " ===");

        cacheService.put("a", new CacheEntry("value1"));
        cacheService.put("b", new CacheEntry("value2"));

        // Access 'a' to update lastAccess
        cacheService.get("a");

        // Wait to test expiration
        try { Thread.sleep(6000); } catch (InterruptedException ignored) {}

        cacheService.put("c", new CacheEntry("value3")); // triggers possible eviction

        System.out.println("Evictions: " + cacheService.getEvictionCount());
        System.out.println("Avg put time (ms): " + cacheService.getAveragePutTimeMillis());
    }

    private static void demoSorts() {
        System.out.println("\n=== Sorting Demo ===");

        int[] unsorted = {5, 3, 8, 1, 2};
        int[] copy1 = unsorted.clone();
        int[] copy2 = unsorted.clone();

        MergeSort.sort(copy1);
        InsertionSort.sort(copy2);

        System.out.println("Original:  " + Arrays.toString(unsorted));
        System.out.println("MergeSort: " + Arrays.toString(copy1));
        System.out.println("InsertionSort: " + Arrays.toString(copy2));
    }

    private static void demoBinarySearch() {
        System.out.println("\n=== Binary Search Demo ===");

        int[] sorted = {1, 3, 5, 7, 9, 11, 13};

        System.out.println("Iterative search for 7: " +
                BinarySearch.iterativeBinarySearch(sorted, 7));
        System.out.println("Recursive search for 7: " +
                BinarySearch.recursiveBinarySearch(sorted, 7, 0, sorted.length - 1));
    }

    private static void demoBinaryTree() {
        System.out.println("\n=== Binary Tree Traversal ===");

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        BinaryTreeTraversal traversal = new BinaryTreeTraversal();
        System.out.println("In-order: " + traversal.inOrder(root));
        System.out.println("Pre-order: " + traversal.preOrder(root));
        System.out.println("Post-order: " + traversal.postOrder(root));
    }
}
