package org.gonza.javaplayground.lotto.domain;

import org.gonza.javaplayground.lotto.application.RandomLottoNumbers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class LottosTest {

    @Test
    void Lottos를_생성할_수_있다() {
        LottoNumber lottoNumber = RandomLottoNumbers.generate();

        Lottos lottos = Lottos.of(List.of(lottoNumber));

        assertNotNull(lottos);
    }

    @Test
    void Lottos에_당첨_여부를_확인할_수_있다() {
        LottoNumber lottoNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = Lottos.of(List.of(lottoNumber, lottoNumber));

        lottos.verify(lottoNumber);

        assertEquals(2, lottos.wonLottosSize());
    }

    @Test
    void 한_개만_당첨이면_won_lottos_는_한_개이다() {
        LottoNumber wonNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lostNumber = LottoNumber.of(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos = Lottos.of(List.of(wonNumber, lostNumber));

        lottos.verify(wonNumber);

        assertEquals(1, lottos.wonLottosSize());
    }

    @Test
    void 모두_당첨되지_않을_수_있다() {
        LottoNumber wonNumber = LottoNumber.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lostNumber = LottoNumber.of(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos = Lottos.of(List.of(lostNumber, lostNumber));

        lottos.verify(wonNumber);

        assertEquals(0, lottos.wonLottosSize());
    }
}