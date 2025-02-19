package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet
import org.gonza.kotlinplayground.domain.payment.Payment

class LottoFinancialPolicy : FinancialPolicy {
    override fun calculateReturnOnInvestment(
        totalInvestment: Payment,
        profitAmount: Payment,
    ): Double = profitAmount.getPaidMoney().toDouble() / totalInvestment.getPaidMoney()

    override fun getLottoMatchListByResult(
        result: LottoTicket,
        ticketList: List<LottoTicket>,
    ): List<LottoStatisticsSheet> =
        ticketList.mapNotNull {
            LottoStatisticsSheet.matchWith(result, it)
        }
}
