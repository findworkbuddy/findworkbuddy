package com.findworkbuddy.mainapiservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

    // Add exception handler for email existing exception
    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleExistingEmailException(EmailExistingException exception) {
        GenericErrorResponse response = new GenericErrorResponse(HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Add exception handler for any exception
    @ExceptionHandler
    public ResponseEntity<GenericErrorResponse> handleAnyException(Exception exception) {
        GenericErrorResponse response = new GenericErrorResponse(HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
