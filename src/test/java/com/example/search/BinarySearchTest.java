package com.example.search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    @ParameterizedTest(name = "iterative: search {1} → expected index {2}")
    @MethodSource("provideValuesForIterativeSearch")
    void testIterativeBinarySearch(int[] array, int target, int expectedIndex) {
        assertEquals(expectedIndex, BinarySearch.iterativeBinarySearch(array, target));
    }

    static Stream<Arguments> provideValuesForIterativeSearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        return Stream.of(
                Arguments.of(arr, 7, 3),
                Arguments.of(arr, 1, 0),
                Arguments.of(arr, 15, 7),
                Arguments.of(arr, 8, -1),
                Arguments.of(arr, 0, -1)
        );
    }

    @ParameterizedTest(name = "recursive: search {1} → expected index {2}")
    @MethodSource("provideValuesForRecursiveSearch")
    void testRecursiveBinarySearch(int[] array, int target, int expectedIndex) {
        assertEquals(expectedIndex, BinarySearch.recursiveBinarySearch(array, target, 0, array.length - 1));
    }

    static Stream<Arguments> provideValuesForRecursiveSearch() {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        return Stream.of(
                Arguments.of(arr, 7, 3),
                Arguments.of(arr, 1, 0),
                Arguments.of(arr, 15, 7),
                Arguments.of(arr, 6, -1),
                Arguments.of(arr, 100, -1)
        );
    }
}
