package com.example.pokemon.exceptions;

public class BatalhaNotFoundException extends RuntimeException {
    public BatalhaNotFoundException(String message) {
        super(message);
    }
}
