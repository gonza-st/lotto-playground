package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.Lotto
import org.gonza.kotlinplayground.domain.LottoNumber
import org.gonza.kotlinplayground.domain.LottoTickets
import org.gonza.kotlinplayground.utils.LottoConstants
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LottoTicketsTest {
    @Test
    fun `한 개 이상의 로또를 가질 수 있다`() {
        val validLottoNumberList = mutableListOf<LottoNumber>()
        val validLottoNumberList2 = mutableListOf<LottoNumber>()
        repeat(LottoConstants.MAX_LOTTO_NUMBER_HAVE_COUNT) {
            validLottoNumberList.add(LottoNumber(number = it + 1))
            validLottoNumberList2.add(LottoNumber(number = it + 2))
        }

        val lottoList = listOf(
            Lotto(lottoNumberList = validLottoNumberList),
            Lotto(lottoNumberList = validLottoNumberList2)
        )
        val lottoTickets = LottoTickets(lottoList = lottoList)

        assertEquals(lottoTickets.lottoList, lottoList)
    }

    @Test
    fun `로또의 당첨 결과를 가질 수 있다`() {
        val validLottoNumberList = mutableListOf<LottoNumber>()
        val answer = mutableListOf<LottoNumber>()
        val expectedPrize = 1
        repeat(LottoConstants.MAX_LOTTO_NUMBER_HAVE_COUNT) {
            validLottoNumberList.add(LottoNumber(number = it + 1))
            answer.add(LottoNumber(number = it + 1))
        }
        val lottoList = listOf(Lotto(lottoNumberList = validLottoNumberList))
        val lottoTickets = LottoTickets(lottoList = lottoList)

        val winner = lottoTickets.findWinLotto(answer = answer)

        assertEquals(expectedPrize, winner[6]?.size)
    }
}