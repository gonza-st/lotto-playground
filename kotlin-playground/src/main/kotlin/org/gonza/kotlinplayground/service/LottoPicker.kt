package org.gonza.kotlinplayground.service

import org.gonza.kotlinplayground.domain.lotto.LottoTicket

interface LottoPicker {
    fun pick(): LottoTicket
}
