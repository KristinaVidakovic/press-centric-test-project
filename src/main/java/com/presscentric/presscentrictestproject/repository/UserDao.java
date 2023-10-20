package com.presscentric.presscentrictestproject.repository;

import com.google.common.base.Preconditions;
import com.presscentric.presscentrictestproject.AbstractHibernateDao;
import com.presscentric.presscentrictestproject.exceptions.DuplicateEmailException;
import com.presscentric.presscentrictestproject.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractHibernateDao<User> implements IUserDao {
    public UserDao(){
        super();
        setClazz(User.class);
    }

    @Override
    public void update(User entity, Integer entityId) {
        User oldEntity = super.findOne(entityId);
        Preconditions.checkState(oldEntity != null);
        User entityWithSameEmail = super.getBy("email", entity.getEmail());
        if (entityWithSameEmail == null || entityWithSameEmail.getId().equals(entityId)) {
            super.update(entity);
        } else {
            throw new DuplicateEmailException("Email already exists");
        }
    }

}
