package com.arthur.calculadorarpg.inventario;

public class InventarioBaseResponseDTO {
    private Long inventarioId;
    private Long personagemId;
    private String personagemNome;

    public InventarioBaseResponseDTO(Long inventarioId, Long personagemId, String personagemNome) {
        this.inventarioId = inventarioId;
        this.personagemId = personagemId;
        this.personagemNome = personagemNome;
    }

    public Long getInventarioId() {
        return inventarioId;
    }

    public Long getPersonagemId() {
        return personagemId;
    }

    public String getPersonagemNome() {
        return personagemNome;
    }
}
