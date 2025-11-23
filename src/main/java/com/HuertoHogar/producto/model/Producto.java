package com.HuertoHogar.producto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String nombre;
    
    @Size(max = 500)
    @Column(length = 500)
    private String descripcion;
    
    @NotNull
    @Column(nullable = false)
    private Double precio;
    
    @NotNull
    @Column(nullable = false)
    private Integer cantidadKg; 
    
    @NotBlank
    @Column(nullable = false)
    private String categoria;
    
    @Column(name = "imagen_url")
    private String imagenUrl;
}
