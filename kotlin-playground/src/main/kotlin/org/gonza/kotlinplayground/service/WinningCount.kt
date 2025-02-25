package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet

class WinningCount(
    val statisticsSheet: LottoStatisticsSheet,
    val count: Int
)
