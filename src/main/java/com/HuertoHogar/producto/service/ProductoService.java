package com.HuertoHogar.producto.service;

import com.HuertoHogar.producto.model.Producto;
import com.HuertoHogar.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    
    // Obtener producto por ID
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
    
    // Crear nuevo producto
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }
    
    // Actualizar producto existente
    public Producto actualizar(Long id, Producto producto) {
        Producto existente = obtenerPorId(id);
        existente.setNombre(producto.getNombre());
        existente.setCategoria(producto.getCategoria());
        existente.setPrecio(producto.getPrecio());
        existente.setCantidadKg(producto.getCantidadKg());
        existente.setDescripcion(producto.getDescripcion());
        existente.setImagenUrl(producto.getImagenUrl());
        return productoRepository.save(existente);
    }
    
    // Eliminar producto
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
    
    // Obtener productos con stock crítico (menos de 20 kg)
    public List<Producto> obtenerCriticos() {
        return productoRepository.findByCantidadKgLessThan(20);
    }
    
    
    // Obtener productos por categoría
    public List<Producto> obtenerPorCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }
}