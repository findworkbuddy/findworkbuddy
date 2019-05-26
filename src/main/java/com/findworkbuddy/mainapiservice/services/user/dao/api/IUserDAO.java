package com.findworkbuddy.mainapiservice.services.user.dao.api;

import com.findworkbuddy.mainapiservice.model.LoginUserRequest;
import com.findworkbuddy.mainapiservice.model.User;

public interface IUserDAO {
    void createUser(User user);
    boolean isEmailAvailable(String email);
    String getUserPassword(LoginUserRequest loginUserRequest);
}
