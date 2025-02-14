package org.gonza.kotlinplayground.service.dto

import org.gonza.kotlinplayground.domain.lotto.LottoNumber

data class GeneratedNumberResult(
    val numberList: List<LottoNumber>,
)
