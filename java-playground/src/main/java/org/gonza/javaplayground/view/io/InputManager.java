package org.gonza.javaplayground.view.io;

import java.util.Scanner;

public class InputManager {
    private final Scanner scanner;

    public InputManager() {
        this.scanner = new Scanner(System.in);
    }

    public int 정수_읽기() {
        try {
            return scanner.nextInt();
        } finally {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }

    public String 문자열_읽기() {
        String 입력 = scanner.nextLine().trim();
        while (입력.isEmpty() && scanner.hasNextLine()) {
            입력 = scanner.nextLine().trim();
        }
        return 입력;
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public void 종료하기() {
        scanner.close();
    }
}