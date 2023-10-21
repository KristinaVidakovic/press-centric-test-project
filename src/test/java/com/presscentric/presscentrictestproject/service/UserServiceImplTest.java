package com.presscentric.presscentrictestproject.service;

import com.presscentric.presscentrictestproject.ObjectMother;
import com.presscentric.presscentrictestproject.dao.IUserDao;
import com.presscentric.presscentrictestproject.exceptions.DuplicateEmailException;
import com.presscentric.presscentrictestproject.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserDao userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName(value = "Method findAll returns all users successfully")
    public void testFindAll() {
        List<User> expectedUsers = List.of(ObjectMother.createUser(Optional.empty()));
        when(userDao.findAll()).thenReturn(expectedUsers);

        List<User> users = userService.findAll();

        assertEquals(expectedUsers, users);
    }

    @Test
    @DisplayName(value = "Method create saves user successfully")
    public void testCreate() {
        User user = ObjectMother.createUser(Optional.empty());
        doNothing().when(userDao).create(user);

        userService.create(user);

        verify(userDao, times(1)).create(user);
    }

    @Test
    @DisplayName(value = "Method create throws DataIntegrityViolationException")
    public void testCreateWithDuplicateEmail() {
        User user = ObjectMother.createUser(Optional.empty());
        doThrow(new DataIntegrityViolationException("Duplicate entry")).when(userDao).create(user);

        assertThrows(DuplicateEmailException.class, () -> userService.create(user));
    }

    @Test
    @DisplayName(value = "Method findById returns user by ID successfully")
    public void testFindById() {
        int userId = 1;
        User expectedUser = ObjectMother.createUser(Optional.empty());
        when(userDao.findOne(userId)).thenReturn(expectedUser);

        User user = userService.findById(userId);

        assertEquals(expectedUser, user);
    }

    @Test
    @DisplayName(value = "Method findById throw EntityNotFoundException")
    public void testFindByIdWithNonExistentUser() {
        int userId = 1;
        when(userDao.findOne(userId)).thenThrow(new EntityNotFoundException("Entity not found"));

        assertThrows(EntityNotFoundException.class, () -> userService.findById(userId));
    }

    @Test
    @DisplayName(value = "Method update updates user successfully")
    public void testUpdate() {
        Integer userId = 1;
        User user = ObjectMother.createUser(Optional.empty());
        doNothing().when(userDao).update(user, userId);

        userService.update(user, userId);

        verify(userDao, times(1)).update(user, userId);
        assertEquals(userId, user.getId());
    }

    @Test
    @DisplayName(value = "Method delete deletes user by ID successfully")
    public void testDeleteById() {
        int userId = 1;
        doNothing().when(userDao).deleteById(userId);

        userService.deleteById(userId);

        verify(userDao, times(1)).deleteById(userId);
    }
}
