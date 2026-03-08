package com.arthur.calculadorarpg.personagem;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.arthur.calculadorarpg.pericia.PericiaService;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    private final PersonagemRepository personagemRepositorio;
    private final PersonagemService personagemService;
    private final PericiaService periciaService;

    public PersonagemController(
            PersonagemRepository personagemRepositorio,
            PersonagemService personagemService,
            PericiaService periciaService) {
        this.personagemRepositorio = personagemRepositorio;
        this.personagemService = personagemService;
        this.periciaService = periciaService;
    }

    @PostMapping
    public Personagem criarPersonagem(@RequestBody Personagem personagem) {
        return personagemService.criarPersonagem(personagem);
    }

    @GetMapping
    public List<Personagem> listarPersonagens() {
        return personagemService.listarPersonagens();
    }

    @GetMapping("/{id}/pericias")
    public Map<String, Integer> buscarPericias(@PathVariable Long id) {
        Personagem personagem = personagemRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        return periciaService.calcularPericias(personagem);
    }

    @PatchMapping("/{id}")
    public Personagem atualizarPersonagem(
            @PathVariable Long id,
            @RequestBody Personagem dadosAtualizados) {

        return personagemService.atualizarPersonagem(id, dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public void deletarPersonagem(@PathVariable Long id) {
        personagemService.deletarPersonagem(id);
    }

    @PostMapping("/{personagemId}/equipar-arma/{armaId}")
    public Personagem equiparArma(@PathVariable Long personagemId, @PathVariable Long armaId) {
        return personagemService.equiparArma(personagemId, armaId);
    }

    @PostMapping("/{personagemId}/desequipar-arma")
    public Personagem desequiparArma(@PathVariable Long personagemId) {
        return personagemService.desequiparArma(personagemId);
    }

    @PostMapping("/{personagemId}/equipar-armadura/{armaduraId}")
    public Personagem equiparArmadura(@PathVariable Long personagemId, @PathVariable Long armaduraId) {
        return personagemService.equiparArmadura(personagemId, armaduraId);
    }

    @PostMapping("/{personagemId}/desequipar-armadura")
    public Personagem desequiparArmadura(@PathVariable Long personagemId) {
        return personagemService.desequiparArmadura(personagemId);
    }

    @PostMapping("/{personagemId}/equipar-escudo/{armaduraId}")
    public Personagem equiparEscudo(@PathVariable Long personagemId, @PathVariable Long armaduraId) {
        return personagemService.equiparEscudo(personagemId, armaduraId);
    }

    @PostMapping("/{personagemId}/desequipar-escudo")
    public Personagem desequiparEscudo(@PathVariable Long personagemId) {
        return personagemService.desequiparEscudo(personagemId);
    }
}