package org.gonza.kotlinplayground.service

interface FinancialPolicy {
    fun calculateReturnOnInvestment(
        totalInvestment: Double,
        profitAmount: Double,
    ): Double
}
