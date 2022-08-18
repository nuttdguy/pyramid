package org.genspark.inventory;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;

public class InventoryController {

    HttpServer server;
    InventoryService inventoryService;

    public InventoryController(HttpServer server) {
        this.server = server;
        inventoryService = new InventoryService();
        api();
        getInventory();
    }

    private void api() {
        server.createContext("/api/", (exchange -> {

            if ("GET".equals(exchange.getRequestMethod())) {
                String responseText = "Hello World! from our framework-less REST API\n";
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseText.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));

    }

    public void getInventory() {
        server.createContext("/api/inventory", (exchange -> {

            if ("GET".equals(exchange.getRequestMethod())) {
                String responseText = "Inventory";
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseText.getBytes());
                output.flush();
            } else if ("POST".equals(exchange.getRequestMethod())) {
                inventoryService.addInventory();

            } else {
                exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));
    }

}
