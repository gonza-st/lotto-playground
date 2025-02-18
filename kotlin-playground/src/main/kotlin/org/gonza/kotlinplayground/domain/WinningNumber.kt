package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.utils.LottoConstants

class WinningNumber private constructor(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == 6) {
            LottoConstants.INVALID_WINNING_NUMBER_FORMAT
        }
    }

    companion object {
        fun from(input: String): WinningNumber {
            try {
                val lottoNumbers = input.trim()
                    .split(",")
                    .map { it.trim() }
                    .map { LottoNumber(number = it.toInt()) }

                return WinningNumber(numbers = lottoNumbers)
            } catch (e: Exception) {
                throw IllegalArgumentException(LottoConstants.INVALID_WINNING_NUMBER_FORMAT)
            }
        }
    }
}
