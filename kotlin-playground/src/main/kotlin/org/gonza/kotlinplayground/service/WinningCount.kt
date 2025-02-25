package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet

data class WinningCount(
    val statisticsSheet: LottoStatisticsSheet,
    val count: Int
)
