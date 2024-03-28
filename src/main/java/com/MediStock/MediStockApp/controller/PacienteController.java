package com.MediStock.MediStockApp.controller;

import com.MediStock.MediStockApp.entities.Paciente;
import com.MediStock.MediStockApp.service.imp.PacienteImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/paciente",method =
        {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class PacienteController {
    //INYECCION DE DEPENDECIAS
    @Autowired
    private PacienteImp pacienteImp;

    //CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@@" + request);
            //INSTACIA DEL OBJETO PACIENTE
            Paciente paciente = new Paciente();
            //CAMPOS DE LA TABLA PACIENTE

            paciente.setTipoDocumento(request.get("tipoDocumento").toString());
            paciente.setGenero(request.get("genero").toString());
            paciente.setNombre(request.get("nombre").toString());
            paciente.setApellido(request.get("apellido").toString());
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fechaNacimiento")).getTime());
            paciente.setEdad(request.get("edad").hashCode());
            paciente.setDepartamento(request.get("departamento").toString());
            paciente.setMunicipio(request.get("municipio").toString());
            paciente.setDireccion(request.get("direccion").toString());
            paciente.setProfesion(request.get("profesion").toString());
            paciente.setTelefono(request.get("telefono").toString());
            paciente.setCorreo(request.get("correo").toString());

            this.pacienteImp.create(paciente);

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
            List<Paciente> pacienteList = this.pacienteImp.findAll();
            response.put("status", "success");
            response.put("data", pacienteList);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //READ ID
    @GetMapping("/list/{idPaciente}")
    public ResponseEntity<Map<String, Object>> findByAll(@PathVariable Long idPaciente) {
        Map<String, Object> response = new HashMap<>();
        try {
            Paciente paciente = this.pacienteImp.findById(idPaciente);
            response.put("status", "success");
            response.put("data", paciente);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CONTROLLER UPDATE
    @PutMapping("/update/{idPaciente}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long idPaciente, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Paciente paciente = this.pacienteImp.findById(idPaciente);

            //CAMPOS DE LA TABLA ROLES
            paciente.setId(request.get("documento").hashCode());
            paciente.setTipoDocumento(request.get("tipoDocumento").toString());
            paciente.setGenero(request.get("genero").toString());
            paciente.setNombre(request.get("nombre").toString());
            paciente.setApellido(request.get("apellido").toString());
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fechaNacimiento")).getTime());
            paciente.setEdad(request.get("edad").hashCode());
            paciente.setDepartamento(request.get("departamento").toString());
            paciente.setMunicipio(request.get("municipio").toString());
            paciente.setDireccion(request.get("direccion").toString());
            paciente.setProfesion(request.get("profesion").toString());
            paciente.setTelefono(request.get("telefono").toString());
            paciente.setCorreo(request.get("correo").toString());

            this.pacienteImp.update(paciente);

            response.put("status", "success");
            response.put("data", "Actualizacion Exitosa");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //CONTROLLER DELETE
    @DeleteMapping("/delete/{idPaciente}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long idPaciente){
        Map<String,Object> response = new HashMap<>();
        try {
            Paciente paciente = this.pacienteImp.findById(idPaciente);
            pacienteImp.delete(paciente);

            response.put("status","success");
            response.put("data","Registro Elimindado Correctamente");
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
