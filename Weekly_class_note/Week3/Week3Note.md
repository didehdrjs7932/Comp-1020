# COMP 1020 — Week 3 Notes

## Table of Contents
1. [Algorithm Analysis (Time & Space Complexity)](#1-algorithm-analysis-time--space-complexity)
2. [Big O Notation](#2-big-o-notation)
3. [Combining Complexities](#3-combining-complexities)
4. [Searching: Linear Search](#4-searching-linear-search)
5. [Searching: Binary Search](#5-searching-binary-search)
6. [Sorting: Insertion Sort](#6-sorting-insertion-sort)
7. [Sorting: Selection Sort](#7-sorting-selection-sort)
8. [Sorting: Bubble Sort](#8-sorting-bubble-sort)
9. [Sorting: Merge Sort](#9-sorting-merge-sort)
10. [Master Comparison Table (Searching + Sorting)](#10-master-comparison-table-searching--sorting)

---

## 1. Algorithm Analysis (Time & Space Complexity)

> Note: algorithm analysis is technically the *last* course topic, but it's introduced early because it's useful for discussing searching/sorting algorithms.

Algorithm analysis has two parts:

- **Time complexity**: How does the number of steps/operations grow as the size of the dataset (`n`) increases? → *How efficient is the algorithm?*
- **Space complexity**: How does the space/memory used by the algorithm grow as the size of the dataset grows?

Both are written using **Big O Notation**.

---

## 2. Big O Notation

Common time complexities, from **fastest** to **slowest**:

| Big O | Name | Speed |
|---|---|---|
| `O(1)` | Constant | fastest |
| `O(log n)` | Logarithmic | fast |
| `O(n)` | Linear | moderate |
| `O(n²)` | Quadratic | slow |
| `O(2ⁿ)` | Exponential | slowest |

```
time / #operations
   ^
   |                                    O(n²) quadratic
   |                                 /
   |                             /
   |                         /        O(n) linear
   |                     /       __--
   |                 /     __---
   |             / __---           O(log n) logarithmic
   |         /_---________________________
   |    _---__________________________________ O(1) constant
   +------------------------------------------> (n) data size
```

- **`O(1)` — constant**: same number of steps no matter how big the dataset is (e.g. accessing `array[0]`).
- **`O(log n)` — logarithmic**: steps grow very slowly as `n` grows (e.g. binary search — you cut the data in half each time).
- **`O(n)` — linear**: steps grow directly proportional to `n` (e.g. a single loop through a collection).
- **`O(n²)` — quadratic**: steps grow with the square of `n` (e.g. nested loops, like comparing every element to every other element).

```java
// O(1) - constant: always exactly 1 step, regardless of array size
public static int getFirstElement(int[] arr) {
    return arr[0];
}

// O(n) - linear: single loop through the dataset
public static void printAll(int[] arr) {
    for (int i = 0; i < arr.length; i++) {   // n iterations
        System.out.println(arr[i]);
    }
}

// O(n^2) - quadratic: nested loops, every element interacts with every other element
public static void printAllPairs(int[] arr) {
    for (int i = 0; i < arr.length; i++) {       // n iterations
        for (int j = 0; j < arr.length; j++) {   // n iterations, for EACH outer iteration
            System.out.println(arr[i] + ", " + arr[j]);
        }
    }
}
```

> **Important:** Always actually **read the code** to understand what it's doing — don't just assume based on the number of loops! A loop's complexity depends on what it's actually iterating over.

---

## 3. Combining Complexities

- The total complexity of an algorithm can be found by combining the complexity of its various stages.
- We **drop any inconsequential constant values** (coefficients) since Big O cares about growth rate, not exact step counts.

```
O(n) + O(n)  =  O(2n)  =  O(n)      // constant "2" is dropped
O(n) × O(n)  =  O(n × n)  =  O(n²)  // multiplying nested complexities
```

```java
// Two SEPARATE loops (not nested) -> O(n) + O(n) = O(2n) = O(n)
public static void twoSeparateLoops(int[] arr) {
    for (int i = 0; i < arr.length; i++) {   // O(n)
        System.out.println(arr[i]);
    }
    for (int j = 0; j < arr.length; j++) {   // O(n)
        System.out.println(arr[j] * 2);
    }
    // Total: O(n) -- recognizable as basically "a single pass" in growth terms
}

// Two NESTED loops -> O(n) x O(n) = O(n^2)
public static void nestedLoops(int[] arr) {
    for (int i = 0; i < arr.length; i++) {        // O(n)
        for (int j = 0; j < arr.length; j++) {    // O(n), running INSIDE the outer loop
            System.out.println(arr[i] + arr[j]);
        }
    }
    // Total: O(n^2) -- every element interacts with every other element
}
```

---

## 4. Searching: Linear Search

- **Complexity: `O(n)`** (slowest of the searches we cover)
- Loop through the collection and check every item until found (or not).
- Works on **unsorted OR sorted** data.

```java
public class LinearSearchExample {

    // simple version -- keeps looping even after finding a match
    public static String linearSearch(String[] myStrings, String target) {
        String result = null;
        for (String s : myStrings) {
            if (s.equals(target)) {
                result = s;
            }
        }
        return result;
    }

    // BETTER version -- stops looping as soon as it's found (change the loop condition)
    public static String linearSearchOptimized(String[] myStrings, String target) {
        boolean found = false;
        String result = null;

        for (int i = 0; i < myStrings.length && !found; i++) { // combine logic in the condition!
            if (myStrings[i].equals(target)) {
                result = myStrings[i];
                found = true;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] names = {"Sam", "Alex", "Jordan", "Casey"};
        System.out.println(linearSearchOptimized(names, "Jordan")); // "Jordan"
        System.out.println(linearSearchOptimized(names, "Taylor")); // null
    }
}
```

---

## 5. Searching: Binary Search

- **Complexity: `O(log n)`** (much better than linear search!)
- **Requires sorted data first.**
- Cuts the data in half, and looks in the correct half — repeats until found.
- Each "pass" of the algorithm cuts the remaining data in half → this is what makes it `O(log n)`.
- Metaphor: finding a word in a dictionary — you don't read page by page, you jump to roughly the right section and narrow down.

**Key indices to track:** `start`, `end`, `middle` — figure out where the search target falls in relation to these, to know where to look next.

```java
public class BinarySearchExample {

    // data MUST be sorted first!
    public static int binarySearch(int[] sortedArr, int target) {
        int start = 0;
        int end = sortedArr.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2; // find the midpoint

            if (sortedArr[middle] == target) {
                return middle; // found it!
            } else if (sortedArr[middle] < target) {
                start = middle + 1; // target must be in the RIGHT half
            } else {
                end = middle - 1;   // target must be in the LEFT half
            }
        }

        return -1; // not found
    }

    public static void main(String[] args) {
        int[] sortedNumbers = {1, 3, 4, 10, 13, 99}; // must already be sorted

        System.out.println(binarySearch(sortedNumbers, 13)); // index 4
        System.out.println(binarySearch(sortedNumbers, 7));  // -1, not found
    }
}
```

---

## 6. Sorting: Insertion Sort

- **Complexity: `O(n²)`** (described using the **worst-case** scenario — average/best cases could differ)
- **"In-place" algorithm**: moves elements around *within the same array/collection* (no extra copy needed). Choose in-place algorithms when possible.
- **Core idea:** grab the next element, insert it into its correct position among the already-sorted elements.

### Walkthrough Example

Starting array: `{3, 10, 1, 99, 13, 4, 2}`

```
Pass 1 (i=0):  [3] | 10, 1, 99, 13, 4, 2        <- sorted | unsorted
Pass 2 (i=1):  3, 10 | 1, 99, 13, 4, 2
Pass 3 (i=2):  1, 3, 10 | 99, 13, 4, 2           <- elements "shift" to make space
Pass 4 (i=3):  1, 3, 10, 99 | 13, 4, 2
Pass 5 (i=4):  1, 3, 10, 13, 99 | 4, 2
Pass 6 (i=5):  1, 3, 4, 10, 13, 99 | 2
Pass 7 (i=6):  1, 2, 3, 4, 10, 13, 99            <- done!
```

> **Optimization Note:** We usually start at `i = 1` (not `i = 0`), because a single element (`i = 0`) is already "sorted" by itself. Our first real pass typically ends with **two** sorted elements (as shown in Pass 2 above).

### Code

```java
public class InsertionSortExample {

    public static void insertionSort(int[] arr) {
        // start at i = 1 -- the element at index 0 is trivially "sorted" alone
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];   // "grab the next element"
            int j = i - 1;

            // shift sorted elements to the right to make space for 'current'
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j]; // shift element down
                j--;
            }

            // insert 'current' into its correct sorted position
            arr[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] data = {3, 10, 1, 99, 13, 4, 2};

        insertionSort(data);

        for (int num : data) {
            System.out.print(num + " ");
        }
        // Output: 1 2 3 4 10 13 99
    }
}
```

---

## 7. Sorting: Selection Sort

- **Complexity: `O(n²)`** (worst, average, AND best case — always the same, unlike Insertion Sort)
- **"In-place" algorithm.**
- **Core idea:** repeatedly find the **minimum** element from the unsorted portion, and swap it into its correct position at the front.

### Walkthrough Example

Starting array: `{3, 10, 1, 99, 13, 4, 2}`

```
Pass 1 (i=0): find min in [3,10,1,99,13,4,2] -> min is 1 (index 2), swap with index 0
              [1, 10, 3, 99, 13, 4, 2]         <- sorted | unsorted
Pass 2 (i=1): find min in [10,3,99,13,4,2]  -> min is 2 (index 6), swap with index 1
              [1, 2, 3, 99, 13, 4, 10]
Pass 3 (i=2): find min in [3,99,13,4,10]    -> min is 3, already in place
              [1, 2, 3, 99, 13, 4, 10]
Pass 4 (i=3): find min in [99,13,4,10]      -> min is 4, swap with index 3
              [1, 2, 3, 4, 13, 99, 10]
Pass 5 (i=4): find min in [13,99,10]        -> min is 10, swap with index 4
              [1, 2, 3, 4, 10, 99, 13]
Pass 6 (i=5): find min in [99,13]           -> min is 13, swap with index 5
              [1, 2, 3, 4, 10, 13, 99]        <- done!
```

> **Selection Sort vs Insertion Sort:** Selection Sort minimizes the number of **swaps** (one swap per pass), while Insertion Sort minimizes comparisons on already-sorted data. Both are `O(n²)`, but Selection Sort is useful when swapping is expensive (e.g. large objects) since it swaps far less often.

### Code

```java
public class SelectionSortExample {

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // assume current position holds the minimum so far

            // scan the REMAINING unsorted portion to find the actual minimum
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // swap the found minimum into its correct sorted position
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {3, 10, 1, 99, 13, 4, 2};

        selectionSort(data);

        for (int num : data) {
            System.out.print(num + " ");
        }
        // Output: 1 2 3 4 10 13 99
    }
}
```

---

## 8. Sorting: Bubble Sort

- **Complexity: `O(n²)`** (worst/average case), `O(n)` best case if already sorted (with an optimization flag)
- **"In-place" algorithm.**
- **Core idea:** repeatedly step through the array, comparing **adjacent** elements and swapping them if they're in the wrong order. Larger elements "bubble up" to the end with each pass.

### Walkthrough Example

Starting array: `{3, 10, 1, 99, 13, 4, 2}`

```
Pass 1: compare each adjacent pair, swap if left > right
  3,10 -> ok        10,1 -> swap       10,99 -> ok      99,13 -> swap
  99,4 -> swap       99,2 -> swap
  Result: [3, 1, 10, 13, 4, 2, 99]     <- largest value (99) "bubbled" to the end

Pass 2: [1, 3, 10, 4, 2, 13, 99]       <- 13 now in place
Pass 3: [1, 3, 4, 2, 10, 13, 99]       <- 10 now in place
Pass 4: [1, 3, 2, 4, 10, 13, 99]       <- 4 now in place
Pass 5: [1, 2, 3, 4, 10, 13, 99]       <- done! (no swaps needed -> can stop early)
```

> **Optimization:** if a full pass completes with **zero swaps**, the array is already sorted — you can stop early instead of running all `n` passes. This is what gives Bubble Sort its best-case `O(n)`.

### Code

```java
public class BubbleSortExample {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false; // optimization flag

            // each pass, the largest unsorted element "bubbles" to the end
            // so we can shrink the range we check by 'i' each time
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap adjacent elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // if nothing was swapped this pass, the array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {3, 10, 1, 99, 13, 4, 2};

        bubbleSort(data);

        for (int num : data) {
            System.out.print(num + " ");
        }
        // Output: 1 2 3 4 10 13 99
    }
}
```

---

## 9. Sorting: Merge Sort

- **Complexity: `O(n log n)`** — much better than the `O(n²)` algorithms above! This is why Merge Sort is preferred for larger datasets.
- **NOT an in-place algorithm** — requires extra memory (`O(n)` space) to hold temporary sub-arrays during merging. Trade-off: better time complexity, worse space complexity.
- Uses the **"divide and conquer"** strategy:
  1. **Divide**: split the array in half, recursively, until each piece has only 1 element (a single element is trivially "sorted").
  2. **Conquer / Merge**: merge the small sorted pieces back together in the correct order, building back up to the full sorted array.

### Walkthrough Example

Starting array: `{3, 10, 1, 99, 13, 4, 2}`

```
DIVIDE (split in half recursively):
        [3,10,1,99,13,4,2]
        /                \
   [3,10,1,99]          [13,4,2]
   /       \             /     \
 [3,10]   [1,99]      [13,4]   [2]
  /  \      /  \        /  \
[3] [10]  [1] [99]   [13] [4]

MERGE (combine sorted pieces back together, in order):
[3] + [10]       -> [3,10]
[1] + [99]       -> [1,99]
[3,10]+[1,99]    -> [1,3,10,99]

[13]+[4]         -> [4,13]
[4,13]+[2]       -> [2,4,13]

[1,3,10,99] + [2,4,13] -> [1,2,3,4,10,13,99]   <- fully merged & sorted!
```

### Code

```java
public class MergeSortExample {

    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return; // base case: a single element is already "sorted"
        }

        // DIVIDE: split the array into two halves
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        // recursively sort each half
        mergeSort(left);
        mergeSort(right);

        // CONQUER: merge the two sorted halves back together
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0; // pointers for left, right, and merged array

        // compare elements from both halves, take the smaller one each time
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // copy any remaining elements (one side may finish before the other)
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] data = {3, 10, 1, 99, 13, 4, 2};

        mergeSort(data);

        for (int num : data) {
            System.out.print(num + " ");
        }
        // Output: 1 2 3 4 10 13 99
    }
}
```

---

## 10. Master Comparison Table (Searching + Sorting)

The core Big O complexity to know for each algorithm, ordered from **fastest → slowest**:

| Algorithm | Category | Big O |
|---|---|---|
| Binary Search | Searching | `O(log n)` |
| Linear Search | Searching | `O(n)` |
| Merge Sort | Sorting | `O(n log n)` |
| Insertion Sort | Sorting | `O(n²)` |
| Selection Sort | Sorting | `O(n²)` |
| Bubble Sort | Sorting | `O(n²)` |

### Big O Growth Rate — Fastest to Slowest (recap)

```
O(1)  <  O(log n)  <  O(n)  <  O(n log n)  <  O(n²)  <  O(2ⁿ)
fastest -------------------------------------------------> slowest
```

### One-Line Summary of Each

| Algorithm | One-Line Summary |
|---|---|
| Linear Search | Check every element one by one until found. |
| Binary Search | Repeatedly cut sorted data in half to zero in on the target. |
| Insertion Sort | Insert each new element into its correct spot among the already-sorted elements. |
| Selection Sort | Repeatedly find the minimum of the unsorted portion and swap it to the front. |
| Bubble Sort | Repeatedly swap adjacent out-of-order pairs until nothing needs swapping. |
| Merge Sort | Split the array in half recursively, then merge the sorted halves back together. |