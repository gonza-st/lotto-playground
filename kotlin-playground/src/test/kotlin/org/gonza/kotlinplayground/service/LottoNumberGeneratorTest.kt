package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.NumberRange
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestNumberRange : NumberRange {
    override fun getMin(): Int {
        return 0
    }

    override fun getMax(): Int {
        return 0
    }
}

class LottoNumberGeneratorTest {
    @Test
    fun `최대값과 최소값을 넘지 않는 값을 생성할 수 있다`() {
        // given
        val testNumberRange = TestNumberRange()
        val lottoNumberGenerator = LottoNumberGenerator(testNumberRange)

        // when
        val result = listOf(
            lottoNumberGenerator.get(),
            lottoNumberGenerator.get(),
            lottoNumberGenerator.get(),
            lottoNumberGenerator.get(),
            lottoNumberGenerator.get(),
            lottoNumberGenerator.get(),
            lottoNumberGenerator.get(),
        )

        // then
        result.forEach {
            assertTrue(it >= testNumberRange.getMin())
            assertTrue(it <= testNumberRange.getMax())
        }
    }
}