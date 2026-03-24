package com.miempresa.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miempresa.tienda.model.Producto;
import com.miempresa.tienda.service.ProductoService;

/**
 * Controlador REST para el catálogo de muebles.
 * Expone los puntos de entrada (endpoints) para que el navegador consulte los datos.
 */
@RestController
@RequestMapping("/api/productos") // Ruta base de la API
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Obtiene la lista completa de muebles (HU-02: Navegar catálogo).
     * URL: http://localhost:8080/api/productos
     */
    @GetMapping
    public List<Producto> listarTodo() {
        return productoService.obtenerCatalogo();
    }

    /**
     * Permite registrar un nuevo mueble en el sistema.
     */
    @PostMapping
    public Producto agregar(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }
}
