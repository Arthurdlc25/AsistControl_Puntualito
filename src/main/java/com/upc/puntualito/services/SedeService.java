package com.upc.puntualito.services;

import com.upc.puntualito.dto.SedeDTO;
import com.upc.puntualito.entities.Sede;
import com.upc.puntualito.interfaces.ISedeService;
import com.upc.puntualito.repositories.SedeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedeService implements ISedeService {
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SedeDTO registrar(SedeDTO sedeDTO) {
        if(sedeDTO.getId()==null){
            Sede sede = modelMapper.map(sedeDTO, Sede.class);
            sedeRepository.save(sede);
            return modelMapper.map(sede, SedeDTO.class);
        }
        return null;
    }
}
