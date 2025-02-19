package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet

class GeneralLottoMatcher : LottoMatcher {
    override fun getMatchedStatisticSheetList(
        result: LottoTicket,
        ticket: LottoTicket,
    ): LottoStatisticsSheet = LottoStatisticsSheet.THREE_MATCHED
}
