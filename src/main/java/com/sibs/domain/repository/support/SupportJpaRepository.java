package com.sibs.domain.repository.support;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author geraldobarrosjr
 */

@NoRepositoryBean
public interface SupportJpaRepository extends
    SupportOrderJpaRepository, SupportStockMovementJpaRepository {


}
