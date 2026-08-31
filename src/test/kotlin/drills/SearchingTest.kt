package drills

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SearchingTest : StringSpec({

    val sorted = intArrayOf(1, 3, 5, 7, 9, 11)

    "binarySearch finds the first element" {
        binarySearch(sorted, 1) shouldBe 0
    }

    "binarySearch finds the last element" {
        binarySearch(sorted, 11) shouldBe 5
    }

    "binarySearch finds a middle element" {
        binarySearch(sorted, 7) shouldBe 3
    }

    "binarySearch returns -1 when absent" {
        binarySearch(sorted, 4) shouldBe -1
        binarySearch(sorted, 0) shouldBe -1
        binarySearch(sorted, 99) shouldBe -1
    }

    "binarySearch handles empty and single element arrays" {
        binarySearch(intArrayOf(), 1) shouldBe -1
        binarySearch(intArrayOf(42), 42) shouldBe 0
        binarySearch(intArrayOf(42), 1) shouldBe -1
    }

    val withDuplicates = intArrayOf(1, 2, 2, 2, 3, 4)

    "binarySearchFirstOccurrence finds the leftmost index" {
        binarySearchFirstOccurrence(withDuplicates, 2) shouldBe 1
    }

    "binarySearchLastOccurrence finds the rightmost index" {
        binarySearchLastOccurrence(withDuplicates, 2) shouldBe 3
    }

    "occurrence variants agree when the value is unique" {
        binarySearchFirstOccurrence(withDuplicates, 4) shouldBe 5
        binarySearchLastOccurrence(withDuplicates, 4) shouldBe 5
    }

    "occurrence variants return -1 when absent" {
        binarySearchFirstOccurrence(withDuplicates, 9) shouldBe -1
        binarySearchLastOccurrence(withDuplicates, 9) shouldBe -1
    }

    "occurrence variants handle a run covering the whole array" {
        val allSame = intArrayOf(5, 5, 5, 5)
        binarySearchFirstOccurrence(allSame, 5) shouldBe 0
        binarySearchLastOccurrence(allSame, 5) shouldBe 3
    }
})
