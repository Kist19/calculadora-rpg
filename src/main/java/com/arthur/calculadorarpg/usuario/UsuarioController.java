package com.arthur.calculadorarpg.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO response = usuarioService.cadastrar(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{usuarioId}/personagem/{personagemId}")
    public ResponseEntity<UsuarioResponseDTO> vincularPersonagem(@PathVariable Long usuarioId,
                                                                 @PathVariable Long personagemId) {
        UsuarioResponseDTO response = usuarioService.vincularPersonagem(usuarioId, personagemId);
        return ResponseEntity.ok(response);
    }
}