package com.cowork.backend.dto;

import com.cowork.backend.model.Reserva;
import com.cowork.backend.model.enums.EstadoReserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservaResponse(

        Long id,
        Long espacioId,
        String espacioNombre,
        Long usuarioId,
        String usuarioNombre,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        BigDecimal precioTotal,
        EstadoReserva estado,
        String codigoCheckin,
        LocalDateTime fechaCheckin,
        LocalDateTime creadoEn
) {
    public ReservaResponse(Reserva reserva) {
        this(

                reserva.getId(),
                reserva.getEspacio().getId(),
                reserva.getEspacio().getNombre(),
                reserva.getUsuario().getId(),
                reserva.getUsuario().getNombre(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                reserva.getPrecioTotal(),
                reserva.getEstado(),
                reserva.getCodigoCheckin(),
                reserva.getFechaCheckin(),
                reserva.getCreadoEn()
        );
    }
}
