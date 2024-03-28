package com.MediStock.MediStockApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPaciente")
    private long id;
    @Column(name = "tipoDocumento", length = 4)
    private String tipoDocumento;
    @Column(name = "genero", length = 50)
    private String genero;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Column(name = "apellido", length = 100)
    private String apellido;
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
    @Column(name = "edad")
    private int edad;
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

    //FOREING KEY
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Agendamiento> agendamientos;
}