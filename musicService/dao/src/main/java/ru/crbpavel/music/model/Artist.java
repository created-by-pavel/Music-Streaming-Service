package ru.crbpavel.music.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_file_path")
    private String imageFilePath;

   /* @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();*/

    public Artist(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    /*public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id.equals(artist.id) && name.equals(artist.name) && imageFilePath.equals(artist.imageFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageFilePath);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageFilePath='" + imageFilePath + '\'' +
                //", albums=" + albums +
                '}';
    }
}
