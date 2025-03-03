package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.BonusLottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.WinningStatistics

class BonusNumberMatcher(
    private val result: LottoTicket,
    private val bonusNumber: LottoNumber,
) : LottoMatcher {
    companion object {
        private val BONUS_STATISTICS_SHEET = BonusLottoStatisticsSheet.BONUS_MATCHED
    }

    override fun getWinningStatistics(purchasedTicketList: List<LottoTicket>): WinningStatistics {
        val matchedStatistics =
            purchasedTicketList.mapNotNull { ticket ->
                findBonusStatistics(ticket)
            }

        return WinningStatistics(
            winningCountList = createWinningCountList(matchedStatistics),
        )
    }

    private fun findBonusStatistics(purchasedTicket: LottoTicket): LottoStatisticsSheet? {
        val matchedLottoNumberList = result.getMatchedNumber(purchasedTicket)

        if (!isBonus(matchedLottoNumberList.size)) return null

        if (!purchasedTicket.isBonus(bonusNumber)) return null

        return BONUS_STATISTICS_SHEET
    }

    private fun createWinningCountList(bonusStatisticsList: List<LottoStatisticsSheet>): List<WinningCount> =
        listOf(
            WinningCount(
                statisticsSheet = BONUS_STATISTICS_SHEET,
                count = bonusStatisticsList.size,
            ),
        )

    private fun isBonus(matchedCount: Int): Boolean = BONUS_STATISTICS_SHEET.isMatched(matchedCount)

    private fun LottoTicket.isBonus(bonusNumber: LottoNumber): Boolean {
        val matchedNumber = this.getMatchedNumber(bonusNumber)

        return matchedNumber != null
    }
}
