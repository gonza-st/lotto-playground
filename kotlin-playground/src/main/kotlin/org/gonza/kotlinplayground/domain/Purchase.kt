package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.utils.LottoConstants

class Purchase(
    private val amount: Amount,
    private val manual: Manual,
) {
    init {
        val availableCount = amount.total / LottoConstants.LOTTO_PRICE
        require(availableCount >= manual.count) { "수동으로 구입 금액보다 많은 로또를 구매할 수 없습니다." }
    }

    val manualCount: Int
        get() = manual.count

    val autoCount: Int
        get() = (amount.total / LottoConstants.LOTTO_PRICE) - manual.count

    val totalPaper: Int
        get() = manualCount + autoCount
}