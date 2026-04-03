package com.arthur.calculadorarpg.armadura;

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
@RequestMapping("/armadura")
public class ArmaduraController {
    private final ArmaduraService armaduraService;

    public ArmaduraController(ArmaduraService armaduraService) {
        this.armaduraService = armaduraService;
    }

    @PostMapping
    public Armadura criarArmadura(@RequestBody Armadura armadura) {
        return armaduraService.criarArmadura(armadura);
    }

    @PostMapping("/inventario/{inventarioId}")
    public Armadura criarArmaduraNoInventario(@PathVariable Long inventarioId, @RequestBody Armadura armadura) {
        return armaduraService.criarArmaduraNoInventario(inventarioId, armadura);
    }

    @PostMapping("/{armaduraId}/inventario/{inventarioId}")
    public Armadura adicionarArmaduraExistenteNoInventario(@PathVariable Long armaduraId,
            @PathVariable Long inventarioId) {
        return armaduraService.adicionarArmaduraExistenteNoInventario(armaduraId, inventarioId);
    }

    @GetMapping
    public List<Armadura> listarArmaduras() {
        return armaduraService.listarArmaduras();
    }

    @GetMapping("/{armaduraId}")
    public Armadura buscarPorId(@PathVariable Long armaduraId) {
        return armaduraService.buscarPorId(armaduraId);
    }

    @PatchMapping("/{id}")
    public Armadura atualizarArmadura(@PathVariable Long id, @RequestBody Armadura dadosAtualizados) {
        return armaduraService.atualizarArmadura(id, dadosAtualizados);
    }

    @PatchMapping("/{armaduraId}/remover-inventario")
    public void removerArmaduraDoInventario(@PathVariable Long armaduraId) {
        armaduraService.removerArmaduraDoInventario(armaduraId);
    }

    @DeleteMapping("/{id}")
    public void deletarArmadura(@PathVariable Long id) {
        armaduraService.deletarArmadura(id);
    }
}
