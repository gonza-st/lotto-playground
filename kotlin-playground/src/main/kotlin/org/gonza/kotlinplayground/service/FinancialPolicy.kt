package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.payment.Payment

interface FinancialPolicy {
    fun calculateReturnOnInvestment(
        totalInvestment: Payment,
        profitAmount: Payment,
    ): Double
}
