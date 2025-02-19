package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoMatch
import org.gonza.kotlinplayground.domain.payment.Payment

interface FinancialPolicy {
    fun calculateReturnOnInvestment(
        totalInvestment: Payment,
        profitAmount: Payment,
    ): Double

    fun getLottoMatchListByResult(
        result: LottoTicket,
        ticketList: List<LottoTicket>,
    ): List<LottoMatch>
}
