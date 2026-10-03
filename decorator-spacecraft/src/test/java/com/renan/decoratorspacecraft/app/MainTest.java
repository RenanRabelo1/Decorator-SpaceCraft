package com.renan.decoratorspacecraft.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void shouldExposeStartupMessage() {
        assertEquals("Decorator SpaceCraft iniciado.", Main.STARTUP_MESSAGE);
    }
}
