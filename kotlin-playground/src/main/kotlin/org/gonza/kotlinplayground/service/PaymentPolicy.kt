package org.gonza.kotlinplayground.service

interface PaymentPolicy {
    fun getLottoPrice(): Int

    fun calculateReturnOnInvestment(totalInvestment: Double, profitAmount: Double): Double
}
