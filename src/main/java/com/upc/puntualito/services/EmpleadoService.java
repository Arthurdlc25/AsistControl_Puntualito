package com.upc.puntualito.services;

import com.upc.puntualito.dto.EmpleadoDTO;
import com.upc.puntualito.entities.Empleado;
import com.upc.puntualito.interfaces.IEmpleadoService;
import com.upc.puntualito.repositories.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EmpleadoDTO registrar(EmpleadoDTO empleadoDTO) {
        if(empleadoDTO.getId()==null){
            Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
            empleadoRepository.save(empleado);
            return modelMapper.map(empleado, EmpleadoDTO.class);
        }
        return null;
    }
}
