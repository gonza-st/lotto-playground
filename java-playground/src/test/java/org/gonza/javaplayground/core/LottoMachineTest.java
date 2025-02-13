package org.gonza.javaplayground.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {
    @Test
    @DisplayName("구입 금액에 맞게 로또 티켓을 생성할 수 있다.")
    void generateLottoTicketTest() throws Exception {
        BigDecimal purchasePrice1 = new BigDecimal(14000);
        BigDecimal purchasePrice2 = BigDecimal.valueOf(1000);
        LottoMachine lottoMachine = new LottoMachine();

        int purchasedTicket1 = lottoMachine.generateLottoTicket(new Money(purchasePrice1));
        int purchasedTicket2 = lottoMachine.generateLottoTicket(new Money(purchasePrice2));

        assertThat(purchasedTicket1).isEqualTo(14);
        assertThat(purchasedTicket2).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 500, 999})
    @DisplayName("로또 구매 가격이 1000원 미만일 경우 예외 발생")
    void generateLottoTicketFailTest_under1000(int invalidValue) throws Exception {
        Money invalidMoney = new Money(new BigDecimal(invalidValue));
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> lottoMachine.generateLottoTicket(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1000원 이상부터 구매할 수 있습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1300, 3387, 9999})
    @DisplayName("로또 구매 가격이 1000원 단위가 아닐 경우 예외 발생")
    void generateLottoTicketFailTest_invalidPriceUnit(int invalidValue) throws Exception {
        Money invalidMoney = new Money(new BigDecimal(invalidValue));
        LottoMachine lottoMachine = new LottoMachine();

        assertThatThrownBy(() -> lottoMachine.generateLottoTicket(invalidMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 구매 금액은 1000원 단위여야 합니다.");

    }
}
