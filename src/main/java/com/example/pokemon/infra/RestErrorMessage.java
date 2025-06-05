package com.example.pokemon.infra;

public class RestErrorMessage {

    private int statusCode;
    private String errorName;
    private String message;

    public RestErrorMessage(int statusCode, String errorName, String message) {
        this.statusCode = statusCode;
        this.errorName = errorName;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
