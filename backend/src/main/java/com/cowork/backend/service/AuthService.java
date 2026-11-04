package com.cowork.backend.service;

import com.cowork.backend.dto.LoginRequest;
import com.cowork.backend.dto.RegistroRequest;
import com.cowork.backend.dto.UsuarioResponse;
import com.cowork.backend.model.Plan;
import com.cowork.backend.model.Usuario;
import com.cowork.backend.model.enums.Rol;
import com.cowork.backend.repository.PlanRepository;
import com.cowork.backend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PlanRepository planRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository,
                       PlanRepository planRepository,
                       PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.planRepository = planRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponse registrar(RegistroRequest request) {

        if (usuarioRepository.existsByEmail(request.email().trim().toLowerCase())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        String temp = "FREE";
        if (request.planCodigo() != null && !request.planCodigo().trim().isEmpty()) {
            temp = request.planCodigo().trim().toUpperCase();
        }

        final String codigoPlan = temp;
        Plan plan = planRepository.findByCodigo(codigoPlan)
                .orElseThrow(() -> new IllegalArgumentException("No existe el plan: " + codigoPlan));

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre().trim());
        usuario.setEmail(request.email().trim().toLowerCase());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRol(Rol.COWORKER);
        usuario.setPlan(plan);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return new UsuarioResponse(usuarioGuardado);
    }

    public UsuarioResponse autenticar(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.email().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(request.password(), usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }

        return new UsuarioResponse(usuario);
    }
}