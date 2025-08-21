package ar.edu.uncuyo.minimarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleFactura {
    @Id
    long id;

    int cantidad;

    int subtotal;

    @ManyToOne
    Factura factura;

    @ManyToOne
    Articulo articulo;
}
