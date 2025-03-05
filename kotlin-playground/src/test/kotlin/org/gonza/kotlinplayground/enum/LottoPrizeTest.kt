package org.gonza.kotlinplayground.enum

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoPrizeTest {
    @ParameterizedTest
    @CsvSource(
        "FIFTH, 3",
        "FOURTH, 2",
        "THIRD, 1",
        "SECOND, 1",
        "WINNER, 1",
    )
    fun `문자열 키와 갯수를 받아서 상금을 계산한다`(
        rank: String,
        count: Int,
    ) {
        val prize = LottoPrize.calculatePrize(ranking = rank, count = count)
        val expected = LottoPrize.valueOf(rank).prize * count

        Assertions.assertThat(prize).isEqualTo(expected)
    }
}
