package org.genspark.inventory.repository;

import org.genspark.inventory.domain.Inventory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        session.persist(inventory);  // persist / save the object within the session

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
        return null;
    }

    @Override
    public Integer deleteInventoryById(Long theId) {
        return null;
    }


}
