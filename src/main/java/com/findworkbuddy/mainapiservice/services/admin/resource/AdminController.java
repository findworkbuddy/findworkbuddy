package com.findworkbuddy.mainapiservice.services.admin.resource;

import com.findworkbuddy.mainapiservice.model.CustomUserDetails;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "secured/v1")
@ApiOperation(value = "Health check")
public class AdminResourceImpl {

    @GetMapping(value = "ping")
    public String ping(@AuthenticationPrincipal CustomUserDetails userDetails) {
            return("pong");
    }
}
