package entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Photo {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String mime;
    private String name;

    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    @OneToOne
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
