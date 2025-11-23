package com.HuertoHogar.usuario.controller;





import com.HuertoHogar.usuario.dto.LoginRequest;
import com.HuertoHogar.usuario.dto.LoginResponse;
import com.HuertoHogar.usuario.model.Usuario;
import com.HuertoHogar.usuario.service.JwtService;
import com.HuertoHogar.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Usuario usuario) {
        try {
            usuario.setRol(Usuario.Role.USER); 
            Usuario nuevoUsuario = usuarioService.registrar(usuario);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/crear-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crearAdmin(@RequestBody Usuario usuario) {
        try {
            usuario.setRol(Usuario.Role.ADMIN);
            Usuario nuevoUsuario = usuarioService.registrar(usuario);
            return ResponseEntity.ok("Administrador creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Usuario usuario = usuarioService.validarCredenciales(
                request.getCorreo(), 
                request.getContrasenna()
            );
            
            String token = jwtService.generateToken(
                usuario.getCorreo(), 
                usuario.getRol().toString()
            );
            
            LoginResponse response = new LoginResponse(
                token,
                usuario.getRol().toString(),
                usuario.getId(),
                usuario.getNombres(),
                usuario.getCorreo()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
