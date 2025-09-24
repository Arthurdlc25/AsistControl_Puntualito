package com.upc.puntualito.services;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.entities.Asistencia;
import com.upc.puntualito.interfaces.IAsistenciaService;
import com.upc.puntualito.repositories.AsistenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenciaService implements IAsistenciaService {
    @Autowired
    private AsistenciaRepository asistenciaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AsistenciaDTO registrar(AsistenciaDTO asistenciaDTO) {
        if(asistenciaDTO.getId()==null){
            Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);
            asistenciaRepository.save(asistencia);
            return  modelMapper.map(asistencia, AsistenciaDTO.class);
        }
        return null;
    }

    @Override
    public AsistenciaDTO actualizar(AsistenciaDTO asistenciaDTO) {
        if(asistenciaDTO.getId()==null){
            Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);
            asistenciaRepository.save(asistencia);
            return  modelMapper.map(asistencia, AsistenciaDTO.class);
        }
        return null;
    }

    @Override
    public List<AsistenciaDTO> listarAsistencias() {
        List<Asistencia> asistencias = asistenciaRepository.findAll();
        return asistencias.stream()
                .map(asistencia -> modelMapper.map(asistencia,AsistenciaDTO.class))
                .toList();
    }
}
