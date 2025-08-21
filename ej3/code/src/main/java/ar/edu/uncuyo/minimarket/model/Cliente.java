package ar.edu.uncuyo.minimarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {
    @Id
    long id;

    int dni;

    String apellido;

    String nombre;

    @OneToOne
    Domicilio domicilio;
}
