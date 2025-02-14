package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.NumberRange

class LottoNumberGenerator(
    private val numberRange: NumberRange,
) : NumberGenerator {
    override fun get(): Int {
        val min = numberRange.getMin()
        val max = numberRange.getMax()
        return (min..max).random()
    }
}
