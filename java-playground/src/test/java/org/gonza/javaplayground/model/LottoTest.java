package org.gonza.javaplayground.model;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("로또는 6개의 번호로 생성할 수 있다")
    void 정상_생성_테스트() {
        List<LottoNumber> 로또번호들 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        Lotto lotto = new Lotto(로또번호들);
        
        List<LottoNumber> 가져온_번호들 = lotto.로또번호_목록_가져오기();
        assertEquals(6, 가져온_번호들.size());
        for (LottoNumber number : 로또번호들) {
            assertTrue(가져온_번호들.contains(number));
        }
    }

    @Test
    @DisplayName("로또 번호가 6개보다 적으면 예외가 발생한다")
    void 적은_번호_예외_테스트() {
        List<LottoNumber> 적은_번호들 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Lotto(적은_번호들));
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("로또 번호가 6개보다 많으면 예외가 발생한다")
    void 많은_번호_예외_테스트() {
        List<LottoNumber> 많은_번호들 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7)
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Lotto(많은_번호들));
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("로또 번호 목록은 불변이다")
    void 불변성_테스트() {
        List<LottoNumber> 번호들 = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
        Lotto lotto = new Lotto(번호들);

        List<LottoNumber> 가져온_번호들 = lotto.로또번호_목록_가져오기();
        assertThrows(UnsupportedOperationException.class, () -> 가져온_번호들.add(new LottoNumber(7)));
    }
}
