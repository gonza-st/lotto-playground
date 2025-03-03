package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.BonusNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.WinningStatistics

class BonusNumberMatcher(
    private val result: LottoTicket,
    private val bonusNumber: BonusNumber
) : LottoMatcher {
    override fun getWinningStatistics(purchasedTicketList: List<LottoTicket>): WinningStatistics {
        TODO("Not yet implemented")
    }
}
