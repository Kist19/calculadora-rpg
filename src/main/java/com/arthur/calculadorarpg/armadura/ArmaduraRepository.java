package com.arthur.calculadorarpg.armadura;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmaduraRepository extends JpaRepository<Armadura, Long> {

    List<Armadura> findByInventarioIsNull();

    List<Armadura> findByInventarioPersonagemId(Long personagemId);

    boolean existsByInventarioPersonagemIdAndArmaduraNomeIgnoreCase(Long personagemId, String armaduraNome);
}