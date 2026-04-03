package com.arthur.calculadorarpg.usuario;

import com.arthur.calculadorarpg.personagem.Personagem;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioNome;

    private String usuarioSenha;

    @Enumerated(EnumType.STRING)
    private UsuarioPerfil UsuarioPerfil;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    public Usuario() {
    }

    public Usuario(Long id, String usuarioNome, String usuarioSenha, UsuarioPerfil UsuarioPerfil, Personagem personagem) {
        this.id = id;
        this.usuarioNome = usuarioNome;
        this.usuarioSenha = usuarioSenha;
        this.UsuarioPerfil = UsuarioPerfil;
        this.personagem = personagem;
    }

    public Long getId() {
        return id;
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
        return UsuarioPerfil;
    }

    public void setUsuarioPerfil(UsuarioPerfil usuarioPerfil) {
        UsuarioPerfil = usuarioPerfil;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

}