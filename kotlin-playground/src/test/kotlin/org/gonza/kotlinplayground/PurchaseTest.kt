package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.Amount
import org.gonza.kotlinplayground.domain.Manual
import org.gonza.kotlinplayground.domain.Purchase
import org.gonza.kotlinplayground.utils.LottoConstants
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PurchaseTest {
    @Test
    fun `구매 가능한 로또 매수를 알 수 있다`() {
        val amount = Amount(total = 14000)
        val manual = Manual(count = 0)
        val purchase = Purchase(amount = amount, manual = manual)
        val expectedPaper = amount.total / LottoConstants.LOTTO_PRICE

        assertEquals(expectedPaper, purchase.totalPaper)
    }

    @Test
    fun `수동과 자동 로또 구매 매수를 알 수 있다`() {
        val amount = Amount(total = 14000)
        val manual = Manual(count = 3)
        val expectedAutoCount = 11
        val expectedTotalCount = 14
        val purchase = Purchase(amount = amount, manual = manual)

        assertEquals(manual.count, purchase.manualCount)
        assertEquals(expectedAutoCount, purchase.autoCount)
        assertEquals(expectedTotalCount, purchase.totalPaper)
    }
}