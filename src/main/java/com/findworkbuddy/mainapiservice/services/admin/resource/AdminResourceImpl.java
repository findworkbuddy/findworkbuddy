package com.findworkbuddy.mainapiservice.services.admin.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/services/admin")
public class AdminResourceImpl implements AdminResource{

    public String ping() {
            return("pong");
    }
}
