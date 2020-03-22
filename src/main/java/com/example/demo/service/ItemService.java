package com.example.demo.service;

import com.example.demo.generator.RandomEntityGenerator;
import com.example.demo.repo.ItemsRepository;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class ItemService {
    private final ItemsRepository itemsRepository;
    private final RandomEntityGenerator generator;

    public ItemService(ItemsRepository itemsRepository, RandomEntityGenerator generator) {
        this.itemsRepository = itemsRepository;
        this.generator = generator;
    }

    public void fillDbWithItems(int number) {
        IntStream.range(0, number)
                .mapToObj(i->generator.generate())
                .forEach(itemsRepository::save);
    }

}
