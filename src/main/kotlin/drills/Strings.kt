package drills

/**
 * Reverse a string.
 *
 * Clarify first: is the input ASCII, or do I need to handle Unicode? Naive char
 * reversal corrupts surrogate pairs (emoji) and combining accents. Saying this
 * out loud is worth more than the implementation.
 *
 * Do it twice: the manual two-pointer swap on a CharArray, then `input.reversed()`.
 * Then say which one you would actually ship and why.
 *
 * Target: O(n) time, O(n) space.
 */
fun reverseString(input: String): String { //TODO("Two-pointer swap over a CharArray")
    val arr = input.toCharArray()
    val res = input.reversed()
    println(res)
    return res
}


/**
 * Check whether a string is a palindrome.
 *
 * Clarify first: case sensitive? Do spaces and punctuation count? Almost always
 * the intended answer is "ignore case and non-alphanumerics" -- but make them say it.
 *
 * Prefer two pointers walking inward over building a reversed copy: same time,
 * O(1) extra space, and it short-circuits on the first mismatch.
 *
 * Target: O(n) time, O(1) space.
 */
fun isPalindrome(input: String): Boolean = TODO("Two pointers inward, skipping non-alphanumerics")

/** Result of [countVowelsAndConsonants]. */
data class LetterCount(val vowels: Int, val consonants: Int)

/**
 * Count vowels and consonants.
 *
 * Clarify first: is 'y' a vowel? (Say you will treat it as a consonant unless told
 * otherwise.) What about digits, spaces and punctuation? They are neither -- they
 * must not land in the consonant bucket, which is the bug the interviewer is watching for.
 *
 * Target: O(n) time, O(1) space.
 */
fun countVowelsAndConsonants(input: String): LetterCount =
    TODO("Single pass; only count characters that are letters")
