package org.gonza.kotlinplayground.domain.payment

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoStatisticsSheetTest {
    @Test
    fun `통계정보와 일치하는 개수를 입력하면 알맞은 통계정보를 조회할 수 있다`() {
        val threeMatchedCount = 3
        val fourMatchedCount = 4
        val fiveMatchedCount = 5
        val allMatchedCount = 6

        val threeStatistics = LottoStatisticsSheet.findByMatchedCount(threeMatchedCount)!!
        val fourStatistics = LottoStatisticsSheet.findByMatchedCount(fourMatchedCount)!!
        val fiveStatistics = LottoStatisticsSheet.findByMatchedCount(fiveMatchedCount)!!
        val allStatistics = LottoStatisticsSheet.findByMatchedCount(allMatchedCount)!!

        assertEquals(threeStatistics.matchedCount, threeMatchedCount)
        assertEquals(fourStatistics.matchedCount, fourMatchedCount)
        assertEquals(fiveStatistics.matchedCount, fiveMatchedCount)
        assertEquals(allStatistics.matchedCount, allMatchedCount)
    }
}
