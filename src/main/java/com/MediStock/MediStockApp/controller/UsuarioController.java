package com.MediStock.MediStockApp.controller;

import com.MediStock.MediStockApp.entities.Usuario;
import com.MediStock.MediStockApp.service.imp.UsuarioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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


    //CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@@" + request);
            //INSTACIA DEL OBJETO USUARIO
            Usuario usuario = new Usuario();
            //CAMPOS DE LA TABLA USUARIO
            usuario.setDocumento(request.get("documento").toString());
            usuario.setNombre(request.get("nombre").toString());
            usuario.setApellido(request.get("apellido").toString());
            usuario.setFechaNacimiento(LocalDate.parse(request.get("fechaNacimiento").toString()));
            usuario.setDepartamento(request.get("departamento").toString());
            usuario.setMunicipio(request.get("municipio").toString());
            usuario.setDireccion(request.get("direccion").toString());
            usuario.setProfesion(request.get("profesion").toString());
            usuario.setTelefono(request.get("telefono").toString());
            usuario.setCorreo(request.get("correo").toString());
            usuario.setContrasenia(request.get("contrasenia").toString());


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
    //Controller Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Validated @RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        try {
            String correo = request.get("correo");
            String contrasenia = request.get("contrasenia");

            // Verificar inicio de sesión utilizando el servicio UsuarioImp
            Usuario usuario = usuarioImp.verificarInicioSesion(correo, contrasenia);

            if (usuario != null) {
                response.put("mensaje", "Inicio de sesión exitoso");
                // Aquí puedes incluir otros datos del usuario en la respuesta si es necesario
                // Por ejemplo, response.put("usuario", usuario.toString());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("mensaje", "Inicio de sesión fallido. Correo o contraseña incorrectos.");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.put("mensaje", "Error al procesar la solicitud");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
            usuario.setDocumento(request.get("documento").toString());
            usuario.setNombre(request.get("nombre").toString());
            usuario.setApellido(request.get("apellido").toString());
            usuario.setFechaNacimiento(LocalDate.parse(request.get("fechaNacimiento").toString()));
            usuario.setDepartamento(request.get("departamento").toString());
            usuario.setMunicipio(request.get("municipio").toString());
            usuario.setDireccion(request.get("direccion").toString());
            usuario.setProfesion(request.get("profesion").toString());
            usuario.setTelefono(request.get("telefono").toString());
            usuario.setCorreo(request.get("correo").toString());
            usuario.setContrasenia(request.get("contrasenia").toString());


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
