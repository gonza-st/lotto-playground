package org.gonza.kotlinplayground.domain

import Lotto
import org.gonza.kotlinplayground.dto.MatchResultDto

data class Lottos(
    val value: List<Lotto>,
) {
    fun print(): List<String> = value.map { it.toString() }

    fun matchResult(winningLotto: Lotto): MatchResultDto =
        value
            .groupingBy { it.matchCount(other = winningLotto) }
            .eachCount()
            .let { counts ->
                MatchResultDto(
                    three = counts[3] ?: 0,
                    four = counts[4] ?: 0,
                    five = counts[5] ?: 0,
                    six = counts[6] ?: 0,
                )
            }
}
