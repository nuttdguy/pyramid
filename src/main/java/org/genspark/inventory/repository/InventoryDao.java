package org.genspark.inventory.repository;

import org.genspark.inventory.domain.Inventory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryDao {

    Inventory addInventory(Inventory inventory);
    Inventory getInventoryById(Long theId);
    List<Inventory> getTheInventoryList();
    Inventory updateInventory(Inventory inventory);
    Integer deleteInventoryById(Long theId);

}
