package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LottoNumberGeneratorTest {

    @Test
    @DisplayName("로또 번호를 생성할 수 있다.")
    void generateLottoNumberSuccessTest() throws Exception {

        // given
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

        // when
        LottoNumber lottoNumber = lottoNumberGenerator.generateLottoNumber();

        // then
        assertNotNull(lottoNumber);
        assertEquals(6, lottoNumber.lottoNumbers().size());
    }
}
