# Binary Search Benchmark Results

## Description

This project implements two versions of the binary search algorithm on a large sorted array of 1,000,000 integers:

- **Iterative Binary Search** — uses a loop to locate the target element.
- **Recursive Binary Search** — uses recursion to locate the target element.

The performance of each implementation was measured by running each search 1000 times on random targets and calculating the average execution time in milliseconds.

## Results

| Search Type | Average Time (ms) | Sample Target | Result Index |
|-------------|-------------------|---------------|--------------|
| Iterative   | 0.4488            | 1,759,684     | 879,842      |
| Recursive   | 0.0029            | 1,759,684     | 879,842      |

## Analysis

- Both implementations return the correct index of the searched element.
- The recursive implementation shows significantly faster average time in this benchmark.
- This result might be influenced by JVM optimizations, CPU caching, or the overhead of the loop in iterative search.
- In real-world use, the iterative approach is generally more memory-efficient since it avoids the overhead of recursive calls and possible stack overflow in very deep recursion.

## Complexity

- **Time Complexity:** O(log n) for both iterative and recursive versions, where _n_ is the size of the array.
- **Space Complexity:** O(1) for iterative; O(log n) for recursive due to call stack.

## Conclusion

- For large datasets and environments where stack size is limited, iterative binary search is preferred.
- Recursive binary search can be more elegant and easier to understand but may not be suitable for extremely large input arrays without tail call optimization.
- Benchmark results can vary depending on JVM, hardware, and runtime conditions, so averaging multiple runs is recommended.
