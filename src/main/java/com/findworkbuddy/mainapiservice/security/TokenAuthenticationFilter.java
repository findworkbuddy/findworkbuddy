package com.findworkbuddy.mainapiservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.findworkbuddy.mainapiservice.exceptions.AuthenticationFailException;
import com.findworkbuddy.mainapiservice.model.AuthenticationToken;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public TokenAuthenticationFilter() {
        super("/secured/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        AuthenticationToken token = validateHeader(request.getHeader(TOKEN_HEADER));
        if (ObjectUtils.isEmpty(token)) {
            throw new AuthenticationFailException("401 - Unauthorized");
        }
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
        Authentication authResult) throws IOException, ServletException {

        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }

    private AuthenticationToken validateHeader(String authenticationHeader) {
        if (StringUtils.isEmpty(authenticationHeader) || !authenticationHeader.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        return new AuthenticationToken(authenticationHeader.replace(TOKEN_PREFIX, ""));
    }

}
