package com.sibs.domain.repository.support;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author geraldobarrosjr
 */

@NoRepositoryBean
public interface SupportStockMovementJpaRepository {

    <T> List<T> findStockMovementByItem(QueryCallback<List<T>> callback);

}
