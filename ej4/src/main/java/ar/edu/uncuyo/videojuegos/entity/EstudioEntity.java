package com.example.ej4.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class EstudioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",length = 100)
    private String nombre;

    @Column(name = "activo")
    private boolean activo;

    @OneToMany(mappedBy = "estudio", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<VideojuegoEntity> videojuegos = new ArrayList<>();

}
