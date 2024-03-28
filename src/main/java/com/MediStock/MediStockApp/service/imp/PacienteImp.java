package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Paciente;
import com.MediStock.MediStockApp.repositories.PacienteRepositorio;
import com.MediStock.MediStockApp.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteImp implements PacienteService{
    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Override
    public List<Paciente> findAll() throws Exception {
        return this.pacienteRepositorio.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return this.pacienteRepositorio.findById(id).orElse(null);
    }

    @Override
    public void create(Paciente paciente) {
        this.pacienteRepositorio.save(paciente);
    }

    @Override
    public void update(Paciente paciente) {
        this.pacienteRepositorio.save(paciente);
    }

    @Override
    public void delete(Paciente paciente) {
        this.pacienteRepositorio.delete(paciente);
    }
}
