package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.utils.LottoConstants
import org.gonza.kotlinplayground.utils.LottoStringConstants

class UserManualNumber private constructor(val numberList: List<LottoNumber>) {
    init {
        require(numberList.size == LottoConstants.MAX_LOTTO_NUMBER_HAVE_COUNT) {
            LottoStringConstants.INVALID_WINNING_NUMBER_FORMAT
        }
    }

    companion object {
        fun from(input: String): UserManualNumber {
            try {
                val lottoNumbers = input.trim()
                    .split(",")
                    .map { it.trim() }
                    .map { LottoNumber(number = it.toInt()) }

                return UserManualNumber(numberList = lottoNumbers)
            } catch (e: Exception) {
                throw IllegalArgumentException(LottoStringConstants.INVALID_WINNING_NUMBER_FORMAT)
            }
        }
    }
}
