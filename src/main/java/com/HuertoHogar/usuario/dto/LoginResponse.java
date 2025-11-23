package com.HuertoHogar.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String rol;
    private Long id;
    private String nombres;
    private String correo;
}
