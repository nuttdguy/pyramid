package org.genspark.inventory.repository;

import org.genspark.inventory.domain.Inventory;

import javax.persistence.criteria.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryDaoImpl implements InventoryDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public InventoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        Session session = sessionFactory.openSession(); // open a session

        session.beginTransaction(); // begin a transaction

        if (inventory.getId() == null) {
            session.persist(inventory);
        } else {
            session.merge(inventory);  // persist / save the object within the session
        }
        // execute the transaction
        Transaction tx = session.getTransaction(); // get the transaction object
        tx.commit();  // commit the transaction to the db
        session.close();  // close the current session

        return getInventoryById(inventory.getId());  // get the inventory item
    }

    @Override
    public Inventory getInventoryById(Long theId) {
        Session session = sessionFactory.openSession();
        Inventory inventory = session.get(Inventory.class, theId);

        session.close();
        return inventory;
    }

    @Override
    public List<Inventory> getTheInventoryList() {
        Session session = sessionFactory.openSession();  // open session
        CriteriaBuilder cBuilder = session.getCriteriaBuilder();  // build query using builder

        // prepare query
        CriteriaQuery<Inventory> criteriaQuery = cBuilder.createQuery(Inventory.class);  // create query object with class
        Root<Inventory> root = criteriaQuery.from(Inventory.class);  // root / initializer statements

        CriteriaQuery<Inventory> criteriaQuery2 = criteriaQuery.select(root); // add query narrowing statements
        Query<Inventory> query = session.createQuery(criteriaQuery2);

        // execute the transaction
        List<Inventory> iList = query.getResultList();
        session.close();
        return iList;

    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cBuilder = session.getCriteriaBuilder();

        CriteriaUpdate<Inventory> update = cBuilder.createCriteriaUpdate(Inventory.class);
        Root<Inventory> root = update.from(Inventory.class);

        update.set("quantity", inventory.getQuantity());
        update.set("cost", inventory.getCost());
        update.set("price", inventory.getPrice());
        update.set("weight", inventory.getWeight());
        update.where(cBuilder.equal(root.get("id"), inventory.getId()));

        Transaction tx = session.beginTransaction();
        Integer count = session.createQuery(update).executeUpdate();
        tx.commit();
        session.close();

        return getInventoryById(inventory.getId());
    }

    @Override
    public Integer deleteInventoryById(Long theId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cBuilder = session.getCriteriaBuilder();

        CriteriaDelete<Inventory> delete = cBuilder.createCriteriaDelete(Inventory.class);
        Root<Inventory> root = delete.from(Inventory.class);
        delete.where(cBuilder.equal(root.get("id"), theId));

        Transaction tx = session.beginTransaction();
        Integer count = session.createQuery(delete).executeUpdate();
        tx.commit();
        session.close();

        return count;
    }


}
