package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.Lotto
import org.gonza.kotlinplayground.domain.LottoNumber
import org.gonza.kotlinplayground.utils.LottoConstants
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또는 로또 번호를 지정된 개수만큼 가질 수 있다`() {
        val maxLottoNumberCount = LottoConstants.MAX_LOTTO_NUMBER_HAVE_COUNT
        val invalidLottoNumberList = mutableListOf<LottoNumber>()
        repeat(maxLottoNumberCount + 1) {
            invalidLottoNumberList.add(LottoNumber(number = it + 1))
        }
        val validLottoNumberList = mutableListOf<LottoNumber>()
        repeat(maxLottoNumberCount) {
            validLottoNumberList.add(LottoNumber(number = it + 1))
        }

        assertThrows<IllegalArgumentException> {
            Lotto(lottoNumberList = invalidLottoNumberList, limit = maxLottoNumberCount)
        }
        assertDoesNotThrow {
            Lotto(lottoNumberList = validLottoNumberList, limit = maxLottoNumberCount)
        }
    }
}