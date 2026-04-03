package com.arthur.calculadorarpg.inventario;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/personagem/{personagemId}")
    public List<Inventario> listarInventario(@PathVariable Long personagemId) {
        return inventarioService.listarInventario(personagemId);
    }

    @GetMapping("/completo/personagem/{personagemId}")
    public Map<String, Object> verInventarioCompleto(@PathVariable Long personagemId) {
        return inventarioService.verInventarioCompleto(personagemId);
    }

    @GetMapping("/base/personagem/{personagemId}")
    public InventarioBaseResponseDTO buscarInventarioBase(@PathVariable Long personagemId) {
        return inventarioService.buscarInventarioBaseDTO(personagemId);
    }

    @PostMapping("/{inventarioId}/usar")
    public void usarItem(@PathVariable Long inventarioId) {
        inventarioService.usarItem(inventarioId);
    }

    @PostMapping("/personagem/{personagemId}/item/{itemId}")
    public Inventario adicionarItemAoPersonagem(
            @PathVariable Long personagemId,
            @PathVariable Long itemId,
            @RequestParam(defaultValue = "1") Integer quantidade) {
        return inventarioService.adicionarItemAoPersonagem(personagemId, itemId, quantidade);
    }

    @DeleteMapping("/{inventarioId}/item/{itemId}")
    public void removerItemDoInventario(
            @PathVariable Long inventarioId,
            @PathVariable Long itemId,
            @RequestParam(defaultValue = "1") Integer quantidade) {
        inventarioService.removerItemDoInventario(inventarioId, itemId, quantidade);
    }

    @DeleteMapping("/{inventarioId}/arma/{armaId}")
    public void removerArmaDoInventario(
            @PathVariable Long inventarioId,
            @PathVariable Long armaId) {
        inventarioService.removerArmaDoInventario(inventarioId, armaId);
    }

    @DeleteMapping("/{inventarioId}/armadura/{armaduraId}")
    public void removerArmaduraDoInventario(
            @PathVariable Long inventarioId,
            @PathVariable Long armaduraId) {
        inventarioService.removerArmaduraDoInventario(inventarioId, armaduraId);
    }
}