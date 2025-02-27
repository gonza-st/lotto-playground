package org.gonza.javaplayground.view.io;

import java.util.Scanner;

public class InputManager {
    private final Scanner 스캐너;

    public InputManager() {
        this.스캐너 = new Scanner(System.in);
    }

    public int 정수_읽기() {
        return 스캐너.nextInt();
    }

    public String 문자열_읽기() {
        return 스캐너.nextLine();
    }

    public void 버퍼_비우기() {
        스캐너.reset();
    }

    public void 종료하기() {
        스캐너.close();
    }
}
