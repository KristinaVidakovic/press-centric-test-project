package com.presscentric.presscentrictestproject.service;

import com.presscentric.presscentrictestproject.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void create(User user);

    User findById(Integer userId);

    void update(User user);

    void deleteById(Integer userId);
}
