package com.miempresa.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.tienda.model.Producto;

/**
 * Repositorio para la gestión de Productos (Muebles y Artesanías).
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Método personalizado para filtrar productos por su categoría (útil para el catálogo)
    List<Producto> findByCategoriaId(Long categoriaId);
}
