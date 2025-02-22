package org.gonza.kotlinplayground.presentation

import org.gonza.kotlinplayground.domain.payment.LottoStatisticsSheet

class OutputView {
    fun printRequestPurchasePrice() {
        println("구입금액을 입력해 주세요.")
    }

    fun printPurchaseLottoTicketCount(ticketCount: Int) {
        println("$ticketCount 개를 구매했습니다")
    }

    fun printGeneratedLottoTicketList(ticketList: List<String>) {
        ticketList.forEach { println(it) }
    }

    fun printRequestResultLottoTicket() {
        println("당첨 번호를 입력해 주세요.")
    }

    fun printNoticeStatisticSheet() {
        println("당첨 통계")
        println("---------")
    }

    fun printStatisticSheet(
        statisticsSheet: LottoStatisticsSheet,
        matchedCount: Int,
    ) {
        println("${statisticsSheet.matchedCount}개 일치 (${statisticsSheet.amount}원) - $matchedCount")
    }

    fun printReturnOnInvestment(returnOnInvestment: Double) {
        println("총 수익률은 $returnOnInvestment 입니다.")
    }
}
