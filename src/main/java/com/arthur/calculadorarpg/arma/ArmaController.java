package com.arthur.calculadorarpg.arma;

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
@RequestMapping("/arma")
public class ArmaController {
    private final ArmaService armaService;

    public ArmaController(ArmaService armaService) {
        this.armaService = armaService;
    }

    @PostMapping
    public Arma criarArma(@RequestBody Arma arma) {
        return armaService.criarArma(arma);
    }

    @PostMapping("/inventario/{inventarioId}")
    public Arma criarArmaNoInventario(@PathVariable Long inventarioId, @RequestBody Arma arma) {
        return armaService.criarArmaNoInventario(inventarioId, arma);
    }

    @PostMapping("/{armaId}/inventario/{inventarioId}")
    public Arma adicionarArmaExistenteNoInventario(@PathVariable Long armaId, @PathVariable Long inventarioId) {
        return armaService.adicionarArmaExistenteNoInventario(armaId, inventarioId);
    }

    @GetMapping
    public List<Arma> listarArmas() {
        return armaService.listarArmas();
    }

    @GetMapping("/{armaId}")
    public Arma buscarPorId(@PathVariable Long armaId) {
        return armaService.buscarPorId(armaId);
    }

    @PatchMapping("/{id}")
    public Arma atualizarArma(@PathVariable Long id, @RequestBody Arma dados) {
        return armaService.atualizarArma(id, dados);
    }

    @PatchMapping("/{armaId}/remover-inventario")
    public void removerArmaDoInventario(@PathVariable Long armaId) {
        armaService.removerArmaDoInventario(armaId);
    }

    @DeleteMapping("/{id}")
    public void deletarArma(@PathVariable Long id) {
        armaService.deletarArma(id);
    }
}
