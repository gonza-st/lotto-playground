package org.gonza.kotlinplayground.domain

import Lotto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호 6개로 Lotto를 생성한다`() {
        val lottoSet: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val lotto: Lotto = Lotto.create(lottoNumberSet = lottoSet)

        val expectedLotto: Lotto = Lotto.create(lottoNumberSet = lottoSet)

        Assertions.assertThat(lotto).isEqualTo(expectedLotto)
    }

    @Test
    fun `로또 번호를 출력한다`() {
        val lottoSet: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )

        val lotto = Lotto.create(lottoNumberSet = lottoSet)
        val result: String = lotto.toString()

        val expectedValue = "[1, 2, 3, 4, 5, 6]"

        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun `로또를 자동으로 발행한다`() {
        val lotto: Lotto = Lotto.create()
    }

    @Test
    fun `로또 번호가 작은 순으로 정렬되어있다`() {
        val unsortedLottoSet: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(4),
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(6),
                LottoNumber(3),
                LottoNumber(5),
            )

        val sortedLottoSet: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val lotto: Lotto = Lotto.create(lottoNumberSet = unsortedLottoSet)

        val expectedValue = Lotto.create(lottoNumberSet = sortedLottoSet)

        Assertions.assertThat(lotto).isEqualTo(expectedValue)
    }

    @Test
    fun `로또의 번호가 6개가 아니라면 에러를 반환한다`() {
        val sevenNumberLottoSet: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
                LottoNumber(7),
            )
        val fiveNumberLottoSet: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
            )

        Assertions
            .assertThatCode {
                Lotto.create(lottoNumberSet = sevenNumberLottoSet)
                Lotto.create(lottoNumberSet = fiveNumberLottoSet)
            }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 6개여야 합니다.")
    }

    @Test
    fun `로또 두개를 비교해서 몇 개의 번호가 맞는지 비교한다`() {
        val lottoSet1: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )
        val lottoSet2: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(7),
                LottoNumber(8),
                LottoNumber(9),
            )

        val lotto1: Lotto = Lotto.create(lottoNumberSet = lottoSet1)
        val lotto2: Lotto = Lotto.create(lottoNumberSet = lottoSet2)

        val result: Int = lotto1.matchCount(lotto2)

        val expectedValue = 3

        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun `우승 번호 입력 시 , 이외의 특수문자가 들어가면 에러가 발생한다`() {
        Assertions
            .assertThatThrownBy { Lotto.create(numberString = "1|2|3|4|5|6") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 숫자와 쉼표(,)만 입력 가능합니다.")
    }

    @Test
    fun `우승 번호를 입력하면 로또를 생성한다`() {
        val winningLotto = Lotto.create(numberString = "1,2,3,4,5,6")

        val expectedValue = Lotto.create(numberList = listOf(1, 2, 3, 4, 5, 6))

        Assertions.assertThat(winningLotto).isEqualTo(expectedValue)
    }

    @Test
    fun `보너스 번호를 포함하는지 확인한다`() {
        val bonus = LottoNumber(7)

        val notMatchedLotto = Lotto.create(numberString = "1,2,3,4,5,6")
        val matchedLotto = Lotto.create(numberString = "1,2,3,4,5,7")

        val notMatchedResult = notMatchedLotto.matchBonus(bonus = bonus)
        val matchedResult = matchedLotto.matchBonus(bonus = bonus)

        Assertions.assertThat(notMatchedResult).isFalse()
        Assertions.assertThat(matchedResult).isTrue()
    }

    @Test
    fun `숫자 문자열 리스트를 받아서 Lotto를 벌크로 생성한다`() {
        val numberStringList = listOf("1,2,3,4,5,6", "7,8,9,10,11,12")

        val lottoList: List<Lotto> = Lotto.bulkCreate(numberStringList = numberStringList)

        val expectedValue =
            listOf(
                Lotto.create(numberList = listOf(1, 2, 3, 4, 5, 6)),
                Lotto.create(numberList = listOf(7, 8, 9, 10, 11, 12)),
            )

        Assertions.assertThat(lottoList).isEqualTo(expectedValue)
    }

    @Test
    fun `빈 리스트의 numberStringLis가 들어오면 빈 리스트를 반환한다`() {
        val numberStringList = emptyList<String>()

        val lottoList: List<Lotto> = Lotto.bulkCreate(numberStringList = numberStringList)

        val expectedValue = emptyList<String>()

        Assertions.assertThat(lottoList).isEqualTo(expectedValue)
    }
}
