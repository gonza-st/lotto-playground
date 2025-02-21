package org.gonza.kotlinplayground.domain

import org.assertj.core.api.Assertions
import kotlin.test.Test

class LottoNumberTest {
    @Test
    fun `로또 번호는 1~45 사이여야 한다`() {
        val passedNumber: List<Int> = listOf(1, 44)

        passedNumber.map { LottoNumber(it) }
    }

    @org.junit.jupiter.api.Test
    fun `로또 번호가 1~45 사이가 아니라면 에러가 발생한다`() {
        val failedNumber: List<Int> = listOf(0, 46)

        Assertions
            .assertThatThrownBy { failedNumber.map { LottoNumber(it) } }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 1부터 45까지의 숫자여야 합니다.")
    }

    @Test
    fun `로또 번호를 출력한다`() {
        val lottoNumber: LottoNumber = LottoNumber(1)
        val result: String = lottoNumber.toString()

        val expectedValue: String = "1"

        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun `로또 번호 객체가 같은 값을 가졌다면 동일한 객체로 판단한다`() {
        val lottoNumber1: LottoNumber = LottoNumber(1)
        val lottoNumber2: LottoNumber = LottoNumber(1)

        Assertions.assertThat(lottoNumber1).isEqualTo(lottoNumber2)
    }

    @Test
    fun `로또 번호를 1~45 사이 숫자로 자동 생성한다`() {
        for (i in 1..100) {
            val lottoNumber: LottoNumber = LottoNumber()
            Assertions.assertThat(lottoNumber.value).isBetween(1, 45)
        }
    }
}
