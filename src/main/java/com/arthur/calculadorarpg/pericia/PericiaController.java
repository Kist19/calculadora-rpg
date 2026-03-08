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

    @PatchMapping("/{id}")
    public Pericia atualizarPericia(@PathVariable Long id, @RequestBody Pericia dadosAtualizados) {
        return periciaService.atualizarPericia(id, dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public void deletarPericia(@PathVariable Long id) {
        periciaService.deletarPericia(id);
    }
}