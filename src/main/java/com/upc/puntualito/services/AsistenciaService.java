package com.upc.puntualito.services;

import com.upc.puntualito.dto.AsistenciaDTO;
import com.upc.puntualito.entities.Asistencia;
import com.upc.puntualito.interfaces.IAsistenciaService;
import com.upc.puntualito.repositories.AsistenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AsistenciaService implements IAsistenciaService {
    @Autowired
    private AsistenciaRepository asistenciaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AsistenciaDTO registrarEntrada(AsistenciaDTO asistenciaDTO) {

        Long empleadoId = asistenciaDTO.getEmpleadoId();

        AsistenciaDTO ultima = findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(empleadoId);

        Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);

        asistencia.setHoraSalida(null);
        asistencia.setModificadoEn(null);
        asistencia.setModificadoPor(null);

        asistencia.setCreadoPor("sistema");
        asistencia.setCreadoEn(Instant.now());

        if (ultima == null) {
            asistenciaRepository.save(asistencia);
            return modelMapper.map(asistencia, AsistenciaDTO.class);
        }

        if (ultima.getHoraEntrada() != null && ultima.getHoraSalida() == null && asistenciaDTO.getHoraEntrada() != null) {
            throw new IllegalStateException("Ya tienes una entrada registrada a las " + ultima.getHoraEntrada());
        }

        if (ultima.getHoraSalida() != null && asistenciaDTO.getHoraSalida() != null) {
            throw new IllegalStateException("Debes marcar entrada primero");
        }

        if (asistenciaDTO.getId() == null) {
            asistenciaRepository.save(asistencia);
            return modelMapper.map(asistencia, AsistenciaDTO.class);
        }
        return null;
    }


    @Override
    public AsistenciaDTO registrarSalida(AsistenciaDTO asistenciaDTO) {

        Long empleadoId = asistenciaDTO.getEmpleadoId();

        AsistenciaDTO ultima = findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(empleadoId);

        if (ultima == null || ultima.getHoraSalida() != null) {
            throw new IllegalStateException("No tienes entrada activa para registrar salida");
        }

        Asistencia asistencia = modelMapper.map(asistenciaDTO, Asistencia.class);
        asistencia.setModificadoEn(Instant.now());
        asistencia.setModificadoPor("Sistema");


        if(asistenciaDTO.getId()==null){
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

    @Override
    public AsistenciaDTO findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(Long empleadoId) {
        Asistencia ultima = asistenciaRepository
                .findTopByEmpleadoIdOrderByFechaRegistroDescHoraEntradaDesc(empleadoId);

        return (ultima != null) ? modelMapper.map(ultima, AsistenciaDTO.class) : null;
    }


}
