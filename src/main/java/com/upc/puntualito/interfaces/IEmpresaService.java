package com.upc.puntualito.interfaces;

import com.upc.puntualito.dto.EmpresaDTO;

public interface IEmpresaService {
    EmpresaDTO registrar(EmpresaDTO empresaDTO);
    EmpresaDTO actualizar(EmpresaDTO empresaDTO);
}
