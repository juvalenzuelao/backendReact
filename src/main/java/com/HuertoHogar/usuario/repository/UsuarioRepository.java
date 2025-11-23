package com.HuertoHogar.usuario.repository;

import com.HuertoHogar.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByCorreo(String correo);
    
    Boolean existsByCorreo(String correo);
    
    Boolean existsByRut(String rut);
}