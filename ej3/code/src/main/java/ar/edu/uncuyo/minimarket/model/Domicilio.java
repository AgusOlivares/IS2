package ar.edu.uncuyo.minimarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Domicilio {
    @Id
    int id;

    String nombreCalle;

    int numero;
}
