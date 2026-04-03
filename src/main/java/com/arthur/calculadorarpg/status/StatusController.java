package com.arthur.calculadorarpg.status;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/personagem/{personagemId}")
    public Status criarStatus(@PathVariable Long personagemId, @RequestBody Status status) {
        return statusService.criarStatus(personagemId, status);
    }

    @GetMapping
    public List<Status> listarStatus() {
        return statusService.listarStatus();
    }

    @PatchMapping("/personagem/{personagemId}/vida/dano")
    public Status aplicarDano(
            @PathVariable Long personagemId,
            @RequestBody Map<String, Integer> body) {

        Integer dano = body.get("dano");

        return statusService.aplicarDano(personagemId, dano);
    }

    @PatchMapping("/personagem/{personagemId}/mana/gastar")
    public Status gastarMana(
            @PathVariable Long personagemId,
            @RequestBody Map<String, Integer> body) {

        Integer mana = body.get("mana");

        return statusService.gastarMana(personagemId, mana);
    }

    @GetMapping("/personagem/{personagemId}")
    public Status buscarStatusPorPersonagemId(@PathVariable Long personagemId) {
        return statusService.buscarStatusPorPersonagemId(personagemId);
    }

    @PatchMapping("/personagem/{personagemId}")
public ResponseEntity<Status> atualizarStatusPorPersonagem(
        @PathVariable Long personagemId,
        @RequestBody Status statusAtualizado) {

    Status status = statusService.atualizarStatusPorPersonagem(personagemId, statusAtualizado);
    return ResponseEntity.ok(status);
}

    @DeleteMapping("/personagem/{personagemId}")
    public void deletarStatusPorPersonagemId(@PathVariable Long personagemId) {
        statusService.deletarStatusPorPersonagemId(personagemId);
    }
}