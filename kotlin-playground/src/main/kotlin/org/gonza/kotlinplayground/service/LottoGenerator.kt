package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.service.dto.GeneratedNumberResult

class LottoGenerator(
    private val numberGenerator: NumberGenerator,
) {
    companion object {
        private const val MAX_LENGTH = 6
    }

    fun generate(): GeneratedNumberResult {
        val result: MutableSet<Int> = mutableSetOf()

        while (shouldGenerate(result.size)) {
            result.add(numberGenerator.get())
        }

        return GeneratedNumberResult(
            numberList = result.toList(),
        )
    }

    private fun shouldGenerate(size: Int): Boolean = size > MAX_LENGTH
}
