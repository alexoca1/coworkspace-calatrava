package com.cowork.backend.controller;

import com.cowork.backend.dto.LoginRequest;
import com.cowork.backend.dto.RegistroRequest;
import com.cowork.backend.dto.UsuarioResponse;
import com.cowork.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // permite conectar con el frontend en vite (puerto 5173)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // endpoint de registro: POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegistroRequest request) {

        try {
            UsuarioResponse nuevoUsuario = authService.registrar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            // error si el email ya existe o el plan es invalido
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno en el servidor"));
        }
    }

    // endpoint de login: POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            UsuarioResponse usuario = authService.autenticar(request);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            // error si las credenciales no coinciden
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno en el servidor"));
        }
    }
}