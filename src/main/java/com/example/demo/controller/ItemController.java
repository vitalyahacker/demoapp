
package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.repo.ItemsRepository;
import com.example.demo.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ItemsRepository itemsRepository;

    public ItemController(ItemService itemService, ItemsRepository itemsRepository) {
        this.itemService = itemService;
        this.itemsRepository = itemsRepository;
    }

    @PostMapping
    public void init() {
        itemService.fillDbWithItems(1000);
    }

    @GetMapping
    public List<Item> start() {
        long before = System.nanoTime();
        List<Item> all = itemsRepository.findAll();
        long after = System.nanoTime();
        System.out.println("Execution time: " + TimeUnit.NANOSECONDS.toMillis(after - before));
        return all;
    }
}
