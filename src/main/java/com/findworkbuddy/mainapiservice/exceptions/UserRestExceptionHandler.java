package com.findworkbuddy.mainapiservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

    // Add exception handler for email existing exception
    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleExistingEmailException(IncorrectEmailException exception) {
        GenericErrorResponse response = new GenericErrorResponse(HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Add exception handler for incorrect login exception
    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleIncorrectLoginException(IncorrectLoginException exception) {
        GenericErrorResponse response = new GenericErrorResponse(HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Add exception handler for authentication fail exception
    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleIncorrectLoginException(AuthenticationFailException exception) {
        GenericErrorResponse response = new GenericErrorResponse(HttpStatus.UNAUTHORIZED.value(),
            exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Add exception handler for any exception
    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleAnyException(Exception exception) {
        GenericErrorResponse response = new GenericErrorResponse(HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
