package org.gonza.kotlinplayground.domain.payment

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoMatchTest {
    @Test
    fun `숫자 중 3개만 일치할 경우 THREE_MATCH 값이 된다`() {
        val result =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val ticket =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(9),
                    LottoNumber(8),
                    LottoNumber(7),
                ),
            )

        val matchedResult = LottoMatch.matchWith(result, ticket)

        assertNotNull(matchedResult)
        assertEquals(3, matchedResult?.matchedCount)
        assertEquals(LottoMatch.THREE_MATCHED, matchedResult)
    }

    @Test
    fun `숫자 중 4개만 일치할 경우 FOUR_MATCH 값이 된다`() {
        val result =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val ticket =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(8),
                    LottoNumber(7),
                ),
            )

        val matchedResult = LottoMatch.matchWith(result, ticket)

        assertNotNull(matchedResult)
        assertEquals(4, matchedResult?.matchedCount)
        assertEquals(LottoMatch.FOUR_MATCHED, matchedResult)
    }

    @Test
    fun `숫자 중 5개만 일치할 경우 FIVE_MATCH 값이 된다`() {
        val result =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val ticket =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(7),
                ),
            )

        val matchedResult = LottoMatch.matchWith(result, ticket)

        assertNotNull(matchedResult)
        assertEquals(5, matchedResult?.matchedCount)
        assertEquals(LottoMatch.FIVE_MATCHED, matchedResult)
    }

    @Test
    fun `숫자 중 모두 일치할 경우 ALL_MATCH 값이 된다`() {
        val result =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )
        val ticket =
            LottoTicket(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val matchedResult = LottoMatch.matchWith(result, ticket)

        assertNotNull(matchedResult)
        assertEquals(6, matchedResult?.matchedCount)
        assertEquals(LottoMatch.ALL_MATCHED, matchedResult)
    }
}
