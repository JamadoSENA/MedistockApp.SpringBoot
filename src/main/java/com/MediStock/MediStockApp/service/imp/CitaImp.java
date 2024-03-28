package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Cita;
import com.MediStock.MediStockApp.repositories.CitaRepositorio;
import com.MediStock.MediStockApp.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaImp implements CitaService {
    @Autowired
    private CitaRepositorio citaRepositorio;

    @Override
    public List<Cita> findAll() throws Exception {
        return this.citaRepositorio.findAll();
    }

    @Override
    public Cita findById(Long id) {
        return this.citaRepositorio.findById(id).orElse(null);
    }

    @Override
    public void create(Cita cita) {
        this.citaRepositorio.save(cita);
    }

    @Override
    public void update(Cita cita) {
        this.citaRepositorio.save(cita);
    }

    @Override
    public void delete(Cita cita) {
        this.citaRepositorio.delete(cita);
    }
}
