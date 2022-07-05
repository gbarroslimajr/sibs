package com.sibs.domain.repository;

import com.sibs.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author geraldobarrosjr
 */

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContaining(String name);

    Item findByName(String name);
}
