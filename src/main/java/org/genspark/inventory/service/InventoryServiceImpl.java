package org.genspark.inventory.service;


import org.genspark.inventory.domain.Inventory;
import org.genspark.inventory.repository.InventoryDao;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private InventoryDao inventoryDao;

    public InventoryDao getInventoryDao() {
        return inventoryDao;
    }

    public void setInventoryDao(InventoryDao inventoryDao) {
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
}
