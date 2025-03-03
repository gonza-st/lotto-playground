package org.gonza.kotlinplayground.domain.payment

enum class BonusLottoStatisticsSheet(
    private val amount: Int,
    private val matchedCount: Int,
) : LottoStatisticsSheet {
    BONUS_MATCHED(30_000_000, 5),
    ;

    override fun isMatched(matchedCount: Int): Boolean = this.matchedCount == matchedCount

    override fun getAmount(): Int = amount

    override fun getMatchedCount(): Int = matchedCount
}
