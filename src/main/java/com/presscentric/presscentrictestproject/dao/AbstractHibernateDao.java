package com.presscentric.presscentrictestproject.dao;

import com.google.common.base.Preconditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable> {
    private Class<T> clazz;

    @Autowired
    private EntityManager entityManager;

    public void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    public T findOne(final long id) {
        T entity = entityManager.find(clazz, id);
        if (entity == null) {
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        }
        return entity;
    }

    public List<T> findAll() {
        return (List<T>) entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        entityManager.persist(entity);
    }

    public void update(final T entity) {
        Preconditions.checkNotNull(entity);
        entityManager.merge(entity);
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        entityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    public T getBy(final String attribute, final String value) {
        try {
            return (T) entityManager.createQuery("from "+ clazz.getName() + " where "+ attribute + " = :value")
                    .setParameter("value", value)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
