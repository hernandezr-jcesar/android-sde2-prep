package drills

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DuplicatesTest : StringSpec({

    val cases = listOf(
        intArrayOf(1, 3, 4, 2, 2) to 2,
        intArrayOf(3, 1, 3, 4, 2) to 3,
        intArrayOf(1, 1) to 1,
        intArrayOf(2, 2, 2, 2, 2) to 2,
        intArrayOf(1, 4, 4, 2, 4) to 4
    )

    cases.forEach { (input, expected) ->
        "findDuplicate finds $expected in ${input.joinToString()}" {
            findDuplicate(input.copyOf()) shouldBe expected
        }
        "findDuplicateFloyd finds $expected in ${input.joinToString()}" {
            findDuplicateFloyd(input.copyOf()) shouldBe expected
        }
    }

    "findDuplicateFloyd does not modify the input array" {
        val input = intArrayOf(1, 3, 4, 2, 2)
        findDuplicateFloyd(input)
        input.toList() shouldBe listOf(1, 3, 4, 2, 2)
    }
})
