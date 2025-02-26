package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.utils.LottoConstants

class Purchase(private val amount: Amount) {
    val totalPaper: Int
        get() = amount.total / LottoConstants.LOTTO_PRICE
}