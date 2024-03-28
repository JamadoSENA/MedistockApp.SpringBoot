package com.MediStock.MediStockApp.controller;

import com.MediStock.MediStockApp.entities.Producto;
import com.MediStock.MediStockApp.entities.Proveedor;
import com.MediStock.MediStockApp.service.imp.ProductoImp;
import com.MediStock.MediStockApp.service.imp.ProveedorImp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/producto",method =
        {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoImp productoImp;
    @Autowired
    private ProveedorImp proveedorImp;

    //CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("@@@@" + request);
            //INSTACIA DEL OBJETO PROVEEDOR
            Producto producto = new Producto();
            //CAMPOS DE LA TABLA PROVEEDOR
            producto.setNombre(request.get("nombre").toString());
            producto.setDescripcion(request.get("descripcion").toString());
            producto.setIndicacionesUso(request.get("indicacioneUso").toString());
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fechaCaducidad")).getTime());
            producto.setCantidad(request.get("cantidad").hashCode());
            producto.setEstado(request.get("estado").toString());


            Proveedor proveedor = proveedorImp.findById((long) Long.hashCode(request.get("FkId_Proveedor").hashCode()));
            producto.setProveedor(proveedor);
            this.productoImp.create(producto);

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
            List<Producto> productoList = this.productoImp.findAll();
            response.put("status", "success");
            response.put("data", productoList);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //READ ID
    @GetMapping("/list/{idProducto}")
    public ResponseEntity<Map<String, Object>> findByAll(@PathVariable Long idProducto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = this.productoImp.findById(idProducto);
            response.put("status", "success");
            response.put("data", producto);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //CONTROLLER UPDATE
    @PutMapping("/update/{idProducto}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long idProducto, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = this.productoImp.findById(idProducto);

            //CAMPOS DE LA TABLA PROVEEDOR
            producto.setNombre(request.get("nombre").toString());
            producto.setDescripcion(request.get("descripcion").toString());
            producto.setIndicacionesUso(request.get("indicacioneUso").toString());
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaAnalizada = new java.sql.Date(formateador.parse((String) request.get("fechaCaducidad")).getTime());
            producto.setCantidad(request.get("cantidad").hashCode());
            producto.setEstado(request.get("estado").toString());
            Proveedor proveedor = proveedorImp.findById((long) Long.hashCode(request.get("FkId_Proveedor").hashCode()));
            producto.setProveedor(proveedor);
            this.productoImp.update(producto);

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
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long idProducto){
        Map<String,Object> response = new HashMap<>();
        try {
            Producto producto = this.productoImp.findById(idProducto);
            productoImp.delete(producto);

            response.put("status","success");
            response.put("data","Registro Eliminado Correctamente");
        }catch (Exception e){
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
