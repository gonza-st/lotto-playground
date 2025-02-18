package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.InvalidLottoNumberException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoNumberTest {
    @Test
    fun `번호는 음수일 수 없다`() {
        assertThrows(InvalidLottoNumberException::class.java) {
            LottoNumber(-1)
        }
    }

    @Test
    fun `번호는 45를 초과할 수 없다`() {
        assertThrows(InvalidLottoNumberException::class.java) {
            LottoNumber(46)
        }
    }

    @Test
    fun `번호는 값이 같다면 같은 번호이다`() {
        val number = LottoNumber(1)
        val other = LottoNumber(1)

        assertEquals(number, other)
    }
}
