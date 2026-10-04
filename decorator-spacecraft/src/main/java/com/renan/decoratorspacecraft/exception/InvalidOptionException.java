package com.renan.decoratorspacecraft.exception;

public class InvalidOptionException extends RuntimeException {

    public InvalidOptionException() {
        super("Opção inválida. Informe um número.");
    }
}
