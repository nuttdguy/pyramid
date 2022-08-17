package com.genspark.spring.card.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping(value={"/item", "/items"})
    List<Item> getItemCards() {
        return itemService.getItems();
    }

    @GetMapping("/item/{theId}")
    Item getAnItem(@PathVariable Long theId) {
        return itemService.getAnItemBy(theId);
    }

    @PostMapping("/item")
    Item addAnItem(@RequestBody Item newItem) {
        return itemService.addAnItem(newItem);
    }

    @PutMapping("/item")
    Item updateAnItem(@RequestBody Item newItem) {
        return itemService.updateAnItem(newItem);
    }

    @DeleteMapping("/item/{theId}")
    Long deleteAnItem(@PathVariable Long theId) {
        if (theId != null) {
            return itemService.deleteAnItemBy(theId);
        }
        return 0L;
    }

}
