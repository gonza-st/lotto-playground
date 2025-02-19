package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket

class LottoMatcherFixture {
    companion object {
        fun createLottoTicket(numberList: List<Int>): LottoTicket =
            LottoTicket(
                numberList.map { LottoNumber(it) },
            )

        fun createLottoTicketList(numberList: List<List<Int>>): List<LottoTicket> =
            numberList.map {
                LottoTicket(
                    it.map { LottoNumber(it) },
                )
            }
    }
}
