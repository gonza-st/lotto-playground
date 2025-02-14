package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.NumberRange
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OneToNineNumberRange : NumberRange {
    override fun getMin(): Int = 1

    override fun getMax(): Int = 9
}

class LottoGeneratorTest {
    @Test
    fun `생성된 숫자는 중복되지 않는다`() {
        val randomNumberGenerator = RandomNumberGenerator(OneToNineNumberRange())
        val lottoGenerator = LottoGenerator(randomNumberGenerator)

        val result = lottoGenerator.generate()
        val numberList = result.numberList

        val testedList: MutableList<Int> = mutableListOf()
        numberList.forEach {
            assertFalse(it.number in testedList)
            testedList.add(it.number)
        }
    }

    @Test
    fun `생성된 숫자는 비어있을 수 없다`() {
        val randomNumberGenerator = RandomNumberGenerator(OneToNineNumberRange())
        val lottoGenerator = LottoGenerator(randomNumberGenerator)

        val result = lottoGenerator.generate()
        val numberList = result.numberList

        assertFalse(numberList.isNotEmpty())
    }
}
