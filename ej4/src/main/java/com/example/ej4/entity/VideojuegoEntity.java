package com.example.ej4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "videojuegos")
public class VideojuegoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "ruta_imagen", nullable = false)
    private String rutaimg;

    @Column(name = "precio", nullable = false)
    private float precio;

    @Column(name = "cantidad", nullable = false)
    private short cantidad;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "en_oferta")
    private boolean oferta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_lanzamiento")
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_estudio")
    private EstudioEntity estudio;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_categoria")
    private CategoriaEntity categoria;

    @Column(name = "activo")
    private boolean activo = true;
}