package ru.crbpavel.musicapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {

    private String name;
    private String imageFilePath;
    //private List<Album> albums = new ArrayList<>();

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

    //public List<Album> getAlbums() {
    //    return albums;
    // }

    // public void setAlbums(List<Album> albums) {
    //     this.albums = albums;
    //}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return name.equals(artist.name) && Objects.equals(imageFilePath, artist.imageFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageFilePath);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", imageFilePath='" + imageFilePath + '\'' +
                //    ", albums=" + albums +
                '}';
    }
}
