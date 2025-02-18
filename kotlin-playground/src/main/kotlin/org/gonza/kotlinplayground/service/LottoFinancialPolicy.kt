package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.payment.Payment

class LottoFinancialPolicy : FinancialPolicy {
    override fun calculateReturnOnInvestment(
        totalInvestment: Payment,
        profitAmount: Payment,
    ): Double = profitAmount.getPaidMoney() / totalInvestment.getPaidMoney()
}
