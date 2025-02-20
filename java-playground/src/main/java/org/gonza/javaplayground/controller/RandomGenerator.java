package org.gonza.javaplayground.controller;

public class RandomGenerator {
    public int 범위_내_난수_생성하기(int 시작, int 끝) {
        return (int) (Math.random() * (끝 - 시작 + 1)) + 시작;
    }
}