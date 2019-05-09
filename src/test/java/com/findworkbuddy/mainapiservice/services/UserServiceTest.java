package com.findworkbuddy.mainapiservice.services;

import com.findworkbuddy.mainapiservice.exceptions.EmailExistingException;
import com.findworkbuddy.mainapiservice.model.User;
import com.findworkbuddy.mainapiservice.services.user.dao.api.IUserDAO;
import com.findworkbuddy.mainapiservice.services.user.service.impl.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.findworkbuddy.mainapiservice.utils.UnitTestUtils.createUser;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private IUserDAO userDAO;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setup() {
        userService = new UserService(userDAO, bCryptPasswordEncoder);
    }

    @Test(expected = EmailExistingException.class)
    public void testVerifyEmailValidation() {
        User testUser = createUser();

        when(userDAO.isEmailAvailable(anyString()))
               .thenReturn(false);

        userService.createNewUser(testUser);
    }

    @Test
    public void createValidUser() {
        User testUser = createUser();

        when(userDAO.isEmailAvailable(anyString()))
            .thenReturn(true);

        userService.createNewUser(testUser);
    }
}
