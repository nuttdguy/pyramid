package org.genspark;

import com.sun.net.httpserver.HttpServer;
import org.genspark.inventory.Inventory;
import org.genspark.inventory.InventoryController;
import org.genspark.inventory.InventoryService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

//@SpringBootApplication
public class App {

    public static void main( String[] args ) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        InventoryController inventoryController = new InventoryController(server);

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("SERVER STARTED");

    }

    private static void init() {

    }

}
