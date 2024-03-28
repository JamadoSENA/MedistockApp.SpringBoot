package com.MediStock.MediStockApp.repositories;

import com.MediStock.MediStockApp.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends JpaRepository <Rol,Integer> {
}
