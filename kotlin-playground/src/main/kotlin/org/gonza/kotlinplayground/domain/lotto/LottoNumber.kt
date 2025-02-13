package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.InvalidLottoNumberException

class LottoNumber(private val number: Int) {

    init {
        Range(number)
    }

    class Range(number: Int) {
        companion object {
            private const val MAX_NUMBER_LENGTH = 45
            private const val MIN_NUMBER_LENGTH = 0
        }

        init {
            validate(number)
        }

        private fun validate(number: Int) {
            if (number < MIN_NUMBER_LENGTH || number > MAX_NUMBER_LENGTH) {
                throw InvalidLottoNumberException()
            }
        }
    }
}