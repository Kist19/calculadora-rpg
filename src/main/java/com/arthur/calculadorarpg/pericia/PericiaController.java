package com.arthur.calculadorarpg.pericia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Pericia criarPericia(Pericia pericia) {
        return periciaService.criarPericia(pericia);
    }

    @GetMapping
    public List<Pericia> listarPericias() {
        return periciaService.listarPericias();
    }
}
