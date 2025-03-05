package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.*
import org.gonza.kotlinplayground.enum.NumberType
import org.gonza.kotlinplayground.enum.Prize
import org.gonza.kotlinplayground.ui.InputView
import org.gonza.kotlinplayground.ui.PrintView
import org.gonza.kotlinplayground.utils.LottoConstants
import org.gonza.kotlinplayground.utils.LottoNumberGenerator
import org.gonza.kotlinplayground.utils.LottoStringConstants
import org.gonza.kotlinplayground.utils.NumberConverter

class LottoRunner(
    private val printView: PrintView,
    private val inputView: InputView,
) {
    fun run() {
        val purchase: Purchase = purchaseLotto()
        val lottoTickets: LottoTickets = getLottoTickets(totalPaper = purchase.totalPaper)
        printLotto(lottoTickets = lottoTickets)
        val winningLotto = getWinningLotto()
        val bonusBall: LottoNumber = getBonusBall()
        addBonusBall(winningNumber = winningLotto, bonusBall = bonusBall)
        lottoStatistics(answer = winningLotto.numberList, lottoTickets = lottoTickets, purchase = purchase.totalPaper)
    }

    private fun purchaseLotto(): Purchase {
        printView.printWithLine(LottoStringConstants.BEFORE_PURCHASE_INPUT_HELP_TEXT)
        try {
            val totalInput = inputView.input().trim().toInt()
            val amount = Amount(total = totalInput)
            val manual = Manual(count = 0) // FIXME
            val purchase = Purchase(amount = amount, manual = manual)
            printView.printWithLine("${purchase.totalPaper}${LottoStringConstants.AFTER_PURCHASE_INPUT_HELP_TEXT}")

            return purchase
        } catch (e: NumberFormatException) {
            throw NumberFormatException(LottoStringConstants.INVALID_PURCHASE_FORMAT)
        }
    }

    private fun getLottoTickets(totalPaper: Int): LottoTickets {
        val lottoList: MutableList<Lotto> = mutableListOf()
        repeat(totalPaper) {
            val lottoNumberList: List<LottoNumber> = LottoNumberGenerator.generate(
                range = LottoConstants.LOTTO_NUMBER_RANGE.toList(),
                take = LottoConstants.MAX_LOTTO_NUMBER_HAVE_COUNT
            ).map { LottoNumber(it) }
            val lotto = Lotto(lottoNumberList = lottoNumberList)

            lottoList.add(lotto)
        }
        val lottoTickets = LottoTickets(lottoList = lottoList)

        return lottoTickets
    }

    private fun printLotto(lottoTickets: LottoTickets) {
        printView.printWithLine(lottoTickets.toString())
        printView.printWithLine(LottoStringConstants.EMPTY_TEXT)
    }

    private fun getWinningLotto(): WinningNumber {
        printView.printWithLine(LottoStringConstants.WINNING_NUMBER_INPUT_HELP_TEXT)
        val winningNumberString = inputView.input()
        val winningNumber = WinningNumber.from(input = winningNumberString)

        return winningNumber
    }

    private fun getBonusBall(): LottoNumber {
        printView.printWithLine(LottoStringConstants.BONUS_BALL_INPUT_HELP_TEXT)
        val bonusBallString = inputView.input()
        val bonusBall = LottoNumber(number = NumberConverter.convert(str = bonusBallString), type = NumberType.BONUS)

        return bonusBall
    }

    private fun addBonusBall(winningNumber: WinningNumber, bonusBall: LottoNumber) {
        winningNumber.numberList + bonusBall
    }

    private fun lottoStatistics(
        answer: List<LottoNumber>,
        lottoTickets: LottoTickets,
        purchase: Int
    ) {
        printView.printWithLine(LottoStringConstants.EMPTY_TEXT)
        printView.printWithLine(LottoStringConstants.DIVIDE_TEXT)
        val totalWinningCount = lottoTickets.getTotalWinningCount(answer = answer)
        val benefitRate = calculateBenefitRate(winningCount = totalWinningCount, purchase = purchase)

        Prize.entries.toTypedArray()
            .sortedByDescending { it.matchCount }
            .forEach { prize ->
                val winningCount = lottoTickets.getWinningCount(answer = answer, prize = prize)
                printView.printWithLine("${prize.displayText}${winningCount}개")
            }
        printView.printWithLine("${LottoStringConstants.STATISTICS_PREFIX_TEXT}${benefitRate}${LottoStringConstants.STATISTICS_POSTFIX_TEXT}")
    }

    private fun calculateBenefitRate(winningCount: Int, purchase: Int): String {
        return "%.2f".format(winningCount.toDouble() / purchase.toDouble())
    }
}