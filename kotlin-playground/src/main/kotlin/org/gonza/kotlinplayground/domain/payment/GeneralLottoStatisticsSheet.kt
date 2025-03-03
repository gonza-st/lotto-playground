package org.gonza.kotlinplayground.domain.payment

enum class GeneralLottoStatisticsSheet(
    private val amount: Int,
    private val matchedCount: Int,
) : LottoStatisticsSheet {
    THREE_MATCHED(5_000, 3),
    FOUR_MATCHED(50_000, 4),
    FIVE_MATCHED(1_500_000, 5),
    ALL_MATCHED(2_000_000_000, 6),
    ;

    companion object {
        fun findByMatchedCount(matchedCount: Int): GeneralLottoStatisticsSheet? {
            val statisticsList = sortedByDescending()
            return statisticsList.find { it.isMatched(matchedCount) }
        }

        private fun sortedByDescending() = values().sortedByDescending { it.matchedCount }
    }

    override fun isMatched(matchedCount: Int): Boolean = this.matchedCount == matchedCount

    override fun getAmount(): Int = amount

    override fun getMatchedCount(): Int = matchedCount
}
