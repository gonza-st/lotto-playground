package org.gonza.kotlinplayground.service

class LottoFinancialPolicy : FinancialPolicy {
    override fun calculateReturnOnInvestment(
        totalInvestment: Double,
        profitAmount: Double,
    ): Double = profitAmount / totalInvestment
}
