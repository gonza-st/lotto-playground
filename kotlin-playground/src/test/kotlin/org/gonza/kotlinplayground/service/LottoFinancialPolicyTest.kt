package org.gonza.kotlinplayground.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoFinancialPolicyTest {
    @Test
    fun `계산된 수익률은 총 투자금액 대비 총 수익금의 백분율이어야 한다`() {
        val lottoFinancialPolicy = LottoFinancialPolicy()
        val totalInvestment = 14000.0
        val profitAmount = 5000.0

        val result = lottoFinancialPolicy.calculateReturnOnInvestment(
            totalInvestment = totalInvestment,
            profitAmount = profitAmount
        )

        val expect = 0.3571
        assertEquals(result, expect, 0.001)
    }
}

