package drills

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParenthesesTest : StringSpec({

    "isValidParentheses accepts balanced simple pairs" {
        isValidParentheses("()") shouldBe true
        isValidParentheses("()[]{}") shouldBe true
    }

    "isValidParentheses accepts correct nesting" {
        isValidParentheses("{[()]}") shouldBe true
    }

    "isValidParentheses rejects wrong pairing" {
        isValidParentheses("(]") shouldBe false
        isValidParentheses("([)]") shouldBe false
    }

    "isValidParentheses rejects an unclosed opener" {
        isValidParentheses("(") shouldBe false
        isValidParentheses("{[}") shouldBe false
    }

    "isValidParentheses rejects a closer with an empty stack" {
        isValidParentheses(")") shouldBe false
        isValidParentheses("()) ".trim()) shouldBe false
    }

    "isValidParentheses treats empty input as valid" {
        isValidParentheses("") shouldBe true
    }

    "minAddToMakeValid returns zero for already valid input" {
        minAddToMakeValid("()") shouldBe 0
        minAddToMakeValid("()()") shouldBe 0
        minAddToMakeValid("(())") shouldBe 0
    }

    "minAddToMakeValid counts unmatched closers" {
        minAddToMakeValid("())") shouldBe 1
        minAddToMakeValid(")))") shouldBe 3
    }

    "minAddToMakeValid counts unmatched openers" {
        minAddToMakeValid("(((") shouldBe 3
        minAddToMakeValid("()(") shouldBe 1
    }

    "minAddToMakeValid counts both directions" {
        minAddToMakeValid("())(") shouldBe 2
        minAddToMakeValid(")(") shouldBe 2
    }

    "minAddToMakeValid handles empty input" {
        minAddToMakeValid("") shouldBe 0
    }
})
