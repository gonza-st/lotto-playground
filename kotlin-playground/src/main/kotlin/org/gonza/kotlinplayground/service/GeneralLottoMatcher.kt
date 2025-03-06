package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.GeneralLottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.WinningStatisticsResult

class GeneralLottoMatcher(
    private val result: LottoTicket,
) : LottoMatcher {
    override fun getWinningStatistics(purchasedTicketList: List<LottoTicket>): WinningStatisticsResult {
        val matchedStatistics =
            purchasedTicketList.mapNotNull { ticket ->
                val matchedLottoNumberList = result.getMatchedNumber(ticket)
                GeneralLottoStatisticsSheet.findByMatchedCount(matchedLottoNumberList.size)
            }

        val winningCountList = getWinningCountList(matchedStatistics)

        return WinningStatisticsResult(
            winningInfoList = winningCountList,
        )
    }

    private fun getWinningCountList(matchedStatistics: List<LottoStatisticsSheet>): List<WinningInfo> {
        if (matchedStatistics.isEmpty()) return emptyList()

        val groupedWonStatistics = matchedStatistics.groupingBy { it }

        return groupedWonStatistics
            .eachCount()
            .map { (sheet, count) ->
                WinningInfo(sheet, count)
            }
    }
}
