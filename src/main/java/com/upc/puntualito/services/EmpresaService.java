package com.upc.puntualito.services;

import com.upc.puntualito.dto.EmpresaDTO;
import com.upc.puntualito.entities.Empresa;
import com.upc.puntualito.interfaces.IEmpresaService;
import com.upc.puntualito.repositories.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EmpresaService implements IEmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmpresaDTO registrar(EmpresaDTO empresaDTO) {
        if(empresaDTO.getId()==null){
            Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);

            empresa.setCreadoPor("sistema");
            empresa.setCreadoEn(Instant.now());

            empresa.setModificadoPor(null);
            empresa.setModificadoEn(null);

            empresaRepository.save(empresa);
            return modelMapper.map(empresa, EmpresaDTO.class);
        }
        return null;
    }

    @Override
    public EmpresaDTO actualizar(EmpresaDTO empresaDTO) {
        if(empresaDTO.getId()==null){
            Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);

            empresa.setModificadoPor("sistema");
            empresa.setModificadoEn(Instant.now());

            empresaRepository.save(empresa);
            return modelMapper.map(empresa, EmpresaDTO.class);
        }
        return null;
    }
}
