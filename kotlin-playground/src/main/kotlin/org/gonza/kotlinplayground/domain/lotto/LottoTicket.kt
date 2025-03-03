package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.DuplicatedNumberLottoTicketException
import org.gonza.kotlinplayground.domain.lotto.exception.InvalidSizeLottoTicketException
import org.gonza.kotlinplayground.domain.payment.Payment

class LottoTicket(
    private val lottoNumberList: List<LottoNumber>,
) {
    companion object {
        const val MAX_LENGTH = 6
        private const val PRICE = 1_000

        fun getPaidTicketCount(payment: Payment) = payment.getPaidMoney() / PRICE
    }

    init {
        if (lottoNumberList.isEmpty()) {
            throw InvalidSizeLottoTicketException()
        }

        if (lottoNumberList.size != MAX_LENGTH) {
            throw InvalidSizeLottoTicketException()
        }

        if (lottoNumberList.toSet().size != MAX_LENGTH) {
            throw DuplicatedNumberLottoTicketException()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is LottoTicket) return false
        val otherNumberList = other.lottoNumberList
        return lottoNumberList.containsAll(otherNumberList)
    }

    override fun hashCode(): Int = lottoNumberList.hashCode()

    fun getMatchedNumber(other: LottoTicket): List<LottoNumber> =
        other.lottoNumberList.mapNotNull { getMatchedNumber(it) }

    fun getMatchedNumber(number: LottoNumber): LottoNumber? =
        lottoNumberList.find { it == number }
}
