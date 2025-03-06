package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.service.WinningInfo

class WinningStatisticsResult(
    private val winningInfoList: List<WinningInfo>,
) {
    fun totalPrizePayment(): Payment? {
        val totalPrizeMoney =
            winningInfoList.sumOf {
                val sheet = it.statisticsSheet
                val matchedCount = it.count

                sheet.getAmount() * matchedCount
            }

        if (totalPrizeMoney == 0) return null

        return Payment(totalPrizeMoney)
    }

    fun getWinningStatisticsList(): List<WinningInfo> = winningInfoList

    fun add(winningStatisticsResult: WinningStatisticsResult): WinningStatisticsResult {
        val list = winningStatisticsResult.getWinningStatisticsList()

        return WinningStatisticsResult(
            winningInfoList = winningInfoList + list,
        )
    }

    fun findWinningInfoBy(statisticsSheet: LottoStatisticsSheet): WinningInfo? =
        winningInfoList.find {
            val winningStatisticsSheet = it.statisticsSheet

            winningStatisticsSheet.isMatched(statisticsSheet.getMatchedCount())
        }
}
