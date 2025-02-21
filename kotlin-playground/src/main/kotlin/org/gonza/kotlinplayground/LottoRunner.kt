package org.gonza.kotlinplayground

import org.gonza.kotlinplayground.domain.*
import org.gonza.kotlinplayground.enum.Prize
import org.gonza.kotlinplayground.ui.InputView
import org.gonza.kotlinplayground.ui.PrintView
import org.gonza.kotlinplayground.utils.LottoConstants
import org.gonza.kotlinplayground.utils.LottoNumberGenerator
import org.gonza.kotlinplayground.utils.LottoStringConstants

class LottoRunner(
    private val printView: PrintView,
    private val inputView: InputView,
) {
    fun run() {
        val amount: Amount = purchaseLotto()
        val lottoTickets: LottoTickets = getLottoTickets(amount = amount)
        printLotto(lottoTickets = lottoTickets)
        val winningLotto = getWinningLotto()
        val result = lottoTickets.findWinLotto(answer = winningLotto.numberList)
        lottoStatistics(result = result, purchase = amount.maxPurchase)
    }

    private fun purchaseLotto(): Amount {
        printView.printWithLine(LottoStringConstants.BEFORE_PURCHASE_INPUT_HELP_TEXT)
        try {
            val purchase = inputView.input().trim().toInt()
            val amount = Amount(total = purchase)
            printView.printWithLine("${amount.maxPurchase}${LottoStringConstants.AFTER_PURCHASE_INPUT_HELP_TEXT}")

            return amount
        } catch (e: NumberFormatException) {
            throw NumberFormatException(LottoStringConstants.INVALID_PURCHASE_FORMAT)
        }
    }

    private fun getLottoTickets(amount: Amount): LottoTickets {
        val lottoList: MutableList<Lotto> = mutableListOf()
        repeat(amount.maxPurchase) {
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

    private fun lottoStatistics(result: Map<Int, List<Lotto>>, purchase: Int) {
        printView.printWithLine(LottoStringConstants.EMPTY_TEXT)
        printView.printWithLine(LottoStringConstants.DIVIDE_TEXT)
        val winningCount = result.values.flatten().size
        val benefitRate = calculateBenefitRate(winningCount = winningCount, purchase = purchase)

        Prize.entries.toTypedArray()
            .sortedByDescending { it.matchCount }
            .forEach { prize ->
                printView.printWithLine("${prize.displayText}${result[prize.matchCount]?.size ?: 0}개")
            }
        printView.printWithLine("${LottoStringConstants.STATISTICS_PREFIX_TEXT}${benefitRate}${LottoStringConstants.STATISTICS_POSTFIX_TEXT}")
    }

    private fun calculateBenefitRate(winningCount: Int, purchase: Int): String {
        return "%.2f".format(winningCount.toDouble() / purchase.toDouble())
    }
}