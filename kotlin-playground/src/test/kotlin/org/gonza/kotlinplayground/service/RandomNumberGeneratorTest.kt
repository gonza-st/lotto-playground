package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.NumberRange
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestNumberRange : NumberRange {
    override fun getMin(): Int = 0

    override fun getMax(): Int = 0
}

class RandomNumberGeneratorTest {
    @Test
    fun `최대값과 최소값을 넘지 않는 값을 생성할 수 있다`() {
        val testNumberRange = TestNumberRange()
        val randomNumberGenerator = RandomNumberGenerator(testNumberRange)

        val result =
            listOf(
                randomNumberGenerator.get(),
                randomNumberGenerator.get(),
                randomNumberGenerator.get(),
                randomNumberGenerator.get(),
                randomNumberGenerator.get(),
                randomNumberGenerator.get(),
                randomNumberGenerator.get(),
            )

        result.forEach {
            assertTrue(it >= testNumberRange.getMin())
            assertTrue(it <= testNumberRange.getMax())
        }
    }
}
