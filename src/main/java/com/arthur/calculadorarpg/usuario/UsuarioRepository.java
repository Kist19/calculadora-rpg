package com.arthur.calculadorarpg.usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuarioNome(String usuarioNome);

    Optional<Usuario> findByUsuarioNomeAndUsuarioSenha(String usuarioNome, String usuarioSenha);

    boolean existsByUsuarioNome(String usuarioNome);
}