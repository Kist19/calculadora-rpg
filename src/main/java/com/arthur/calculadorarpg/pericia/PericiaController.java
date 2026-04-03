package com.arthur.calculadorarpg.pericia;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pericia")
public class PericiaController {
    private final PericiaService periciaService;

    public PericiaController(PericiaService periciaService) {
        this.periciaService = periciaService;
    }

    @PostMapping
    public Pericia criarPericia(@RequestBody Pericia pericia) {
        return periciaService.criarPericia(pericia);
    }

    @GetMapping
    public List<Pericia> listarPericias() {
        return periciaService.listarPericias();
    }

    @PostMapping("/personagem/{personagemId}")
    public Pericia criarPericiaPorPersonagemId(
            @PathVariable Long personagemId,
            @RequestBody Pericia pericia) {
        return periciaService.criarPericiaPorPersonagemId(personagemId, pericia);
    }

    @GetMapping("/personagem/{personagemId}")
    public Pericia buscarPericiaPorPersonagemId(@PathVariable Long personagemId) {
        return periciaService.buscarPericiaPorPersonagemId(personagemId);
    }

    @PatchMapping("/personagem/{personagemId}")
    public Pericia atualizarPericiaPorPersonagemId(
            @PathVariable Long personagemId,
            @RequestBody Pericia dadosAtualizados) {
        return periciaService.atualizarPericiaPorPersonagemId(personagemId, dadosAtualizados);
    }

    @DeleteMapping("/personagem/{personagemId}")
    public void deletarPericiaPorPersonagemId(@PathVariable Long personagemId) {
        periciaService.deletarPericiaPorPersonagemId(personagemId);
    }
}