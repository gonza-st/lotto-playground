package org.gonza.kotlinplayground.domain

import Lotto
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

    @Test
    fun `수동 로또와 amount를 입력하면 발급 시에 수동 로또 번호와 함께 출력한다`() {
        val lottoIssuer = LottoIssuer()
        val manualLottoList =
            listOf(
                Lotto.create(numberString = "1, 2, 3, 4, 5, 6"),
                Lotto.create(numberString = "7, 8, 9, 10, 11, 12"),
            )
        val amount = 14

        val lottos = lottoIssuer.buy(amount = amount, manualLottoList = manualLottoList)

        Assertions.assertThat(lottos.size()).isEqualTo(amount + manualLottoList.size)
    }

    @Test
    fun `수동 로또와 amount를 입력한 다른 두 lottos에 똑같이 수동 번호가 들어가 있다`() {
        val lottoIssuer = LottoIssuer()
        val manualLottoList =
            listOf(
                Lotto.create(numberString = "1, 2, 3, 4, 5, 6"),
                Lotto.create(numberString = "7, 8, 9, 10, 11, 12"),
            )
        val amount = 14

        val lottosFirst = lottoIssuer.buy(amount = amount, manualLottoList = manualLottoList)
        val lottosSecond = lottoIssuer.buy(amount = amount, manualLottoList = manualLottoList)

        Assertions.assertThat(lottosFirst.contains(lottoList = manualLottoList)).isTrue()
        Assertions.assertThat(lottosSecond.contains(lottoList = manualLottoList)).isTrue()
    }
}
