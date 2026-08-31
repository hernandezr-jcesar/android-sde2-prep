package drills

/**
 * Binary search and its two variants.
 *
 * The base version is easy; the variants are where people fall apart, and they are
 * the natural follow-up question. Practise all three until the boundary updates are
 * automatic.
 *
 * Two traps to mention out loud:
 *   - `(low + high) / 2` can overflow on large ints. Use `low + (high - low) / 2`.
 *   - The loop condition is `low <= high` when searching for a value, but `low < high`
 *     in the boundary-finding variants. Mixing them up is the classic off-by-one.
 */

/**
 * Return the index of [target] in the ascending [sorted] array, or -1 if absent.
 * If the value appears more than once, any matching index is acceptable.
 *
 * Target: O(log n) time, O(1) space.
 */
fun binarySearch(sorted: IntArray, target: Int): Int = TODO("Classic loop, low <= high")

/**
 * Return the index of the FIRST occurrence of [target], or -1 if absent.
 *
 * Do not stop at the first match -- record it and keep searching left.
 *
 * Target: O(log n) time, O(1) space.
 */
fun binarySearchFirstOccurrence(sorted: IntArray, target: Int): Int =
    TODO("On a match, record the index and move high left")

/**
 * Return the index of the LAST occurrence of [target], or -1 if absent.
 *
 * Target: O(log n) time, O(1) space.
 */
fun binarySearchLastOccurrence(sorted: IntArray, target: Int): Int =
    TODO("On a match, record the index and move low right")
