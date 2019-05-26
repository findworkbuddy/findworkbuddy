package com.findworkbuddy.mainapiservice.security;

import com.findworkbuddy.mainapiservice.exceptions.AuthenticationFailException;
import com.findworkbuddy.mainapiservice.model.AuthenticationToken;
import com.findworkbuddy.mainapiservice.model.LoginUserRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    public static final String SECURITY_KEY = "!@asdsadJAS780";

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
        UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username,
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        AuthenticationToken authenticationToken = (AuthenticationToken) usernamePasswordAuthenticationToken;
        String token = authenticationToken.getToken();

        Claims claim;
        try {
            claim = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            throw new AuthenticationFailException("401 - Unauthorized");
        } catch (ExpiredJwtException e) {
            throw new AuthenticationFailException("401 - Unauthorized");
        } catch (MalformedJwtException e) {
            throw new AuthenticationFailException("401 - Unauthorized");
        }

        return new LoginUserRequest(claim.getSubject(), (String) claim.get("password"), token);
    }
}