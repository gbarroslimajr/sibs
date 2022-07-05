package com.sibs.domain.repository.support;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @author geraldobarrosjr
 */
public class SibsJpaRepository <E, ID extends Serializable> extends SimpleJpaRepository<E,ID> implements SupportJpaRepository {

    private final EntityManager entityManager;


    public SibsJpaRepository(JpaEntityInformation<E, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public <T> List<T> findOrdersByItem(QueryCallback<List<T>> callback) {
        return callback.doWithEntityManager(this.entityManager);
    }

    @Override
    public <T> List<T> findOrdersCompleted(QueryCallback<List<T>> callback) {
        return callback.doWithEntityManager(this.entityManager);
    }

    @Override
    public <T> List<T> findStockMovementByItem(QueryCallback<List<T>> callback) {
        return callback.doWithEntityManager(this.entityManager);
    }
}
