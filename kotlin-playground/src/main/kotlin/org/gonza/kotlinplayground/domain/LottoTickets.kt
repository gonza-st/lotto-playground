package org.gonza.kotlinplayground.domain

class LottoTickets(val lottoList: List<Lotto>) {

    fun findWinLotto(answer: List<LottoNumber>): Map<Int, List<Lotto>> {
        return this.lottoList
            .map { lotto ->
                lotto.apply { compareAll(target = answer) }
            }
            .groupBy { lotto ->
                lotto.winnerNumberList.size
            }
    }
}
