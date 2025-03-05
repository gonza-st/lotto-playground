package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.enum.NumberType
import org.gonza.kotlinplayground.utils.LottoConstants

class LottoNumber(
    val number: Int,
    val type: NumberType = NumberType.NORMAL,
    private val limit: Int = LottoConstants.LOTTO_NUMBER_RANGE.last
) {
    init {
        validLottoNumber()
    }

    private fun validLottoNumber() {
        require(this.number > 0) { "로또 번호는 양수만 가능합니다." }
        require(this.number <= limit) { "로또 번호는 ${limit}을(를) 초과할 수 없습니다." }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LottoNumber) return false
        return number == other.number
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }
}