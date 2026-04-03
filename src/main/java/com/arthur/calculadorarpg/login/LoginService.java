package com.arthur.calculadorarpg.login;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.usuario.Usuario;
import com.arthur.calculadorarpg.usuario.UsuarioRepository;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponseDTO autenticar(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository
                .findByUsuarioNomeAndUsuarioSenha(
                        request.getUsuarioNome(),
                        request.getUsuarioSenha())
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos."));

        Long personagemId = null;

        if (usuario.getPersonagem() != null) {
            personagemId = usuario.getPersonagem().getId();
        }

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getUsuarioPerfil(),
                personagemId
        );
    }
}