package org.genspark.inventory.service;

import org.genspark.inventory.domain.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getInventoryList();
    Inventory getInventoryById(Long theId);
    Inventory addInventory(Inventory inventory);
}
