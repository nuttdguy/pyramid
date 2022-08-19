package org.genspark;

import org.genspark.inventory.domain.Inventory;
import org.genspark.inventory.repository.InventoryDao;
//import org.springframework.boot.CommandLineRunner;

import java.util.Random;

//@Component
//public class DataInitializer implements CommandLineRunner {
public class DataInitializer {
    // all classes implementing commandLineRunner gets executed on boot
    private InventoryDao inventoryDao;

    public InventoryDao getInventoryDao() {
        return inventoryDao;
    }

    public void setInventoryDao(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public void seedTheDb() {
//        inventoryDAO.deleteAll(); // delete all records from db before seeding data

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Inventory inventory = new Inventory();

            inventory.setQuantity( (int) (Math.random() * 100));
            inventory.setCost(random.nextFloat() * 50);
            inventory.setPrice(random.nextFloat() * 100);
            inventory.setWeight(random.nextFloat() * 30);

            inventoryDao.addInventory(inventory);
        }

    }
}
