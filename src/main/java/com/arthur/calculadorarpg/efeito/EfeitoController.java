package com.arthur.calculadorarpg.efeito;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/efeito")
public class EfeitoController {

    private final EfeitoService efeitoService;

    public EfeitoController(EfeitoService efeitoService) {
        this.efeitoService = efeitoService;
    }

    @PostMapping
    public Efeito criarEfeito(@RequestBody Efeito efeito) {
        return efeitoService.criarEfeito(efeito);
    }

    @GetMapping
    public List<Efeito> listarEfeitos() {
        return efeitoService.listarEfeitos();
    }

    @GetMapping("/{efeitoId}")
    public Efeito buscarPorId(@PathVariable Long efeitoId) {
        return efeitoService.buscarPorId(efeitoId);
    }

    @PatchMapping("/{id}")
    public Efeito atualizarEfeito(@PathVariable Long id, @RequestBody Efeito dadosAtualizados) {
        return efeitoService.atualizarEfeito(id, dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public void deletarEfeito(@PathVariable Long id) {
        efeitoService.deletarEfeito(id);
    }
}