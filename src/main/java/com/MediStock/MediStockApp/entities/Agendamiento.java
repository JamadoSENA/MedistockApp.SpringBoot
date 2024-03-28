package com.MediStock.MediStockApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name= "Agendamientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgendamiento")
    private long id;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "motivo", length = 5000)
    private String motivo;
    @Column(name = "estado", length = 100)
    private String estado;

    //FOREING KEY
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FkId_Paciente",nullable = false)
    private Paciente paciente;

    @OneToMany(mappedBy = "agendamiento", cascade = CascadeType.ALL)
    private List<Cita> citas;
}
