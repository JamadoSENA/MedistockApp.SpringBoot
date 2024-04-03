package com.MediStock.MediStockApp.repositories;

import com.MediStock.MediStockApp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}
