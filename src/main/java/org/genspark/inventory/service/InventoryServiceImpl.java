package org.genspark.inventory.service;


import org.genspark.inventory.domain.Inventory;
import org.genspark.inventory.repository.InventoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class InventoryServiceImpl implements InventoryService {

    private final InventoryDao inventoryDao;

    @Autowired
    InventoryServiceImpl(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Override
    public List<Inventory> getInventoryList() {
        return inventoryDao.getTheInventoryList();
    }

    @Override
    public Inventory getInventoryById(Long theId) {
        return inventoryDao.getInventoryById(theId);
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryDao.addInventory(inventory);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryDao.updateInventory(inventory);
    }

    @Override
    public Integer deleteInventoryById(Long theId) {
        return inventoryDao.deleteInventoryById(theId);
    }
}
