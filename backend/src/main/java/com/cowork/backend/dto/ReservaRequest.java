package com.cowork.backend.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ReservaRequest(
        @NotNull(message = "El id del espacio es obligatorio")
        Long espacioId,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDateTime fechaInicio,

        @NotNull(message = "La fecha de fin es obligatoria")
        LocalDateTime fechaFin
) {
}