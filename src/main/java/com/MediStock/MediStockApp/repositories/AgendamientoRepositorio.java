package com.MediStock.MediStockApp.repositories;

import com.MediStock.MediStockApp.entities.Agendamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamientoRepositorio extends JpaRepository<Agendamiento,Long> {
}
