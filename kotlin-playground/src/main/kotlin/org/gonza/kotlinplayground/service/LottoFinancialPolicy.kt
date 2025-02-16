package org.gonza.kotlinplayground.service

class LottoFinancialPolicy : FinancialPolicy {
    companion object {
        private const val PRICE = 1000
    }
    override fun getPrice(): Int = PRICE

    override fun calculateReturnOnInvestment(totalInvestment: Double, profitAmount: Double): Double =
        profitAmount / totalInvestment
}
