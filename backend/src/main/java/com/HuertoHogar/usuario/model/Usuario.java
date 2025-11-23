package com.HuertoHogar.usuario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 50)
    private String nombres;
    
    @NotBlank
    @Pattern(regexp = "^[0-9]{7,8}-?[0-9Kk]{1}$")
    @Column(unique = true)
    private String rut;
    
    @NotBlank
    @Size(max = 100)
    private String apellidos;
    
    @NotBlank
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String correo;
    
    @NotBlank
    private String contrasenna;
    
    @NotBlank
    @Size(max = 300)
    private String direccion;
    
    @NotBlank
    private String region;
    
    @NotBlank
    private String comuna;
    
    @Enumerated(EnumType.STRING)
    private Role rol;
    
    public enum Role {
        ADMIN, USER
    }
}
