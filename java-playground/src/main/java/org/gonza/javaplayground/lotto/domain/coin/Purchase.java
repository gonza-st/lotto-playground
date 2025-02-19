package org.gonza.javaplayground.lotto.domain.coin;

public record Purchase(Integer price) {
    public Purchase {
        validatePrice(price);
    }

    private void validatePrice(Integer money) {
        if (money <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }
}
