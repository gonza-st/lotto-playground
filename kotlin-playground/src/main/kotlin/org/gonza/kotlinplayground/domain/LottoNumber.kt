package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.enum.LottoRange

data class LottoNumber(
    val value: Int = LottoRange.createValidNumber(),
) {
    init {
        require(value in LottoRange.START.value..LottoRange.END.value) { "로또 번호는 1부터 45까지의 숫자여야 합니다." }
    }

    override fun toString(): String = value.toString()
}
