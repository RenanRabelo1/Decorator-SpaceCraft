package com.renan.decoratorspacecraft.ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.renan.decoratorspacecraft.exception.InvalidOptionException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class ConsoleMenuTest {

    @Test
    void shouldRejectNonNumericOption() {
        ConsoleMenu menu = new ConsoleMenu(new Scanner("letra"));

        assertThrows(InvalidOptionException.class, menu::readOption);
    }
}
