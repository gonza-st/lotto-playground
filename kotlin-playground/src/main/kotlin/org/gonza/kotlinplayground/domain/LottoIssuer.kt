package org.gonza.kotlinplayground.domain

import Lotto

class LottoIssuer {
    fun amount(money: Int): Int {
        val amount: Int = money / 1000
        val rest: Int = money % 1000

        if (rest != 0) {
            throw IllegalArgumentException("1000원 단위로만 구매 가능합니다.")
        }

        return amount
    }

    fun buy(amount: Int): Lottos {
        var tryCount: Int = amount
        val lottoList: MutableList<Lotto> = mutableListOf()

        while (tryCount > 0) {
            val lotto = Lotto.create()
            lottoList.add(lotto)
            tryCount--
        }

        val lottos = Lottos(value = lottoList)
        return lottos
    }

    fun buy(
        amount: Int,
        manualLottoList: List<Lotto>,
    ): Lottos {
        var tryCount: Int = amount
        val lottoList: MutableList<Lotto> = mutableListOf()

        while (tryCount > 0) {
            val lotto = Lotto.create()
            lottoList.add(lotto)
            tryCount--
        }

        val mergedLottoList = manualLottoList.toMutableList() + lottoList
        val lottos = Lottos(value = mergedLottoList)
        return lottos
    }
}
