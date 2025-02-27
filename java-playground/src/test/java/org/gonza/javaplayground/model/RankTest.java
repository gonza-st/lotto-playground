package org.gonza.javaplayground.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "6, true, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "4, true, FOURTH",
            "3, false, FIFTH",
            "3, true, FIFTH",
            "2, false, MISS",
            "1, false, MISS",
            "0, false, MISS"
    })
    @DisplayName("일치하는 번호 개수와 보너스 번호 일치 여부에 따라 당첨 등수를 결정한다")
    void valueOf(int 일치개수, boolean 보너스번호일치, Rank 예상등수) {
        Rank 실제등수 = Rank.valueOf(일치개수, 보너스번호일치);
        assertEquals(예상등수, 실제등수);
    }

    @Test
    @DisplayName("각 등수의 당첨금을 정확히 반환한다")
    void getWinningMoney() {
        assertEquals(2_000_000_000, Rank.FIRST.getWinningMoney());
        assertEquals(30_000_000, Rank.SECOND.getWinningMoney());
        assertEquals(1_500_000, Rank.THIRD.getWinningMoney());
        assertEquals(50_000, Rank.FOURTH.getWinningMoney());
        assertEquals(5_000, Rank.FIFTH.getWinningMoney());
        assertEquals(0, Rank.MISS.getWinningMoney());
    }
}