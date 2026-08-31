package drills

/** A closed interval [start, end]. */
data class Interval(val start: Int, val end: Int)

/**
 * Merge all overlapping intervals and return them sorted by start.
 *
 * The algorithm is trivial once sorted, so the interviewer is really testing
 * whether you sort first without being told. Say it explicitly: "these aren't
 * guaranteed sorted, so I'll sort by start -- that's the O(n log n) that dominates."
 *
 * Clarify the edge case that decides the implementation: do [1,3] and [3,5] merge?
 * Touching endpoints usually do merge, but make them confirm it -- it is the
 * difference between `<=` and `<` in your overlap check.
 *
 * Also handle: empty input, a single interval, and one interval fully containing
 * another (the merged end is max(a.end, b.end), not simply b.end).
 *
 * Target: O(n log n) time from the sort, O(n) space for the output.
 */
fun mergeIntervals(intervals: List<Interval>): List<Interval> =
    TODO("Sort by start, then fold: extend the last interval or append a new one")
