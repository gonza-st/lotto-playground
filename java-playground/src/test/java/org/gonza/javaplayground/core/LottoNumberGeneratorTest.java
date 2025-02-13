package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("로또 번호를 생성할 수 있다.")
    void generateLottoNumberSuccessTest() throws Exception {

        // given
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        // when
        List<Integer> lottoNumbers = lottoNumberGenerator.generateLottoNumber();

        // then
        assertNotNull(lottoNumbers);
        assertEquals(6, lottoNumbers.size());
    }
}
