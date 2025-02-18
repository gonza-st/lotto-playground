package org.gonza.kotlinplayground.enum

import org.gonza.kotlinplayground.utils.LottoConstants

enum class Prize(
    val matchCount: Int,
    val displayText: String,
) {
    FIRST(6, LottoConstants.FIRST_PRIZE_TEXT),
    SECOND(5, LottoConstants.SECOND_PRIZE_TEXT),
    THIRD(4, LottoConstants.THIRD_PRIZE_TEXT),
    FOURTH(3, LottoConstants.FOURTH_PRIZE_TEXT);

    companion object {
        fun getByMatchCount(count: Int): Prize? =
            entries.find { it.matchCount == count }
    }
}