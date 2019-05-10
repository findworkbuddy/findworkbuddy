package com.findworkbuddy.mainapiservice.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.findworkbuddy.mainapiservice.model.User;
import com.findworkbuddy.mainapiservice.services.user.endpoint.UserController;
import com.findworkbuddy.mainapiservice.services.user.service.api.IUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.findworkbuddy.mainapiservice.utils.UnitTestUtils.createUser;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @MockBean
    private IUserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        User testUser = createUser();

        String content = objectMapper.writeValueAsString(testUser);
        mockMvc.perform(
            post("/services/v1/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
               .andDo(print())
               // Test validation
               .andExpect(status().isCreated());

        verify(userService, times(1)).createNewUser(testUser);
    }

    @Test
    public void testCreateUser_EmptyUser() throws Exception {
        String content = objectMapper.writeValueAsString(new User());
        mockMvc.perform(
            post("/services/v1/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
               .andDo(print())
               // Test validation
               .andExpect(jsonPath("message",
                    containsString("NotBlank.user.email")))
               .andExpect(jsonPath("message",
                    containsString("NotBlank.user.password")))
               .andExpect(jsonPath("message",
                    containsString("NotBlank.user.firstName")))
               .andExpect(jsonPath("message",
                    containsString("NotBlank.user.lastName")))
               .andExpect(status().isBadRequest());

        verify(userService, times(0)).createNewUser(new User());
    }
}
