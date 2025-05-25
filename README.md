# Cache Service & Algorithms

This project is a practical implementation of core software engineering concepts inspired by the book **"Effective Java" by Joshua Bloch**. It includes multiple components such as custom cache implementations, sorting algorithms, binary search approaches, benchmarking, and binary tree traversal.

## ✅ Tasks

The project covers the following tasks:

### Cache Service

- **Max Size** = 100,000 entries
- **Eviction Policy**: Time-based (5 seconds after last access)
- **Removal Listener**: Logs removed entries
- **Statistics Provided**:
    - Average time to insert values (`put()` method)
    - Number of cache evictions
- **Concurrency Support**
- Implemented in two versions:
    - **Simple Java (LFU strategy)** – 60 points
    - **Guava Cache (LRU strategy)** – 40 points

### Binary Search

- Implemented in two ways:
    - Iterative binary search
    - Recursive binary search
- Benchmarking and effectiveness comparison between iterative and recursive approaches
- Complexity analysis and usage recommendations included

### Sorting Algorithms

- **Merge Sort**
    - Time complexity: O(N log N)
    - Includes unit and benchmark tests
- **Insertion Sort**
    - Time complexity: O(N²) in the worst case
    - Space complexity: O(1)
    - Includes unit and benchmark tests

### Binary Search Integration

- Search algorithm integrates with different sorting strategies using the Strategy design pattern
- Parametrized unit tests verify consistent behavior across different sorting strategies
- Reflects recommendations from "Effective Java"

### Binary Tree Traversal

- Custom implementation of binary tree and its traversal
- Covers pre-order, in-order, and post-order traversal algorithms
- Demonstrates understanding of binary tree structure and its usage in Java

## ✅ Project Structure

```
.
├── benchmark/              # Benchmarking classes
├── cache/                  # Cache implementation (LFU, Guava-based LRU)
├── search/                 # Binary search implementations & integration service
├── sort/                   # Sorting strategies (Merge, Insertion)
├── tree/                   # Binary tree and traversal implementation
└── test/                   # JUnit and benchmark tests for all components
```

## 🧪 Tests and Quality

- Comprehensive unit tests using **JUnit 5**
- Parametrized tests for sorting + binary search integration
- Benchmark classes using large arrays
- Logging with SLF4J for removal listener

## 🛠 Technologies Used

- Java 17+
- Guava Library (for LRU cache)
- Maven
- JUnit 5

## 💡 Notes

This project reflects a real-world approach to comparing and testing algorithms using clean code, modular architecture, and effective design patterns. All features were implemented keeping concurrency, performance, and code readability in mind.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
