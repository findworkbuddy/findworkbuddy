package com.findworkbuddy.mainapiservice.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailException extends AuthenticationException {

    public AuthenticationFailException(String message) {
        super(message);
    }
}
