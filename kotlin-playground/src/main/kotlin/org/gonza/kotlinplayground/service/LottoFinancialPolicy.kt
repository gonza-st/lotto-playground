package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoMatch
import org.gonza.kotlinplayground.domain.payment.Payment

class LottoFinancialPolicy : FinancialPolicy {
    override fun calculateReturnOnInvestment(
        totalInvestment: Payment,
        profitAmount: Payment,
    ): Double = profitAmount.getPaidMoney() / totalInvestment.getPaidMoney()

    override fun getLottoMatchListByResult(
        result: LottoTicket,
        ticketList: List<LottoTicket>,
    ): List<LottoMatch> =
        ticketList.mapNotNull {
            LottoMatch.matchWith(result, it)
        }
}
