package com.renan.decoratorspacecraft.exception;

public class InvalidUpgradeException extends RuntimeException {

    public InvalidUpgradeException(String upgrade) {
        super("Melhoria inválida: " + upgrade);
    }
}
