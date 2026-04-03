package com.arthur.calculadorarpg.efeitoativo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EfeitoAtivoRepository extends JpaRepository<EfeitoAtivo, Long> {

        List<EfeitoAtivo> findByPersonagemId(Long personagemId);

        List<EfeitoAtivo> findByPersonagemIdAndEfeitoAtivoLigadoTrue(Long personagemId);

        List<EfeitoAtivo> findByPersonagemIdAndEfeitoAtivoLigadoTrueAndEfeito_EfeitoTipoDuracao(
                        Long personagemId,
                        EfeitoAtivoTipoDuracao efeitoTipoDuracao);

        Optional<EfeitoAtivo> findByPersonagemIdAndEfeitoId(Long personagemId, Long efeitoId);
}