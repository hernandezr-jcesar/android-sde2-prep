package drills

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringsTest : StringSpec({

    "reverseString handles a normal word" {
        reverseString("kotlin") shouldBe "niltok"
    }

    "reverseString handles empty and single character" {
        reverseString("") shouldBe ""
        reverseString("a") shouldBe "a"
    }

    "reverseString preserves spaces and punctuation" {
        reverseString("ab c!") shouldBe "!c ba"
    }

    "isPalindrome accepts a simple palindrome" {
        isPalindrome("racecar") shouldBe true
    }

    "isPalindrome ignores case, spaces and punctuation" {
        isPalindrome("A man, a plan, a canal: Panama") shouldBe true
    }

    "isPalindrome rejects a non-palindrome" {
        isPalindrome("kotlin") shouldBe false
    }

    "isPalindrome treats empty and single characters as palindromes" {
        isPalindrome("") shouldBe true
        isPalindrome("x") shouldBe true
    }

    "isPalindrome handles a string of only punctuation" {
        isPalindrome(".,!") shouldBe true
    }

    "countVowelsAndConsonants counts a simple word" {
        countVowelsAndConsonants("kotlin") shouldBe LetterCount(vowels = 2, consonants = 4)
    }

    "countVowelsAndConsonants ignores digits, spaces and punctuation" {
        countVowelsAndConsonants("a1 b!") shouldBe LetterCount(vowels = 1, consonants = 1)
    }

    "countVowelsAndConsonants is case insensitive" {
        countVowelsAndConsonants("AEIOUxyz") shouldBe LetterCount(vowels = 5, consonants = 3)
    }

    "countVowelsAndConsonants handles empty input" {
        countVowelsAndConsonants("") shouldBe LetterCount(vowels = 0, consonants = 0)
    }
})
