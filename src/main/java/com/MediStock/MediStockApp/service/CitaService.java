package com.MediStock.MediStockApp.service;

import com.MediStock.MediStockApp.entities.Cita;

import java.util.List;

public interface CitaService {
    public List<Cita> findAll() throws Exception;
    public Cita findById(Long id);
    public void create(Cita cita);
    public void update(Cita cita);
    public void delete(Cita cita);

}
