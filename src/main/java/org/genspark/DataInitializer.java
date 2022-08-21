package org.genspark;

import org.genspark.inventory.domain.Inventory;
import org.genspark.inventory.repository.InventoryDao;
import org.genspark.util.AppContextUtil;

import java.util.Random;
import java.util.stream.Stream;

public class DataInitializer {

    public static void seedTheDb() {

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Inventory inventory = new Inventory();

            inventory.setQuantity( (int) (Math.random() * 100));
            inventory.setCost(random.nextFloat() * 50);
            inventory.setPrice(random.nextFloat() * 100);
            inventory.setWeight(random.nextFloat() * 30);

            InventoryDao inventoryDao = AppContextUtil.getApplicationContext().getBean(InventoryDao.class);
            inventoryDao.addInventory(inventory);

            Stream.of(inventoryDao.getTheInventoryList()).forEach(System.out::println);
        }

    }

}
