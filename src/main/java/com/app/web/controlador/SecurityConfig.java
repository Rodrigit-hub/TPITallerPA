package com.app.web.controlador;

import com.app.web.entidad.Usuario;
import com.app.web.repository.UsuarioRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioRepositorio usuarioRepositorio;

    public SecurityConfig(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register").permitAll() // Rutas públicas
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            .formLogin(form -> form
                .loginPage("/login") // Ruta para el formulario de login
                .defaultSuccessUrl("/index", true) // Ruta después de un login exitoso
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
            )
            .exceptionHandling()
            .accessDeniedPage("/403"); // Página de error de acceso denegado

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioRepositorio.findByUsername(username);
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(usuario.getUsername())
                    .password(usuario.getPassword()) // Contraseña ya codificada
                    .roles(usuario.getRole())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
