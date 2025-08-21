package ar.edu.uncuyo.minimarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categoria {
    @Id
    long id;

    String denominacion;
}
