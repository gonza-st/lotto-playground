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
        val three = matchResult.fourth * LottoPrize.THREE.prize
        val four = matchResult.third * LottoPrize.FOUR.prize
        val five = matchResult.second * LottoPrize.FIVE.prize
        val six = matchResult.winner * LottoPrize.SIX.prize

        val total = three + four + five + six

        val result =
            BigDecimal(total / cost.toDouble())
                .setScale(2, RoundingMode.DOWN)
                .toDouble()

        return result
    }
}
