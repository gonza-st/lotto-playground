package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.Amount
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

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
            Amount(total = -1000)
        }
    }
}