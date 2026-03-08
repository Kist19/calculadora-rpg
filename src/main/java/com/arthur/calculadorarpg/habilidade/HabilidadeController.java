package com.arthur.calculadorarpg.habilidade;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {

    private final HabilidadeRepository habilidadeRepository;
    private final HabilidadeService habilidadeService;

    public HabilidadeController(
            HabilidadeRepository habilidadeRepository,
            HabilidadeService habilidadeService) {
        this.habilidadeRepository = habilidadeRepository;
        this.habilidadeService = habilidadeService;
    }

    @PostMapping
    public Habilidade criarHabilidade(@RequestBody Habilidade habilidade) {
        return habilidadeRepository.save(habilidade);
    }

    @GetMapping
    public List<Habilidade> listarHabilidades() {
        return habilidadeRepository.findAll();
    }

    @PostMapping("/usar")
    public String usarHabilidade(@RequestParam Long personagemId, @RequestParam Long habilidadeId) {
        return habilidadeService.usarHabilidade(personagemId, habilidadeId);
    }

    @PatchMapping("/{id}")
    public Habilidade atualizarHabilidade(@PathVariable Long id, @RequestBody Habilidade dadosAtualizados) {
        return habilidadeService.atualizarHabilidade(id, dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public void deletarHabilidade(@PathVariable Long id) {
        habilidadeService.deletarHabilidade(id);
    }
}
