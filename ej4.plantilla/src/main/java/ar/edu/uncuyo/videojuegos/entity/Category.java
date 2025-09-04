package ar.edu.uncuyo.videojuegos.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Videogame> videogames = new ArrayList<>();
}
