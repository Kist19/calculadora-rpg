package com.arthur.calculadorarpg.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Long> {

    List<Loja> findByAtivaTrue();

    Optional<Loja> findFirstByAtivaTrue();
}