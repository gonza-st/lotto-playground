package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.WinningStatisticsResult

interface LottoMatcher {
    fun getWinningStatistics(purchasedTicketList: List<LottoTicket>): WinningStatisticsResult
}
