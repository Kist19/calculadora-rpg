package com.arthur.calculadorarpg.loja;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaItemRepository extends JpaRepository<LojaItem, Long> {

    List<LojaItem> findByLojaId(Long lojaId);

    boolean existsByItemId(Long itemId);

    boolean existsByArmaId(Long armaId);

    boolean existsByArmaduraId(Long armaduraId);
}