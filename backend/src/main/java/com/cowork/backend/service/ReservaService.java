package com.cowork.backend.service;

import com.cowork.backend.dto.ReservaRequest;
import com.cowork.backend.model.Espacio;
import com.cowork.backend.model.Plan;
import com.cowork.backend.model.Reserva;
import com.cowork.backend.model.Usuario;
import com.cowork.backend.model.enums.EstadoReserva;
import com.cowork.backend.repository.EspacioRepository;
import com.cowork.backend.repository.ReservaRepository;
import com.cowork.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final EspacioRepository espacioRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          EspacioRepository espacioRepository,
                          UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.espacioRepository = espacioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Reserva crearReserva(Long usuarioId, ReservaRequest request) {
        LocalDateTime ahora = LocalDateTime.now();

        if (request.fechaInicio().isBefore(ahora)) {
            throw new IllegalArgumentException("No se pueden realizar reservas en fechas pasadas");
        }

        if (!request.fechaFin().isAfter(request.fechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de inicio");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Espacio espacio = espacioRepository.findById(request.espacioId())
                .orElseThrow(() -> new IllegalArgumentException("Espacio no encontrado"));

        if (!espacio.getActivo()) {
            throw new IllegalStateException("El espacio no está disponible");
        }

        // verificar si ya existe una reserva en ese horario
        boolean solapado = reservaRepository.existsSolapamiento(
                espacio.getId(),
                request.fechaInicio(),
                request.fechaFin(),
                EstadoReserva.CANCELADA
        );
        if (solapado) {
            throw new IllegalStateException("El espacio ya está reservado en ese horario");
        }

        BigDecimal precioTotal = calcularPrecio(espacio, usuario.getPlan(), request.fechaInicio(), request.fechaFin());

        // generar codigo unico para el check-in (ej: CW-8F2A9B1C)
        String tokenCheckin = "CW-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Reserva reserva = new Reserva(
                usuario,
                espacio,
                request.fechaInicio(),
                request.fechaFin(),
                precioTotal,
                EstadoReserva.CONFIRMADA,
                tokenCheckin
        );

        return reservaRepository.save(reserva);
    }

    @Transactional
    public Reserva cancelarReserva(Long reservaId, Long usuarioId, boolean esAdmin) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        // solo el dueño o un admin pueden cancelar
        if (!esAdmin && !reserva.getUsuario().getId().equals(usuarioId)) {
            throw new SecurityException("No tienes permiso para cancelar esta reserva");
        }

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva ya está cancelada");
        }

        if (reserva.getEstado() == EstadoReserva.CHECKED_IN) {
            throw new IllegalStateException("No se puede cancelar una reserva ya utilizada");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public Reserva registrarCheckin(String codigoCheckin) {
        Reserva reserva = reservaRepository.findByCodigoCheckin(codigoCheckin.trim())
                .orElseThrow(() -> new IllegalArgumentException("Código de check-in no válido"));

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva está cancelada");
        }

        if (reserva.getEstado() == EstadoReserva.CHECKED_IN) {
            throw new IllegalStateException("El check-in ya fue realizado");
        }

        reserva.setEstado(EstadoReserva.CHECKED_IN);
        reserva.setFechaCheckin(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    public List<Long> obtenerEspaciosOcupados(LocalDateTime inicio, LocalDateTime fin) {
        if (!fin.isAfter(inicio)) {
            throw new IllegalArgumentException("Rango de fechas inválido");
        }
        return reservaRepository.findEspacioIdsOcupadosEnRango(inicio, fin, EstadoReserva.CANCELADA);
    }

    public List<Reserva> listarPorUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioIdOrderByFechaInicioDesc(usuarioId);
    }

    public List<Reserva> listarTodas() {
        return reservaRepository.findAllByOrderByFechaInicioDesc();
    }

    private BigDecimal calcularPrecio(Espacio espacio, Plan plan, LocalDateTime inicio, LocalDateTime fin) {
        long minutos = Duration.between(inicio, fin).toMinutes();
        double horas = minutos / 60.0;
        double total = espacio.getPrecioHora().doubleValue() * horas;

        if (plan != null && plan.getDescuentoPorcentaje() != null) {
            double descuento = plan.getDescuentoPorcentaje().doubleValue();
            total = total - (total * (descuento / 100.0));
        }

        return BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP);
    }
}