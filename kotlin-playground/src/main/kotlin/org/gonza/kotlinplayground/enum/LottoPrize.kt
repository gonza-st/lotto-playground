package org.gonza.kotlinplayground.enum

enum class LottoPrize(
    val prize: Long,
) {
    THREE(5_000),
    FOUR(50_000),
    FIVE(1_500_000),
    SIX(2_000_000_000),
}
