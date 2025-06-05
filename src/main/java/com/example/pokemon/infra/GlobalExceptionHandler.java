package com.example.pokemon.infra;

import com.example.pokemon.exceptions.JogadorDuplicadoException;
import com.example.pokemon.exceptions.JogadorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JogadorNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleJogadorNotFoundException(JogadorNotFoundException ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(), "Jogador não encontrado", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(JogadorDuplicadoException.class)
    public ResponseEntity<RestErrorMessage> handleJogadorDuplicadoException(JogadorDuplicadoException ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
                HttpStatus.CONFLICT.value(), "Jogador já cadastrado", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestErrorMessage> handleGenericException(RuntimeException ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "ErroInterno", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
        RestErrorMessage errorResponse = new RestErrorMessage(
                HttpStatus.BAD_REQUEST.value(), "Erro genérico", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}