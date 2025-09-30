package com.upc.puntualito.services;

import com.upc.puntualito.dto.EmpleadoDTO;
import com.upc.puntualito.entities.Empleado;
import com.upc.puntualito.entities.Empresa;
import com.upc.puntualito.interfaces.IEmpleadoService;
import com.upc.puntualito.repositories.EmpleadoRepository;
import com.upc.puntualito.repositories.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EmpleadoDTO registrar(EmpleadoDTO empleadoDTO) {


        //valida DNI unico
        if(empleadoRepository.existsByDni(empleadoDTO.getDni())) {
            throw new IllegalArgumentException("El DNI ya está registrado");
        }

        //validacion de limite de empleados x empresa
        Empresa empresa = empresaRepository.findById(empleadoDTO.getEmpresaId()).orElseThrow(()-> new IllegalArgumentException("Empresa no encontrada"));
        long totalEmpleados = empleadoRepository.countByEmpresaId(empresa.getId());
        if (totalEmpleados >= empresa.getMaximoEmpleado()) {
            throw new IllegalStateException("La empresa ya alcanzó el límite de empleados permitidos");
        }


        //guardar Empleado
        if(empleadoDTO.getId()==null){

            Empleado empleado = modelMapper.map(empleadoDTO, Empleado.class);
            empleadoRepository.save(empleado);
            return modelMapper.map(empleado, EmpleadoDTO.class);
        }
        return null;
    }
}
