package com.renan.decoratorspacecraft.ui;

import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner;

    public ConsoleMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readOption() {
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
