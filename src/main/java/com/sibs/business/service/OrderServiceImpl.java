package com.sibs.business.service;

import com.sibs.api.dto.InputoOrderDTO;
import com.sibs.api.dto.OrderDTO;
import com.sibs.business.interfaces.ItemService;
import com.sibs.business.interfaces.OrderService;
import com.sibs.business.notification.Notification;
import com.sibs.domain.exception.BusinessException;
import com.sibs.domain.model.Item;
import com.sibs.domain.model.Order;
import com.sibs.domain.model.StockMovement;
import com.sibs.domain.model.User;
import com.sibs.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author geraldobarrosjr
 */

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ItemService itemService;

    private final Notification notification;

    private final ModelMapper modelMapper;

    @Override
    public List<Order> findByItem_Name(String name) {
        return orderRepository.findOrdersByItem(name);
    }

    @Override
    public List<Order> findOrdersPending() {
        return orderRepository.findOrdersByPendingGreaterThan(0.0);
    }

    @Override
    public List<Order> findOrdersCompleted() {
        return orderRepository.findOrdersCompleted();
    }

    @Override
    public List<Order> findAllItems() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new BusinessException("Order not found with ID " + id, "Order Not Found"));
    }

    @Override
    @Transactional
    public Order create(InputoOrderDTO request) {
        Item item = itemService.findById(Long.valueOf(request.getItemId()));
        if (item != null) {
            Double pending = item.getQuantity() - request.getQuantity();
            pending = pending < 0 ? pending * (-1) : 0;
            Double fulfilled = pending >= 0.0 ? request.getQuantity() - pending : 0;

            Order orderCreate = Order.builder()
                    .creationDate(OffsetDateTime.now())
                    .item(Item.builder().id(Long.valueOf(request.getItemId())).build())
                    .user(User.builder().id(Long.valueOf(request.getUserId())).build())
                    .quantity(request.getQuantity())
                    .fulfilled(fulfilled)
                    .pending(pending)
                    .build();
            Double quantity = pending > 0 ? item.getQuantity() : request.getQuantity();

            if (pending <= 0) {

                notification.notify(orderCreate.getUser(), "Your Order has been completed!");
            }

            itemService.decreaseItemQuantity(item.getId(), quantity);
            Order orderSaved =  orderRepository.save(orderCreate);
            log.info("Order {} completed", orderSaved.getId());
            return orderSaved;

        } else {
            String msg = "Item not found with ID " + request.getItemId();
            log.error(msg);
            throw new BusinessException(msg, "Item Not Found");
        }

    }

    @Override
    public Order update(Long id, InputoOrderDTO request) {
        Order order = orderExists(id);
        if(order != null) {
            order.setItem(
                    Item.builder()
                            .id(Long.valueOf(request.getItemId()))
                            .build());
            order.setQuantity(request.getQuantity());
            return orderRepository.save(order);

        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Order order = orderExists(id);
        if (order != null) {
            orderRepository.delete(order);
        }

    }

    @Override
    @Transactional
    public void finishOrdersIncompletes(StockMovement stockMovement){
        Double quantity = 0.0;
        Item item = itemService.findById(stockMovement.getItem().getId());
        List<Order> ordersPending = orderRepository.findOrdersByPendingGreaterThan(0.0);
        List<Order> orderList = ordersPending.stream().filter(order -> order.getItem().getId().equals(item.getId()))
                .collect(Collectors.toList());


        for(Order order : orderList) {
            Double diffValue = item.getQuantity() - order.getPending();
            Double pending = diffValue < 0 ? diffValue * (-1) : 0;
            Double fulfilled = pending >= 0.0 ? order.getQuantity() - pending : 0;
             if (order.getPending() >= item.getQuantity()) {
                 quantity = item.getQuantity();
             } else {
                 quantity = item.getQuantity() - diffValue;
                 notification.notify(order.getUser(), "Your Order has been completed!");
             }
            order.setPending(pending);
            order.setFulfilled(fulfilled);
            order.getStockMovement().add(stockMovement);
            itemService.decreaseItemQuantity(item.getId(), quantity);
            orderRepository.save(order);

        }


    }

    @Override
    public InputoOrderDTO toModel(Order request) {
        return modelMapper.map(request, InputoOrderDTO.class);
    }

    @Override
    public Order toEntity(InputoOrderDTO request) {
        return modelMapper.map(request, Order.class);
    }

    @Override
    public List<InputoOrderDTO> toModelList(List<Order> request) {
        return request.stream().map(this::toModel).collect(java.util.stream.Collectors.toList());
    }

    public Order orderExists(Long id) {
         Optional<Order> order =  orderRepository.findById(id);
         return order.orElse(null);
    }


}

