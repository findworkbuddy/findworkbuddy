package com.findworkbuddy.mainapiservice.services.admin.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "secured/v1", method = RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
@ApiOperation(value = "Health check")
public class AdminResourceImpl implements AdminResource{

    @GetMapping(value = "ping")
    public String ping() {
            return("pong");
    }
}
