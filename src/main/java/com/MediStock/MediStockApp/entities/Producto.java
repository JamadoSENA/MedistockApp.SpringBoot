package com.MediStock.MediStockApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name= "Productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private long id;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    @Column(name = "indicacionesUso", length = 500)
    private String indicacionesUso;
    @Column(name = "fechaCaducidad")
    private Date fechaCaducidad;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "estado", length = 20)
    private String estado;

    //FOREING KEY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FkId_Proveedor",nullable = false)
    private Proveedor proveedor;

    @ManyToMany(mappedBy = "productos")
    private List<Cita> citas;

}
