package com.HuertoHogar.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HuertoHogar.usuario.model.Usuario;
import com.HuertoHogar.usuario.repository.UsuarioRepository;
import com.HuertoHogar.usuario.service.UsuarioService;

import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    //     LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    //     BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Long id) {

        return usuarioRepository.findById(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body("Usuario no encontrado"));
    }



    //     CREAR USUARIO
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {

        // Validaciones de unicidad (correo y rut)
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }

        if (usuarioRepository.existsByRut(usuario.getRut())) {
            return ResponseEntity.badRequest().body("El RUT ya está registrado");
        }

        Usuario nuevo = usuarioService.registrar(usuario);
        return ResponseEntity.ok(nuevo);
    }

    //     ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody Usuario usuarioActualizado
    ) {
        return usuarioRepository.findById(id).map(usuario -> {

            // Validar correo único si se cambia
            if (!usuario.getCorreo().equals(usuarioActualizado.getCorreo())
                    && usuarioRepository.existsByCorreo(usuarioActualizado.getCorreo())) {
                return ResponseEntity.badRequest().body("El correo ya está registrado");
            }

            // Validar rut único si se cambia
            if (!usuario.getRut().equals(usuarioActualizado.getRut())
                    && usuarioRepository.existsByRut(usuarioActualizado.getRut())) {
                return ResponseEntity.badRequest().body("El RUT ya está registrado");
            }

            // Actualizar valores
            usuario.setNombres(usuarioActualizado.getNombres());
            usuario.setApellidos(usuarioActualizado.getApellidos());
            usuario.setRut(usuarioActualizado.getRut());
            usuario.setCorreo(usuarioActualizado.getCorreo());
            usuario.setContrasenna(usuarioActualizado.getContrasenna());
            usuario.setDireccion(usuarioActualizado.getDireccion());
            usuario.setRegion(usuarioActualizado.getRegion());
            usuario.setComuna(usuarioActualizado.getComuna());
            usuario.setRol(usuarioActualizado.getRol());

            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);

        }).orElseGet(() -> ResponseEntity.badRequest().body("Usuario no encontrado"));
    }

    //     ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {

        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return ResponseEntity.ok("Usuario eliminado correctamente");
                })
                .orElseGet(() -> ResponseEntity.badRequest().body("Usuario no encontrado"));
    }

}
