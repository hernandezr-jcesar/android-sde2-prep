package drills

/**
 * Given [nums] of length n+1 where every value is in 1..n, find the one duplicated
 * value. There is exactly one duplicate, but it may appear more than twice.
 *
 * This is the best "can you do better?" problem on the list, so use it deliberately:
 * give the O(n) time / O(n) space HashSet version first, say its cost out loud, then
 * offer the O(1) space version. Showing that progression is the signal.
 *
 * Target: O(n) time, O(n) space.
 */
fun findDuplicate(nums: IntArray): Int = TODO("Track seen values in a HashSet")

/**
 * Same problem, O(1) extra space, without modifying the input.
 *
 * Floyd's cycle detection. The trick is seeing the array as a linked list where
 * index i points to nums[i]; because values are in 1..n and there are n+1 of them,
 * that list must contain a cycle, and the cycle entrance is the duplicate.
 *
 * Two phases: advance slow by one and fast by two until they meet, then reset slow
 * to the start and advance both by one until they meet again.
 *
 * Be honest in the interview if you have to think it through -- deriving it slowly
 * reads better than reciting it. If you cannot recall it, say "I know this reduces
 * to cycle detection, let me reason about why" and work forward.
 *
 * Target: O(n) time, O(1) space.
 */
fun findDuplicateFloyd(nums: IntArray): Int = TODO("Floyd's tortoise and hare, then find the cycle entrance")
