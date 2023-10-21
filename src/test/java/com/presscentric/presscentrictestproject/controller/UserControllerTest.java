package com.presscentric.presscentrictestproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.presscentric.presscentrictestproject.ObjectMother;
import com.presscentric.presscentrictestproject.model.User;
import com.presscentric.presscentrictestproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    @DisplayName(value = "Method findAll returns all users successfully and status OK")
    public void testFindAll() throws Exception {
        List<User> users = List.of(ObjectMother.createUser(Optional.empty()));
        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }

    @Test
    @DisplayName(value = "Method create saves user successfully and returns status NO CONTENT")
    public void testCreate() throws Exception {
        User user = ObjectMother.createUser(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName(value = "Method getById returns user by forwarded ID successfully and status OK")
    public void testGetById() throws Exception {
        int userId = 1;
        User user = ObjectMother.createUser(Optional.empty());
        Mockito.when(userService.findById(userId)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()));
    }

    @Test
    @DisplayName(value = "Method update updates user successfully and returns status NO CONTENT")
    public void testUpdate() throws Exception {
        int userId = 1;
        User user = ObjectMother.createUser(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}", userId)
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName(value = "Method deleteById deletes user by forwarded ID successfully and returns status NO CONTENT")
    public void testDeleteById() throws Exception {
        Integer userId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{userId}", userId))
                .andExpect(status().isNoContent());
    }

    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
