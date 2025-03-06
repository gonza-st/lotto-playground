package org.gonza.kotlinplayground.domain

import Lotto
import org.gonza.kotlinplayground.dto.MatchResultDto

data class Lottos(
    val value: List<Lotto>,
) {
    fun print(): List<String> = value.map { it.toString() }

    fun size(): Int = value.size

    fun contains(lottoList: List<Lotto>): Boolean = value.containsAll(lottoList)

    fun matchResult(
        winningLotto: Lotto,
        bonusBall: LottoNumber,
    ): MatchResultDto {
        val totalResult: MatchResultDto =
            value.fold(MatchResultDto()) { result, lotto ->
                val matchCount = lotto.matchCount(other = winningLotto)
                val matchBonus = lotto.matchBonus(bonus = bonusBall)

                result.apply {
                    rankingCalculate(
                        matchCount = matchCount,
                        matchBonus = matchBonus,
                    )
                }
            }

        return totalResult
    }

    private fun MatchResultDto.rankingCalculate(
        matchCount: Int,
        matchBonus: Boolean,
    ) {
        when (matchCount) {
            6 -> winner++
            5 -> if (matchBonus) second++ else third++
            4 -> fourth++
            3 -> fifth++
        }
    }
}
