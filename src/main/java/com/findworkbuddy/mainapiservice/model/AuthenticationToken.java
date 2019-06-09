package com.findworkbuddy.mainapiservice.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;

    public AuthenticationToken(String token) {
        super(null, null, null);
        this.token = token;
    }
}