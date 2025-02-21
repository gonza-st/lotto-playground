package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.utils.LottoStringConstants

class WinningNumber private constructor(val numberList: List<LottoNumber>) {
    init {
        require(numberList.size == 6) {
            LottoStringConstants.INVALID_WINNING_NUMBER_FORMAT
        }
    }

    companion object {
        fun from(input: String): WinningNumber {
            try {
                val lottoNumbers = input.trim()
                    .split(",")
                    .map { it.trim() }
                    .map { LottoNumber(number = it.toInt()) }

                return WinningNumber(numberList = lottoNumbers)
            } catch (e: Exception) {
                throw IllegalArgumentException(LottoStringConstants.INVALID_WINNING_NUMBER_FORMAT)
            }
        }
    }
}
