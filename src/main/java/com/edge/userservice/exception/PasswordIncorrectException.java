package com.edge.userservice.exception;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException() {
    }

    public PasswordIncorrectException(String message) {
        super(message);
    }

}
