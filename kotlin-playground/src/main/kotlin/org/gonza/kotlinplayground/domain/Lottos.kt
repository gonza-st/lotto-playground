package org.gonza.kotlinplayground.domain

import Lotto
import org.gonza.kotlinplayground.dto.MatchResultDto

data class Lottos(
    val value: List<Lotto>,
) {
    fun print(): List<String> = value.map { it.print() }

    fun matchResult(winningLotto: Lotto): MatchResultDto {
        val matchResultDto = MatchResultDto()
        for (lotto: Lotto in value) {
            val matchCount: Int = lotto.matchCount(other = winningLotto)
            when (matchCount) {
                3 -> matchResultDto.three += 1
                4 -> matchResultDto.four += 1
                5 -> matchResultDto.five += 1
                6 -> matchResultDto.six += 1
            }
        }

        return matchResultDto
    }
}
