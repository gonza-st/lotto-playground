package org.gonza.kotlinplayground.domain.money

class Money(
    private val paidMoney: Int,
) {
    companion object {
        private const val PRICE = 1_000
    }

    fun getPaidTicketCount() = paidMoney / PRICE
}
