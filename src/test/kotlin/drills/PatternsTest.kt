package drills

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class PatternsTest : StringSpec({

    "ConnectionPool returns the same instance on repeated calls" {
        val first = ConnectionPool.getInstance(10)
        val second = ConnectionPool.getInstance(20)
        second shouldBeSameInstanceAs first
    }

    "ConnectionPool keeps the size from the first construction" {
        ConnectionPool.getInstance(10).size shouldBe ConnectionPool.getInstance(99).size
    }

    "ConnectionPool creates exactly one instance under concurrent access" {
        val threads = 32
        val pool = Executors.newFixedThreadPool(threads)
        val results = java.util.Collections.synchronizedList(mutableListOf<ConnectionPool>())
        repeat(threads) {
            pool.submit { results.add(ConnectionPool.getInstance(5)) }
        }
        pool.shutdown()
        pool.awaitTermination(5, TimeUnit.SECONDS) shouldBe true
        results.distinctBy { System.identityHashCode(it) }.size shouldBe 1
    }

    "PaymentMethodFactory builds a card with its last four digits" {
        val method = PaymentMethodFactory.create("card", last4 = "4242")
        method shouldBe PaymentMethod.Card("4242")
        method.displayName shouldBe "Card ending 4242"
    }

    "PaymentMethodFactory builds the parameterless methods" {
        PaymentMethodFactory.create("googlepay") shouldBe PaymentMethod.GooglePay
        PaymentMethodFactory.create("giftcard") shouldBe PaymentMethod.GiftCard
    }

    "PaymentMethodFactory rejects an unknown type" {
        shouldThrow<IllegalArgumentException> {
            PaymentMethodFactory.create("crypto")
        }
    }

    "AppConfig is a single shared instance" {
        AppConfig.environment = "staging"
        AppConfig.environment shouldBe "staging"
        AppConfig.environment shouldNotBe "prod"
        AppConfig.environment = "prod"
    }
})
