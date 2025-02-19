package org.gonza.javaplayground.lotto.domain.coin;

public class CoinFactory {
    private final Integer pricePerTime;

    public CoinFactory(Integer pricePerTime) {
        this.pricePerTime = pricePerTime;
    }

    public Coin issueCoin(Purchase purchase) {
        Integer amount = purchase.price() / pricePerTime;
        return new Coin(amount);
    }
}
