package com.MediStock.MediStockApp.service;

import com.MediStock.MediStockApp.entities.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> findAll() throws Exception;
    public Producto findById(Long id);
    public void create(Producto producto);
    public void update(Producto producto);
    public void delete(Producto producto);
}
