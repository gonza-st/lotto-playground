package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.enum.LottoStatus
import org.gonza.kotlinplayground.enum.Prize

class LottoTickets(val lottoList: List<Lotto>) {

    fun findWinLotto(answer: List<LottoNumber>): Map<Int, List<Lotto>> {
        return this.lottoList
            .map { lotto ->
                lotto.apply { compareAll(target = answer) }
            }
            .filter { lotto ->
                lotto.status == LottoStatus.WON
            }
            .groupBy { lotto ->
                lotto.winnerNumberList.size
            }
    }

    fun getWinningCount(answer: List<LottoNumber>, prize: Prize): Int {
        val result = findWinLotto(answer = answer)
        return result[prize.matchCount]?.size ?: 0
    }

    fun getTotalWinningCount(answer: List<LottoNumber>): Int {
        val result = findWinLotto(answer = answer)
        return result.values.flatten().size
    }

    override fun toString(): String {
        return lottoList.joinToString("\n") { lotto->
            lotto.toString()
        }
    }
}
