package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.domain.payment.exception.InvalidPaidMoneyException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PaymentTest {
    @Test
    fun `결제한 금액이 음수라면 예외가 발생한다`() {
        val paidMoney = -1

        assertThrows(InvalidPaidMoneyException::class.java) {
            Payment(paidMoney)
        }
    }

    @Test
    fun `결제한 금액이 0이라면 예외가 발생한다`() {
        val paidMoney = 0

        assertThrows(InvalidPaidMoneyException::class.java) {
            Payment(paidMoney)
        }
    }
}
