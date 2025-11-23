package com.HuertoHogar.producto.repository;

import com.HuertoHogar.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    List<Producto> findByCategoria(String categoria);
    
    List<Producto> findByCantidadKgLessThan(Integer cantidad);

}