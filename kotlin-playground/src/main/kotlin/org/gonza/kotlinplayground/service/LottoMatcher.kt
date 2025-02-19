package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet

interface LottoMatcher {
    fun getMatchedStatisticSheetList(
        result: LottoTicket,
        ticket: LottoTicket,
    ): LottoStatisticsSheet?
}
