package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.utils.LottoConstants

class Amount(val total: Int) {
    val maxPurchase: Int
        get() = total / LottoConstants.LOTTO_PRICE

    init {
        require(total > 0) { "금액은 양수만 가능합니다." }
    }
}