package org.gonza.javaplayground.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {

    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(new LottoNumberGenerator());
    }

    @Test
    @DisplayName("구입 금액에 맞게 로또 티켓을 생성할 수 있다.")
    void generateLottoTicketTest() throws Exception {
        BigDecimal purchasePrice1 = new BigDecimal(14000);
        BigDecimal purchasePrice2 = BigDecimal.valueOf(1000);

        LottoTicket purchasedTicket1 = lottoMachine.generateLottoTicket(new Money(purchasePrice1));
        LottoTicket purchasedTicket2 = lottoMachine.generateLottoTicket(new Money(purchasePrice2));

        assertThat(purchasedTicket1.lottoNumbers().size()).isEqualTo(14);
        assertThat(purchasedTicket2.lottoNumbers().size()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 500, 999})
    @DisplayName("로또 구매 가격이 1000원 미만일 경우 예외 발생")
    void generateLottoTicketFailTest_under1000(int invalidValue) throws Exception {
        Money invalidMoney = new Money(new BigDecimal(invalidValue));

        assertThatThrownBy(() -> lottoMachine.generateLottoTicket(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1000원 이상부터 구매할 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1300, 3387, 9999})
    @DisplayName("로또 구매 가격이 1000원 단위가 아닐 경우 예외 발생")
    void generateLottoTicketFailTest_invalidPriceUnit(int invalidValue) throws Exception {
        Money invalidMoney = new Money(new BigDecimal(invalidValue));

        assertThatThrownBy(() -> lottoMachine.generateLottoTicket(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 구매 금액은 1000원 단위여야 합니다.");
    }

    @Test
    @DisplayName("수동 구매와 자동 구매를 조합하여 로또 티켓을 생성할 수 있다")
    void generateLottoTicketWithManualNumbersTest() {
        // given
        BigDecimal purchasePrice = new BigDecimal(5000);
        List<LottoNumber> manualNumbers = Arrays.asList(
                new LottoNumber(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoNumber(Arrays.asList(7, 8, 9, 10, 11, 12))
        );

        // when
        LottoTicket ticket = lottoMachine.generateLottoTicket(new Money(purchasePrice), manualNumbers);

        // then
        assertThat(ticket.lottoNumbers().size()).isEqualTo(5);
        assertThat(ticket.lottoNumbers()).containsAll(manualNumbers);

        long autoTicketCount = ticket.lottoNumbers().stream()
                .filter(number -> !manualNumbers.contains(number))
                .count();
        assertThat(autoTicketCount).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 구매 수가 총 구매 가능 수를 초과하면 예외 발생")
    void generateLottoTicketWithExcessiveManualNumbersTest() {
        // given
        BigDecimal purchasePrice = new BigDecimal(2000);
        List<LottoNumber> manualNumbers = Arrays.asList(
                new LottoNumber(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoNumber(Arrays.asList(7, 8, 9, 10, 11, 12)),
                new LottoNumber(Arrays.asList(13, 14, 15, 16, 17, 18))
        );

        // then
        assertThatThrownBy(() -> lottoMachine.generateLottoTicket(new Money(purchasePrice), manualNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 수가 총 구매 가능한 로또 수를 초과합니다.");
    }

    @Test
    @DisplayName("수동 구매 없이 자동으로만 구매할 수 있다")
    void generateLottoTicketWithOnlyAutoNumbersTest() {
        // given
        BigDecimal purchasePrice = new BigDecimal(3000);
        List<LottoNumber> emptyManualNumbers = new ArrayList<>();

        // when
        LottoTicket ticket = lottoMachine.generateLottoTicket(new Money(purchasePrice), emptyManualNumbers);

        // then
        assertThat(ticket.lottoNumbers().size()).isEqualTo(3);

        for (LottoNumber number : ticket.lottoNumbers()) {
            assertThat(number.lottoNumbers().size()).isEqualTo(6);
            number.lottoNumbers().forEach(n -> assertThat(n).isBetween(1, 45));
        }
    }

    @Test
    @DisplayName("수동 구매만으로 로또 티켓을 구매할 수 있다")
    void generateLottoTicketWithOnlyManualNumbersTest() {
        // given
        BigDecimal purchasePrice = new BigDecimal(3000);
        List<LottoNumber> manualNumbers = Arrays.asList(
                new LottoNumber(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoNumber(Arrays.asList(7, 8, 9, 10, 11, 12)),
                new LottoNumber(Arrays.asList(13, 14, 15, 16, 17, 18))
        );

        // when
        LottoTicket ticket = lottoMachine.generateLottoTicket(new Money(purchasePrice), manualNumbers);

        // then
        assertThat(ticket.lottoNumbers().size()).isEqualTo(3);
        assertThat(ticket.lottoNumbers()).containsExactlyElementsOf(manualNumbers);
    }
}
