package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.domain.payment.exception.InvalidPaidMoneyException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PaymentTest {
    @Test
    fun `구입한 로또 가격을 입력하면 구입된 로또 티켓의 개수를 알 수 있다`() {
        val payment = Payment(14000)

        val result = payment.getPaidTicketCount()

        val expected = 14
        assertEquals(expected, result)
    }

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
