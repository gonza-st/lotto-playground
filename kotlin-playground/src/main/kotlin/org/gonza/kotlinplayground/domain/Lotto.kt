package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.enum.LottoStatus
import org.gonza.kotlinplayground.enum.NumberType
import org.gonza.kotlinplayground.enum.Prize
import org.gonza.kotlinplayground.utils.LottoConstants

class Lotto(
    val lottoNumberList: List<LottoNumber>,
    private val limit: Int = LottoConstants.MAX_LOTTO_NUMBER_HAVE_COUNT,
) {
    init {
        validateLottoNumberList()
    }

    var status: LottoStatus = LottoStatus.ISSUE
        private set

    var winnerNumberList: List<LottoNumber> = emptyList()
        get() {
            require(status != LottoStatus.ISSUE) { "당첨 번호와 일치하는지 확인해주세요." }
            return field
        }
        private set

    var isBonus: Boolean = false
        private set

    fun updateStatus(status: LottoStatus) {
        if (this.status == status) return
        this.status = status
    }

    fun compareAll(target: List<LottoNumber>): Boolean {
        // 일치하는 번호 찾기
        val matchedNumbers = getMatchedCount(target = target)
        val matchCount = matchedNumbers.size
        val isWinner = matchCount >= LottoConstants.WINNER_CRITERIA
        this.winnerNumberList = matchedNumbers

        if (isMatchedBonus(target = target)) {
            isBonus = true
            updateStatus(LottoStatus.WON)
            return true
        }
        if (isWinner) {
            updateStatus(LottoStatus.WON)
            return true
        }

        updateStatus(LottoStatus.LOST)
        return false
    }

    private fun isMatchedBonus(target: List<LottoNumber>): Boolean {
        val bonusNumber = target.find { it.type == NumberType.BONUS }
        val hasBonusMatch = bonusNumber != null &&
                lottoNumberList.any { it.number == bonusNumber.number }

        val matchedCount = getMatchedCount(target = target).size

        return matchedCount == Prize.SECOND.matchCount && hasBonusMatch
    }

    private fun getMatchedCount(target: List<LottoNumber>): List<LottoNumber> {
        val normalNumbers = target.filter { it.type == NumberType.NORMAL }
        return lottoNumberList.filter { myNumber ->
            normalNumbers.any { it.number == myNumber.number }
        }
    }


    private fun validateLottoNumberList() {
        require(lottoNumberList.size <= limit) { "로또 번호 개수는 ${limit}개를 초과할 수 없습니다." }
    }

    override fun toString(): String {
        return lottoNumberList.joinToString(
            prefix = "[",
            separator = ", ",
            postfix = "]"
        ) {
            it.number.toString()
        }
    }
}
