package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("보너스 번호를 생성할 수 있다.")
    void createBonusLottoNumber() {
        // given
        int bonusNumber = 7;

        // when
        LottoNumber bonusLottoNumber = LottoNumber.createBonusLottoNumber(bonusNumber);

        // then
        assertThat(bonusLottoNumber.lottoNumbers()).containsExactly(bonusNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1, 100})
    @DisplayName("보너스 번호가 유효한 범위를 벗어나면 예외가 발생한다.")
    void createBonusLottoNumberWithInvalidRange(int invalidBonusNumber) {
        // then
        assertThatThrownBy(() -> LottoNumber.createBonusLottoNumber(invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("수동 로또 번호를 생성할 수 있다.")
    void generateManualLottoNumbers() {
        // given
        List<String> inputData = Arrays.asList(
                "1, 2, 3, 4, 5, 6",
                "7, 8, 9, 10, 11, 12"
        );

        // when
        List<LottoNumber> manualLottoNumbers = LottoNumber.generateManualLottoNumbers(inputData);

        // then
        assertThat(manualLottoNumbers).hasSize(2);
        assertThat(manualLottoNumbers.get(0).lottoNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(manualLottoNumbers.get(1).lottoNumbers()).containsExactly(7, 8, 9, 10, 11, 12);
    }

    @Test
    @DisplayName("수동 로또 번호 입력이 잘못된 형식이면 예외가 발생한다.")
    void generateManualLottoNumbersWithInvalidFormat() {
        // given
        List<String> invalidInputs = List.of("1, 2, 3, 4, 5");

        // then
        assertThatThrownBy(() -> LottoNumber.generateManualLottoNumbers(invalidInputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("수동 로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void generateManualLottoNumbersWithDuplicateNumbers() {
        // given
        List<String> duplicateInputs = List.of("1, 2, 3, 4, 5, 5");

        // then
        assertThatThrownBy(() -> LottoNumber.generateManualLottoNumbers(duplicateInputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호에 중복된 숫자가 있습니다.");
    }

    @Test
    @DisplayName("수동 로또 번호의 범위가 유효하지 않으면 예외가 발생한다.")
    void generateManualLottoNumbersWithInvalidRange() {
        // given
        List<String> invalidRangeInputs = List.of("1, 2, 3, 4, 5, 46");

        // then
        assertThatThrownBy(() -> LottoNumber.generateManualLottoNumbers(invalidRangeInputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 이용해 등수를 계산할 수 있다.")
    void getRanking() {
        // given
        LottoNumber lottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber winningNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 7));
        LottoNumber bonusNumber = LottoNumber.createBonusLottoNumber(6);

        // when
        Ranking ranking = lottoNumber.getRanking(winningNumber, bonusNumber);

        // then
        assertThat(ranking).isEqualTo(Ranking.SECOND);
    }

    @Test
    @DisplayName("당첨 번호와 부분적으로 일치할 경우 맞는 개수를 반환한다.")
    void getPartialMatchCount() {
        // given
        LottoNumber lottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber winningNumber = new LottoNumber(List.of(1, 2, 3, 10, 11, 12));

        // when
        int matchCount = lottoNumber.getMatchCount(winningNumber);

        // then
        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 번호를 이용해 등수를 계산할 때 보너스 번호가 일치하지 않는 경우")
    void getRankingWithoutBonusMatch() {
        // given
        LottoNumber lottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber winningNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 7));
        LottoNumber bonusNumber = LottoNumber.createBonusLottoNumber(8);

        // when
        Ranking ranking = lottoNumber.getRanking(winningNumber, bonusNumber);

        // then
        assertThat(ranking).isEqualTo(Ranking.THIRD);
    }
}
