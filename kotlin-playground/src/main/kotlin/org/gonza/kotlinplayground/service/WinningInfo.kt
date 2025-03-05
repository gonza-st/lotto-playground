package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet

data class WinningInfo(
    val statisticsSheet: LottoStatisticsSheet,
    val count: Int,
)
