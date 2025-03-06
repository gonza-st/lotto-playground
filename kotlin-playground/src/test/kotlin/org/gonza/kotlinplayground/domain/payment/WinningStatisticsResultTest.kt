package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.service.WinningInfo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WinningStatisticsResultTest {
    @Test
    fun `총 당첨 금액을 올바르게 계산해야 한다`() {
        val threeMatchedCount = 2
        val fourMatchedCount = 1

        val threeMatchedPrize = GeneralLottoStatisticsSheet.THREE_MATCHED.getAmount() * threeMatchedCount
        val fourMatchedPrize = GeneralLottoStatisticsSheet.FOUR_MATCHED.getAmount() * fourMatchedCount
        val expectedTotalPrize = Payment(threeMatchedPrize + fourMatchedPrize)

        val winningInfoLists =
            listOf(
                WinningInfo(GeneralLottoStatisticsSheet.THREE_MATCHED, threeMatchedCount),
                WinningInfo(GeneralLottoStatisticsSheet.FOUR_MATCHED, fourMatchedCount),
            )
        val winningStatisticsResult = WinningStatisticsResult(winningInfoLists)

        val totalPrizeMoney = winningStatisticsResult.totalPrizePayment()!!
        val result = totalPrizeMoney.getPaidMoney()

        val expected = expectedTotalPrize.getPaidMoney()
        assertEquals(expected, result)
    }

    @Test
    fun `당첨 통계를 올바르게 반환해야 한다`() {
        val winningInfoLists =
            listOf(
                WinningInfo(GeneralLottoStatisticsSheet.THREE_MATCHED, 1),
                WinningInfo(GeneralLottoStatisticsSheet.FIVE_MATCHED, 1),
            )
        val winningStatisticsResult = WinningStatisticsResult(winningInfoLists)

        val result = winningStatisticsResult.getWinningStatisticsList()

        assertEquals(winningInfoLists, result)
    }

    @Test
    fun `당첨 정보 목록을 추가할 수 있다`() {
        val size = 1
        val given1 =
            WinningStatisticsResult(
                winningInfoList = listOf(WinningInfo(GeneralLottoStatisticsSheet.THREE_MATCHED, size)),
            )
        val given2 =
            WinningStatisticsResult(
                winningInfoList = listOf(),
            )

        val result = given1.add(given2)
        val expectedSize = 1

        assertEquals(result.getWinningStatisticsList().size, expectedSize)
    }

    @Test
    fun `당첨 되지 않았다면 총 보상금액은 null 이다`() {
        val matchedZero =
            WinningStatisticsResult(
                winningInfoList = emptyList(),
            )

        val totalPrizePayment = matchedZero.totalPrizePayment()

        assertNull(totalPrizePayment)
    }

    @Test
    fun `당첨통계 정보를 입력하면 해당하는 당첨된 당첨통계결과를 찾을 수 있다`() {
        val matchedThree =
            WinningStatisticsResult(
                winningInfoList =
                    listOf(
                        WinningInfo(GeneralLottoStatisticsSheet.THREE_MATCHED, 1),
                    ),
            )

        val result = matchedThree.findWinningInfoBy(GeneralLottoStatisticsSheet.THREE_MATCHED)!!

        assertEquals(result.statisticsSheet.getMatchedCount(), GeneralLottoStatisticsSheet.THREE_MATCHED.getMatchedCount())
        assertEquals(result.statisticsSheet.getAmount(), GeneralLottoStatisticsSheet.THREE_MATCHED.getAmount())
    }
}
