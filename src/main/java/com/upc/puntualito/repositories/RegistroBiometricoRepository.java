package com.upc.puntualito.repositories;

import com.upc.puntualito.entities.RegistroBiometrico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroBiometricoRepository extends JpaRepository<RegistroBiometrico, Long> {
}
