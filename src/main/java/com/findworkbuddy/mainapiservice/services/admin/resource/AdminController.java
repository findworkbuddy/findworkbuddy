package com.findworkbuddy.mainapiservice.services.admin.resource;

import com.findworkbuddy.mainapiservice.model.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/secured/v1")
@ApiOperation(value = "Health check")
public class AdminController {

    @GetMapping(value = "/ping")
    public String ping(@AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("{}", userDetails.getUser().getEmail());
        log.info("{} Logged successfuly.", userDetails.getUser().getFirstName());
        log.info("{}", userDetails.getUser());
        return ("pong");
    }
}
