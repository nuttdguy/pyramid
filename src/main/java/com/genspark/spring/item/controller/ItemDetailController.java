package com.genspark.spring.item.controller;

import com.genspark.spring.item.domain.Detail;
import com.genspark.spring.item.service.ItemItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class ItemDetailController {

    private final ItemItemDetailService itemDetailService;

    @Autowired
    public ItemDetailController(ItemItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    @GetMapping(value = {"/"})
    public List<Detail> getItemCards() {
        return itemDetailService.getItemDetails();
    }

    @GetMapping("/{theId}")
    public Detail getAnItem(@PathVariable Long theId) {
        return itemDetailService.getItemDetailBy(theId);
    }

    @PostMapping("/")
    public Detail addAnItem(@RequestBody Detail newDetail) {
        return itemDetailService.addItemDetail(newDetail);
    }

    @PutMapping("/")
    public Detail updateAnItem(@RequestBody Detail newDetail) {
        return itemDetailService.updateItemDetail(newDetail);
    }

    @DeleteMapping("/{theId}")
    public Long deleteAnItem(@PathVariable Long theId) {
        if (theId != null) {
            return itemDetailService.deleteItemDetailBy(theId);
        }
        return 0L;
    }


}
