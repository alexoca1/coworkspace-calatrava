package com.cowork.backend.controller;

import com.cowork.backend.dto.ReservaRequest;
import com.cowork.backend.dto.ReservaResponse;
import com.cowork.backend.model.Reserva;
import com.cowork.backend.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {
    private final ReservaService reservaService;
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<?> crearReserva(
            @RequestParam Long usuarioId,
            @Valid @RequestBody ReservaRequest request) {
        try {
            Reserva reserva = reservaService.crearReserva(usuarioId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ReservaResponse(reserva));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    // Listar las reservas de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReservaResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Reserva> lista = reservaService.listarPorUsuario(usuarioId);
        List<ReservaResponse> respuesta = lista.stream()
                .map(reserva -> new ReservaResponse(reserva))
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }
    // Listar todas las reservas (para panel de admin)
    @GetMapping
    public ResponseEntity<List<ReservaResponse>> listarTodas() {
        List<Reserva> lista = reservaService.listarTodas();
        List<ReservaResponse> respuesta = lista.stream()
                .map(reserva -> new ReservaResponse(reserva))
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }
    // Cancelar una reserva
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarReserva(
            @PathVariable Long id,
            @RequestParam Long usuarioId,
            @RequestParam(defaultValue = "false") boolean esAdmin) {
        try {
            Reserva reserva = reservaService.cancelarReserva(id, usuarioId, esAdmin);
            return ResponseEntity.ok(new ReservaResponse(reserva));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    // Validar check-in mediante código
    @PostMapping("/checkin/{codigo}")
    public ResponseEntity<?> registrarCheckin(@PathVariable String codigo) {
        try {
            Reserva reserva = reservaService.registrarCheckin(codigo);
            return ResponseEntity.ok(new ReservaResponse(reserva));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    // Consultar espacios ocupados en un rango de fechas
    @GetMapping("/ocupados")
    public ResponseEntity<List<Long>> obtenerEspaciosOcupados(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin) {
        try {
            List<Long> ocupados = reservaService.obtenerEspaciosOcupados(inicio, fin);
            return ResponseEntity.ok(ocupados);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}