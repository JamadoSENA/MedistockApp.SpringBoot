package com.MediStock.MediStockApp.service;

import com.MediStock.MediStockApp.entities.Rol;

import java.util.List;

public interface RolService {
    public List<Rol> findAll() throws Exception;
    public Rol findById(Integer idRol);
    public void create (Rol rol);
    public void update (Rol rol);
    public void delete (Rol rol);
}
