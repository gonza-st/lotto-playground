package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.Amount
import org.gonza.kotlinplayground.domain.Purchase
import org.gonza.kotlinplayground.utils.LottoConstants
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PurchaseTest {
    @Test
    fun `구매 가능한 로또 매수를 알 수 있다`() {
        val amount = Amount(total = 14000)
        val purchase = Purchase(amount = amount)
        val expectedPaper = amount.total / LottoConstants.LOTTO_PRICE

        assertEquals(expectedPaper, purchase.totalPaper)
    }
}