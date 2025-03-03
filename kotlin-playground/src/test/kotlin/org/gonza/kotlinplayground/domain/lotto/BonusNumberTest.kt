package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.BonusNumberDuplicatedWithWinningLottoTicketException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BonusNumberTest {
    @Test
    fun `보너스 숫자는 당첨 로또 티켓의 숫자와 중복될 수 없다`() {
        val winningTicket = LottoTicket(
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        )
        val duplicatedNumber = 6

        assertThrows(BonusNumberDuplicatedWithWinningLottoTicketException::class.java) {
            BonusNumber(winningTicket, duplicatedNumber)
        }
    }
}
