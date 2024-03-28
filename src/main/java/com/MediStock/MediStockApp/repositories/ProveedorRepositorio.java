package com.MediStock.MediStockApp.repositories;

import com.MediStock.MediStockApp.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor,Long> {
}
