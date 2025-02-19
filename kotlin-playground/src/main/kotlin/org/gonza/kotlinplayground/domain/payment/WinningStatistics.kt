package org.gonza.kotlinplayground.domain.payment

class WinningStatistics(
    private val statistics: Map<LottoStatisticsSheet, Int>
) {
    fun totalPrizeMoney(): Int {
        return statistics.entries.sumOf { (sheet, matchedCount) ->
            sheet.amount * matchedCount
        }
    }

    fun getStatistics(): Map<LottoStatisticsSheet, Int> = statistics
}
