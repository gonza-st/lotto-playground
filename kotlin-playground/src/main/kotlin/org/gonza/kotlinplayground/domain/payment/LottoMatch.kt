package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.domain.lotto.LottoTicket

enum class LottoMatch(
    val amount: Int,
    val matchedCount: Int,
) {
    THREE_MATCHED(5_000, 3),
    FOUR_MATCHED(50_000, 4),
    FIVE_MATCHED(1_500_000, 5),
    ALL_MATCHED(2_000_000_000, 6),
    ;

    companion object {
        fun matchWith(
            result: LottoTicket,
            ticket: LottoTicket,
        ): LottoMatch? = values().sortedByDescending { it.matchedCount }.find { it.isMatched(result, ticket) }
    }

    fun isMatched(
        result: LottoTicket,
        ticket: LottoTicket,
    ): Boolean {
        val numberList = result.getMatchedNumber(ticket)
        return numberList.size == matchedCount
    }
}
