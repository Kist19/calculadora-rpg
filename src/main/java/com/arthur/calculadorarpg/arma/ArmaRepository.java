package com.arthur.calculadorarpg.arma;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmaRepository extends JpaRepository<Arma, Long> {
    List<Arma> findByPersonagemId(Long personagemId);
}
