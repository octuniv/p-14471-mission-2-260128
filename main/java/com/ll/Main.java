package com.ll;

import com.ll.global.ConsoleInputReader;
import com.ll.global.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args) {
        App app = App.getInstance(new ConsoleInputReader(), new ConsoleOutputWriter());
        app.run();
    }
}