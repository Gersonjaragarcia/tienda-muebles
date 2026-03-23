package com.miempresa.tienda.model;

import jakarta.persistence.*;

/**
 * Clase Entidad: Producto
 * Representa los muebles y artesanías del catálogo.
 * Basado en el Diagrama de Clases y el Modelo de Base de Datos del proyecto.
 */
@Entity // Le dice a JPA que esta clase es una tabla de base de datos
@Table(name = "productos") // Define el nombre real de la tabla en PostgreSQL
public class Producto {

    @Id // Define la llave primaria (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento (1, 2, 3...)
    private Long id;

    @Column(nullable = false, length = 100) // El nombre no puede ser nulo
    private String nombre;

    @Column(columnDefinition = "TEXT") // Permite descripciones largas
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    private Boolean disponible;

        // Relación: Muchos productos pertenecen a una categoría
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    // No olvides agregar su Getter y Setter
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }


    // --- CONSTRUCTORES ---

    // Constructor vacío (Obligatorio para que JPA funcione)
    public Producto() {}

    // Constructor con parámetros (Útil para crear productos rápidamente)
    public Producto(String nombre, Double precio, Integer stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.disponible = (stock > 0);
    }

    // --- LÓGICA DE NEGOCIO (HU-04: Gestión de Inventario) ---

    /**
     * Método para reducir el stock tras una venta.
     * Si el stock llega a cero, el producto deja de estar disponible.
     */
    public void reducirStock(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
            this.disponible = (this.stock > 0);
        }
    }

    // --- GETTERS Y SETTERS (Estándar de Java) ---
    // Permiten leer y modificar los datos de forma segura (Encapsulamiento)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }
}