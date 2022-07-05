package com.sibs.domain.repository;


import com.sibs.domain.model.Order;
import com.sibs.domain.repository.support.QueryCallback;
import com.sibs.domain.repository.support.SupportOrderJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author geraldobarrosjr
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, SupportOrderJpaRepository {

    default List<Order> findOrdersByItem(String itemName) {
        return this.findOrdersByItem(new QueryCallback<List<Order>>() {
            @Override
            public List<Order> doWithEntityManager(EntityManager entityManager) {
                StringBuilder builder = new StringBuilder();
                builder.append(" SELECT o.*, i.name as itemName, u.name as userName FROM orders o " );
                builder.append(" JOIN item i on i.id = o.item_id " );
                builder.append(" JOIN users u on u.id = o.user_id " );
                builder.append(" WHERE i.name like '%' || ?1 || '%'   ");

                String nativeQuery = builder.toString();

                Query query = entityManager.createNativeQuery(nativeQuery, "OrderDtoMapping");
                query.setParameter(1, itemName);

                return query.getResultList();
            }
        });
    }

    default List<Order> findOrdersCompleted() {
        return this.findOrdersCompleted(new QueryCallback<List<Order>>() {
            @Override
            public List<Order> doWithEntityManager(EntityManager entityManager) {
                StringBuilder builder = new StringBuilder();
                builder.append(" SELECT o.*, i.name as itemName, u.name as userName FROM orders o " );
                builder.append(" JOIN item i on i.id = o.item_id " );
                builder.append(" JOIN users u on u.id = o.user_id " );
                builder.append(" WHERE o.pending = 0  ");

                String nativeQuery = builder.toString();

                Query query = entityManager.createNativeQuery(nativeQuery, "OrderDtoMapping");


                return query.getResultList();
            }
        });
    }


    List<Order> findOrdersByPendingGreaterThan(Double pending);




}

