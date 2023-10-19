package com.presscentric.presscentrictestproject.service;

import com.presscentric.presscentrictestproject.model.User;
import com.presscentric.presscentrictestproject.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final IUserDao iUserDao;

    @Autowired
    UserServiceImpl(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return iUserDao.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        iUserDao.create(user);
    }

    @Override
    @Transactional
    public User findById(Integer userId) {
        return iUserDao.findOne(userId);
    }

    @Override
    @Transactional
    public void update(User user) {
        iUserDao.update(user);
    }

    @Override
    @Transactional
    public void deleteById(Integer userId) {
        iUserDao.deleteById(userId);
    }
}
