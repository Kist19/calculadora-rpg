package com.arthur.calculadorarpg.loja;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    private final LojaRepository lojaRepository;
    private final LojaItemRepository lojaItemRepository;
    private final LojaService lojaService;

    public LojaController(LojaRepository lojaRepository, LojaItemRepository lojaItemRepository, LojaService lojaService) {
        this.lojaRepository = lojaRepository;
        this.lojaItemRepository = lojaItemRepository;
        this.lojaService = lojaService;
    }

    @PostMapping
    public Loja criarLoja(@RequestBody Loja loja) {
        return lojaRepository.save(loja);
    }

    @GetMapping
    public List<Loja> listarLojas() {
        return lojaRepository.findAll();
    }

    @GetMapping("/{lojaId}/itens")
    public List<LojaItem> listarItensDaLoja(@PathVariable Long lojaId) {
        return lojaItemRepository.findByLojaId(lojaId);
    }

    @PostMapping("/comprar")
    public String comprarItem(@RequestParam Long personagemId, @RequestParam Long lojaItemId) {
        return lojaService.comprarItem(personagemId, lojaItemId);
    }

    @PostMapping("/{lojaId}/itens")
    public LojaItem adicionarItemNaLoja(@PathVariable Long lojaId, @RequestBody LojaItem lojaItem){
        Loja loja = lojaRepository.findById(lojaId).orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        lojaItem.setLoja(loja);
        return lojaItemRepository.save(lojaItem);
    }
}
