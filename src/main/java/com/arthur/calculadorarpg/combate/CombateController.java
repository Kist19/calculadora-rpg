package com.arthur.calculadorarpg.combate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personagens")
public class CombateController {

    private final CombateService combateService;

    public CombateController(CombateService combateService) {
        this.combateService = combateService;
    }

    @GetMapping("/{personagemId}/dados-combate")
    public ResponseEntity<CombateDadosResponse> buscarDadosCombate(@PathVariable Long personagemId) {
        CombateDadosResponse response = combateService.buscarDadosCombate(personagemId);
        return ResponseEntity.ok(response);
    }
}