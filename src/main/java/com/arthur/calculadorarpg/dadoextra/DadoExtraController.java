package com.arthur.calculadorarpg.dadoextra;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dados-extras")
public class DadoExtraController {

    private final DadoExtraService dadoExtraService;

    public DadoExtraController(DadoExtraService dadoExtraService) {
        this.dadoExtraService = dadoExtraService;
    }

    @PostMapping
    public DadoExtra criarDadoExtra(@RequestBody DadoExtra dadoExtra) {
        return dadoExtraService.criarDadoExtra(dadoExtra);
    }

    @GetMapping
    public List<DadoExtra> listarTodos() {
        return dadoExtraService.listarTodos();
    }

    @GetMapping("/{id}")
    public DadoExtra buscarPorId(@PathVariable Long id) {
        return dadoExtraService.buscarPorId(id);
    }

    @GetMapping("/origem/{origemTipo}/{origemId}")
    public List<DadoExtra> buscarPorOrigem(
            @PathVariable DadoExtraOrigemTipo origemTipo,
            @PathVariable Long origemId) {
        return dadoExtraService.buscarPorOrigem(origemTipo, origemId);
    }

    @GetMapping("/origem/{origemTipo}/{origemId}/ligados")
    public List<DadoExtra> buscarLigadosPorOrigem(
            @PathVariable DadoExtraOrigemTipo origemTipo,
            @PathVariable Long origemId) {
        return dadoExtraService.buscarLigadosPorOrigem(origemTipo, origemId);
    }

    @PatchMapping("/{id}")
    public DadoExtra atualizarDadoExtra(
            @PathVariable Long id,
            @RequestBody DadoExtra dadoExtraAtualizado) {
        return dadoExtraService.atualizarDadoExtra(id, dadoExtraAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarDadoExtra(@PathVariable Long id) {
        dadoExtraService.deletarDadoExtra(id);
    }
}