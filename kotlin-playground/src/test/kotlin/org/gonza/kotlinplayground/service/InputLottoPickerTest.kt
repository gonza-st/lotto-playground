package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.service.exception.InvalidInputFormatException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InputLottoPickerTest {
    @Test
    fun `사용자가 당첨번호를 입력시 쉼표를 통해 로또 번호를 가져올 수 있다`() {
        val input = "1, 2, 3, 4, 5, 6"
        val inputLottoPicker = InputLottoPicker(input)

        val result = inputLottoPicker.pick()

        val expected =
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
        assertEquals(expected, result)
    }

    @Test
    fun `사용자가 당첨번호를 올바르지 않게 입력하면 예외가 발생한다`() {
        val input = "1|2|3|4|5|6"

        assertThrows(InvalidInputFormatException::class.java) {
            InputLottoPicker(input)
        }
    }
}
