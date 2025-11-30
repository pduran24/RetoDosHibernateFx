package org.example.retodos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Copia")
public class Copia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "soporte", nullable = false)
    private String soporte;

    @ManyToOne
    @JoinColumn(name = "id_pelicula", nullable = false)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    public Copia(String estado, String soporte, Pelicula pelicula, Usuario usuario) {
        this.estado = estado;
        this.soporte = soporte;
        this.pelicula = pelicula;
        this.usuario = usuario;
    }

}
