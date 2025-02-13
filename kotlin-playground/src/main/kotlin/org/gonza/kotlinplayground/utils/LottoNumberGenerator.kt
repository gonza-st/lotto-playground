package org.gonza.kotlinplayground.utils

object LottoNumberGenerator {
    fun generate(range: List<Int>, take: Int): List<Int> {
        require(range.size > take) { "요소의 개수를 초과하여 생성할 수 없습니다." }
        return range.shuffled().take(take)
    }
}