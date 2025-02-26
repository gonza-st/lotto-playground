package org.gonza.javaplayground.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomNumber {
    public static List<Integer> generate(int size, int start, int end) {
        Set<Integer> set = new HashSet<>();

        while (set.size() < size) {
            Double d = Math.random() * end + start;
            set.add(d.intValue());
        }

        return new ArrayList<>(set);
    }
}
