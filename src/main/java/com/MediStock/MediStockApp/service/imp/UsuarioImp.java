package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Usuario;
import com.MediStock.MediStockApp.repositories.UsuarioRepositorio;
import com.MediStock.MediStockApp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioImp implements UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> fidAll() throws Exception {
        return this.usuarioRepositorio.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return this.usuarioRepositorio.findById(id).orElse(null);
    }

    @Override
    public void create(Usuario usuario) {
        this.usuarioRepositorio.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        this.usuarioRepositorio.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        this.usuarioRepositorio.delete(usuario);
    }
}
