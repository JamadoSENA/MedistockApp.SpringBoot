package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Rol;
import com.MediStock.MediStockApp.repositories.RolRepositorio;
import com.MediStock.MediStockApp.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolImp implements RolService {
    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public List<Rol> findAll() throws Exception {
        return this.rolRepositorio.findAll();
    }

    @Override
    public Rol findById(Integer idRol) {
        return this.rolRepositorio.findById(idRol).orElse(null);
    }

    @Override
    public void create(Rol rol) {
        this.rolRepositorio.save(rol);
    }

    @Override
    public void update(Rol rol) {
        this.rolRepositorio.save(rol);
    }

    @Override
    public void delete(Rol rol) {
        this.rolRepositorio.delete(rol);
    }
}
