package com.findworkbuddy.mainapiservice.exceptions;

public class IncorrectLoginException extends RuntimeException {

    public IncorrectLoginException(String message) {
        super(message);
    }

    public IncorrectLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
