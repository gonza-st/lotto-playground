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
        val matchedStatistics = purchasedTicketList.mapNotNull { ticket ->
            getMatchedStatisticSheetList(ticket, result)
        }

        val winningCountList = getWinningCountList(matchedStatistics)

        return WinningStatistics(
            winningCountList = winningCountList
        )
    }

    private fun getWinningCountList(matchedStatistics: List<LottoStatisticsSheet>): List<WinningCount> {
        val groupedWonStatistics = matchedStatistics.groupingBy { it }

        return groupedWonStatistics.eachCount()
            .map { (sheet, count) ->
                WinningCount(sheet, count)
            }
    }
}
