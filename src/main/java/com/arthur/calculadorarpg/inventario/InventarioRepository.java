package com.arthur.calculadorarpg.inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByPersonagemId(Long personagemId);

    Optional<Inventario> findByPersonagemIdAndItemId(Long personagemId, Long itemId);
}
