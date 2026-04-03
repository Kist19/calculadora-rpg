package com.arthur.calculadorarpg.efeitoativo;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/efeito")
public class EfeitoAtivoController {

    private final EfeitoAtivoService efeitoAtivoService;

    public EfeitoAtivoController(EfeitoAtivoService efeitoAtivoService) {
        this.efeitoAtivoService = efeitoAtivoService;
    }

    @PostMapping("/{efeitoId}/personagem/{personagemId}")
    public EfeitoAtivo aplicarEfeitoAoPersonagem(
            @PathVariable Long efeitoId,
            @PathVariable Long personagemId) {
        return efeitoAtivoService.aplicarEfeitoAoPersonagem(efeitoId, personagemId);
    }

    @GetMapping("/personagem/{personagemId}")
    public List<EfeitoAtivo> listarEfeitosPorPersonagem(@PathVariable Long personagemId) {
        return efeitoAtivoService.listarEfeitosPorPersonagem(personagemId);
    }

    @PostMapping("/personagem/{personagemId}/passar-turno")
    public Map<String, Object> passarTurno(@PathVariable Long personagemId) {
        return efeitoAtivoService.passarTurno(personagemId);
    }

    @DeleteMapping("/personagem/{personagemId}/{efeitoAtivoId}")
    public void removerEfeitoAtivo(
            @PathVariable Long personagemId,
            @PathVariable Long efeitoAtivoId) {
        efeitoAtivoService.removerEfeitoAtivo(personagemId, efeitoAtivoId);
    }
}