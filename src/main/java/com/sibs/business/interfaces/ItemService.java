package com.sibs.business.interfaces;

import com.sibs.api.dto.ItemDTO;
import com.sibs.domain.model.Item;

import java.util.List;

/**
 * @author geraldobarrosjr
 */
public interface ItemService extends BaseInterface<Item, ItemDTO> {

    List<Item> findAllItems();
    List<Item> findByName(String name);
    Item findById(Long id);

    void increaseItemQuantity(Long id, Double quantity);
    void decreaseItemQuantity(Long id, Double quantity);

}
