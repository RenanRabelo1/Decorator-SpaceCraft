package com.renan.decoratorspacecraft.app;

import com.renan.decoratorspacecraft.controller.GameController;

public class Main {

    public static final String STARTUP_MESSAGE = "Decorator SpaceCraft iniciado.";

    public static void main(String[] args) {
        new GameController().start();
    }
}
