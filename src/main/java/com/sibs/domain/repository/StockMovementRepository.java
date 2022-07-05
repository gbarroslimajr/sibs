package com.sibs.domain.repository;

import com.sibs.domain.model.StockMovement;
import com.sibs.domain.repository.support.QueryCallback;
import com.sibs.domain.repository.support.SupportStockMovementJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author geraldobarrosjr
 */

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long>, SupportStockMovementJpaRepository {

    default List<StockMovement> findOrdersByItem(String itemName) {
        return this.findStockMovementByItem(new QueryCallback<List<StockMovement>>() {
            @Override
            public List<StockMovement> doWithEntityManager(EntityManager entityManager) {
                StringBuilder builder = new StringBuilder();
                builder.append("  SELECT o.*, i.name as itemName FROM stock_movements o " );
                builder.append(" JOIN item i on i.id = o.item_id " );
                builder.append(" WHERE i.name like '%' || ?1 || '%'   ");

                String nativeQuery = builder.toString();

                Query query = entityManager.createNativeQuery(nativeQuery, "StockMovementDtoMapping");
                query.setParameter(1, itemName);

                return query.getResultList();
            }
        });
    }
}
