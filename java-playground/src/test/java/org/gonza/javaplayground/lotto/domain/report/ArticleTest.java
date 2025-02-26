package org.gonza.javaplayground.lotto.domain.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleTest {
    private Article sut;

    @BeforeEach
    public void setUp() {
        this.sut = new Article(1, 10, 100);
    }

    @Test
    public void should_convert_to_map_using_field_name_as_key() {
        Map<String, Integer> map = sut.toMap();

        assertEquals(1, map.get("winningNumberCount"));
        assertEquals(10, map.get("prize"));
        assertEquals(100, map.get("count"));
    }
}
