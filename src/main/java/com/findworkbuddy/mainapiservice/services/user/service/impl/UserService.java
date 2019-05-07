package com.findworkbuddy.mainapiservice.services.user.service.impl;

import com.findworkbuddy.mainapiservice.exceptions.EmailExistingException;
import com.findworkbuddy.mainapiservice.model.User;
import com.findworkbuddy.mainapiservice.services.user.dao.api.IUserDAO;
import com.findworkbuddy.mainapiservice.services.user.service.api.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if(userDAO.isEmailAvailable(user.getEmail())) {
            user.setId(0);
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userDAO.createUser(user);
        } else {
            throw new EmailExistingException("There is an account with that email address:" + user.getEmail());
        }
    }
}
