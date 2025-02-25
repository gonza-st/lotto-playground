package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.service.WinningCount

class WinningStatistics(
    private val statistics: Map<LottoStatisticsSheet, Int>,
    private val winningCountList: List<WinningCount>
) {
    fun totalPrizePayment(): Payment {
        val totalPrizeMoney =
            winningCountList.sumOf {
                val sheet = it.statisticsSheet
                val matchedCount = it.count

                sheet.amount * matchedCount
            }

        return Payment(totalPrizeMoney)
    }

    fun getStatistics(): Map<LottoStatisticsSheet, Int> = statistics
}
