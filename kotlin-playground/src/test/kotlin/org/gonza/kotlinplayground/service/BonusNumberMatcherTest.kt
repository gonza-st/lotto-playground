package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.BonusNumber
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BonusNumberMatcherTest {
    @Test
    fun `구매한 티켓리스트를 입력하면 보너스에 해당하는 통계정보를 가져올 수 있다`() {
        val winningTicket = LottoMatcherFixture.createLottoTicket(listOf(1, 2, 3, 4, 5, 6))
        val number = 7
        val bonusNumber = BonusNumber(winningTicket, number)
        val bonusNumberMatcher = BonusNumberMatcher(winningTicket, bonusNumber)
        val purchasedTicketList =
            LottoMatcherFixture.createLottoTicketList(
                listOf(
                    listOf(1, 2, 3, 4, 5, 7),
                ),
            )

        val bonusStatistics = bonusNumberMatcher.getWinningStatistics(purchasedTicketList)
        val prizeResult = bonusStatistics.totalPrizePayment()
        val result = bonusStatistics.getWinningStatisticsList().first()

        assertEquals(prizeResult.getPaidMoney(), LottoStatisticsSheet.BONUS_MATCHED.amount)
        assertEquals(result.statisticsSheet.matchedCount, LottoStatisticsSheet.BONUS_MATCHED.matchedCount)
    }
}
