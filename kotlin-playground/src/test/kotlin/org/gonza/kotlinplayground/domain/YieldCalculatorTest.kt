package org.gonza.kotlinplayground.domain

import org.assertj.core.api.Assertions
import org.gonza.kotlinplayground.dto.MatchResultDto
import org.junit.jupiter.api.Test

class YieldCalculatorTest {
    @Test
    fun `수익률을 계산한다`() {
        val calculator = YieldCalculator()
        val cost = 14000
        val matchResultDto = MatchResultDto(three = 1, four = 0, five = 0, six = 0)

        val result: Double = calculator.yield(cost = cost, matchResult = matchResultDto)

        Assertions.assertThat(result).isEqualTo(0.35)
    }
}
