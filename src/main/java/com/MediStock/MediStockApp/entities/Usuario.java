package com.MediStock.MediStockApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name= "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private long id;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Column(name = "apellido", length = 100)
    private String apellido;
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
    @Column(name = "departamento", length = 50)
    private String departamento;
    @Column(name = "municipio", length = 50)
    private String municipio;
    @Column(name = "direccion", length = 50)
    private String direccion;
    @Column(name = "profesion", length = 50)
    private String profesion;
    @Column(name = "telefono", length = 10)
    private String telefono;
    @Column(name = "correo", length = 60)
    private String correo;
    @Column(name = "contrasenia", length = 45)
    private String contrasenia;

//FOREING KEYS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FkId_Rol",nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Cita> citas;
}
