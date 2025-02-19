package org.gonza.kotlinplayground.domain

import Lotto
import org.assertj.core.api.Assertions
import org.gonza.kotlinplayground.dto.MatchResultDto
import org.junit.jupiter.api.Test

class LottosTest {
    @Test
    fun `출력하면 로또의 번호들이 출력된다`() {
        val lottoNumberSet: MutableSet<LottoNumber> =
            mutableSetOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6),
            )

        val lotto1 = Lotto.create(lottoNumberSet = lottoNumberSet)
        val lotto2 = Lotto.create(lottoNumberSet = lottoNumberSet)
        val lotto3 = Lotto.create(lottoNumberSet = lottoNumberSet)
        val lottos = Lottos(value = listOf(lotto1, lotto2, lotto3))

        val result: List<String> = lottos.print()

        val expectedValue =
            listOf(
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
                "[1, 2, 3, 4, 5, 6]",
            )

        Assertions.assertThat(result).isEqualTo(expectedValue)
    }

    @Test
    fun `당첨 로또와 비교해서 몇 개씩 맞았는지 반환한다`() {
        val winningLotto =
            Lotto.create(
                mutableSetOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val lottoList: List<Lotto> =
            listOf(
                Lotto.create(
                    mutableSetOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(7),
                        LottoNumber(8),
                        LottoNumber(9),
                    ),
                ),
                Lotto.create(
                    mutableSetOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(7),
                        LottoNumber(8),
                    ),
                ),
                Lotto.create(
                    mutableSetOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(7),
                    ),
                ),
                Lotto.create(
                    mutableSetOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                    ),
                ),
            )

        val lottos = Lottos(value = lottoList)

        val matchResult: MatchResultDto = lottos.matchResult(winningLotto = winningLotto)

        val expectedThreeMatchedResult = 1
        val expectedFourMatchedResult = 1
        val expectedFiveMatchedResult = 1
        val expectedSixMatchedResult = 1

        Assertions.assertThat(matchResult.three).isEqualTo(expectedThreeMatchedResult)
        Assertions.assertThat(matchResult.four).isEqualTo(expectedFourMatchedResult)
        Assertions.assertThat(matchResult.five).isEqualTo(expectedFiveMatchedResult)
        Assertions.assertThat(matchResult.six).isEqualTo(expectedSixMatchedResult)
    }
}
