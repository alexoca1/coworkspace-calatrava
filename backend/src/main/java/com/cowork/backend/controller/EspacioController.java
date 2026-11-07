package com.cowork.backend.controller;

import com.cowork.backend.dto.EspacioResponse;
import com.cowork.backend.model.Espacio;
import com.cowork.backend.model.enums.TipoEspacio;
import com.cowork.backend.repository.EspacioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/espacios")
@CrossOrigin(origins = "*")
public class EspacioController {

    private final EspacioRepository espacioRepository;

    public EspacioController(EspacioRepository espacioRepository) {

        this.espacioRepository = espacioRepository;
    }

    // Listar todos los espacios activos (con filtro opcional por tipo)
    @GetMapping
    public ResponseEntity<List<EspacioResponse>> listarEspacios(@RequestParam(required = false) TipoEspacio tipo) {
        List<Espacio> listaEspacios;
        if (tipo != null) {
            listaEspacios = espacioRepository.findByActivoTrueAndTipo(tipo);
        } else {
            listaEspacios = espacioRepository.findByActivoTrue();
        }
        List<EspacioResponse> respuesta = listaEspacios.stream()
                .map(espacio -> new EspacioResponse(espacio))
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    // Ver el detalle de un espacio por su id
    @GetMapping("/{id}")
    public ResponseEntity<EspacioResponse> obtenerEspacio(@PathVariable Long id) {
        return espacioRepository.findById(id)
                .map(espacio -> ResponseEntity.ok(new EspacioResponse(espacio)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}