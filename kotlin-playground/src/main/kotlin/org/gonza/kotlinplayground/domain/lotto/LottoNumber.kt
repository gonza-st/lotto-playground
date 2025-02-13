package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.InvalidLottoNumberException

class LottoNumber(private val number: Int) {
    companion object {
        private const val MAX_NUMBER_LENGTH = 45
    }

    init {
        validate(number)
    }

    private fun validate(number: Int) {
        if (number < 0 || number > MAX_NUMBER_LENGTH) {
            throw InvalidLottoNumberException()
        }
    }
}