package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.dto.MatchResultDto
import org.gonza.kotlinplayground.enum.LottoPrize
import java.math.BigDecimal
import java.math.RoundingMode

class YieldCalculator {
    fun yield(
        cost: Int,
        matchResult: MatchResultDto,
    ): Double {
        val fifth = matchResult.fifth * LottoPrize.FIFTH.prize
        val fourth = matchResult.fourth * LottoPrize.FOURTH.prize
        val third = matchResult.third * LottoPrize.THIRD.prize
        val second = matchResult.second * LottoPrize.SECOND.prize
        val winner = matchResult.winner * LottoPrize.WINNER.prize

        val total = winner + second + third + fourth + fifth

        val result =
            BigDecimal(total / cost.toDouble())
                .setScale(2, RoundingMode.DOWN)
                .toDouble()

        return result
    }
}
