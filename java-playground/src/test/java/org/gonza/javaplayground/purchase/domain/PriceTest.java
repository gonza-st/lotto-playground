package org.gonza.javaplayground.purchase.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void Price는_음수가_될_수_없다() {
        assertThrows(IllegalArgumentException.class, () -> Price.of(-1L));
    }
}