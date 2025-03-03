package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.BonusNumberDuplicatedWithWinningLottoTicketException

class BonusNumber(
    winningTicket: LottoTicket,
    number: Int,
) : LottoNumber(number) {
    init {
        validate(winningTicket, number)
    }

    private fun validate(winningTicket: LottoTicket, number: Int) {
        val lottoNumber = LottoNumber(number)
        val result = winningTicket.getMatchedNumber(lottoNumber)

        if (result != null) {
            throw BonusNumberDuplicatedWithWinningLottoTicketException()
        }
    }
}
