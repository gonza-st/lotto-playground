package org.gonza.javaplayground.framework.mapper;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Option {
    ERROR(-1),
    PURCHASE(1),
    MATCH(2);

    private final Integer code;

    Option(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static Option fromCode(Integer code) {
        Optional<Option> found = Arrays.stream(Option.values())
                .filter(option -> Objects.equals(option.code, code))
                .findFirst();

        return found.orElse(ERROR);
    }
}
