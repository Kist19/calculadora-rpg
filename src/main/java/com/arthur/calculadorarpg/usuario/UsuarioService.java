package com.arthur.calculadorarpg.usuario;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonagemRepository personagemRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PersonagemRepository personagemRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personagemRepository = personagemRepository;
    }

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO request) {
        if (usuarioRepository.existsByUsuarioNome(request.getUsuarioNome())) {
            throw new RuntimeException("Já existe um usuário com esse nome.");
        }

        Usuario usuario = new Usuario();
        usuario.setUsuarioNome(request.getUsuarioNome());
        usuario.setUsuarioSenha(request.getUsuarioSenha());
        usuario.setUsuarioPerfil(request.getUsuarioPerfil());
        usuario.setPersonagem(null);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getUsuarioNome(),
                usuarioSalvo.getUsuarioPerfil(),
                null
        );
    }

    public UsuarioResponseDTO vincularPersonagem(Long usuarioId, Long personagemId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado."));

        usuario.setPersonagem(personagem);

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getUsuarioNome(),
                usuarioAtualizado.getUsuarioPerfil(),
                usuarioAtualizado.getPersonagem() != null ? usuarioAtualizado.getPersonagem().getId() : null
        );
    }
}