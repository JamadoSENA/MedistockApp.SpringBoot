package com.MediStock.MediStockApp.service;

import com.MediStock.MediStockApp.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> fidAll() throws Exception;
    public Usuario findById(Long id);
    public void create (Usuario usuario);
    public void update (Usuario usuario);
    public void delete (Usuario usuario);
}
