package org.gonza.kotlinplayground.domain.lotto

import org.gonza.kotlinplayground.domain.lotto.exception.InvalidLottoNumberException

class LottoNumber(
    val number: Int,
) {
    init {
        validate(number)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is LottoNumber) return false
        return other.number == number
    }

    override fun hashCode(): Int = number.hashCode()


    private fun validate(number: Int) {
        val range = Range()
        if (number < range.getMin() || number > range.getMax()) {
            throw InvalidLottoNumberException()
        }
    }

    class Range : NumberRange {
        companion object {
            private const val MAX_NUMBER_LENGTH = 45
            private const val MIN_NUMBER_LENGTH = 1
        }

        override fun getMin(): Int = MIN_NUMBER_LENGTH

        override fun getMax(): Int = MAX_NUMBER_LENGTH
    }
}
