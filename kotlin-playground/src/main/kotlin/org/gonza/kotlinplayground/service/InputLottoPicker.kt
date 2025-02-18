package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.service.exception.InvalidInputFormatException

class InputLottoPicker(
    input: String,
) : LottoPicker {
    private val numberList: List<Int>

    companion object {
        private const val DELIMITER = ","
    }

    init {
        try {
            numberList = input.split(DELIMITER).map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw InvalidInputFormatException()
        }
    }

    override fun pick(): LottoTicket {
        val lottoNumberList = numberList.map { LottoNumber(it) }
        return LottoTicket(lottoNumberList)
    }
}
