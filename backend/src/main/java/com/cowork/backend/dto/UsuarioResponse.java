package com.cowork.backend.dto;

import com.cowork.backend.model.Usuario;
import com.cowork.backend.model.enums.Rol;

public record UsuarioResponse(
        Long id,
        String nombre,
        String email,
        Rol rol,
        String planCodigo
) {
    // constructor de conveniencia a partir de la entidad Usuario
    public UsuarioResponse(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                (usuario.getPlan() != null) ? usuario.getPlan().getCodigo() : "FREE"
        );
    }
}