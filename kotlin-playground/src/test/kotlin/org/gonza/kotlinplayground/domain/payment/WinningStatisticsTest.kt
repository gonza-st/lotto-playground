package org.gonza.kotlinplayground.domain.payment

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

        val statistics =
            mapOf(
                LottoStatisticsSheet.THREE_MATCHED to threeMatchedCount,
                LottoStatisticsSheet.FOUR_MATCHED to fourMatchedCount,
            )
        val winningStatistics = WinningStatistics(statistics)

        val totalPrizeMoney = winningStatistics.totalPrizePayment()
        val result = totalPrizeMoney.getPaidMoney()

        val expected = expectedTotalPrize.getPaidMoney()
        assertEquals(expected, result)
    }

    @Test
    fun `당첨 통계를 올바르게 반환해야 한다`() {
        val statistics =
            mapOf(
                LottoStatisticsSheet.THREE_MATCHED to 1,
                LottoStatisticsSheet.FIVE_MATCHED to 1,
            )
        val winningStatistics = WinningStatistics(statistics)

        val result = winningStatistics.getStatistics()

        assertEquals(statistics, result)
    }
}
