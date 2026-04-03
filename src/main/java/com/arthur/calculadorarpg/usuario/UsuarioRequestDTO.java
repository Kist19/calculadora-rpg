package com.arthur.calculadorarpg.usuario;

public class UsuarioRequestDTO {

    private String usuarioNome;
    private String usuarioSenha;
    private UsuarioPerfil usuarioPerfil;

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String usuarioNome, String usuarioSenha, UsuarioPerfil usuarioPerfil) {
        this.usuarioNome = usuarioNome;
        this.usuarioSenha = usuarioSenha;
        this.usuarioPerfil = usuarioPerfil;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }

    public UsuarioPerfil getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }
}