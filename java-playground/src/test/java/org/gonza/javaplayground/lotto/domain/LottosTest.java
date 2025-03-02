package org.gonza.javaplayground.lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.gonza.javaplayground.lotto.LottoSupport;
import org.gonza.javaplayground.lotto.application.RandomLottoNumbers;
import org.junit.jupiter.api.Test;
class LottosTest {

    @Test
    void Lottos를_생성할_수_있다() {
        LottoNumbers lottoNumbers = RandomLottoNumbers.generate();

        Lottos lottos = Lottos.of(List.of(lottoNumbers));

        assertNotNull(lottos);
    }

    @Test
    void Lottos에_당첨_여부를_확인할_수_있다() {
        LottoNumbers lottoNumbers = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = Lottos.of(List.of(lottoNumbers, lottoNumbers));

        lottos.verify(lottoNumbers, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(2, lottos.wonLottosSize());
    }

    @Test
    void 한_개만_당첨이면_won_lottos_는_한_개이다() {
        LottoNumbers wonNumber = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers lostNumber = LottoNumbers.of(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos = Lottos.of(List.of(wonNumber, lostNumber));

        lottos.verify(wonNumber, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(1, lottos.wonLottosSize());
    }

    @Test
    void 모두_당첨되지_않을_수_있다() {
        LottoNumbers wonNumber = LottoNumbers.of(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers lostNumber = LottoNumbers.of(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos = Lottos.of(List.of(lostNumber, lostNumber));

        lottos.verify(wonNumber, LottoSupport.BONUS_NUMBER_OF_45);

        assertEquals(0, lottos.wonLottosSize());
    }
}