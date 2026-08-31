package drills

/**
 * Validate that brackets are balanced and correctly nested.
 * Handles three pairs: (), [] and {}.
 *
 * The stack is the whole insight: push openers, and on a closer check that the top
 * of the stack is its partner. Two failure cases people forget -- a closer arriving
 * when the stack is empty, and a non-empty stack at the end.
 *
 * Clarify: can the string contain other characters? (In the classic version, no.)
 *
 * Target: O(n) time, O(n) space.
 */
fun isValidParentheses(input: String): Boolean = TODO("Stack of openers; verify the partner on each closer")

/**
 * Return the minimum number of parentheses to ADD to make [input] valid.
 * Only round brackets. Characters are only '(' and ')'.
 *
 * You do not need a stack here, and noticing that is the point of the question:
 * track two counters -- how many openers are currently unmatched, and how many
 * closers have already gone unmatched. The answer is their sum.
 *
 * Walk through "())(" out loud: one unmatched closer, one unmatched opener, answer 2.
 *
 * Target: O(n) time, O(1) space.
 */
fun minAddToMakeValid(input: String): Int = TODO("Two counters: open needed, close needed")
