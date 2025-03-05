package org.gonza.javaplayground.lotto.application;

import java.util.ArrayList;
import java.util.List;

import org.gonza.javaplayground.lotto.LottoConstant;
import org.gonza.javaplayground.lotto.domain.LottoNumbers;
import org.gonza.javaplayground.lotto.domain.Lottos;
import org.gonza.javaplayground.lotto.domain.Price;

// TODO: 금액을 차감하는 방식은 어떤가
// TODO: 수동과 자동 번호의 추상화는 어떤가
public class LottoIssuer {

    public static Lottos issue(Price price) {
        int pages = getLottoPages(price);
        List<LottoNumbers> lottoNumbers = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            lottoNumbers.add(RandomLottoNumbers.generate());
        }

        return Lottos.of(lottoNumbers);
    }

    public static Lottos issue(Price price, List<List<Integer>> manualNumbers) {
        int automaticPages = getAutomaticPages(price, manualNumbers.size());
        List<LottoNumbers> lottoNumbers = new ArrayList<>();


        for (int i = 0; i < automaticPages; i++) {
            lottoNumbers.add(RandomLottoNumbers.generate());
        }

		for (List<Integer> manualNumber : manualNumbers) {
			lottoNumbers.add(LottoNumbers.manualOf(manualNumber));
		}

        return Lottos.of(lottoNumbers);
    }

    private static int getAutomaticPages(Price price, int size) {
        int pages = getLottoPages(price);
        return pages - size;
    }

    private static int getLottoPages(Price price) {
        Price lottoPrice = Price.of(LottoConstant.LOTTO_PRICE);
        return price.divide(lottoPrice)
                .intValue();
    }
}
