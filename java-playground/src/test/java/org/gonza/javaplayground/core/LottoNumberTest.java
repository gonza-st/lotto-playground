package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberTest {

    @Test
    @DisplayName("당첨 번호와 몇개가 같은지 비교할 수 있다.")
    void getMatchCount() {
        // given
        LottoNumber lottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber winningLottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));

        // when
        int matchCount = lottoNumber.getMatchCount(winningLottoNumber);

        // then
        assertThat(matchCount).isEqualTo(6);
    }

}
