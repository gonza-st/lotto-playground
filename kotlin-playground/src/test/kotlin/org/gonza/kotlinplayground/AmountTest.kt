package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.Amount
import org.gonza.kotlinplayground.utils.LottoConstants
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class AmountTest {
    @Test
    fun `금액은 양의 정수를 가질 수 있다`() {
        assertDoesNotThrow {
            Amount(total = 14000)
        }
    }

    @Test
    fun `금액이 0원일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            Amount(total = 0)
        }
    }

    @Test
    fun `금액이 음수일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            Amount(total = -1)
        }
    }

    @Test
    fun `구매할 수 있는 로또 매수를 가진다`() {
        val amount = Amount(total = 14000)
        val expectedCount = amount.total / LottoConstants.LOTTO_PRICE

        assertEquals(expectedCount, amount.maxPurchase)
    }
}