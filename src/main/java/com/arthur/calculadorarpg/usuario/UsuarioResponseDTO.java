package com.arthur.calculadorarpg.usuario;

public class UsuarioResponseDTO {

    private Long id;
    private String usuarioNome;
    private UsuarioPerfil usuarioPerfil;
    private Long personagemId;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String usuarioNome, UsuarioPerfil usuarioPerfil, Long personagemId) {
        this.id = id;
        this.usuarioNome = usuarioNome;
        this.usuarioPerfil = usuarioPerfil;
        this.personagemId = personagemId;
    }

    public Long getId() {
        return id;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public UsuarioPerfil getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public Long getPersonagemId() {
        return personagemId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public void setPersonagemId(Long personagemId) {
        this.personagemId = personagemId;
    }
}