package org.genspark.inventory;


import org.genspark.util.AppContextUtil;
import org.genspark.inventory.domain.Inventory;
import org.genspark.inventory.service.InventoryService;
import org.genspark.inventory.service.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    private final InventoryServiceImpl inventoryServiceImpl;

    @Autowired
    public InventoryController(InventoryServiceImpl inventoryServiceImpl) {
        this.inventoryServiceImpl = inventoryServiceImpl;
    }

    @GetMapping("/inventory")
    public List<Inventory> getInventory() {
        return inventoryServiceImpl.getInventoryList();
    }

    @GetMapping("/inventory/{theId}")
    public Inventory getInventory(@PathVariable("theId") Long theId) {
        return inventoryServiceImpl.getInventoryById(theId);
    }

    @PostMapping("/inventory")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryServiceImpl.addInventory(inventory);
    }

    @PutMapping("/inventory")
    public Inventory updateInventory(@RequestBody Inventory inventory) {
        return inventoryServiceImpl.updateInventory(inventory);
    }

    @DeleteMapping("/inventory/{theId}")
    public Integer deleteInventory(@PathVariable("id") Long theId) {
        return inventoryServiceImpl.deleteInventoryById(theId);
    }





}
