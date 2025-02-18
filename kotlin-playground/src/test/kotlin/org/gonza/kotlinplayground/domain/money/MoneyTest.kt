package org.gonza.kotlinplayground.domain.money

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `구입한 로또 가격을 입력하면 구입된 로또 티켓의 개수를 알 수 있다`() {
        val money = Money(14000)

        val result = money.getPaidTicketCount()

        val expected = 14
        assertEquals(expected, result)
    }
}
