package org.gonza.kotlinplayground.domain.payment

enum class LottoStatisticsSheet(
    val amount: Int,
    val matchedCount: Int,
) {
    THREE_MATCHED(5_000, 3),
    FOUR_MATCHED(50_000, 4),
    FIVE_MATCHED(1_500_000, 5),
    ALL_MATCHED(2_000_000_000, 6),
    ;

    companion object {
        fun sortedByDescending() = values().sortedByDescending { it.matchedCount }

        fun findByMatchedCount(matchedCount: Int): LottoStatisticsSheet? {
            val statisticsList = sortedByDescending()
            return statisticsList.find { it.isMatched(matchedCount) }
        }
    }

    fun isMatched(matchedCount: Int): Boolean = this.matchedCount == matchedCount
}
