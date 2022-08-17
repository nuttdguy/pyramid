package com.genspark.spring.card.item;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    EntityManager entityManager;

    // get all the items
    List<Item> getItems() {
        return itemRepository.findAll();
    }

    // get one item
    Item getAnItemBy(Long theId) {
        return itemRepository.findById(theId).orElse(beanFactory.getBean(Item.class));
    }

    // save the item
    @Transactional
    Item addAnItem(Item theItem) {
        return itemRepository.save(theItem);
    }

    // update an item
    Item updateAnItem(Item theItem) {
        return itemRepository.save(theItem);
    }

    // delete an item
    Long deleteAnItemBy(Long theId) {
        if (itemRepository.existsById(theId)) {
            itemRepository.deleteById(theId);
            return 1L;
        }
        return 0L;
    }

}
