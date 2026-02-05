package com.ll.global;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader{
    private final Scanner scanner;
    public ConsoleInputReader() {
        this.scanner = new Scanner(System.in);
    }
    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
