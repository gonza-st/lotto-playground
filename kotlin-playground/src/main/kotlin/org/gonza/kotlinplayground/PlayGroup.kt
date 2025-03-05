package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.lotto.BonusNumber
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

    outputView.printPurchasedLottoTicketCount(lottoTicketList.size)
    outputView.printGeneratedLottoTicketList(lottoTicketList.map { it.toString() })

    val winningTicket = getWinningTicket(inputView, outputView)
    val bonusNumber = getBonusNumber(winningTicket, inputView, outputView)
    val winningStatistics = matchLottoResults(winningTicket, lottoTicketList)
    val bonusStatistics = matchBonusLottoResults(winningTicket, bonusNumber, lottoTicketList)
    val allStatistics = winningStatistics.add(bonusStatistics)

    printStatistics(outputView, allStatistics)
    printReturnOnInvestment(outputView, payment, allStatistics)
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
    val paidTicketCount = LottoTicket.getPaidTicketCount(payment)
    return List(paidTicketCount) {
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

private fun getBonusNumber(
    winningTicket: LottoTicket,
    inputView: InputView,
    outputView: OutputView,
): BonusNumber {
    outputView.printBonusNumber()
    val bonusNumber =
        inputView.read()?.toIntOrNull()
            ?: throw IllegalArgumentException("보너스 숫자를 입력하세요")
    return BonusNumber(winningTicket, bonusNumber)
}

private fun matchLottoResults(
    winningTicket: LottoTicket,
    purchasedTicketList: List<LottoTicket>,
): WinningStatistics {
    val lottoMatcher = GeneralLottoMatcher(winningTicket)
    return lottoMatcher.getWinningStatistics(
        purchasedTicketList = purchasedTicketList,
    )
}

private fun matchBonusLottoResults(
    winningTicket: LottoTicket,
    bonusNumber: BonusNumber,
    purchasedTicketList: List<LottoTicket>,
): WinningStatistics {
    val bonusLottoMatcher = BonusNumberMatcher(winningTicket, bonusNumber)
    return bonusLottoMatcher.getWinningStatistics(
        purchasedTicketList = purchasedTicketList,
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
