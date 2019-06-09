package com.findworkbuddy.mainapiservice.security;

import com.findworkbuddy.mainapiservice.exceptions.AuthenticationFailException;
import com.findworkbuddy.mainapiservice.model.AuthenticationToken;
import com.findworkbuddy.mainapiservice.model.CustomUserDetails;
import com.findworkbuddy.mainapiservice.model.User;
import com.findworkbuddy.mainapiservice.services.user.dao.impl.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDAO userDAO;

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
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException e) {
            throw new AuthenticationFailException("401 - Unauthorized");
        }

        User user = userDAO.getUserByEmail(claim.getSubject());

        return new CustomUserDetails(claim.getSubject(), user);
    }
}