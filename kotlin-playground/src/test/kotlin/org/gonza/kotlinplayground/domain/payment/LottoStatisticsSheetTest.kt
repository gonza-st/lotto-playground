package org.gonza.kotlinplayground.domain.payment

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoStatisticsSheetTest {
    @Test
    fun `통계 정보를 일치하는 숫자 기준으로 오름차순 정렬로 가져올 수 있다`() {
        val expected =
            listOf(
                LottoStatisticsSheet.ALL_MATCHED,
                LottoStatisticsSheet.FIVE_MATCHED,
                LottoStatisticsSheet.FOUR_MATCHED,
                LottoStatisticsSheet.THREE_MATCHED,
            )

        val result = LottoStatisticsSheet.sortedByDescending()

        assertEquals(expected, result)
    }

    @Test
    fun `당첨 번호와 일치하는 숫자 개수를 통해 일치하는 당첨 통계 정보를 가져올 수 있다`() {
        val threeMatchedNumber = 3
        val fourMatchedNumber = 4
        val fiveMatchedNumber = 5
        val allMatchedNumber = 6

        val threeMatchedStatistics = LottoStatisticsSheet.findByMatchedNumber(threeMatchedNumber)
        val fourMatchedStatistics = LottoStatisticsSheet.findByMatchedNumber(fourMatchedNumber)
        val fiveMatchedStatistics = LottoStatisticsSheet.findByMatchedNumber(fiveMatchedNumber)
        val allMatchedStatistics = LottoStatisticsSheet.findByMatchedNumber(allMatchedNumber)

        assertEquals(threeMatchedStatistics?.matchedCount, threeMatchedNumber)
        assertEquals(fourMatchedStatistics?.matchedCount, fourMatchedNumber)
        assertEquals(fiveMatchedStatistics?.matchedCount, fiveMatchedNumber)
        assertEquals(allMatchedStatistics?.matchedCount, allMatchedNumber)
    }
}
