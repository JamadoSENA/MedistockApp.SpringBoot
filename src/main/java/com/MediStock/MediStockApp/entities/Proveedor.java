package  com.MediStock.MediStockApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "departamento", length = 50)
    private String departamento;
    @Column(name = "municipio", length = 50)
    private String municipio;
    @Column(name = "direccion", length = 50)
    private String direccion;
    @Column(name = "telefono", length = 10)
    private String telefono;
    @Column(name = "correo", length = 60)
    private String correo;

    //FOREING KEY
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Producto> productos;
}
