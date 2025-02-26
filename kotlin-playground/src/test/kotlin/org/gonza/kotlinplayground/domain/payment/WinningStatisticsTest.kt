package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.service.WinningCount
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WinningStatisticsTest {
    @Test
    fun `총 당첨 금액을 올바르게 계산해야 한다`() {
        val threeMatchedCount = 2
        val fourMatchedCount = 1

        val threeMatchedPrize = LottoStatisticsSheet.THREE_MATCHED.amount * threeMatchedCount
        val fourMatchedPrize = LottoStatisticsSheet.FOUR_MATCHED.amount * fourMatchedCount
        val expectedTotalPrize = Payment(threeMatchedPrize + fourMatchedPrize)

        val winningCountList = listOf(
            WinningCount(LottoStatisticsSheet.THREE_MATCHED, threeMatchedCount),
            WinningCount(LottoStatisticsSheet.FOUR_MATCHED, fourMatchedCount),
        )
        val winningStatistics = WinningStatistics(winningCountList)

        val totalPrizeMoney = winningStatistics.totalPrizePayment()
        val result = totalPrizeMoney.getPaidMoney()

        val expected = expectedTotalPrize.getPaidMoney()
        assertEquals(expected, result)
    }

    @Test
    fun `당첨 통계를 올바르게 반환해야 한다`() {
        val winningCountList = listOf(
            WinningCount(LottoStatisticsSheet.THREE_MATCHED, 1),
            WinningCount(LottoStatisticsSheet.FIVE_MATCHED, 1)
        )
        val winningStatistics = WinningStatistics(winningCountList)

        val result = winningStatistics.getWinningStatisticsList()

        assertEquals(winningCountList, result)
    }
}
