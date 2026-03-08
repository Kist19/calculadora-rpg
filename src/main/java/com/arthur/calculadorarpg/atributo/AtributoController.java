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

    @PostMapping
    public Atributo criarAtributo(@RequestBody Atributo atributo) {
        return atributoService.criarAtributo(atributo);
    }

    @GetMapping
    public List<Atributo> listarAtributos() {
        return atributoService.listarAtributos();
    }

    @PatchMapping("/{id}")
    public Atributo atualizarAtributo(@PathVariable Long id, @RequestBody Atributo dadosAtualizados) {
        return atributoService.atualizarAtributo(id, dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public void deletarAtributo(@PathVariable Long id) {
        atributoService.deletarAtributo(id);
    }
}
