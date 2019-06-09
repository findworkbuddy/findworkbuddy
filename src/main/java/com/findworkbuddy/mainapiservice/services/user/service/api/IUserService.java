package com.findworkbuddy.mainapiservice.services.user.service.api;

import com.findworkbuddy.mainapiservice.model.AuthenticationToken;
import com.findworkbuddy.mainapiservice.model.LoginUserRequest;
import com.findworkbuddy.mainapiservice.model.User;

public interface IUserService {

    void createNewUser(User user);
    AuthenticationToken loginUser(LoginUserRequest loginRequest);
}
