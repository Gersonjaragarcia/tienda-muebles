package com.miempresa.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.tienda.model.Categoria;

/**
 * Repositorio para la gestión de Categorías en la base de datos.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Aquí podrías agregar búsquedas por nombre si lo deseas
}
