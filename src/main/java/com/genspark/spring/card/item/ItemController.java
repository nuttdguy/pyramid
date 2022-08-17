package com.genspark.spring.card.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/items"})
public class ItemController {

    private final ItemService itemService;

    @Autowired
    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value={"/"})
    List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{theId}")
    Item getAnItem(@PathVariable Long theId) {
        return itemService.getAnItemBy(theId);
    }

    @PostMapping("/")
    Item addAnItem(@RequestBody Item newItem) {
        return itemService.addAnItem(newItem);
    }

    @PutMapping("/")
    Item updateAnItem(@RequestBody Item newItem) {
        return itemService.updateAnItem(newItem);
    }

    @DeleteMapping("/{theId}")
    Long deleteAnItem(@PathVariable Long theId) {
        if (theId != null) {
            return itemService.deleteAnItemBy(theId);
        }
        return 0L;
    }

}
