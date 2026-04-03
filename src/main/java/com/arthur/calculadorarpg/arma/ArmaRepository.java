package com.arthur.calculadorarpg.arma;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmaRepository extends JpaRepository<Arma, Long> {

    List<Arma> findByInventarioIsNull();

    List<Arma> findByInventarioPersonagemId(Long personagemId);

    boolean existsByInventarioPersonagemIdAndArmaNomeIgnoreCase(Long personagemId, String armaNome);
}