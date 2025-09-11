package ar.edu.uncuyo.videojuegos.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Videogame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    @Column
    private float price;

    @Column
    private short amount;

    @Column(columnDefinition = "TEXT", nullable = false, length = 512)
    private String description;

    @Column
    private boolean onSale;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column
    private boolean active = true;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Studio studio;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}