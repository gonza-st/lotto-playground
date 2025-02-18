package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.lotto.NumberRange
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OneToSixNumberRange : NumberRange {
    override fun getMin(): Int = 1

    override fun getMax(): Int = 6
}

class LottoGeneratorTest {
    @Test
    fun `원하는 범위 내에 모든 값은 중복될 수 없다`() {
        val randomNumberGenerator = RandomNumberGenerator(OneToSixNumberRange())
        val lottoGenerator = LottoGenerator(randomNumberGenerator)

        val result = lottoGenerator.generate()
        val ticket = result.ticket

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
        assertEquals(expected, ticket)
    }
}
