package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RankingTest {
    @Test
    @DisplayName("맞춘 갯수에 따른 등수를 반환한다.")
    void returnMatchRankingSuccessTest() throws Exception {
        //given
        Map<Integer, Ranking> matchCountToRanking = Map.of(
                6, Ranking.FIRST,
                5, Ranking.SECOND,
                4, Ranking.THIRD,
                3, Ranking.FOURTH,
                2, Ranking.NONE,
                1, Ranking.NONE,
                0, Ranking.NONE
        );

        //then
        assertSoftly(softly ->
                matchCountToRanking.forEach((matchCount, expectedRanking) ->
                        softly.assertThat(Ranking.valueOf(matchCount))
                                .isEqualTo(expectedRanking)
                )
        );
    }
}
