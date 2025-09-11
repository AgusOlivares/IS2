package ar.edu.uncuyo.minimarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Factura {
    @Id
    long id;

    String fecha;

    int numero;

    int total;

    @ManyToOne
    Cliente cliente;
}
