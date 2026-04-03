package com.arthur.calculadorarpg.login;

public class LoginRequestDTO {

    private String usuarioNome;
    private String usuarioSenha;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String usuarioNome, String usuarioSenha) {
        this.usuarioNome = usuarioNome;
        this.usuarioSenha = usuarioSenha;
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
}