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
        var total: Long = 0
        matchResult.toList().forEach { (key, count) ->
            total += LottoPrize.calculatePrize(ranking = key, count = count)
        }

        val result =
            BigDecimal(total / cost.toDouble())
                .setScale(2, RoundingMode.DOWN)
                .toDouble()

        return result
    }
}
