package ru.crbpavel.musicapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    private Artist artist;
    private String title;
    private String imageFilePath;
    private Date releaseDate;
    private List<Song> songs = new ArrayList<>();

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(title, album.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist=" + artist +
                ", title='" + title + '\'' +
                ", imageFilePath='" + imageFilePath + '\'' +
                ", releaseDate=" + releaseDate +
                ", songs=" + songs +
                '}';
    }
}