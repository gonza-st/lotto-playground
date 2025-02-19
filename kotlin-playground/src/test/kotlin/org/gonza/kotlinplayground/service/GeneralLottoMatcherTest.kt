package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GeneralLottoMatcherTest {
    @Test
    fun `당첨 로또 와 뽑은 로또를 입력하면 통계 시트내에서 맞춘 통계 시트 목록을 알 수 있다`() {
        val result =
            LottoMatcherFixture.createLottoTicket(
                listOf(1, 2, 3, 4, 5, 6),
            )
        val ticketList =
            LottoMatcherFixture.createLottoTicketList(
                listOf(
                    listOf(1, 2, 3, 7, 8, 9),
                    listOf(1, 2, 3, 4, 8, 9),
                    listOf(1, 2, 3, 4, 5, 9),
                    listOf(1, 2, 3, 4, 5, 6),
                ),
            )
        val lottoMatcher = GeneralLottoMatcher()

        val statisticSheetList =
            ticketList.map { ticket ->
                lottoMatcher.getMatchedStatisticSheetList(
                    result = result,
                    ticket = ticket,
                )
            }

        val expected =
            listOf(
                LottoStatisticsSheet.THREE_MATCHED,
                LottoStatisticsSheet.FOUR_MATCHED,
                LottoStatisticsSheet.FIVE_MATCHED,
                LottoStatisticsSheet.ALL_MATCHED,
            )
        assertEquals(expected, statisticSheetList)
        assertEquals(statisticSheetList.size, expected.size)
    }

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

        val lottoMatcher = GeneralLottoMatcher()

        val winningStatistics = lottoMatcher.getWinningStatistics(winningTicket, purchasedTickets)

        val expectedResults = mapOf(
            LottoStatisticsSheet.THREE_MATCHED to 1,
            LottoStatisticsSheet.FOUR_MATCHED to 1,
            LottoStatisticsSheet.FIVE_MATCHED to 1
        )
        assertEquals(expectedResults, winningStatistics.getStatistics())
    }
}
