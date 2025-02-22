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
}
