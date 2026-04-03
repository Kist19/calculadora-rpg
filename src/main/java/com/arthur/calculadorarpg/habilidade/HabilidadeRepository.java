package com.arthur.calculadorarpg.habilidade;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {

    Optional<Habilidade> findByHabilidadeNome(String habilidadeNome);

}
