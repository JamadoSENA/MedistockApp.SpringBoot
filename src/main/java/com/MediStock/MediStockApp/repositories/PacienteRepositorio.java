package com.MediStock.MediStockApp.repositories;

import com.MediStock.MediStockApp.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente,Long> {
}
