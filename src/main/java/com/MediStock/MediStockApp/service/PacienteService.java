package com.MediStock.MediStockApp.service;

import com.MediStock.MediStockApp.entities.Paciente;

import java.util.List;

public interface PacienteService {
    public List<Paciente> findAll() throws Exception;
    public Paciente findById(Long id);
    public void create(Paciente paciente);
    public void update(Paciente paciente);
    public void delete(Paciente paciente);
}
