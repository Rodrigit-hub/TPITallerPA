package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.web.entidad.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username); // Para encontrar un usuario por nombre
}
