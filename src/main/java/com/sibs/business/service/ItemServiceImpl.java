package com.sibs.business.service;

import com.sibs.api.dto.ItemDTO;
import com.sibs.business.interfaces.ItemService;
import com.sibs.domain.exception.BusinessException;
import com.sibs.domain.model.Item;
import com.sibs.domain.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author geraldobarrosjr
 */

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findByName(String name) {
        return itemRepository.findByNameContaining(name);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new BusinessException("Item not found with ID " + id, "Item Not Found"));
    }




    @Override
    public Item create(ItemDTO request) {

        Item item   = itemRepository.findByName(request.getName());
        if (item != null) {
            throw new BusinessException("Item already exists with name " + request.getName(), "Item Already Exists");
        }

        return itemRepository.save(toEntity(request));
    }

    @Override
    public Item update(Long id, ItemDTO request) {
        Item item = itemExists(id);

        if (item != null) {
            item.setName(request.getName());
            return itemRepository.save(item);

        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Item item = itemExists(id);
        if (item != null) {
            itemRepository.delete(item);
        }

    }

    @Override
    public void increaseItemQuantity(Long id, Double quantity) {
        Item item = itemExists(id);
        item.setQuantity(item.getQuantity() +  quantity);
        itemRepository.save(item);
    }

    @Override
    public void decreaseItemQuantity(Long id, Double quantity) {
        Item item = itemExists(id);
        item.setQuantity(item.getQuantity() -  quantity);
        itemRepository.save(item);

    }

    @Override
    public ItemDTO toModel(Item request) {
        return modelMapper.map(request, ItemDTO.class);
    }

    @Override
    public Item toEntity(ItemDTO request) {
        return modelMapper.map(request, Item.class);
    }

    @Override
    public List<ItemDTO> toModelList(List<Item> request) {
        return request.stream().map(this::toModel).collect(java.util.stream.Collectors.toList());
    }

    public Item itemExists(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElse(null);
    }


}
