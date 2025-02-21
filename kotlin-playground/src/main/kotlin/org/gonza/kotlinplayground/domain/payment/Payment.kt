package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.domain.payment.exception.InvalidPaidMoneyException

class Payment(
    private val paidMoney: Int,
) {
    companion object {
        private const val PRICE = 1_000
    }

    init {
        validate(paidMoney)
    }

    fun getPaidTicketCount() = paidMoney / PRICE

    fun getPaidMoney() = paidMoney

    private fun validate(paidMoney: Int) {
        if (paidMoney <= 0) {
            throw InvalidPaidMoneyException()
        }
    }
}
