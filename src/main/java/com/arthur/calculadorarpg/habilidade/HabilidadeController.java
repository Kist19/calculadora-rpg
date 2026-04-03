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

import com.arthur.calculadorarpg.personagem.Personagem;

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

    @PostMapping("/{habilidadeId}/personagem/{personagemId}")
    public Personagem vincularHabilidadeAoPersonagem(
            @PathVariable Long habilidadeId,
            @PathVariable Long personagemId) {
        return habilidadeService.vincularHabilidadeAoPersonagem(personagemId, habilidadeId);
    }

    @GetMapping
    public List<Habilidade> listarHabilidades() {
        return habilidadeRepository.findAll();
    }

    @GetMapping("/{habilidadeId}")
    public Habilidade buscarPorId(@PathVariable Long habilidadeId) {
        return habilidadeService.buscarPorId(habilidadeId);
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

    @DeleteMapping("/{habilidadeId}/personagem/{personagemId}")
    public void desvincularHabilidadeDoPersonagem(
            @PathVariable Long habilidadeId,
            @PathVariable Long personagemId) {

        habilidadeService.desvincularHabilidadeDoPersonagem(habilidadeId, personagemId);
    }
}
