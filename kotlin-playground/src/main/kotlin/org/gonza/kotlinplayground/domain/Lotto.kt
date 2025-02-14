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

    fun updateStatus(status: LottoStatus) {
        require(this.status == LottoStatus.UNKNOWN) { "변경된 상태는 다시 수정할 수 없습니다." }
        this.status = status
    }

    private fun validateLottoNumberList() {
        require(lottoNumberList.size <= limit) { "로또 번호 개수는 ${limit}개를 초과할 수 없습니다." }
    }
}