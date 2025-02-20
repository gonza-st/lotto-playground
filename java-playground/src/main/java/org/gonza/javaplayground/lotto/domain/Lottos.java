package org.gonza.javaplayground.lotto.domain;

import java.util.List;

public class Lottos {
    public static Lottos of(List<LottoNumber> lottoNumbers) {
        List<Lotto> lottos = lottoNumbers.stream()
                .map(Lotto::of)
                .toList();
        return new Lottos(lottos);
    }

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int getSize() {
        return this.lottos.size();
    }

    public void matchBy(LottoNumber lottoNumber) {
        lottos.forEach(lotto -> lotto.matchBy(lottoNumber));
    }

    public List<Lotto> wonLottos() {
        return this.lottos.stream()
                .filter(Lotto::isWon)
                .toList();
    }

    public int wonLottosSize() {
        return this.wonLottos().size();
    }
}
