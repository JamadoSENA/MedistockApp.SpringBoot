package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Producto;
import com.MediStock.MediStockApp.repositories.ProductoRepositorio;
import com.MediStock.MediStockApp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoImp implements ProductoService {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> findAll() throws Exception {
        return this.productoRepositorio.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return this.productoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void create(Producto producto) {
        this.productoRepositorio.save(producto);
    }

    @Override
    public void update(Producto producto) {
        this.productoRepositorio.save(producto);
    }

    @Override
    public void delete(Producto producto) {
        this.productoRepositorio.delete(producto);
    }
}
