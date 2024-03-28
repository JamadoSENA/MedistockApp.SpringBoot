package com.MediStock.MediStockApp.service;

import com.MediStock.MediStockApp.entities.Agendamiento;

import java.util.List;

public interface AgendamientoService {
    public List<Agendamiento> findAll()throws Exception;
    public Agendamiento findById(Long id);
    public void create(Agendamiento agendamiento);
    public void update(Agendamiento agendamiento);
    public void delete(Agendamiento agendamiento);
}
