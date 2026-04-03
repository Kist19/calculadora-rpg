package com.arthur.calculadorarpg.login;

import com.arthur.calculadorarpg.usuario.UsuarioPerfil;

public class LoginResponseDTO {

    private Long usuarioId;
    private UsuarioPerfil usuarioPerfil;
    private Long personagemId;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Long usuarioId, UsuarioPerfil usuarioPerfil, Long personagemId) {
        this.usuarioId = usuarioId;
        this.usuarioPerfil = usuarioPerfil;
        this.personagemId = personagemId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioPerfil getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public Long getPersonagemId() {
        return personagemId;
    }

    public void setPersonagemId(Long personagemId) {
        this.personagemId = personagemId;
    }
}