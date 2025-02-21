package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.enum.LottoStatus

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

    override fun toString(): String {
        return lottoList.joinToString("\n") { lotto->
            lotto.toString()
        }
    }
}
