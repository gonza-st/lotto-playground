package org.gonza.javaplayground.lotto.domain.coin;

public class CoinFactory {
    private final Integer pricePerUsage;

    public CoinFactory(CoinProperties coinProperties) {
        this.pricePerUsage = coinProperties.getPricePerUsage();
    }

    public Coin createCoin(Integer money) {
        validateMoney(money);

        Integer amount = money / pricePerUsage;
        return new Coin(amount);
    }

    private void validateMoney(Integer money) {
        if (money <= 0) {
            throw new IllegalArgumentException("Money must be greater than zero");
        }
    }
}
