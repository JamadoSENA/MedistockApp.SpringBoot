package com.MediStock.MediStockApp.controller;

import com.MediStock.MediStockApp.entities.Agendamiento;
import com.MediStock.MediStockApp.entities.Paciente;
import com.MediStock.MediStockApp.service.imp.AgendamientoImp;
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
@RequestMapping(path = "/api/agendamiento",method =
        {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class AgendamientoController {
    //INYECCION DE DEPENDECIAS
    @Autowired
    private AgendamientoImp agendamientoImp;
    @Autowired
    private PacienteImp pacienteImp;

    //CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@@" + request);
            //INSTACIA DEL OBJETO AGENDAMIENTO
            Agendamiento agendamiento = new Agendamiento();
            //CAMPOS DE LA TABLA AGENDAMIENTO
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fecha")).getTime());
            agendamiento.setMotivo(request.get("motivo").toString());
            agendamiento.setEstado(request.get("estado").toString());
            Paciente paciente = pacienteImp.findById((long) Long.hashCode(request.get("FkId_Paciente").hashCode()));
            agendamiento.setPaciente(paciente);

            this.agendamientoImp.create(agendamiento);

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
            List<Agendamiento> agendamientoList = this.agendamientoImp.findAll();
            response.put("status", "success");
            response.put("data", agendamientoList);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //READ ID
    @GetMapping("/list/{idAgendamiento}")
    public ResponseEntity<Map<String, Object>> findByAll(@PathVariable Long idAgendamiento) {
        Map<String, Object> response = new HashMap<>();
        try {
            Agendamiento agendamiento = this.agendamientoImp.findById(idAgendamiento);
            response.put("status", "success");
            response.put("data", agendamiento);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CONTROLLER UPDATE
    @PutMapping("/update/{idAgendamiento}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long idAgendamiento, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Agendamiento agendamiento = this.agendamientoImp.findById(idAgendamiento);

            //CAMPOS DE LA TABLA AGENDAMIENTOS
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fecha")).getTime());
            agendamiento.setMotivo(request.get("motivo").toString());
            agendamiento.setEstado(request.get("estado").toString());
            Paciente paciente = pacienteImp.findById((long) Long.hashCode(request.get("FkId_Paciente").hashCode()));
            agendamiento.setPaciente(paciente);

            this.agendamientoImp.update(agendamiento);

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
    @DeleteMapping("/delete/{idAgendamiento}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long idAgendamiento){
        Map<String,Object> response = new HashMap<>();
        try {
            Agendamiento agendamiento = this.agendamientoImp.findById(idAgendamiento);
            agendamientoImp.delete(agendamiento);

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
