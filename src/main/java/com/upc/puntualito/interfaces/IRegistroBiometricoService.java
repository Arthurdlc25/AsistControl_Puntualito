package com.upc.puntualito.interfaces;

import com.upc.puntualito.dto.RegistroBiometricoDTO;

public interface IRegistroBiometricoService {
    RegistroBiometricoDTO registrar(RegistroBiometricoDTO registroBiometricoDTO);
    RegistroBiometricoDTO actualizar(RegistroBiometricoDTO registroBiometricoDTO);
}
