package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.utils.LottoConstants

class LottoNumber(
    val number: Int,
    private val limit: Int = LottoConstants.LOTTO_NUMBER_RANGE.last
) {
    init {
        validLottoNumber()
    }

    private fun validLottoNumber() {
        require(this.number > 0) { "로또 번호는 양수만 가능합니다." }
        require(this.number <= limit) { "로또 번호는 양수만 가능합니다." }
    }
}