package com.arthur.calculadorarpg.loja;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    private final LojaItemRepository lojaItemRepository;
    private final LojaService lojaService;

    public LojaController(
            LojaItemRepository lojaItemRepository,
            LojaService lojaService) {

        this.lojaItemRepository = lojaItemRepository;
        this.lojaService = lojaService;
    }

    @PostMapping
    public Loja criarLoja(@RequestBody Loja loja) {
        return lojaService.criarLoja(loja);
    }

    @GetMapping
    public List<Loja> listarLojas() {
        return lojaService.listarLojas();
    }

    @GetMapping("/ativas")
    public List<Loja> listarLojasAtivas() {
        return lojaService.listarLojasAtivas();
    }

    @GetMapping("/{lojaId}")
    public Loja buscarPorId(@PathVariable Long lojaId) {
        return lojaService.buscarPorId(lojaId);
    }

    @GetMapping("/{lojaId}/itens")
    public List<LojaItem> listarItensDaLoja(@PathVariable Long lojaId) {
        return lojaItemRepository.findByLojaId(lojaId);
    }

    @GetMapping("/loja-item/{lojaItemId}")
    public LojaItem buscarLojaItemPorId(@PathVariable Long lojaItemId) {
        return lojaService.buscarLojaItemPorId(lojaItemId);
    }

    @PostMapping("/comprar")
    public String comprarItem(@RequestParam Long personagemId, @RequestParam Long lojaItemId) {
        return lojaService.comprarItem(personagemId, lojaItemId);
    }

    @PatchMapping("/{id}")
    public Loja atualizarLoja(@PathVariable Long id, @RequestBody Loja dadosAtualizados) {
        return lojaService.atualizarLoja(id, dadosAtualizados);
    }

    @PatchMapping("/{id}/ativar")
    public Loja ativarLoja(@PathVariable Long id) {
        return lojaService.ativarLoja(id);
    }

    @DeleteMapping("/{id}")
    public void deletarLoja(@PathVariable Long id) {
        lojaService.deletarLoja(id);
    }

    @PostMapping("/{lojaId}/itens")
    public LojaItem adicionarItemNaLoja(@PathVariable Long lojaId, @RequestBody LojaItem lojaItem) {
        return lojaService.adicionarProdutoNaLoja(lojaId, lojaItem);
    }

    @PatchMapping("/loja-item/{id}")
    public LojaItem atualizarLojaItem(@PathVariable Long id, @RequestBody LojaItem dadosAtualizados) {
        return lojaService.atualizarLojaItem(id, dadosAtualizados);
    }

    @DeleteMapping("/loja-item/{id}")
    public void deletarLojaItem(@PathVariable Long id) {
        lojaService.deletarLojaItem(id);
    }
}