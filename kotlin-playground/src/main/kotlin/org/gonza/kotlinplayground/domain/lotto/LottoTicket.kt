package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.DuplicatedNumberLottoTicketException
import org.gonza.kotlinplayground.domain.lotto.exception.InvalidSizeLottoTicketException

class LottoTicket(
    private val lottoNumberList: List<LottoNumber>,
) {
    companion object {
        private const val MAX_LENGTH = 6
    }

    init {
        if (lottoNumberList.size != MAX_LENGTH) {
            throw InvalidSizeLottoTicketException()
        }

        if (lottoNumberList.toSet().size != MAX_LENGTH) {
            throw DuplicatedNumberLottoTicketException()
        }
    }
}
