package com.sibs.business.service;

import com.sibs.api.dto.InputStockMovementDTO;
import com.sibs.business.interfaces.ItemService;
import com.sibs.business.interfaces.OrderService;
import com.sibs.business.interfaces.StockMovementService;
import com.sibs.domain.exception.BusinessException;
import com.sibs.domain.model.Item;
import com.sibs.domain.model.StockMovement;
import com.sibs.domain.repository.StockMovementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author geraldobarrosjr
 */

@Slf4j
@Service
@AllArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    private final ItemService itemService;

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    @Override
    public List<StockMovement> findByItem_Name(String name) {
        return stockMovementRepository.findOrdersByItem(name);
    }

    @Override
    public List<StockMovement> findAllStockMovements() {
        return stockMovementRepository.findAll();
    }

    @Override
    public StockMovement findById(Long id) {
        return stockMovementRepository.findById(id).orElseThrow(() -> new BusinessException("StockMovement not found with ID " + id, "StockMovement Not Found"));
    }


    @Override
    @Transactional
    public StockMovement create(InputStockMovementDTO request) {
        StockMovement stockMovementCreate = StockMovement.builder()
                .creationDate(OffsetDateTime.now())
                .item(Item.builder().id(Long.valueOf(request.getItemId())).build())
                .quantity(request.getQuantity())
                .build();
        itemService.increaseItemQuantity(Long.valueOf(request.getItemId()), request.getQuantity());
        StockMovement stockMovementSaved =  stockMovementRepository.save(stockMovementCreate);
        stockMovementSaved.getItem().setId(Long.valueOf(request.getItemId()));
        log.info("StockMovement created with ID: {}", stockMovementSaved.getId());
        orderService.finishOrdersIncompletes(stockMovementSaved);
        return stockMovementSaved;
    }

    @Override
    @Transactional
    public StockMovement update(Long id, InputStockMovementDTO request) {
        StockMovement stockMovementCreate = stockMovementExists(id);
        if (stockMovementCreate != null) {
            if (request.getQuantity() > stockMovementCreate.getQuantity()) {
                Double diffValue = request.getQuantity() - stockMovementCreate.getQuantity();
                itemService.increaseItemQuantity(Long.valueOf(request.getItemId()), diffValue);
            } else {
                Double diffValue = stockMovementCreate.getQuantity() - request.getQuantity();
                itemService.decreaseItemQuantity(Long.valueOf(request.getItemId()), diffValue);
            }
            stockMovementCreate.setQuantity(request.getQuantity());
            stockMovementCreate.setItem(Item.builder().id(Long.valueOf(request.getItemId())).build());
            return  stockMovementRepository.save(stockMovementCreate);

        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

        StockMovement stockMovement = stockMovementExists(id);
        if (stockMovement != null) {
            itemService.decreaseItemQuantity(stockMovement.getItem().getId(), stockMovement.getQuantity());
            stockMovementRepository.delete(stockMovement);

        }

    }

    @Override
    public InputStockMovementDTO toModel(StockMovement request) {
        return modelMapper.map(request, InputStockMovementDTO.class);
    }

    @Override
    public StockMovement toEntity(InputStockMovementDTO request) {
        return modelMapper.map(request, StockMovement.class);
    }

    @Override
    public List<InputStockMovementDTO> toModelList(List<StockMovement> request) {
        return request.stream().map(this::toModel).collect(java.util.stream.Collectors.toList());
    }

    public StockMovement stockMovementExists(Long id) {
        Optional<StockMovement> stockMovement = stockMovementRepository.findById(id);
        return stockMovement.orElse(null);
    }


}
