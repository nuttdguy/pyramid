package org.genspark.inventory;

import com.sun.net.httpserver.HttpServer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InventoryService {

    public InventoryService() {    }

    public void addInventory() {
        SessionFactory sessionFactory;

        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Inventory inventory = new Inventory();
            inventory.setQuantity(5);
            inventory.setCost(30.00F);
            inventory.setPrice(60.00F);
            inventory.setWeight(8.24F);
            session.save(inventory);

            tx.commit();
        } catch (HibernateException hb) {
            if (tx!=null) tx.rollback();
            hb.printStackTrace();
        } finally {
            session.close();
        }


    }

}
