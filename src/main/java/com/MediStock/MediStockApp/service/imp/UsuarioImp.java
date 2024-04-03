package com.MediStock.MediStockApp.service.imp;

import com.MediStock.MediStockApp.entities.Usuario;
import com.MediStock.MediStockApp.repositories.UsuarioRepositorio;
import com.MediStock.MediStockApp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Usuario verificarInicioSesion(String correo, String contrasenia) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByCorreo(correo);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getContrasenia().equals(contrasenia)) {
                return usuario; // Inicio de sesión exitoso
            }
        }
        return  null;

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
