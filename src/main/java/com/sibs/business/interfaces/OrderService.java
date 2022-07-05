package com.sibs.business.interfaces;

import com.sibs.api.dto.InputoOrderDTO;
import com.sibs.domain.model.Order;
import com.sibs.domain.model.StockMovement;

import java.util.List;

/**
 * @author geraldobarrosjr
 */
public interface OrderService extends BaseInterface<Order, InputoOrderDTO>{

    List<Order> findByItem_Name(String name);

    List<Order> findOrdersPending();

    List<Order> findOrdersCompleted();

    List<Order> findAllItems();

    Order findById(Long id);

    void finishOrdersIncompletes(StockMovement stockMovement);
}
