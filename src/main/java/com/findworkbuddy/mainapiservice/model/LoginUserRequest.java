package com.findworkbuddy.mainapiservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequest {

    private static final long serialVersionUID = 1L;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
