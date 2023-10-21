package com.presscentric.presscentrictestproject.controller;

import com.presscentric.presscentrictestproject.ObjectMother;
import com.presscentric.presscentrictestproject.model.User;
import com.presscentric.presscentrictestproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserControllerTest {
    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    @DisplayName(value = "Method findAll returns all users successfully and status OK")
    public void testFindAll() {
        List<User> users = List.of(ObjectMother.createUser(Optional.empty()));
        Mockito.when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<User> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(users.size(), responseBody.size());
    }

    @Test
    @DisplayName(value = "Method create saves user successfully and returns status NO CONTENT")
    public void testCreate() {
        User user = ObjectMother.createUser(Optional.empty());

        ResponseEntity<Void> response = userController.create(user);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(userService).create(user);
    }

    @Test
    @DisplayName(value = "Method getById returns user by forwarded ID successfully and status OK")
    public void testGetById() {
        int userId = 1;
        User user = ObjectMother.createUser(Optional.empty());
        Mockito.when(userService.findById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        User responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(user, responseBody);
    }

    @Test
    @DisplayName(value = "Method update updates user successfully and returns status NO CONTENT")
    public void testUpdate() {
        int userId = 1;
        User user = ObjectMother.createUser(Optional.empty());

        ResponseEntity<Void> response = userController.update(userId, user);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(userService).update(user, userId);
    }

    @Test
    @DisplayName(value = "Method deleteById deletes user by forwarded ID successfully and returns status NO CONTENT")
    public void testDeleteById() {
        Integer userId = 1;

        ResponseEntity<Void> response = userController.deleteById(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(userService).deleteById(userId);
    }
}
