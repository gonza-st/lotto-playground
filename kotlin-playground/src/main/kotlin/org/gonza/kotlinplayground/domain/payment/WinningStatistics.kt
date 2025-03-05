package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.service.WinningInfo

class WinningStatistics(
    private val winningInfoList: List<WinningInfo>,
) {
    fun totalPrizePayment(): Payment {
        val totalPrizeMoney =
            winningInfoList.sumOf {
                val sheet = it.statisticsSheet
                val matchedCount = it.count

                sheet.getAmount() * matchedCount
            }

        return Payment(totalPrizeMoney)
    }

    fun getWinningStatisticsList(): List<WinningInfo> = winningInfoList

    fun add(winningStatistics: WinningStatistics): WinningStatistics {
        val list = winningStatistics.getWinningStatisticsList()

        return WinningStatistics(
            winningInfoList = winningInfoList + list,
        )
    }
}
