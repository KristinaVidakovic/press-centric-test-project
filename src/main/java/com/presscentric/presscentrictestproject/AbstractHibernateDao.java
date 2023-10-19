package com.presscentric.presscentrictestproject;

import com.google.common.base.Preconditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        return entityManager.createQuery(criteriaQuery.select(root)).getResultList();
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

}
