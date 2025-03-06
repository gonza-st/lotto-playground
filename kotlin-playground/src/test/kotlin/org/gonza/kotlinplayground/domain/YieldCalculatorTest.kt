package org.gonza.kotlinplayground.domain

import org.assertj.core.api.Assertions
import org.gonza.kotlinplayground.dto.MatchResultDto
import org.junit.jupiter.api.Test

class YieldCalculatorTest {
    @Test
    fun `수익률을 계산한다`() {
        val calculator = YieldCalculator()
        val cost = 14000
        val matchResultDto = MatchResultDto(fifth = 1, fourth = 0, third = 0, second = 0, winner = 0)

        val result: Double = calculator.yield(cost = cost, matchResult = matchResultDto)

        Assertions.assertThat(result).isEqualTo(0.35)
    }
}
