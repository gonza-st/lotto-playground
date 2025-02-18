package org.gonza.kotlinplayground.domain

import org.gonza.kotlinplayground.enum.LottoStatus
import org.gonza.kotlinplayground.utils.LottoConstants

class Lotto(
    val lottoNumberList: List<LottoNumber>,
    private val limit : Int = LottoConstants.MAX_LOTTO_NUMBER_HAVE_COUNT,
) {
    init {
        validateLottoNumberList()
    }

    var status: LottoStatus = LottoStatus.UNKNOWN
        private set

    var winnerNumberList: List<LottoNumber> = emptyList()
        get() {
            require(status != LottoStatus.UNKNOWN) { "당첨 번호와 일치하는지 확인해주세요." }
            return field
        }
        private set

    fun updateStatus(status: LottoStatus) {
        require(this.status == LottoStatus.UNKNOWN) { "변경된 상태는 다시 수정할 수 없습니다." }
        this.status = status
    }

    fun compareAll(target: List<LottoNumber>): Boolean {
        val targetNumberList = target.map { it.number }.toSet()
        val winnerNumberList = this.lottoNumberList.filter { it.number in targetNumberList }
        val isWinner = winnerNumberList.size >= LottoConstants.WINNER_CRITERIA
        this.winnerNumberList = winnerNumberList

        if (isWinner) updateStatus(LottoStatus.WIN)
        else updateStatus(LottoStatus.LOSE)

        return isWinner
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
