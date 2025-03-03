package org.gonza.kotlinplayground.domain.payment

interface LottoStatisticsSheet {
    fun isMatched(matchedCount: Int): Boolean

    fun getMatchedCount(): Int

    fun getAmount(): Int
}
