package com.anker.dashboard.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Aplica a todas las rutas de nuestra API
        registry.addMapping("/api/**")
                // Permite solicitudes solo desde nuestro frontend de Angular
                .allowedOrigins("http://localhost:4200")
                // Permite explícitamente los métodos, incluyendo OPTIONS (vital para el Preflight)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}