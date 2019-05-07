package com.findworkbuddy.mainapiservice.exceptions;

public class EmailExistingException extends RuntimeException {

    public EmailExistingException(String message) {
        super(message);
    }

    public EmailExistingException(String message, Throwable cause) {
        super(message, cause);
    }
}
