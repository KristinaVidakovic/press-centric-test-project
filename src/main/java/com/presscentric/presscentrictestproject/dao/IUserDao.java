package com.presscentric.presscentrictestproject.dao;

import com.presscentric.presscentrictestproject.model.User;

import java.util.List;

public interface IUserDao {
    User findOne(long id);

    List<User> findAll();

    void update(User entity, Integer entityId);

    void create(User entity);

    void deleteById(long entityId);
}
