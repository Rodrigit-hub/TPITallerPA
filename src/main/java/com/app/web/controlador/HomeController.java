package com.app.web.controlador;

import com.app.web.entidad.Usuario;
import com.app.web.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UsuarioServicio usuarioServicio; // Inyectar el servicio correctamente

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioServicio.guardarUsuario(usuario); // Llama al servicio para guardar el usuario
        return "redirect:/login"; // Redirige al login después de un registro exitoso
    }
}
