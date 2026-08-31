package drills

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class IntervalsTest : StringSpec({

    "mergeIntervals merges overlapping ranges" {
        mergeIntervals(
            listOf(Interval(1, 3), Interval(2, 6), Interval(8, 10), Interval(15, 18))
        ) shouldBe listOf(Interval(1, 6), Interval(8, 10), Interval(15, 18))
    }

    "mergeIntervals merges intervals that only touch at an endpoint" {
        mergeIntervals(listOf(Interval(1, 4), Interval(4, 5))) shouldBe listOf(Interval(1, 5))
    }

    "mergeIntervals sorts unsorted input before merging" {
        mergeIntervals(
            listOf(Interval(8, 10), Interval(1, 3), Interval(2, 6))
        ) shouldBe listOf(Interval(1, 6), Interval(8, 10))
    }

    "mergeIntervals keeps the widest end when one interval contains another" {
        mergeIntervals(listOf(Interval(1, 10), Interval(2, 3))) shouldBe listOf(Interval(1, 10))
    }

    "mergeIntervals leaves disjoint intervals untouched" {
        mergeIntervals(
            listOf(Interval(1, 2), Interval(4, 5))
        ) shouldBe listOf(Interval(1, 2), Interval(4, 5))
    }

    "mergeIntervals handles empty and single inputs" {
        mergeIntervals(emptyList()) shouldBe emptyList()
        mergeIntervals(listOf(Interval(3, 7))) shouldBe listOf(Interval(3, 7))
    }

    "mergeIntervals collapses a fully overlapping chain" {
        mergeIntervals(
            listOf(Interval(1, 4), Interval(2, 5), Interval(3, 9))
        ) shouldBe listOf(Interval(1, 9))
    }
})
