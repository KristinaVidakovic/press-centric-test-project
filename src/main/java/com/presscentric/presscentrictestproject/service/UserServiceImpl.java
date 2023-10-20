package com.presscentric.presscentrictestproject.service;

import com.presscentric.presscentrictestproject.exceptions.DuplicateEmailException;
import com.presscentric.presscentrictestproject.model.User;
import com.presscentric.presscentrictestproject.dao.IUserDao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public List<User> findAll() {
        return iUserDao.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        try {
            user.setId(null);
            iUserDao.create(user);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateEmailException("Email already exists");
        }
    }

    @Override
    public User findById(Integer userId) {
        try {
            return iUserDao.findOne(userId);
        } catch (EntityNotFoundException exception) {
            throw new EntityNotFoundException("Entity with ID " + userId + " not found");
        }
    }

    @Override
    @Transactional
    public void update(User user, Integer userId) {
        user.setId(userId);
        iUserDao.update(user, userId);
    }

    @Override
    @Transactional
    public void deleteById(Integer userId) {
        iUserDao.deleteById(userId);
    }
}
