package org.gonza.kotlinplayground.domain

class LottoTickets(val lottoList: List<Lotto>) {

    private val winnerMap: MutableMap<Int, List<Lotto>> = mutableMapOf()

    fun findWinner(answer: List<LottoNumber>): Map<Int, List<Lotto>> {
        // TODO
        return winnerMap
    }
}
