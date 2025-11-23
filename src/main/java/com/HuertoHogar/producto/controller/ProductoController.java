package com.HuertoHogar.producto.controller;

import com.HuertoHogar.producto.model.Producto;
import com.HuertoHogar.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }
    
    @GetMapping("/categoria/{categoria}")
    public List<Producto> obtenerPorCategoria(@PathVariable String categoria) {
        return productoService.obtenerPorCategoria(categoria);
    }
    
    @GetMapping("/criticos")
    public List<Producto> obtenerCriticos() {
        return productoService.obtenerCriticos();
    }
    
    
    // Solo ADMIN - CRUD
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.crear(producto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Producto> actualizar(
        @PathVariable Long id, 
        @RequestBody Producto producto
    ) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }
}