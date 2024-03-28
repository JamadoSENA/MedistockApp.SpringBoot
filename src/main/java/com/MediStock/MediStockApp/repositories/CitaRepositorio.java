package com.MediStock.MediStockApp.repositories;

import com.MediStock.MediStockApp.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita,Long> {
}
