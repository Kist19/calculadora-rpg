package com.arthur.calculadorarpg.item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item criarItem(@RequestBody Item item) {
        return itemService.criarItem(item);
    }

    @GetMapping
    public List<Item> listarItens() {
        return itemService.listarItens();
    }
}
