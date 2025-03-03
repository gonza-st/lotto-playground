package org.gonza.kotlinplayground.domain.payment

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GeneralLottoStatisticsSheetTest {
    @Test
    fun `통계정보와 일치하는 개수를 입력하면 알맞은 통계정보를 조회할 수 있다`() {
        val threeMatchedCount = 3
        val fourMatchedCount = 4
        val fiveMatchedCount = 5
        val allMatchedCount = 6

        val threeStatistics = GeneralLottoStatisticsSheet.findByMatchedCount(threeMatchedCount)!!
        val fourStatistics = GeneralLottoStatisticsSheet.findByMatchedCount(fourMatchedCount)!!
        val fiveStatistics = GeneralLottoStatisticsSheet.findByMatchedCount(fiveMatchedCount)!!
        val allStatistics = GeneralLottoStatisticsSheet.findByMatchedCount(allMatchedCount)!!

        assertEquals(threeStatistics.getMatchedCount(), threeMatchedCount)
        assertEquals(fourStatistics.getMatchedCount(), fourMatchedCount)
        assertEquals(fiveStatistics.getMatchedCount(), fiveMatchedCount)
        assertEquals(allStatistics.getMatchedCount(), allMatchedCount)
    }
}
