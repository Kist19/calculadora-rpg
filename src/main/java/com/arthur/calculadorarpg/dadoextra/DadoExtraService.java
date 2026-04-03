package com.arthur.calculadorarpg.dadoextra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DadoExtraService {

    private final DadoExtraRepository dadoExtraRepository;

    public DadoExtraService(DadoExtraRepository dadoExtraRepository) {
        this.dadoExtraRepository = dadoExtraRepository;
    }

    public DadoExtra criarDadoExtra(DadoExtra dadoExtra) {
        validarDadoExtra(dadoExtra);
        return dadoExtraRepository.save(dadoExtra);
    }

    public List<DadoExtra> listarTodos() {
        return dadoExtraRepository.findAll();
    }

    public DadoExtra buscarPorId(Long id) {
        return dadoExtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dado extra não encontrado"));
    }

    public List<DadoExtra> buscarPorOrigem(DadoExtraOrigemTipo origemTipo, Long origemId) {
        return dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdOrderByDadoExtraOrdemAsc(origemTipo, origemId);
    }

    public List<DadoExtra> buscarLigadosPorOrigem(DadoExtraOrigemTipo origemTipo, Long origemId) {
        return dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId);
    }

    public List<DadoExtra> buscarLigadosPorOrigemEAplicacao(
            DadoExtraOrigemTipo origemTipo,
            Long origemId,
            DadoExtraAplicacao aplicacao) {
        return dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId, aplicacao);
    }

    public List<DadoExtra> buscarDadosExtrasCombatePorOrigemEAplicacao(
            DadoExtraOrigemTipo origemTipo,
            Long origemId,
            DadoExtraAplicacao aplicacao) {

        List<DadoExtra> dadosCombate = dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId, DadoExtraContexto.COMBATE, aplicacao);

        List<DadoExtra> dadosAmbos = dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId, DadoExtraContexto.AMBOS, aplicacao);

        List<DadoExtra> resultado = new ArrayList<>();
        resultado.addAll(dadosCombate);
        resultado.addAll(dadosAmbos);

        return resultado;
    }

    public List<DadoExtra> buscarDadosExtrasForaCombatePorOrigemEAplicacao(
            DadoExtraOrigemTipo origemTipo,
            Long origemId,
            DadoExtraAplicacao aplicacao) {

        List<DadoExtra> dadosForaCombate = dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId, DadoExtraContexto.FORA_COMBATE, aplicacao);

        List<DadoExtra> dadosAmbos = dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId, DadoExtraContexto.AMBOS, aplicacao);

        List<DadoExtra> resultado = new ArrayList<>();
        resultado.addAll(dadosForaCombate);
        resultado.addAll(dadosAmbos);

        return resultado;
    }

    public List<DadoExtra> buscarDadosExtrasTurnoPorOrigem(
            DadoExtraOrigemTipo origemTipo,
            Long origemId,
            DadoExtraAplicacao aplicacao) {

        if (aplicacao != DadoExtraAplicacao.DANO_POR_TURNO
                && aplicacao != DadoExtraAplicacao.CURA_POR_TURNO) {
            throw new RuntimeException("A aplicação informada não é válida para turno");
        }

        List<DadoExtra> dadosCombate = dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId, DadoExtraContexto.COMBATE, aplicacao);

        List<DadoExtra> dadosAmbos = dadoExtraRepository
                .findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
                        origemTipo, origemId, DadoExtraContexto.AMBOS, aplicacao);

        List<DadoExtra> resultado = new ArrayList<>();
        resultado.addAll(dadosCombate);
        resultado.addAll(dadosAmbos);

        return resultado;
    }

    public DadoExtra atualizarDadoExtra(Long id, DadoExtra dadoExtraAtualizado) {
        DadoExtra dadoExtraExistente = buscarPorId(id);

        if (dadoExtraAtualizado.getDadoExtraNome() != null) {
            dadoExtraExistente.setDadoExtraNome(dadoExtraAtualizado.getDadoExtraNome());
        }

        if (dadoExtraAtualizado.getDadoExtraQuantidade() != null) {
            dadoExtraExistente.setDadoExtraQuantidade(dadoExtraAtualizado.getDadoExtraQuantidade());
        }

        if (dadoExtraAtualizado.getDadoExtraTipoDado() != null) {
            dadoExtraExistente.setDadoExtraTipoDado(dadoExtraAtualizado.getDadoExtraTipoDado());
        }

        if (dadoExtraAtualizado.getDadoExtraDescricao() != null) {
            dadoExtraExistente.setDadoExtraDescricao(dadoExtraAtualizado.getDadoExtraDescricao());
        }

        if (dadoExtraAtualizado.getDadoExtraContexto() != null) {
            dadoExtraExistente.setDadoExtraContexto(dadoExtraAtualizado.getDadoExtraContexto());
        }

        if (dadoExtraAtualizado.getDadoExtraAplicacao() != null) {
            dadoExtraExistente.setDadoExtraAplicacao(dadoExtraAtualizado.getDadoExtraAplicacao());
        }

        if (dadoExtraAtualizado.getDadoExtraOrigemTipo() != null) {
            dadoExtraExistente.setDadoExtraOrigemTipo(dadoExtraAtualizado.getDadoExtraOrigemTipo());
        }

        if (dadoExtraAtualizado.getDadoExtraOrigemId() != null) {
            dadoExtraExistente.setDadoExtraOrigemId(dadoExtraAtualizado.getDadoExtraOrigemId());
        }

        if (dadoExtraAtualizado.getDadoExtraLigado() != null) {
            dadoExtraExistente.setDadoExtraLigado(dadoExtraAtualizado.getDadoExtraLigado());
        }

        if (dadoExtraAtualizado.getDadoExtraOrdem() != null) {
            dadoExtraExistente.setDadoExtraOrdem(dadoExtraAtualizado.getDadoExtraOrdem());
        }

        validarDadoExtra(dadoExtraExistente);
        return dadoExtraRepository.save(dadoExtraExistente);
    }

    public void deletarDadoExtra(Long id) {
        DadoExtra dadoExtra = buscarPorId(id);
        dadoExtraRepository.delete(dadoExtra);
    }

    private void validarDadoExtra(DadoExtra dadoExtra) {
        if (dadoExtra.getDadoExtraNome() == null || dadoExtra.getDadoExtraNome().isBlank()) {
            throw new RuntimeException("O nome do dado extra é obrigatório");
        }

        if (dadoExtra.getDadoExtraQuantidade() == null || dadoExtra.getDadoExtraQuantidade() <= 0) {
            throw new RuntimeException("A quantidade do dado extra deve ser maior que zero");
        }

        if (dadoExtra.getDadoExtraTipoDado() == null) {
            throw new RuntimeException("O tipo do dado extra é obrigatório");
        }

        if (dadoExtra.getDadoExtraContexto() == null) {
            throw new RuntimeException("O contexto do dado extra é obrigatório");
        }

        if (dadoExtra.getDadoExtraAplicacao() == null) {
            throw new RuntimeException("A aplicação do dado extra é obrigatória");
        }

        if (dadoExtra.getDadoExtraOrigemTipo() == null) {
            throw new RuntimeException("O tipo de origem do dado extra é obrigatório");
        }

        if (dadoExtra.getDadoExtraOrigemId() == null) {
            throw new RuntimeException("O id de origem do dado extra é obrigatório");
        }

        if (dadoExtra.getDadoExtraLigado() == null) {
            throw new RuntimeException("O campo dadoExtraLigado é obrigatório");
        }

        if (dadoExtra.getDadoExtraOrdem() == null || dadoExtra.getDadoExtraOrdem() < 0) {
            throw new RuntimeException("A ordem do dado extra é obrigatória e deve ser zero ou maior");
        }

        validarAplicacaoPorContexto(dadoExtra);
    }

    private void validarAplicacaoPorContexto(DadoExtra dadoExtra) {
        DadoExtraContexto contexto = dadoExtra.getDadoExtraContexto();
        DadoExtraAplicacao aplicacao = dadoExtra.getDadoExtraAplicacao();

        if (contexto == DadoExtraContexto.FORA_COMBATE
                && (aplicacao == DadoExtraAplicacao.DANO_POR_TURNO
                        || aplicacao == DadoExtraAplicacao.CURA_POR_TURNO
                        || aplicacao == DadoExtraAplicacao.DANO
                        || aplicacao == DadoExtraAplicacao.CURA
                        || aplicacao == DadoExtraAplicacao.TESTE_DE_ATAQUE)) {
            throw new RuntimeException("FORA_COMBATE deve usar apenas TESTE_GERAL");
        }

        if (contexto == DadoExtraContexto.COMBATE
                && aplicacao == DadoExtraAplicacao.TESTE_GERAL) {
            throw new RuntimeException("COMBATE não deve usar TESTE_GERAL");
        }
    }
}