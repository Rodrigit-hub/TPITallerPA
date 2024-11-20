package com.app.web.services;

import com.app.web.entidad.Usuario;
import com.app.web.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Codificación de la contraseña
        usuario.setRole("USER"); // Rol por defecto
        return usuarioRepositorio.save(usuario);
    }
}
