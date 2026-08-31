package drills

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class AlienDictionaryTest : StringSpec({

    val normal = "abcdefghijklmnopqrstuvwxyz"

    "isAlienSorted accepts a sorted list under a custom alphabet" {
        isAlienSorted(listOf("hello", "leetcode"), "hlabcdefgijkmnopqrstuvwxyz") shouldBe true
    }

    "isAlienSorted rejects an unsorted list under a custom alphabet" {
        isAlienSorted(listOf("word", "world", "row"), "worldabcefghijkmnpqstuvxyz") shouldBe false
    }

    "isAlienSorted applies the prefix rule: shorter first is sorted" {
        isAlienSorted(listOf("app", "apple"), normal) shouldBe true
    }

    "isAlienSorted applies the prefix rule: longer first is not sorted" {
        isAlienSorted(listOf("apple", "app"), "abcdefghijklmnopqrstuvwxyz") shouldBe false
    }

    "isAlienSorted accepts identical adjacent words" {
        isAlienSorted(listOf("app", "app"), normal) shouldBe true
    }

    "isAlienSorted handles empty and single word lists" {
        isAlienSorted(emptyList(), normal) shouldBe true
        isAlienSorted(listOf("solo"), normal) shouldBe true
    }

    "isAlienSorted uses the alien order, not the natural one" {
        isAlienSorted(listOf("zebra", "apple"), "zyxwvutsrqponmlkjihgfedcba") shouldBe true
        isAlienSorted(listOf("zebra", "apple"), normal) shouldBe false
    }

    "isAlienSorted checks every adjacent pair, not just the first" {
        isAlienSorted(listOf("abc", "abd", "abb"), normal) shouldBe false
    }
})
