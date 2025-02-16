package org.gonza.kotlinplayground.service

interface FinancialPolicy {
    fun getPrice(): Int

    fun calculateReturnOnInvestment(totalInvestment: Double, profitAmount: Double): Double
}
