package com.arthur.calculadorarpg.arma;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<Arma> listarArmas() {
        return armaService.listarArmas();
    }
}
