package com.miempresa.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/nuevo-pedido")
    public String mostrarFormulario() {
        return "Formulario"; 
    }
}
