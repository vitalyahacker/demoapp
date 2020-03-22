package com.example.demo.generator;

import com.example.demo.entity.Item;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RandomEntityGenerator {
    private static final Random RANDOM = new Random();

    public Item generate(){
        Item item = new Item();
        item.setName(getRandomName());
        item.setNumber(RANDOM.nextInt(1000));
        return item;
    }

    private String getRandomName() {
        return IntStream.range(0, 10)
                .map(i -> 97 + RANDOM.nextInt(25))
                .mapToObj(i -> (char) i)
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }
}
