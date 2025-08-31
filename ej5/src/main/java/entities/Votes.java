package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Entity
public class Votes {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Temporal(TemporalType.TIMESTAMP)
    private Date respuesta;


    @ManyToOne
    private Pet pet1 ; //origina el voto
    @ManyToOne
    private Pet pet2; //recibe el voto

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Date respuesta) {
        this.respuesta = respuesta;
    }

    public Pet getPet1() {
        return pet1;
    }

    public void setPet1(Pet pet1) {
        this.pet1 = pet1;
    }

    public Pet getPet2() {
        return pet2;
    }

    public void setPet2(Pet pet2) {
        this.pet2 = pet2;
    }
}
