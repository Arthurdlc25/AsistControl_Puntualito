package com.upc.puntualito.services;

import com.upc.puntualito.dto.SedeDTO;
import com.upc.puntualito.entities.Sede;
import com.upc.puntualito.interfaces.ISedeService;
import com.upc.puntualito.repositories.SedeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SedeService implements ISedeService {
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SedeDTO registrar(SedeDTO sedeDTO) {
        if(sedeDTO.getId()==null){
            validarRadio(sedeDTO.getRadioGeocerca());

            Sede sede = modelMapper.map(sedeDTO, Sede.class);
            sedeRepository.save(sede);
            return modelMapper.map(sede, SedeDTO.class);
        }
        return null;
    }

    @Override
    public SedeDTO actualizarUbicacion(Long sedeId, BigDecimal latitud, BigDecimal longitud, BigDecimal radioGeocerca) {
        validarRadio(radioGeocerca);

        Sede sede = sedeRepository.findById(sedeId)
                .orElseThrow(() -> new RuntimeException("La sede no existe"));

        sede.setLatitud(latitud);
        sede.setLongitud(longitud);
        sede.setRadioGeocerca(radioGeocerca);

        sedeRepository.save(sede);

        return modelMapper.map(sede, SedeDTO.class);
    }

    private void validarRadio(BigDecimal radio) {
        if (radio.compareTo(new BigDecimal("20")) < 0 || radio.compareTo(new BigDecimal("200")) > 0) {
            throw new IllegalArgumentException("El radio debe estar entre 20 y 200 metros");
        }
    }

}
