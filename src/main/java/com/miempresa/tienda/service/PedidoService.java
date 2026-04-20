package com.miempresa.tienda.service;

import com.miempresa.tienda.model.*;
import com.miempresa.tienda.repository.PedidoRepository;
import com.miempresa.tienda.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Pedido guardarPedido(Pedido pedido) {

        double total = 0.0;

        // Recorremos cada producto del pedido
        for (DetallePedido detalle : pedido.getDetalles()) {

            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // 🔴 VALIDACIÓN DE STOCK
            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
            }

            // 💰 CALCULAR PRECIO
            detalle.setPrecio(producto.getPrecio());

            // 🔗 RELACIÓN
            detalle.setPedido(pedido);
            detalle.setProducto(producto);

            // ➖ DESCONTAR STOCK
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            // ➕ SUMAR AL TOTAL
            total += detalle.getCantidad() * producto.getPrecio();
        }

        // Guardar total del pedido
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }
}