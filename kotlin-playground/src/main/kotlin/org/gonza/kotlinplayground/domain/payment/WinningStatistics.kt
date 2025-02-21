package org.gonza.kotlinplayground.domain.payment

class WinningStatistics(
    private val statistics: Map<LottoStatisticsSheet, Int>,
) {
    fun totalPrizePayment(): Payment {
        val totalPrizeMoney =
            statistics.entries.sumOf { (sheet, matchedCount) ->
                sheet.amount * matchedCount
            }

        return Payment(totalPrizeMoney)
    }

    fun getStatistics(): Map<LottoStatisticsSheet, Int> = statistics
}
