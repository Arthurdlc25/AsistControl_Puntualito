package com.upc.puntualito.services;

import com.upc.puntualito.dto.HorarioTrabajoDTO;
import com.upc.puntualito.entities.HorarioTrabajo;
import com.upc.puntualito.interfaces.IHorarioTrabajoService;
import com.upc.puntualito.repositories.HorarioTrabajoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(horarioTrabajoDTO.getId() == null){
            HorarioTrabajo horarioTrabajo = modelMapper.map(horarioTrabajoDTO, HorarioTrabajo.class);
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

        HorarioTrabajo actualizado = horarioTrabajoRepository.save(horario);
        return modelMapper.map(actualizado, HorarioTrabajoDTO.class);
    }

    @Override
    public HorarioTrabajoDTO findByEmpleadoId(Long empleadoId) {
        HorarioTrabajo horarioTrabajo= horarioTrabajoRepository.findByEmpleadoId(empleadoId);
        return (horarioTrabajo != null) ? modelMapper.map(horarioTrabajo, HorarioTrabajoDTO.class) : null;
    }
}
