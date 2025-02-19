package org.gonza.kotlinplayground.domain.payment

class Payment(
    private val paidMoney: Int,
) {
    companion object {
        private const val PRICE = 1_000
    }

    fun getPaidTicketCount() = paidMoney / PRICE

    fun getPaidMoney() = paidMoney
}
