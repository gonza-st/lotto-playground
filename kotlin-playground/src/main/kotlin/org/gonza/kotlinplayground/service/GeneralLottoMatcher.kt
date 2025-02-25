package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.WinningStatistics

class GeneralLottoMatcher : LottoMatcher {
    override fun getMatchedStatisticSheetList(
        result: LottoTicket,
        ticket: LottoTicket,
    ): LottoStatisticsSheet? {
        val matchedNumberList = result.getMatchedNumber(ticket)
        val allStatisticSheetList = LottoStatisticsSheet.sortedByDescending()
        return allStatisticSheetList.find {
            it.isMatched(matchedNumberList.size)
        }
    }

    override fun getWinningStatistics(result: LottoTicket, purchasedTicketList: List<LottoTicket>): WinningStatistics {
        val rankedWinningCountByStatistics = purchasedTicketList
            .mapNotNull { getMatchedStatisticSheetList(result, it) }
            .groupingBy { it }
            .eachCount()

        return WinningStatistics(
            statistics = rankedWinningCountByStatistics,
            winningCountList = listOf(WinningCount(LottoStatisticsSheet.ALL_MATCHED, 0))
        )
    }
}
