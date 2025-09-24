package com.upc.puntualito.services;

import com.upc.puntualito.dto.RegistroBiometricoDTO;
import com.upc.puntualito.entities.RegistroBiometrico;
import com.upc.puntualito.interfaces.IRegistroBiometricoService;
import com.upc.puntualito.repositories.RegistroBiometricoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroBiometricoService implements IRegistroBiometricoService {
    @Autowired
    private RegistroBiometricoRepository registroBiometricoRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RegistroBiometricoDTO registrar(RegistroBiometricoDTO registroBiometricoDTO) {
        if(registroBiometricoDTO.getId()==null){
            RegistroBiometrico registroBiometrico = modelMapper.map(registroBiometricoDTO, RegistroBiometrico.class);
            registroBiometricoRepository.save(registroBiometrico);
            return modelMapper.map(registroBiometrico, RegistroBiometricoDTO.class);
        }
        return null;
    }
}
