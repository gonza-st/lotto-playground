package org.gonza.javaplayground.model;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto();
    }

    @Test
    @DisplayName("로또는 생성 시 빈 번호 목록을 가진다")
    void 초기_상태_테스트() {
        ArrayList<LottoNumber> 번호_목록 = lotto.번호_목록_가져오기();

        assertTrue(번호_목록.isEmpty());
    }

    @Test
    @DisplayName("로또 번호를 추가할 수 있다")
    void 번호_추가_테스트() {
        LottoNumber 로또_번호 = new LottoNumber(1);

        lotto.번호_추가하기(로또_번호);

        ArrayList<LottoNumber> 번호_목록 = lotto.번호_목록_가져오기();
        assertEquals(1, 번호_목록.size());
        assertEquals(로또_번호, 번호_목록.get(0));
    }

    @Test
    @DisplayName("로또 번호를 여러 개 추가할 수 있다")
    void 여러_번호_추가_테스트() {
        LottoNumber 첫번째_번호 = new LottoNumber(1);
        LottoNumber 두번째_번호 = new LottoNumber(2);
        LottoNumber 세번째_번호 = new LottoNumber(3);

        lotto.번호_추가하기(첫번째_번호);
        lotto.번호_추가하기(두번째_번호);
        lotto.번호_추가하기(세번째_번호);

        ArrayList<LottoNumber> 번호_목록 = lotto.번호_목록_가져오기();
        assertEquals(3, 번호_목록.size());
        assertTrue(번호_목록.contains(첫번째_번호));
        assertTrue(번호_목록.contains(두번째_번호));
        assertTrue(번호_목록.contains(세번째_번호));
    }
}