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
        val amount: Amount = getAmount()
        val manual: Manual = getManualLottoCount()
        val purchase = Purchase(amount = amount, manual = manual)
        val manualLotto = getManualLotto(manual = manual)
        val lottoTickets: LottoTickets = getLottoTickets(manualLotto = manualLotto, purchase = purchase)
        printLotto(purchase = purchase, lottoTickets = lottoTickets)
        val winningLotto = getWinningLotto()
        val bonusBall: LottoNumber = getBonusBall()
        addBonusBall(userManualNumber = winningLotto, bonusBall = bonusBall)
        lottoStatistics(answer = winningLotto.numberList, lottoTickets = lottoTickets, purchase = purchase.totalPaper)
    }

    private fun getAmount(): Amount {
        printView.printWithLine(LottoStringConstants.BEFORE_PURCHASE_INPUT_HELP_TEXT)
        try {
            val totalInput = inputView.input().trim().toInt()
            val amount = Amount(total = totalInput)
            return amount
        } catch (e: NumberFormatException) {
            throw NumberFormatException(LottoStringConstants.INVALID_PURCHASE_FORMAT)
        }
    }

    private fun getLottoTickets(manualLotto: List<Lotto>, purchase: Purchase): LottoTickets {
        val lottoList: MutableList<Lotto> = mutableListOf()

        lottoList.addAll(manualLotto)
        repeat(purchase.autoCount) {
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

    private fun getManualLottoCount(): Manual {
        printView.printWithLine(LottoStringConstants.BEFORE_MANUAL_PURCHASE_INPUT_HELP_TEXT)
        try {
            val input = inputView.input().trim().toInt()
            return Manual(count = input)
        } catch (e: NumberFormatException) {
            throw NumberFormatException(LottoStringConstants.INVALID_PURCHASE_FORMAT)
        }
    }

    private fun getManualLotto(manual: Manual): List<Lotto> {
        printView.printWithLine(LottoStringConstants.BEFORE_MANUAL_LOTTO_INPUT_HELP_TEXT)

        val manualLottoList = mutableListOf<Lotto>()
        for (i in 0 until manual.count) {
            val input = inputView.input().trim()
            val userManualNumber = UserManualNumber.from(input = input)
            val lotto = Lotto(lottoNumberList = userManualNumber.numberList)

            manualLottoList.add(lotto)
        }

        return manualLottoList
    }

    private fun printLotto(purchase: Purchase, lottoTickets: LottoTickets) {
        printView.printWithLine(LottoStringConstants.EMPTY_TEXT)
        printView.printWithLine("수동으로 ${purchase.manualCount}, 자동으로 ${purchase.autoCount}개를 구매했습니다.")
        printView.printWithLine(lottoTickets.toString())
        printView.printWithLine(LottoStringConstants.EMPTY_TEXT)
    }

    private fun getWinningLotto(): UserManualNumber {
        printView.printWithLine(LottoStringConstants.WINNING_NUMBER_INPUT_HELP_TEXT)
        val winningNumberString = inputView.input()
        val userManualNumber = UserManualNumber.from(input = winningNumberString)

        return userManualNumber
    }

    private fun getBonusBall(): LottoNumber {
        printView.printWithLine(LottoStringConstants.BONUS_BALL_INPUT_HELP_TEXT)
        val bonusBallString = inputView.input()
        val bonusBall = LottoNumber(number = NumberConverter.convert(str = bonusBallString), type = NumberType.BONUS)

        return bonusBall
    }

    private fun addBonusBall(userManualNumber: UserManualNumber, bonusBall: LottoNumber) {
        userManualNumber.numberList + bonusBall
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