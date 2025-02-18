package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.InvalidLottoNumberException

class LottoNumber(
    val number: Int,
) {
    init {
        Range(number)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is LottoNumber) return false
        return other.number == number
    }

    override fun hashCode(): Int = javaClass.hashCode()

    class Range(
        number: Int,
    ) : NumberRange {
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

        override fun getMin(): Int = MIN_NUMBER_LENGTH

        override fun getMax(): Int = MAX_NUMBER_LENGTH
    }
}
