package ru.crbpavel.music.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "song_file_path")
    private String songFilePath;

    @Column(name = "text_file_path")
    private String textFilePath;

    @Column(name = "img_file_path")
    private String imgFilePath;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artist artist;
    @Column(name = "rating")
    private Integer rating;

    public Song(Long id, String name, String songFilePath, String textFilePath, String imgFilePath, Artist artist, Integer rating) {
        this.id = id;
        this.name = name;
        this.songFilePath = songFilePath;
        this.textFilePath = textFilePath;
        this.artist = artist;
        //this.album = album;
        this.rating = rating;
        this.imgFilePath = imgFilePath;
    }

    public Song() {
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

    public String getSongFilePath() {
        return songFilePath;
    }

    public void setSongFilePath(String songFilePath) {
        this.songFilePath = songFilePath;
    }

    public String getTextFilePath() {
        return textFilePath;
    }

    public void setTextFilePath(String textFilePath) {
        this.textFilePath = textFilePath;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id.equals(song.id) && name.equals(song.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songFilePath='" + songFilePath + '\'' +
                ", textFilePath='" + textFilePath + '\'' +
                ", artist=" + artist +
                ", rating=" + rating +
                ", imgFilePath=" + imgFilePath +
                '}';
    }
}
