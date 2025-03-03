package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GeneralLottoMatcherTest {
    @Test
    fun `로또 당첨 결과를 올바르게 계산해야 한다`() {
        val winningTicket = LottoMatcherFixture.createLottoTicket(
            listOf(1, 2, 3, 4, 5, 6)
        )
        val purchasedTickets =
            LottoMatcherFixture.createLottoTicketList(
                listOf(
                    listOf(1, 2, 3, 7, 8, 9),
                    listOf(1, 2, 3, 4, 8, 9),
                    listOf(1, 2, 3, 4, 5, 9),
                ),
            )

        val lottoMatcher = GeneralLottoMatcher(winningTicket)

        val winningStatistics = lottoMatcher.getWinningStatistics(purchasedTickets)

        val expectedResultList = listOf(
            WinningCount(LottoStatisticsSheet.THREE_MATCHED, 1),
            WinningCount(LottoStatisticsSheet.FOUR_MATCHED, 1),
            WinningCount(LottoStatisticsSheet.FIVE_MATCHED, 1),
        )
        val result = winningStatistics.getWinningStatisticsList()
        result.forEach {
            assertTrue(expectedResultList.contains(it))
        }
    }
}
