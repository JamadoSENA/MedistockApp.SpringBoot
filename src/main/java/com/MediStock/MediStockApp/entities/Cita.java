package com.MediStock.MediStockApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name= "Citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCita")
    private long id;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "diagnostico", length = 5000)
    private String diagnostico;
    @Column(name = "tratamiento", length = 5000)
    private String tratamiento;
    @Column(name = "recomendaciones", length = 5000)
    private String recomendaciones;

    //FOREING KEY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FkId_Agendamiento",nullable = false)
    private Agendamiento agendamiento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FkId_Medico",nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "Citas_Productos",
            joinColumns = @JoinColumn(name = "FkId_Cita"),
            inverseJoinColumns = @JoinColumn(name = "FkId_Producto")
    )
    private List<Producto> productos;
}
