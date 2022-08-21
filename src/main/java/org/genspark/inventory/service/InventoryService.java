package org.genspark.inventory.service;

import org.genspark.inventory.domain.Inventory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryService {

    List<Inventory> getInventoryList();
    Inventory getInventoryById(Long theId);
    Inventory addInventory(Inventory inventory);
    Inventory updateInventory(Inventory inventory);
    Integer deleteInventoryById(Long theId);

}
