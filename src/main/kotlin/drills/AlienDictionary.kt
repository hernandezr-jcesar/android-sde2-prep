package drills

/**
 * Return true if [words] is sorted lexicographically according to [order], a
 * permutation of the 26 lowercase letters giving the alien alphabet.
 *
 * Two things the interviewer is checking:
 *
 *  1. Do you build an index map from the order string (letter -> rank) instead of
 *     calling indexOf inside the comparison loop? indexOf turns an O(n*m) solution
 *     into O(n*m*26). Say why you are building the map.
 *
 *  2. Do you handle the prefix rule? "app" before "apple" is sorted; "apple" before
 *     "app" is not. When one word is a prefix of the other, the shorter must come
 *     first. This is the case almost everyone misses -- name it before you code.
 *
 * Compare adjacent pairs only. If every adjacent pair is ordered, the list is sorted.
 *
 * Target: O(total characters) time, O(1) space (the map is fixed at 26 entries).
 */
fun isAlienSorted(words: List<String>, order: String): Boolean =
    TODO("Build letter->rank map, compare adjacent pairs, handle the prefix rule")
