package com.upc.puntualito.interfaces;

import com.upc.puntualito.dto.AsistenciaDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAsistenciaService {
    AsistenciaDTO registrar(AsistenciaDTO asistenciaDTO);
    AsistenciaDTO actualizar(AsistenciaDTO asistenciaDTO);
    List<AsistenciaDTO> listarAsistencias();
}
