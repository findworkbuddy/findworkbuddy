package com.findworkbuddy.mainapiservice.services.admin.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = { "Admin Resource "})
public interface AdminResource {

    String ping();
}
