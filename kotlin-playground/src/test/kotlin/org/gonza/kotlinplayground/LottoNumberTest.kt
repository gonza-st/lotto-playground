package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.LottoNumber
import org.gonza.kotlinplayground.utils.LottoConstants
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoNumberTest {
    @Test
    fun `로또 번호는 양의 정수여야 한다`() {
        val invalidNumber = -1
        val validNumber = 17

        assertThrows<IllegalArgumentException> {
            LottoNumber(number = invalidNumber)
        }
        assertDoesNotThrow { LottoNumber(number = validNumber) }
    }

    @Test
    fun `로또 번호는 지정된 번호를 넘을 수 없다`() {
        val limit = LottoConstants.LOTTO_NUMBER_RANGE.last
        val invalidNumber = 46
        val validNumber = 45

        assertThrows<IllegalArgumentException> {
            LottoNumber(number = invalidNumber, limit = limit)
        }
        assertDoesNotThrow { LottoNumber(number = validNumber, limit = limit) }
    }
}