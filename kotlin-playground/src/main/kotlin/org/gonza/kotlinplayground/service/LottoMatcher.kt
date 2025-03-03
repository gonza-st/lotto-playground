package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.WinningStatistics

interface LottoMatcher {
    fun getWinningStatistics(purchasedTicketList: List<LottoTicket>): WinningStatistics
}
