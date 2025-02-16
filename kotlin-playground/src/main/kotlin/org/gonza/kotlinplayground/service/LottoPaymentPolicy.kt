package org.gonza.kotlinplayground.service

class LottoPaymentPolicy : PaymentPolicy {
    companion object {
        private const val PRICE = 1000
    }
    override fun getLottoPrice(): Int = PRICE

    override fun calculateReturnOnInvestment(totalInvestment: Double, profitAmount: Double): Double =
        profitAmount / totalInvestment
}
