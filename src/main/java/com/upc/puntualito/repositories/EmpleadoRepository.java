package com.upc.puntualito.repositories;

import com.upc.puntualito.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByDni(String dni);
    long countByEmpresaId(Long empresaId);
}
