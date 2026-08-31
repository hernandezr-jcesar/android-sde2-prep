package drills

/**
 * The three sorts named in the SDE II guide. Each returns a sorted copy so the
 * caller's array is untouched -- in a real interview, ask whether they want it
 * sorted in place, because that changes the signature and the space complexity.
 *
 * You will be asked "which would you use in production?" The answer is none of
 * them: you would call `sorted()`, which is a dual-pivot quicksort for primitives
 * and a stable Timsort for objects. These exist to prove you understand the
 * mechanics, so be ready to explain the mechanics, not just reproduce the loops.
 */

/**
 * Bubble sort: repeatedly swap adjacent out-of-order pairs; the largest element
 * "bubbles" to the end each pass.
 *
 * Add the early-exit flag -- if a full pass makes no swaps, the array is sorted.
 * That is what turns the best case from O(n^2) into O(n), and interviewers look for it.
 *
 * Target: O(n^2) average, O(n) best with early exit, O(1) extra space. Stable.
 */
fun bubbleSort(input: IntArray): IntArray = TODO("Adjacent swaps with an early-exit flag")

/**
 * Selection sort: find the minimum of the unsorted remainder, swap it into place.
 *
 * Always O(n^2) -- there is no early exit, because you cannot know the minimum
 * without scanning the rest. Its one virtue is the minimum number of writes (n-1
 * swaps), which matters when writes are expensive.
 *
 * Target: O(n^2) always, O(1) extra space. Not stable.
 */
fun selectionSort(input: IntArray): IntArray = TODO("Find min of remainder, swap into position")

/**
 * Insertion sort: grow a sorted prefix, shifting each new element left into place.
 *
 * The one to name when asked "which of these is actually useful?" -- it is O(n) on
 * nearly sorted input and it is what real sorts fall back to for small partitions.
 *
 * Target: O(n^2) average, O(n) best on sorted input, O(1) extra space. Stable.
 */
fun insertionSort(input: IntArray): IntArray = TODO("Shift elements right until the slot is found")
