package org.genspark.inventory;


import org.genspark.inventory.service.InventoryServiceImpl;

public class InventoryController {

    private final InventoryServiceImpl inventoryServiceImpl;

    public InventoryController(InventoryServiceImpl inventoryServiceImpl) {
        this.inventoryServiceImpl = inventoryServiceImpl;
    }

    public void getInventory() {    }

}
