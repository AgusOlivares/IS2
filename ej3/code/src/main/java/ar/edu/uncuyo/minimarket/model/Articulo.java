package ar.edu.uncuyo.minimarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

@Entity
public class Articulo {
    @Id
    long id;

    int cantidad;

    String denominacion;

    int precio;

    @ManyToMany
    Collection<Categoria> categorias;
}
