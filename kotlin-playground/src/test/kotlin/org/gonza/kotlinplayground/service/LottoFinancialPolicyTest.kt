package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.Payment
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class LottoFinancialPolicyTest {
    @Test
    fun `계산된 수익률은 총 투자금액 대비 총 수익금의 백분율이어야 한다`() {
        val lottoFinancialPolicy = LottoFinancialPolicy()
        val totalInvestment = Payment(14000)
        val profitAmount = Payment(5000)

        val result =
            lottoFinancialPolicy.calculateReturnOnInvestment(
                totalInvestment = totalInvestment,
                profitAmount = profitAmount,
            )

        val expect = 0.3571
        assertEquals(result, expect, 0.001)
    }

    @Test
    fun `당첨 로또 티켓과 뽑은 로또 티켓들을 가지고 당첨된 통계를 가져올 수 있다`() {
        val result =
            LottoFinancialPolicyFixture.createLottoTicket(
                listOf(1, 2, 3, 4, 5, 6),
            )
        val ticketList =
            LottoFinancialPolicyFixture.createLottoTicketList(
                listOf(
                    listOf(1, 2, 3, 9, 8, 7),
                    listOf(1, 2, 3, 4, 8, 7),
                    listOf(1, 2, 3, 4, 5, 7),
                    listOf(1, 2, 3, 4, 5, 6),
                ),
            )
        val lottoFinancialPolicy = LottoFinancialPolicy()

        val matchedList =
            lottoFinancialPolicy.getLottoMatchListByResult(
                result = result,
                ticketList = ticketList,
            )

        val expected =
            listOf(
                LottoStatisticsSheet.THREE_MATCHED,
                LottoStatisticsSheet.FOUR_MATCHED,
                LottoStatisticsSheet.FIVE_MATCHED,
                LottoStatisticsSheet.ALL_MATCHED,
            )
        matchedList.forEach {
            assertContains(expected, it)
        }
        assertEquals(matchedList.size, expected.size)
    }
}
