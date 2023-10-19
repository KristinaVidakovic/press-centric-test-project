package com.presscentric.presscentrictestproject.repository;

import com.presscentric.presscentrictestproject.AbstractHibernateDao;
import com.presscentric.presscentrictestproject.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {
    public UserDao(){
        super();
        setClazz(User.class);
    }
}
