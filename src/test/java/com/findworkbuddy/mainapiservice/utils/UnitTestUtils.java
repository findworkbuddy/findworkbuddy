package com.findworkbuddy.mainapiservice.utils;

import com.findworkbuddy.mainapiservice.model.User;

public final class UnitTestUtils {

    public static User createUser() {
        return User.builder()
            .firstName("TestFirstName")
            .lastName("TestLastName")
            .email("TestEmail@gmail.com")
            .headLine("Test headline")
            .summary("Test summary")
            .password("Test password")
            .build();
    }
}
