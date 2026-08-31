# SDE II interview prep

Practice material for the Android Software Development Engineer II interview at McDonald's, scoped to what the org's own interview material says is actually asked.

Companion to the study plan at [Android Engineer II things to study](https://mcd-tools.atlassian.net/wiki/spaces/~712020599cf2d9081f49a08e8560901a90faac/pages/2333966885/Android+Engineer+II+things+to+study.).

## What's here

| Path | What it is |
| --- | --- |
| `src/main/kotlin/drills/` | The 13 coding problems as unimplemented stubs. Each doc comment holds the clarifying questions to ask, the traps, and the complexity target. |
| `src/test/kotlin/drills/` | Kotest suites covering the edge cases interviewers actually probe. |
| `docs/non-coding-answers.md` | Model answers to the five SDE2 non-coding questions, built from real evidence. |
| `docs/star-stories.md` | Eight two-minute STAR stories with a question-to-story coverage map. |

## Running the drills

Requires Java 17, which you have. The Gradle wrapper is included, so no install needed.

```bash
# every drill
./gradlew test

# one drill at a time -- this is the normal loop
./gradlew test --tests "*StringsTest*"
./gradlew test --tests "*SortingTest*"
./gradlew test --tests "*SearchingTest*"
./gradlew test --tests "*PatternsTest*"
./gradlew test --tests "*ParenthesesTest*"
./gradlew test --tests "*IntervalsTest*"
./gradlew test --tests "*DuplicatesTest*"
./gradlew test --tests "*AlienDictionaryTest*"
```

Everything fails on first run with `kotlin.NotImplementedError`. That is the starting state, not a broken setup — each function is a `TODO()` waiting for you.

You can also open the folder directly in Android Studio or IntelliJ and run tests from the gutter, which is closer to how you'll code in the interview.

## How to actually use this

The drills are not here to teach you algorithms — none of these are hard, and you can already write all of them. They're here to make you **fluent under observation**, because the SDE playbook says the SDE I and II technical rounds primarily emphasise live coding, and the failure mode at this level is process, not knowledge.

So each rep runs like the real thing:

1. **Read the doc comment first.** It lists the clarifying questions. In the interview you ask those out loud before writing anything — inputs, empty cases, duplicates, case sensitivity, Unicode. Interviewers score this explicitly.
2. **Start a timer.** Twenty minutes per problem, hard stop.
3. **Talk the whole time.** Out loud, alone, feeling ridiculous. Silence is scored as not knowing.
4. **Say the complexity before being asked**, then offer to optimise.
5. **Walk a small example by hand** before you run the tests. In the interview there is no green bar.
6. **Then run the tests.** If they pass first time, do it again tomorrow from an empty file.

Rewriting a solved problem cold three days later is worth more than solving a new one. Recall is the thing being tested.

## No reference solutions, on purpose

The tests are your correctness oracle and the doc comments are your approach oracle. A solutions file would just get read instead of the problem getting solved.

If you get genuinely stuck on one — `findDuplicateFloyd` is the likely candidate, since cycle detection is hard to derive under time pressure — ask for that one worked through rather than the set.

## Coverage against the official scope

From the [Android Interview Guide](https://mcd-tools.atlassian.net/wiki/spaces/ENGG/pages/612469474/Android+Interview+Guide) and the [Technical Interview Question Bank](https://mcd-tools.atlassian.net/wiki/spaces/ENGG/pages/93590587/Technical+Interview+Question+Bank):

| Required | File |
| --- | --- |
| Reverse a string | `Strings.kt` |
| Check for palindrome | `Strings.kt` |
| Count vowels and consonants | `Strings.kt` |
| Bubble / Selection / Insertion sort | `Sorting.kt` |
| Binary search | `Searching.kt` (plus first/last-occurrence variants) |
| Singleton | `Patterns.kt` |
| Factory | `Patterns.kt` |
| Valid Parentheses | `Parentheses.kt` |
| Minimum parentheses to add | `Parentheses.kt` |
| Merge Intervals | `Intervals.kt` |
| Find the Duplicate Number | `Duplicates.kt` (both the O(n) space and O(1) space versions) |
| Verifying an Alien Dictionary | `AlienDictionary.kt` |

That is the complete documented coding scope for SDE II.
