# Day 1 — Kotlin core and string manipulation

Companion to [Android Engineer II things to study](https://mcd-tools.atlassian.net/wiki/spaces/~712020599cf2d9081f49a08e8560901a90faac/pages/2333966885/Android+Engineer+II+things+to+study.) · Block B theory reference with doc links.

Use this instead of Googling every term. Each section is one concept, a one-line interview hook, and the canonical link.

---

## Block A — Coding (today's reps)

| Problem | Where to practice |
| --- | --- |
| Reverse a string (two-pointer + idiomatic) | `src/main/kotlin/drills/Strings.kt` |
| Palindrome (case, non-alphanumerics) | `src/main/kotlin/drills/Strings.kt` |
| Count vowels and consonants | `src/main/kotlin/drills/Strings.kt` |
| Valid Parentheses | `src/main/kotlin/drills/Parentheses.kt` |

**Interview habits:** clarify inputs first · narrate out loud · state complexity before asked · walk a small example by hand.

---

## Block B — Kotlin theory

### Null safety

**What to know:** `String?` vs `String`; safe call `?.`, Elvis `?:`, not-null assertion `!!` (avoid in interviews); `let` for null blocks; platform types from Java.

- [Null safety](https://kotlinlang.org/docs/null-safety.html) — Kotlin docs
- [Safe calls, Elvis, assertions](https://kotlinlang.org/docs/null-safety.html#safe-call-operator) — operators reference

---

### `val` vs `var`

**What to know:** `val` = read-only reference (not necessarily immutable object); `var` = reassignable. Prefer `val`. `const val` only for compile-time primitives/strings at top level or in `object`.

- [Basic types, val and var](https://kotlinlang.org/docs/basic-types.html) — Kotlin docs
- [Properties](https://kotlinlang.org/docs/properties.html) — getters, backing fields, `const`

---

### Data classes

**What to know:** Auto-generates `equals`/`hashCode`/`toString`/`copy`/`componentN`. Use for DTOs and UI state. Not for classes with behaviour or inheritance-heavy models.

- [Data classes](https://kotlinlang.org/docs/data-classes.html) — Kotlin docs

---

### Sealed classes and exhaustive `when`

**What to know:** Restricted class hierarchies — compiler knows all subtypes. Pair with exhaustive `when` (no `else` needed). Ideal for UI state, network results, navigation events.

- [Sealed classes and interfaces](https://kotlinlang.org/docs/sealed-classes.html) — Kotlin docs
- [`when` expressions](https://kotlinlang.org/docs/control-flow.html#when-expressions-and-statements) — exhaustive branches

---

### Extension functions

**What to know:** Add functions to existing types without inheritance. Resolved statically. Common in Kotlin stdlib (`String.trim()`, etc.).

- [Extensions](https://kotlinlang.org/docs/extensions.html) — Kotlin docs

---

### `companion object`

**What to know:** Singleton tied to the class — factory methods, constants, `@JvmStatic` for Java interop. Not a replacement for DI.

- [Object declarations and companion objects](https://kotlinlang.org/docs/object-declarations.html#companion-objects) — Kotlin docs

---

### Higher-order functions and lambdas

**What to know:** Functions that take or return functions. Lambdas `{ x -> x * 2 }`; trailing lambda syntax; `it` for single-param; function types `(Int) -> String`.

- [Higher-order functions and lambdas](https://kotlinlang.org/docs/lambdas.html) — Kotlin docs
- [Function types](https://kotlinlang.org/docs/lambdas.html#function-types) — syntax reference

---

### `inline` and `reified`

**What to know:** `inline` copies bytecode at call site — avoids lambda allocation; required for non-reified type params at runtime. `reified` lets you write `T::class` inside an inline generic function. Used heavily in stdlib (`let`, `apply`, etc.).

- [Inline functions](https://kotlinlang.org/docs/inline-functions.html) — Kotlin docs
- [Reified type parameters](https://kotlinlang.org/docs/inline-functions.html#reified-type-parameters) — `inline` + `reified`

---

### Type inference

**What to know:** Compiler deduces types from RHS or context. Explicit types at public API boundaries; inference fine for locals and chains.

- [Basic syntax — variable declarations](https://kotlinlang.org/docs/basic-syntax.html#variables) — Kotlin docs
- [Generics — type projections](https://kotlinlang.org/docs/generics.html) — when inference hits limits

---

### Scope functions — `let`, `run`, `apply`, `also`, `with`

**What to know:** Same object, different intent:

| Function | Receiver | Returns | Use when |
| --- | --- | --- | --- |
| `let` | `it` | Lambda result | Null-safe transform; chain results |
| `run` | `this` | Lambda result | Configure object + compute result |
| `apply` | `this` | Receiver | Build/configure (builder style) |
| `also` | `it` | Receiver | Side effects (logging) without changing chain |
| `with` | `this` | Lambda result | Non-extension: group calls on one object |

- [Scope functions](https://kotlinlang.org/docs/scope-functions.html) — Kotlin docs (includes decision flowchart)

---

### Collections — `map`, `filter`, `fold`, `groupBy`, `associateBy`

**What to know:** Prefer stdlib over manual loops. Immutable by default; `map` transforms, `filter` selects, `fold`/`reduce` aggregate, `groupBy` → `Map<K, List<V>>`, `associateBy` → `Map<K, V>` (key collisions throw).

- [Collection transformations](https://kotlinlang.org/docs/collection-transformations.html) — map, filter, zip
- [Aggregate operations](https://kotlinlang.org/docs/collection-aggregate.html) — fold, reduce, sum
- [Grouping](https://kotlinlang.org/docs/collection-grouping.html) — groupBy, associateBy, associate
- [Collections overview](https://kotlinlang.org/docs/collections-overview.html) — List vs Set vs Map, mutability

---

### `==` vs `===`

**What to know:** `==` is structural equality (calls `equals`). `===` is referential equality (same instance). For nullable types, `==` handles null safely (`a == b` ≡ null-safe equals).

- [Equality](https://kotlinlang.org/docs/equality.html) — Kotlin docs

---

### Big-O notation and collection complexity

**What to know:** Describe time/space vs input size. Common ops:

| Operation | Array/List | HashMap/HashSet | Notes |
| --- | --- | --- | --- |
| Index access | O(1) | — | |
| Search (unsorted) | O(n) | O(1) avg | |
| Insert (end) | O(1)* | O(1) avg | *amortized for dynamic array |
| Insert (middle) | O(n) | — | shift elements |
| Sort | O(n log n) | — | |

String reverse / palindrome / vowel count → **O(n)** time, **O(1)** extra space if in-place on `CharArray`.

- [Big-O cheat sheet](https://www.bigocheatsheet.com/) — common complexities at a glance
- [Kotlin collections overview](https://kotlinlang.org/docs/collections-overview.html) — read vs modify cost
- [Big O notation (Wikipedia)](https://en.wikipedia.org/wiki/Big_O_notation) — formal definition

---

## Block C — Narrative (today)

Draft: _"Describe the architecture of a project you've worked on — what would you change?"_

Use `account/login-registration`: layers, dependency direction, why. End with **two concrete improvements**.

---

## Deliverable

One-page Kotlin cheat sheet **written from memory** after reading the links above — not copied.

## Block D — Cold recall (tomorrow, 15 min)

From memory, no notes:

1. When do you use `apply` vs `also`?
2. What makes a `when` exhaustive over a sealed class?
3. Time complexity of `groupBy` on a list of size n?
4. Difference between `==` and `===`?
5. Why is `findDuplicate` Floyd's cycle detection O(1) space?
