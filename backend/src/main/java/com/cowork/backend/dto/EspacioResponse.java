package com.cowork.backend.dto;

import com.cowork.backend.model.Espacio;
import com.cowork.backend.model.enums.TipoEspacio;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public record EspacioResponse(
        Long id,
        String nombre,
        TipoEspacio tipo,
        Integer capacidad,
        BigDecimal precioHora,
        BigDecimal precioDia,
        BigDecimal precioMes,
        Integer posX,
        Integer posY,
        String imagenUrl,
        Boolean activo,
        List<String> equipamientos
) {
    public EspacioResponse(Espacio espacio) {
        this(
                espacio.getId(),
                espacio.getNombre(),
                espacio.getTipo(),
                espacio.getCapacidad(),
                espacio.getPrecioHora(),
                espacio.getPrecioDia(),
                espacio.getPrecioMes(),
                espacio.getPosX(),
                espacio.getPosY(),
                espacio.getImagenUrl(),
                espacio.getActivo(),
                espacio.getEquipamientos().stream()
                        .map(ee -> ee.getEquipamiento().getNombre())
                        .collect(Collectors.toList())
        );
    }
}