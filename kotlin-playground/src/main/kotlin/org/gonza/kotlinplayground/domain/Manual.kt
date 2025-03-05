package org.gonza.kotlinplayground.domain

class Manual(val count: Int) {
    init {
        require(count >= 0) { "구매 수는 음수일 수 없습니다." }
    }
}