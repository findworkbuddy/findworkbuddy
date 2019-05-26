package com.findworkbuddy.mainapiservice.exceptions;

public class IncorrectEmailException extends RuntimeException {

    public IncorrectEmailException(String message) {
        super(message);
    }

    public IncorrectEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
