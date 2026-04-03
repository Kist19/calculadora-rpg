package com.arthur.calculadorarpg.atributo;

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
@RequestMapping("/atributo")
public class AtributoController {
    private final AtributoService atributoService;

    public AtributoController(AtributoService atributoServico) {
        this.atributoService = atributoServico;
    }

    @PostMapping("/personagem/{personagemId}")
    public Atributo criarAtributo(@PathVariable Long personagemId,
            @RequestBody Atributo atributo) {

        return atributoService.criarAtributo(personagemId, atributo);
    }

    @GetMapping
    public List<Atributo> listarAtributos() {
        return atributoService.listarAtributos();
    }

    @GetMapping("/personagem/{personagemId}")
    public Atributo buscarAtributoPorPersonagemId(@PathVariable Long personagemId) {
        return atributoService.buscarAtributoPorPersonagemId(personagemId);
    }

    @PatchMapping("/personagem/{personagemId}")
    public Atributo atualizarAtributoPorPersonagemId(
            @PathVariable Long personagemId,
            @RequestBody Atributo dadosAtualizados) {
        return atributoService.atualizarAtributoPorPersonagemId(personagemId, dadosAtualizados);
    }

    @DeleteMapping("/personagem/{personagemId}")
    public void deletarAtributoPorPersonagemId(@PathVariable Long personagemId) {
        atributoService.deletarAtributoPorPersonagemId(personagemId);
    }
}
