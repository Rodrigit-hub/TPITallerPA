package com.app.web.controlador;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
    	// Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificar si el usuario ya está autenticado
        if (authentication != null && authentication.isAuthenticated() && 
            !authentication.getPrincipal().equals("anonymousUser")) {
            // Redirigir a la página de inicio si está logueado
            return "redirect:/index";
        }
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
}

