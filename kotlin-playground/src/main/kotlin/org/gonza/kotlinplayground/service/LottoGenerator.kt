package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.service.dto.GeneratedNumberResult

class LottoGenerator(
    private val numberGenerator: NumberGenerator,
) {
    companion object {
        private const val MAX_LENGTH = 6
    }

    fun generate(): GeneratedNumberResult {
        val result: MutableSet<LottoNumber> = mutableSetOf()

        while (shouldGenerate(result.size)) {
            val number = numberGenerator.get()
            result.add(LottoNumber(number))
        }

        return GeneratedNumberResult(
            numberList = result.toList(),
        )
    }

    private fun shouldGenerate(size: Int): Boolean = size > MAX_LENGTH
}
