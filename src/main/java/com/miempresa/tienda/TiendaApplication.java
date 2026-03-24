package com.miempresa.tienda;

import org.springframework.boot.SpringApplication; // Importante
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TiendaApplication {

    public static void main(String[] args) {
        // 1. Carga el archivo .env de la raíz
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        
        // 2. Inyecta la contraseña en las propiedades del sistema
        if (dotenv.get("DB_PASSWORD") != null) {
            System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        }

        SpringApplication.run(TiendaApplication.class, args);
    }
}

