package org.gonza.kotlinplayground.enum

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class LottoRangeTest {
    @Nested
    inner class ValidationTest {
        @Test
        fun `START는 1이다`() {
            assertThat(LottoRange.START.value).isEqualTo(1)
        }

        @Test
        fun `END는 45이다`() {
            assertThat(LottoRange.END.value).isEqualTo(45)
        }

        @Test
        fun `SIZE는 6이다`() {
            assertThat(LottoRange.SIZE.value).isEqualTo(6)
        }
    }

    @Nested
    inner class CreateValidNumberTest {
        @RepeatedTest(100)
        fun `1부터 45 사이의 랜덤 숫자를 반환한다`() {
            val number = LottoRange.createValidNumber()

            assertThat(number).isBetween(LottoRange.START.value, LottoRange.END.value)
        }
    }
}
