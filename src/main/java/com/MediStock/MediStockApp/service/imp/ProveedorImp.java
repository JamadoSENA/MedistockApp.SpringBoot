package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Proveedor;
import com.MediStock.MediStockApp.repositories.ProveedorRepositorio;
import com.MediStock.MediStockApp.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorImp implements ProveedorService {
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Override
    public List<Proveedor> findAll() throws Exception {
        return this.proveedorRepositorio.findAll();
    }

    @Override
    public Proveedor findById(Long id) {
        return this.proveedorRepositorio.findById(id).orElse(null);
    }

    @Override
    public void create(Proveedor proveedor) {
        this.proveedorRepositorio.save(proveedor);
    }

    @Override
    public void update(Proveedor proveedor) {
        this.proveedorRepositorio.save(proveedor);
    }

    @Override
    public void delete(Proveedor proveedor) {
        this.proveedorRepositorio.delete(proveedor);
    }
}
