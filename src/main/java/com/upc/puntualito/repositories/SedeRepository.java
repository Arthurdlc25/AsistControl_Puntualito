package com.upc.puntualito.repositories;

import com.upc.puntualito.dto.SedeDTO;
import com.upc.puntualito.entities.Sede;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Long> {
    List<Sede> findByEmpresaId(Long id);
    List<Sede> findByEsEliminadoTrue();
    List<Sede> findByEmpresaIdAndEsEliminadoTrue(Long id);
    List<Sede> findByNombreContainingIgnoreCase(String nombre);
}
