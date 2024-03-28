package com.MediStock.MediStockApp.controller;

import com.MediStock.MediStockApp.entities.Proveedor;
import com.MediStock.MediStockApp.service.imp.ProveedorImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/proveedor",method =
        {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class ProveedorController {
    //INYECCION DE DEPENDECIAS
    @Autowired
    private ProveedorImp proveedorImp;

    //CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@@" + request);
            //INSTACIA DEL OBJETO PROVEEDOR
            Proveedor proveedor = new Proveedor();
            //CAMPOS DE LA TABLA PROVEEDOR
            proveedor.setNombre(request.get("nombre").toString());
            proveedor.setDepartamento(request.get("departamento").toString());
            proveedor.setMunicipio(request.get("municipio").toString());
            proveedor.setDireccion(request.get("direccion").toString());
            proveedor.setTelefono(request.get("telefono").toString());
            proveedor.setCorreo(request.get("correo").toString());

            this.proveedorImp.create(proveedor);

            response.put("status", "succses");
            response.put("data", "Registro Exitoso");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CONTROLLER
    //READ ALL
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Proveedor> proveedorList = this.proveedorImp.findAll();
            response.put("status", "success");
            response.put("data", proveedorList);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //READ ID
    @GetMapping("/list/{idProveedor}")
    public ResponseEntity<Map<String, Object>> findByAll(@PathVariable Long idProveedor) {
        Map<String, Object> response = new HashMap<>();
        try {
            Proveedor proveedor = this.proveedorImp.findById(idProveedor);
            response.put("status", "success");
            response.put("data", proveedor);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CONTROLLER UPDATE
    @PutMapping("/update/{idProveedor}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long idProveedor, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Proveedor proveedor = this.proveedorImp.findById(idProveedor);

            //CAMPOS DE LA TABLA PROVEEDOR
            proveedor.setId(request.get("idProveedor").hashCode());
            proveedor.setNombre(request.get("nombre").toString());
            proveedor.setDepartamento(request.get("departamento").toString());
            proveedor.setMunicipio(request.get("municipio").toString());
            proveedor.setDireccion(request.get("direccion").toString());
            proveedor.setTelefono(request.get("telefono").toString());
            proveedor.setCorreo(request.get("correo").toString());

            this.proveedorImp.update(proveedor);

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
    @DeleteMapping("/delete/{idProveedor}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long idProveedor){
        Map<String,Object> response = new HashMap<>();
        try {
            Proveedor proveedor = this.proveedorImp.findById(idProveedor);
            proveedorImp.delete(proveedor);

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
