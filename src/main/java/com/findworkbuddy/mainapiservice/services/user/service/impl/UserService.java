package com.findworkbuddy.mainapiservice.services.user.service.impl;

import com.findworkbuddy.mainapiservice.exceptions.IncorrectEmailException;
import com.findworkbuddy.mainapiservice.exceptions.IncorrectLoginException;
import com.findworkbuddy.mainapiservice.model.AuthenticationToken;
import com.findworkbuddy.mainapiservice.model.LoginUserRequest;
import com.findworkbuddy.mainapiservice.model.User;
import com.findworkbuddy.mainapiservice.services.user.dao.api.IUserDAO;
import com.findworkbuddy.mainapiservice.services.user.service.api.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.findworkbuddy.mainapiservice.config.JwtSecurityConfig.EXPIRATION_SECONDS;
import static com.findworkbuddy.mainapiservice.security.TokenAuthenticationProvider.SECURITY_KEY;
import static java.util.regex.Pattern.*;

@Service
public class UserService implements IUserService {

    private IUserDAO userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(final IUserDAO userDAO, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public void createNewUser(User user) {
        verifyValidEmailFormat(user.getEmail());
        verifyAvailableEmail(user.getEmail());

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.createUser(user);
    }

    @Override
    public AuthenticationToken loginUser(LoginUserRequest loginRequest) {
        verifyValidEmailFormat(loginRequest.getEmail());

        String expectedPassword = userDAO.getUserPassword(loginRequest);

        if(bCryptPasswordEncoder.matches(loginRequest.getPassword(), expectedPassword)) {
            return new AuthenticationToken(Jwts.builder()
                .setSubject(loginRequest.getEmail())
                .claim("password", loginRequest.getPassword())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()
                               + EXPIRATION_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                .compact());
        } else {
            throw new IncorrectLoginException("Incorrect password");
        }
    }

    private void verifyValidEmailFormat(String email) {
        String validEmailRegex = "^(.+)@(.+)$";
        if (!matches(validEmailRegex, email)) {
            throw new IncorrectEmailException(
                "The entered email address is invalid");
        }
    }

    private void verifyAvailableEmail(String email) {
        if (!userDAO.isEmailAvailable(email)) {
            throw new IncorrectEmailException(
                "There is an account with that email address:" + email);
        }

    }
}
