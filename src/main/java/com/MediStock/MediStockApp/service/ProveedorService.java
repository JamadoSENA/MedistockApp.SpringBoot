package com.MediStock.MediStockApp.service;

import com.MediStock.MediStockApp.entities.Proveedor;

import java.util.List;

public interface ProveedorService {
    public List<Proveedor> findAll() throws Exception;
    public Proveedor findById(Long id);
    public void create(Proveedor proveedor);
    public void update(Proveedor proveedor);
    public void delete(Proveedor proveedor);
}
