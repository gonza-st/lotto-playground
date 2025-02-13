package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.utils.LottoConstants
import org.gonza.kotlinplayground.utils.LottoNumberGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertTrue

class LottoNumberGeneratorTest {
    @Test
    fun `범위 내의 숫자로 로또 번호를 생성할 수 있다`() {
        val validNumberList = LottoConstants.LOTTO_NUMBER_RANGE.toList()
        val take = 6
        val result = LottoNumberGenerator.generate(range = validNumberList, take = take)

        assertTrue { validNumberList.containsAll(result) }
        assertTrue { result.size == take }
    }

    @Test
    fun `범위를 초과하는 로또번호를 생성할 경우 예외가 발생한다`() {
        val validNumberList = LottoConstants.LOTTO_NUMBER_RANGE.toList()
        val invalidTake = 46

        assertThrows<IllegalArgumentException> {
            LottoNumberGenerator.generate(
                range = validNumberList,
                take = invalidTake
            )
        }
    }
}