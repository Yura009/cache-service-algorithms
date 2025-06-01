package com.example.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InsertionSortTest {

    @ParameterizedTest(name = "{index} => input={0}, expected={1}")
    @MethodSource("provideArraysForSorting")
    @DisplayName("Test InsertionSort with multiple scenarios")
    void testSortVariousArrays(int[] input, int[] expected) {
        InsertionSort.sort(input);
        assertArrayEquals(expected, input);
    }

    static Stream<Arguments> provideArraysForSorting() {
        return Stream.of(
                Arguments.of(new int[]{}, new int[]{}),
                Arguments.of(new int[]{42}, new int[]{42}),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}),
                Arguments.of(new int[]{5, 2, 9, 1, 3}, new int[]{1, 2, 3, 5, 9}),
                Arguments.of(new int[]{4, 2, 2, 8, 3, 3, 1}, new int[]{1, 2, 2, 3, 3, 4, 8})

        );
    }
}