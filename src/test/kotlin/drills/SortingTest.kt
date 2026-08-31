package drills

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SortingTest : StringSpec({

    val cases = listOf(
        "empty" to intArrayOf(),
        "single" to intArrayOf(1),
        "already sorted" to intArrayOf(1, 2, 3, 4, 5),
        "reverse sorted" to intArrayOf(5, 4, 3, 2, 1),
        "duplicates" to intArrayOf(3, 1, 3, 1, 2),
        "negatives" to intArrayOf(0, -5, 3, -1, 2),
        "all equal" to intArrayOf(7, 7, 7)
    )

    cases.forEach { (name, input) ->
        "bubbleSort sorts $name" {
            bubbleSort(input.copyOf()).toList() shouldBe input.sorted()
        }
        "selectionSort sorts $name" {
            selectionSort(input.copyOf()).toList() shouldBe input.sorted()
        }
        "insertionSort sorts $name" {
            insertionSort(input.copyOf()).toList() shouldBe input.sorted()
        }
    }

    "sorts do not mutate the caller's array" {
        val original = intArrayOf(3, 1, 2)
        bubbleSort(original)
        selectionSort(original)
        insertionSort(original)
        original.toList() shouldBe listOf(3, 1, 2)
    }
})
