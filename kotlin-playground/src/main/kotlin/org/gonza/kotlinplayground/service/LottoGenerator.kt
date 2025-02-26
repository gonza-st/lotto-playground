package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.service.dto.GeneratedNumberResult

class LottoGenerator(
    private val numberGenerator: NumberGenerator,
) {
    fun generate(): GeneratedNumberResult {
        val result: MutableSet<LottoNumber> = mutableSetOf()

        while (shouldGenerate(result.size)) {
            val number = numberGenerator.get()
            result.add(LottoNumber(number))
        }

        return GeneratedNumberResult(
            ticket = LottoTicket(result.toList()),
        )
    }

    private fun shouldGenerate(size: Int): Boolean = size < LottoTicket.MAX_LENGTH
}
