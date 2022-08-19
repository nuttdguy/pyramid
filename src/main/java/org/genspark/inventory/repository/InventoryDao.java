package org.genspark.inventory.repository;

import org.genspark.inventory.domain.Inventory;

import java.util.List;

public interface InventoryDao {

    Inventory addInventory(Inventory inventory);
    Inventory getInventoryById(Long theId);
    List<Inventory> getTheInventoryList();

}
