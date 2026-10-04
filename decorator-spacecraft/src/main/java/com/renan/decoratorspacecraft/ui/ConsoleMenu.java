package com.renan.decoratorspacecraft.ui;

import com.renan.decoratorspacecraft.exception.InvalidOptionException;
import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner;

    public ConsoleMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readOption() {
        if (!scanner.hasNextLine()) {
            throw new InvalidOptionException();
        }

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            throw new InvalidOptionException();
        }
    }
}
