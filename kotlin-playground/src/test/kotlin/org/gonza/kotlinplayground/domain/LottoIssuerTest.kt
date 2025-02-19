package org.gonza.kotlinplayground.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoIssuerTest {
    @Test
    fun `금액을 입력하면 몇 장을 살 수 있는지 반환한다`() {
        val lottoIssuer = LottoIssuer()
        val money = 14000

        val amount: Int = lottoIssuer.amount(money = money)

        val expectedAmount = 14

        Assertions.assertThat(amount).isEqualTo(expectedAmount)
    }

    @Test
    fun `로또 구입 금액이 1,000원 단위가 아니라면 에러를 반환한다`() {
        val lottoIssuer = LottoIssuer()
        val money = 14200

        Assertions
            .assertThatThrownBy {
                lottoIssuer.amount(money = money)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("1000원 단위로만 구매 가능합니다.")
    }
}
