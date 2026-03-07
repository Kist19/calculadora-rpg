package com.arthur.calculadorarpg.personagem;

import org.springframework.web.bind.annotation.*;

import java.util.Map; 
import java.util.List;

import com.arthur.calculadorarpg.pericia.PericiaService;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {
    private final PersonagemRepository personagemRepositorio;
    private final PersonagemService personagemService;
    private final PericiaService periciaService;

    public PersonagemController(PersonagemRepository personagemRepositorio, PersonagemService personagemServico, PericiaService periciaService) {
        this.personagemRepositorio = personagemRepositorio;
        this.personagemService = personagemServico;
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
}
