package com.arthur.calculadorarpg.atributo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Atributo criarAtributo(Atributo atributo) {
        return atributoService.criarAtributo(atributo);
    }

    @GetMapping
    public List<Atributo> listarAtributos() {
        return atributoService.listarAtributos();
    }
}
