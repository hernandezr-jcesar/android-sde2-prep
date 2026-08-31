package drills

/**
 * Singleton and Factory -- the two patterns named in the SDE II guide.
 *
 * In Kotlin the Singleton question is a trap, because `object` gives it to you for
 * free. The interviewer wants to know whether you understand what `object` is doing
 * (lazy, thread-safe class initialisation by the JVM) and when it stops being the
 * right tool: when the instance needs constructor parameters, when it needs to be
 * replaced in tests, or when it holds a Context and leaks it.
 *
 * Have the opinion ready: on Android, most "singletons" should be an ordinary class
 * with an @Singleton Hilt binding, not an `object`. Same lifetime, but injectable
 * and therefore testable. That answer connects the pattern question to the DI
 * question and to your real work.
 */

/** The idiomatic Kotlin singleton. Lazy and thread-safe by JVM class-init guarantees. */
object AppConfig {
    var environment: String = "prod"
}

/**
 * A parameterised singleton, for when `object` cannot work because construction
 * needs arguments.
 *
 * Implement double-checked locking: read the @Volatile field, and only if it is
 * null enter a synchronized block and check again. Explain why both the `@Volatile`
 * and the second check are required -- that is the actual question.
 */
class ConnectionPool private constructor(val size: Int) {
    companion object {
        @Volatile
        private var instance: ConnectionPool? = null

        fun getInstance(size: Int): ConnectionPool =
            TODO("Double-checked locking over the @Volatile instance field")
    }
}

/** Products for the factory drill. */
sealed interface PaymentMethod {
    val displayName: String

    data class Card(val last4: String) : PaymentMethod {
        override val displayName = "Card ending $last4"
    }

    data object GooglePay : PaymentMethod {
        override val displayName = "Google Pay"
    }

    data object GiftCard : PaymentMethod {
        override val displayName = "Gift Card"
    }
}

/**
 * Factory: map an opaque input (an API string, a feature flag, a deep link) onto a
 * concrete type, so callers depend on the [PaymentMethod] abstraction rather than
 * on the construction rules.
 *
 * Throw [IllegalArgumentException] for an unknown type. Be ready for the follow-up
 * "why not just a `when` at the call site?" -- because then every call site repeats
 * the mapping, and adding a payment method means editing all of them. That is the
 * Open/Closed principle, which is also on the SOLID question list.
 */
object PaymentMethodFactory {
    fun create(type: String, last4: String? = null): PaymentMethod =
        TODO("Map the type string onto a PaymentMethod; reject unknown types")
}
