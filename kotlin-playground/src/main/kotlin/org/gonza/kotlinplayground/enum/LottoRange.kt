package org.gonza.kotlinplayground.enum

enum class LottoRange(
    val value: Int,
) {
    START(1),
    END(45),
    SIZE(6), ;

    companion object {
        init {
            require(START.value < END.value) { "로또 범위의 시작 값은 끝 값보다 작아야 합니다" }
        }

        fun createValidNumber(): Int = (START.value..END.value).random()
    }
}
