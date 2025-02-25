package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.lotto.LottoNumber
import org.gonza.kotlinplayground.domain.lotto.LottoTicket
import org.gonza.kotlinplayground.domain.payment.Payment
import org.gonza.kotlinplayground.domain.payment.WinningStatistics
import org.gonza.kotlinplayground.presentation.InputView
import org.gonza.kotlinplayground.presentation.OutputView
import org.gonza.kotlinplayground.service.*

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val payment = purchaseLotto(inputView, outputView)
    val lottoTicketList = generateLottoTickets(payment)

    outputView.printPurchaseLottoTicketCount(lottoTicketList.size)
    outputView.printGeneratedLottoTicketList(lottoTicketList.map { it.toString() })

    val winningTicket = getWinningTicket(inputView, outputView)
    val winningStatistics = matchLottoResults(winningTicket, lottoTicketList)

    printStatistics(outputView, winningStatistics)
    printReturnOnInvestment(outputView, payment, winningStatistics)
}

private fun purchaseLotto(
    inputView: InputView,
    outputView: OutputView,
): Payment {
    outputView.printRequestPurchasePrice()
    val purchaseAmount =
        inputView.read()?.toIntOrNull()
            ?: throw IllegalArgumentException("올바른 금액을 입력하세요")
    return Payment(purchaseAmount)
}

private fun generateLottoTickets(payment: Payment): List<LottoTicket> {
    val randomNumberGenerator = RandomNumberGenerator(LottoNumber.Range())
    val lottoGenerator = LottoGenerator(randomNumberGenerator)
    return List(payment.getPaidTicketCount()) {
        lottoGenerator.generate().ticket
    }
}

private fun getWinningTicket(
    inputView: InputView,
    outputView: OutputView,
): LottoTicket {
    outputView.printRequestResultLottoTicket()
    val winningLottoInput =
        inputView.read()
            ?: throw IllegalArgumentException("당첨 번호를 입력하세요")
    return InputLottoPicker(winningLottoInput).pick()
}

private fun matchLottoResults(
    winningTicket: LottoTicket,
    lottoTicketList: List<LottoTicket>,
): WinningStatistics {
    val lottoMatcher = GeneralLottoMatcher()
    return lottoMatcher.getWinningStatistics(
        result = winningTicket,
        purchasedTicketList = lottoTicketList,
    )
}

private fun printStatistics(
    outputView: OutputView,
    winningStatistics: WinningStatistics,
) {
    outputView.printNoticeStatisticSheet()
    val winningStatisticsList = winningStatistics.getWinningStatisticsList()

    winningStatisticsList.forEach {
        val sheet = it.statisticsSheet
        val matchedCount = it.count

        outputView.printStatisticSheet(sheet, matchedCount)
    }
}

private fun printReturnOnInvestment(
    outputView: OutputView,
    payment: Payment,
    winningStatistics: WinningStatistics,
) {
    val profitPayment = winningStatistics.totalPrizePayment()
    val financialPolicy = LottoFinancialPolicy()
    val returnOnInvestment = financialPolicy.calculateReturnOnInvestment(payment, profitPayment)

    outputView.printReturnOnInvestment(returnOnInvestment)
}
