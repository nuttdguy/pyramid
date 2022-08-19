package org.genspark;


import org.genspark.inventory.domain.Inventory;
import org.genspark.inventory.service.InventoryService;
import org.genspark.inventory.service.InventoryServiceImpl;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.stream.Stream;

public class App {

    public static void main( String[] args ) {
        AbstractApplicationContext appContext = null;

        try {
            appContext = new ClassPathXmlApplicationContext("ApplicationContext.xml", "authDataSource.xml");
            DataInitializer dataInitializer = appContext.getBean(DataInitializer.class);
            dataInitializer.seedTheDb();

            InventoryService iService = appContext.getBean(InventoryServiceImpl.class);
            List<Inventory> iList = iService.getInventoryList();

            Stream.of(iList).forEach(System.out::println);

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(appContext != null)
                appContext.close();
        }
    }



}
