package com.sibs.domain.repository.support;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author geraldobarrosjr
 */

@NoRepositoryBean
public interface SupportOrderJpaRepository {

    <T> List<T> findOrdersByItem(QueryCallback<List<T>> callback);
    <T> List<T> findOrdersCompleted(QueryCallback<List<T>> callback);

}
