package com.example.pokemon.exceptions;

public class JogadorNotFoundException extends RuntimeException {
    public JogadorNotFoundException(String message) {
        super(message);
    }
}
