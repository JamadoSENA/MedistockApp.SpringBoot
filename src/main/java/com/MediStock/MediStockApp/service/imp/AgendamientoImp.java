package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Agendamiento;
import com.MediStock.MediStockApp.repositories.AgendamientoRepositorio;
import com.MediStock.MediStockApp.service.AgendamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamientoImp implements AgendamientoService {
    @Autowired
    private AgendamientoRepositorio agendamientoRepositorio;

    @Override
    public List<Agendamiento> findAll() throws Exception {
        return this.agendamientoRepositorio.findAll();
    }

    @Override
    public Agendamiento findById(Long id) {
        return this.agendamientoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void create(Agendamiento agendamiento) {
        this.agendamientoRepositorio.save(agendamiento);
    }

    @Override
    public void update(Agendamiento agendamiento) {
        this.agendamientoRepositorio.save(agendamiento);
    }

    @Override
    public void delete(Agendamiento agendamiento) {
        this.agendamientoRepositorio.delete(agendamiento);
    }
}
