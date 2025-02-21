package org.gonza.kotlinplayground.domain

class Amount(val total: Int) {
    init {
        require(total > 0) { "금액은 양수만 가능합니다." }
    }
}