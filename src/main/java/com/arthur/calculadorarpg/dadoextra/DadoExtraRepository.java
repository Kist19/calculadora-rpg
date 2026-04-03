package com.arthur.calculadorarpg.dadoextra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DadoExtraRepository extends JpaRepository<DadoExtra, Long> {

    List<DadoExtra> findByDadoExtraOrigemTipoAndDadoExtraOrigemIdOrderByDadoExtraOrdemAsc(
            DadoExtraOrigemTipo dadoExtraOrigemTipo,
            Long dadoExtraOrigemId);

    List<DadoExtra> findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueOrderByDadoExtraOrdemAsc(
            DadoExtraOrigemTipo dadoExtraOrigemTipo,
            Long dadoExtraOrigemId);

    List<DadoExtra> findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraContextoOrderByDadoExtraOrdemAsc(
            DadoExtraOrigemTipo dadoExtraOrigemTipo,
            Long dadoExtraOrigemId,
            DadoExtraContexto dadoExtraContexto);

    List<DadoExtra> findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoOrderByDadoExtraOrdemAsc(
            DadoExtraOrigemTipo dadoExtraOrigemTipo,
            Long dadoExtraOrigemId,
            DadoExtraContexto dadoExtraContexto);

    List<DadoExtra> findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
            DadoExtraOrigemTipo dadoExtraOrigemTipo,
            Long dadoExtraOrigemId,
            DadoExtraAplicacao dadoExtraAplicacao);

    List<DadoExtra> findByDadoExtraOrigemTipoAndDadoExtraOrigemIdAndDadoExtraLigadoTrueAndDadoExtraContextoAndDadoExtraAplicacaoOrderByDadoExtraOrdemAsc(
            DadoExtraOrigemTipo dadoExtraOrigemTipo,
            Long dadoExtraOrigemId,
            DadoExtraContexto dadoExtraContexto,
            DadoExtraAplicacao dadoExtraAplicacao);
}