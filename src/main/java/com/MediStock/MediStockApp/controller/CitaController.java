package com.MediStock.MediStockApp.controller;

import com.MediStock.MediStockApp.entities.*;
import com.MediStock.MediStockApp.service.imp.AgendamientoImp;
import com.MediStock.MediStockApp.service.imp.CitaImp;
import com.MediStock.MediStockApp.service.imp.PacienteImp;
import com.MediStock.MediStockApp.service.UsuarioService;
import com.MediStock.MediStockApp.service.imp.UsuarioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/cita",method =
        {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class CitaController {
    //INYECCION DE DEPENDECIAS
    @Autowired
    private CitaImp citaImp;
    @Autowired
    private PacienteImp pacienteImp;
    @Autowired
    private UsuarioImp usuarioImp;
    @Autowired
    private AgendamientoImp agendamientoImp;

    //CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@@" + request);
            //INSTACIA DEL OBJETO CITA
            Cita cita = new Cita();
            //CAMPOS DE LA TABLA CITA
            cita.setFecha(LocalDate.parse(request.get("fecha").toString()));
            cita.setDiagnostico(request.get("diagnostico").toString());
            cita.setTratamiento(request.get("tratamiento").toString());
            cita.setRecomendaciones(request.get("recomendaciones").toString());
            Agendamiento agendamiento = agendamientoImp.findById(Long.valueOf(request.get("FkId_Agendamiento").toString()));
            cita.setAgendamiento(agendamiento);
            Usuario usuario = usuarioImp.findById(Long.valueOf(request.get("FkId_Medico").toString()));
            cita.setUsuario(usuario);



            this.citaImp.create(cita);

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
            List<Cita> citaList = this.citaImp.findAll();
            response.put("status", "success");
            response.put("data", citaList);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //READ ID
    @GetMapping("/list/{idCita}")
    public ResponseEntity<Map<String, Object>> findByAll(@PathVariable Long idCita) {
        Map<String, Object> response = new HashMap<>();
        try {
            Cita cita = this.citaImp.findById(idCita);
            response.put("status", "success");
            response.put("data", cita);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CONTROLLER UPDATE
    @PutMapping("/update/{idCita}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long idCita, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Cita cita = this.citaImp.findById(idCita);

            //CAMPOS DE LA TABLA CITAS
            cita.setFecha(LocalDate.parse(request.get("fecha").toString()));
            cita.setDiagnostico(request.get("diagnostico").toString());
            cita.setTratamiento(request.get("tratamiento").toString());
            cita.setRecomendaciones(request.get("recomendaciones").toString());

            this.citaImp.update(cita);

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
    @DeleteMapping("/delete/{idCita}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long idCita){
        Map<String,Object> response = new HashMap<>();
        try {
            Cita cita = this.citaImp.findById(idCita);
            citaImp.delete(cita);

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
