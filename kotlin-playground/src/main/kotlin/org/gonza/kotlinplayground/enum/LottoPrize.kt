package org.gonza.kotlinplayground.enum

enum class LottoPrize(
    val prize: Long,
) {
    FIFTH(5_000),
    FOURTH(50_000),
    THIRD(1_500_000),
    SECOND(30_000_000),
    WINNER(2_000_000_000), ;

    companion object {
        fun calculatePrize(
            ranking: String,
            count: Int,
        ): Long {
            val prize = LottoPrize.valueOf(value = ranking.uppercase()).prize
            return count * prize
        }
    }
}
