package com.sibs.business.interfaces;

import com.sibs.api.dto.InputStockMovementDTO;
import com.sibs.domain.model.StockMovement;

import java.util.List;

/**
 * @author geraldobarrosjr
 */


public interface StockMovementService extends BaseInterface<StockMovement, InputStockMovementDTO>{

    List<StockMovement> findByItem_Name(String name);

    List<StockMovement> findAllStockMovements();

    StockMovement findById(Long id);
}
