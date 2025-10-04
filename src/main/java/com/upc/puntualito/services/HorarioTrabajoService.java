package com.upc.puntualito.services;

import com.upc.puntualito.dto.HorarioTrabajoDTO;
import com.upc.puntualito.entities.HorarioTrabajo;
import com.upc.puntualito.interfaces.IHorarioTrabajoService;
import com.upc.puntualito.repositories.HorarioTrabajoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class HorarioTrabajoService implements IHorarioTrabajoService {
    @Autowired
    private HorarioTrabajoRepository horarioTrabajoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public HorarioTrabajoDTO registrar(HorarioTrabajoDTO horarioTrabajoDTO) {

        HorarioTrabajo horario = modelMapper.map(findByEmpleadoId(horarioTrabajoDTO.getEmpleadoId()),HorarioTrabajo.class);

        if (horario!=null){
            throw new IllegalStateException("El empleado ya tiene un horario registrado");
        }

        if (horarioTrabajoDTO.getHoraSalida().isBefore(horarioTrabajoDTO.getHoraEntrada())) {
            throw new IllegalStateException("La hora de salida debe ser posterior a la entrada");
        }

        if(horarioTrabajoDTO.getId() == null){
            HorarioTrabajo horarioTrabajo = modelMapper.map(horarioTrabajoDTO, HorarioTrabajo.class);

            horarioTrabajo.setCreadoEn(Instant.now());
            horarioTrabajo.setCreadoPor("Sistema");

            horarioTrabajo.setModificadoPor(null);
            horarioTrabajo.setModificadoEn(null);

            horarioTrabajoRepository.save(horarioTrabajo);
            return modelMapper.map(horarioTrabajo, HorarioTrabajoDTO.class);
        }
        return null;
    }

    @Override
    public HorarioTrabajoDTO actualizarHorario(HorarioTrabajoDTO horarioTrabajoDTO) {
        HorarioTrabajo horario = modelMapper.map(findByEmpleadoId(horarioTrabajoDTO.getEmpleadoId()),HorarioTrabajo.class);

        if(horario==null){
            throw new IllegalStateException("No tienes entrada activa para registrar salida");
        }

        if (horarioTrabajoDTO.getHoraSalida().isBefore(horarioTrabajoDTO.getHoraEntrada())) {
            throw new IllegalStateException("La hora de salida debe ser posterior a la entrada");
        }

        horario.setModificadoEn(Instant.now());
        horario.setModificadoPor("Sistema");
        HorarioTrabajo actualizado = horarioTrabajoRepository.save(horario);
        return modelMapper.map(actualizado, HorarioTrabajoDTO.class);
    }

    @Override
    public HorarioTrabajoDTO findByEmpleadoId(Long empleadoId) {
        HorarioTrabajo horarioTrabajo= horarioTrabajoRepository.findByEmpleadoId(empleadoId);
        return (horarioTrabajo != null) ? modelMapper.map(horarioTrabajo, HorarioTrabajoDTO.class) : null;
    }
}
