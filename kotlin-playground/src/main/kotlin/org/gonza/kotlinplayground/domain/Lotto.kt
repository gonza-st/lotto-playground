package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.enum.LottoStatus
import org.gonza.kotlinplayground.enum.NumberType
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

    fun updateStatus(status: LottoStatus) {
        if (this.status == status) return
        this.status = status
    }

    fun compareAll(target: List<LottoNumber>): Boolean {
        val normalNumbers = target.filter { it.type == NumberType.NORMAL }
        val bonusNumber = target.find { it.type == NumberType.BONUS }

        // 일치하는 번호 찾기
        val matchedNumbers = lottoNumberList.filter { myNumber ->
            normalNumbers.any { it.number == myNumber.number }
        }
        val matchCount = matchedNumbers.size
        val hasBonusMatch = bonusNumber != null &&
                lottoNumberList.any { it.number == bonusNumber.number }
        val isWinner = matchCount >= LottoConstants.WINNER_CRITERIA
        val isBonus = matchCount == 5 && hasBonusMatch
        this.winnerNumberList = matchedNumbers

        if (isBonus) {
            updateStatus(LottoStatus.BONUS)
            return true
        }
        if (isWinner) {
            updateStatus(LottoStatus.WON)
            return true
        }

        updateStatus(LottoStatus.LOST)
        return false
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
