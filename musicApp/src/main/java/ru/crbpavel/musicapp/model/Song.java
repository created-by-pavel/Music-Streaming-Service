package ru.crbpavel.musicapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {

    private String name;
    private String songFilePath;
    private String imgFilePath;
    private String textFilePath;
    private Artist artist;
    private Album album;

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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", songFilePath='" + songFilePath + '\'' +
                ", imgFilePath='" + imgFilePath + '\'' +
                ", textFilePath='" + textFilePath + '\'' +
                ", artist=" + artist +
                ", album=" + album +
                '}';
    }
}
