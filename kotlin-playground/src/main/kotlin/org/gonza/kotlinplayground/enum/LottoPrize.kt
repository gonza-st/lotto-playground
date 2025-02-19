package org.gonza.kotlinplayground.enum

enum class LottoPrize(
    val prize: Long,
) {
    THREE(5000),
    FOUR(50000),
    FIVE(1500000),
    SIX(2000000000),
}
