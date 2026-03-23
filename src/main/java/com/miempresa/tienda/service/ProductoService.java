package com.miempresa.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.tienda.model.Producto;
import com.miempresa.tienda.repository.ProductoRepository;

/**
 * Capa de Servicio: Gestiona la lógica de negocio para los productos.
 * Implementa las reglas definidas en las Historias de Usuario (HU-02 y HU-04).
 */
@Service
public class ProductoService {

    @Autowired // Inyección de dependencia para usar el repositorio
    private ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos del catálogo (HU-02).
     */
    public List<Producto> obtenerCatalogo() {
        return productoRepository.findAll();
    }

    /**
     * Guarda o actualiza un producto en el sistema.
     */
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Lógica para procesar una venta y reducir stock (HU-04).
     * @param id ID del producto
     * @param cantidad Cantidad a vender
     */
    public boolean procesarVenta(Long id, int cantidad) {
        return productoRepository.findById(id).map(producto -> {
            if (producto.getStock() >= cantidad) {
                producto.reducirStock(cantidad); // Usamos el método que creamos en la Entidad
                productoRepository.save(producto);
                return true;
            }
            return false;
        }).orElse(false);
    }
}
