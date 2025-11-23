package com.HuertoHogar.usuario.service;

import com.HuertoHogar.usuario.model.Usuario;
import com.HuertoHogar.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LOGIN PARA JWT 
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasenna())
                .roles(usuario.getRol().name())  // ADMIN o USER
                .build();
    }

    // REGISTRO 
    public Usuario registrar(Usuario usuario) {
        // rol lo asignamos como USER
        if (usuario.getRol() == null) {
            usuario.setRol(Usuario.Role.USER);
        }

        return usuarioRepository.save(usuario);
    }

    // VALIDAR LOGIN 
    public Usuario validarCredenciales(String correo, String contrasenna) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Correo no encontrado"));

        if (!contrasenna.equals(usuario.getContrasenna())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}
