package org.gonza.kotlinplayground.enum

import org.gonza.kotlinplayground.utils.LottoStringConstants

enum class Prize(
    val matchCount: Int,
    val displayText: String,
) {
    FIRST(6, LottoStringConstants.FIRST_PRIZE_TEXT),
    SECOND(5, LottoStringConstants.SECOND_PRIZE_TEXT),
    THIRD(5, LottoStringConstants.THIRD_PRIZE_TEXT),
    FOURTH(4, LottoStringConstants.FOURTH_PRIZE_TEXT),
    FIFTH(3, LottoStringConstants.FIFTH_PRIZE_TEXT);

    companion object {
        fun getByMatchCount(count: Int): Prize? =
            entries.find { it.matchCount == count }
    }
}