package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.DuplicatedNumberLottoTicketException
import org.gonza.kotlinplayground.domain.lotto.exception.InvalidSizeLottoTicketException
import org.gonza.kotlinplayground.domain.payment.Payment
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoTicketTest {
    @Test
    fun `로또 티켓은 6개의 숫자를 초과하면 예외가 발생한다`() {
        val lottoNumberList =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
                LottoNumber(7),
            )

        assertThrows(InvalidSizeLottoTicketException::class.java) {
            LottoTicket(lottoNumberList)
        }
    }

    @Test
    fun `로또 티켓은 6개의 숫자 미만이면 예외가 발생한다`() {
        val lottoNumberList =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
            )

        assertThrows(InvalidSizeLottoTicketException::class.java) {
            LottoTicket(lottoNumberList)
        }
    }

    @Test
    fun `로또 티켓은 각 숫자가 중복된다면 예외가 발생한다`() {
        val lottoNumberList =
            listOf(
                LottoNumber(1),
                LottoNumber(1),
                LottoNumber(1),
                LottoNumber(1),
                LottoNumber(1),
                LottoNumber(1),
            )

        assertThrows(DuplicatedNumberLottoTicketException::class.java) {
            LottoTicket(lottoNumberList)
        }
    }

    @Test
    fun `로또 티켓은 비어있다면 예외가 발생한다`() {
        val lottoNumberList = emptyList<LottoNumber>()

        assertThrows(InvalidSizeLottoTicketException::class.java) {
            LottoTicket(lottoNumberList)
        }
    }

    @Test
    fun `로또 티켓은 같은 숫자가 모두 존재하는 티켓이 있다면 같은 로또 티켓이다`() {
        val lottoTicket =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val other =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        assertEquals(lottoTicket, other)
    }

    @Test
    fun `로또 티켓은 다른 로또 티켓과 같은 숫자를 판단할 수 있다`() {
        val lottoTicket =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val other =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(9),
                    LottoNumber(8),
                    LottoNumber(7),
                ),
            )

        val result = lottoTicket.getMatchedNumber(other)

        val expected =
            listOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
            )
        assertEquals(expected, result)
    }

    @Test
    fun `구매한 결제 정보를 통해 구매한 티켓 개수를 알 수 있다`() {
        val payment = Payment(14_000)

        val paidTicketCount = LottoTicket.getPaidTicketCount(payment)
        val expected = 14

        assertEquals(expected, paidTicketCount)
    }
}
