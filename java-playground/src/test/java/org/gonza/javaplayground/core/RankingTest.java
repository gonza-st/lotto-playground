package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RankingTest {

    @Test
    @DisplayName("매치 개수와 보너스 번호 일치 여부에 따른 등수를 반환한다.")
    void returnMatchRankingSuccessTest() throws Exception {
        record TestCase(int matchCount, boolean bonusMatch, Ranking expectedRanking) {
        }

        List<TestCase> testCases = List.of(
                new TestCase(6, false, Ranking.FIRST),
                new TestCase(6, true, Ranking.FIRST),
                new TestCase(5, true, Ranking.SECOND),
                new TestCase(5, false, Ranking.THIRD),
                new TestCase(4, false, Ranking.FOURTH),
                new TestCase(4, true, Ranking.FOURTH),
                new TestCase(3, false, Ranking.FIFTH),
                new TestCase(3, true, Ranking.FIFTH),
                new TestCase(2, false, Ranking.NONE),
                new TestCase(1, false, Ranking.NONE),
                new TestCase(0, false, Ranking.NONE)
        );

        assertSoftly(softly -> {
            for (TestCase testCase : testCases) {
                softly.assertThat(Ranking.valueOf(testCase.matchCount(), testCase.bonusMatch()))
                        .isEqualTo(testCase.expectedRanking());
            }
        });
    }
}
