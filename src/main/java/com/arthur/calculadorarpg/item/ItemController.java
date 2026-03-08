package com.arthur.calculadorarpg.item;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PatchMapping("/{id}")
    public Item atualizarItem(@PathVariable Long id, @RequestBody Item dadosAtualizados) {
        return itemService.atualizarItem(id, dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public void deletarItem(@PathVariable Long id) {
        itemService.deletarItem(id);
    }
}
