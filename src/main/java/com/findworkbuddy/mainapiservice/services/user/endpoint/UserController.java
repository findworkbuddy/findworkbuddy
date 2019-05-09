package com.findworkbuddy.mainapiservice.services.user.endpoint;

import com.findworkbuddy.mainapiservice.model.User;
import com.findworkbuddy.mainapiservice.services.user.service.api.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/services/v1")
public class UserController{

    private IUserService userService;

    @Autowired
    public UserController(final IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Creates new User")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public void createNewUser(@RequestBody @Valid User user) {
        userService.createNewUser(user);
    }

}
