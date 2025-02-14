package org.gonza.javaplayground.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningComparatorTest {

    private WinningComparator winningComparator;

    @BeforeEach
    void setUp() {
        winningComparator = new WinningComparator();
    }

    @Test
    @DisplayName("당첨 숫자와 비교할 수 있다.")
    void compareWinningNumberSuccessTest() throws Exception {
        // given
        LottoNumber lottoNumber1 = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber2 = new LottoNumber(List.of(13, 12, 33, 43, 5, 23));

        LottoNumber winningLottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));

        // when
        Ranking ranking1 = winningComparator.compareWinningNumber(lottoNumber1, winningLottoNumber);
        Ranking ranking2 = winningComparator.compareWinningNumber(lottoNumber2, winningLottoNumber);

        // then
        assertThat(ranking1).isEqualTo(Ranking.FIRST);
        assertThat(ranking2).isEqualTo(Ranking.NONE);
    }

}
