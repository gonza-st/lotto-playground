package org.gonza.kotlinplayground.domain

import Lotto
import org.gonza.kotlinplayground.dto.MatchResultDto

data class Lottos(
    val value: List<Lotto>,
) {
    fun print(): List<String> = value.map { it.toString() }

    fun matchResult(
        winningLotto: Lotto,
        bonusBall: LottoNumber,
    ): MatchResultDto {
        val totalResult: MatchResultDto =
            value.fold(MatchResultDto()) { result, lotto ->
                val matchCount = lotto.matchCount(other = winningLotto)

                result.apply {
                    rankingCalculate(
                        matchCount = matchCount,
                        lotto = lotto,
                        bonusBall = bonusBall,
                    )
                }
            }

        return totalResult
    }

    private fun MatchResultDto.rankingCalculate(
        matchCount: Int,
        lotto: Lotto,
        bonusBall: LottoNumber,
    ) {
        when (matchCount) {
            6 -> winner++
            5 -> if (lotto.matchBonus(bonus = bonusBall)) second++ else third++
            4 -> fourth++
            3 -> fifth++
        }
    }
}
