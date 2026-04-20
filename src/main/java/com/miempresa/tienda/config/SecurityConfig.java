package com.miempresa.tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF para permitir POST desde el formulario y Thunder Client
            .authorizeHttpRequests(auth -> auth
                // Permitimos el acceso libre a la ruta del formulario y a toda la API
                .requestMatchers("/nuevo-pedido", "/api/**").permitAll() 
                .anyRequest().permitAll() // Permite cualquier otra petición por ahora (para desarrollo)
            )
            .httpBasic(withDefaults()); // Mantiene activa la opción de autenticación básica si se requiere
            
        return http.build();
    }
}

