package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet

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
}
