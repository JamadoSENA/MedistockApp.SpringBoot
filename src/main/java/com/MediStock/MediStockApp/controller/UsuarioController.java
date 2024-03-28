package com.MediStock.MediStockApp.controller;

import com.MediStock.MediStockApp.entities.Rol;
import com.MediStock.MediStockApp.entities.Usuario;
import com.MediStock.MediStockApp.service.imp.RolImp;
import com.MediStock.MediStockApp.service.imp.UsuarioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/usuario",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class UsuarioController {
    //INYECCION DE DEPENDECIAS
    @Autowired
    private UsuarioImp usuarioImp;
    @Autowired
    private RolImp rolImp;

    //CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@@" + request);
            //INSTACIA DEL OBJETO USUARIO
            Usuario usuario = new Usuario();
            //CAMPOS DE LA TABLA USUARIO
            usuario.setNombre(request.get("nombre").toString());
            usuario.setApellido(request.get("apellido").toString());
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fechaNacimiento")).getTime());
            usuario.setDepartamento(request.get("departamento").toString());
            usuario.setMunicipio(request.get("municipio").toString());
            usuario.setDireccion(request.get("direccion").toString());
            usuario.setProfesion(request.get("profesion").toString());
            usuario.setTelefono(request.get("telefono").toString());
            usuario.setCorreo(request.get("correo").toString());
            usuario.setContrasenia(request.get("contrasenia").toString());

            Rol rol = rolImp.findById(Integer.parseInt(request.get("FkId_Rol").toString()));
            usuario.setRol(rol);

            this.usuarioImp.create(usuario);

            response.put("status", "succses");
            response.put("data", "Registro Exitoso");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CONTROLLER READ
    //READ ALL
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Usuario> usuarioList = this.usuarioImp.fidAll();
            response.put("status", "success");
            response.put("data", usuarioList);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //READ ID
    @GetMapping("/list/{idUsuario}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long idUsuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = this.usuarioImp.findById(idUsuario);
            response.put("status","success");
            response.put("data",usuario);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//CONTROLLER UPDATE
    @PutMapping("/update/{idUsuario}")
    public ResponseEntity<Map<String,Object>> update(@PathVariable Long idUsuario, @RequestBody Map<String,Object> request){
        Map<String,Object> response = new HashMap<>();
        try{
            Usuario usuario = this.usuarioImp.findById(idUsuario);

            //CAMPOS DE LA TABLA USUARIOS
            usuario.setNombre(request.get("nombre").toString());
            usuario.setApellido(request.get("apellido").toString());
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fechaNacimiento")).getTime());
            usuario.setDepartamento(request.get("departamento").toString());
            usuario.setMunicipio(request.get("municipio").toString());
            usuario.setDireccion(request.get("direccion").toString());
            usuario.setProfesion(request.get("profesion").toString());
            usuario.setTelefono(request.get("telefono").toString());
            usuario.setCorreo(request.get("correo").toString());
            usuario.setContrasenia(request.get("contrasenia").toString());

            Rol rol = rolImp.findById(Integer.parseInt(request.get("FkId_Rol").toString()));
            usuario.setRol(rol);

            this.usuarioImp.update(usuario);

            response.put("status","success");
            response.put("data","Actualizacion Exitosa");
        }catch (Exception e){
            response.put("status",HttpStatus.BAD_GATEWAY);
            response.put("data",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//CONTROLLER DELETE
    @DeleteMapping("/delete/{idUsuario}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long idUsuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = this.usuarioImp.findById(idUsuario);
            usuarioImp.delete(usuario);

            response.put("status","success");
            response.put("data","Registro Elimindado Correctamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
